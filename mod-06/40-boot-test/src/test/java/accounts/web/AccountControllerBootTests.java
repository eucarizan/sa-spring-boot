package accounts.web;

import accounts.AccountManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.money.Percentage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rewards.internal.account.Account;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountManager accountManager;

    @Test
    public void accountDetails() throws Exception {
        given(accountManager.getAccount(0L))
                .willReturn(new Account("1234567890", "John Doe"));

        mockMvc.perform(get("/accounts/0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("John Doe"))
                .andExpect(jsonPath("number").value("1234567890"));

        verify(accountManager).getAccount(0L);
    }

    @Test
    public void accountDetailsFail() throws Exception {
        given(accountManager.getAccount(any(Long.class)))
                .willThrow(new IllegalArgumentException("No such account with id " + 0L));

        mockMvc.perform(get("/accounts/9999"))
                .andExpect(status().isNotFound());

        verify(accountManager).getAccount(any(Long.class));
    }

    @Test
    public void accountSummary() throws Exception {
        List<Account> testAccounts = List.of(new Account("1234567890", "John Doe"));
        given(accountManager.getAllAccounts())
                .willReturn(testAccounts);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..number").value("1234567890"))
                .andExpect(jsonPath("$..name").value("John Doe"));

        verify(accountManager).getAllAccounts();
    }

    @Test
    public void createAccount() throws Exception {
        Account testAccount = new Account("1234512345", "Mary Jones");
        testAccount.setEntityId(21L);

        given(accountManager.save(any(Account.class)))
                .willReturn(testAccount);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testAccount)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/accounts/21"));

        verify(accountManager).save(any(Account.class));
    }

    @Test
    public void getBeneficiary() throws Exception {
        Account testAccount = new Account("1234567890", "John Doe");
        testAccount.addBeneficiary("Corgan", new Percentage(0.1));

        given(accountManager.getAccount(anyLong()))
                .willReturn(testAccount);

        mockMvc.perform(get("/accounts/{accountId}/beneficiaries/{beneficiaryName}", 0L, "Corgan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("Corgan"))
                .andExpect(jsonPath("allocationPercentage").value(0.1));

        verify(accountManager).getAccount(anyLong());
    }

    @Test
    public void getBeneficiaryFail() throws Exception {
        Account testAccount = new Account("1234567890", "John Doe");

        given(accountManager.getAccount(anyLong()))
                .willReturn(testAccount);

        mockMvc.perform(get("/accounts/{accountId}/beneficiaries/{beneficiaryName}", 0L, "Corgan"))
                .andExpect(status().isNotFound());

        verify(accountManager).getAccount(anyLong());
    }

    @Test
    public void addBeneficiary() throws Exception {
        mockMvc.perform(post("/accounts/{accountId}/beneficiaries", 0L).content("Kate"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/accounts/0/beneficiaries/Kate"));
    }

    @Test
    public void removeBeneficiary() throws Exception {
        Account testAccount = new Account("1234567890", "John Doe");
        testAccount.addBeneficiary("Corgan", new Percentage(0.1));

        given(accountManager.getAccount(anyLong()))
                .willReturn(testAccount);

        mockMvc.perform(delete("/accounts/{accountId}/beneficiaries/{beneficiaryName}", 0L, "Corgan"))
                .andExpect(status().isNoContent());

        verify(accountManager).getAccount(anyLong());
    }

    @Test
    public void removeBeneficiaryFail() throws Exception {
        Account testAccount = new Account("1234567890", "John Doe");
        given(accountManager.getAccount(anyLong())).willReturn(testAccount);

        mockMvc.perform(delete("/accounts/{accountId}/beneficiaries/{beneficiaryName}", 0L, "Corgan"))
                .andExpect(status().isNotFound());

        verify(accountManager).getAccount(anyLong());
    }

    // Utility class for converting an object into JSON string
    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
