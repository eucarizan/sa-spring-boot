package accounts.services;

import accounts.RestWsApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AccountServiceMethodSecurityTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAuthoritiesForUser_should_return_403_for_user() {
        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("user", "user")
                                                         .getForEntity("/authorities?username=user", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void getAuthoritiesForUser_should_return_authorities_for_admin() {

        String[] authorities = restTemplate.withBasicAuth("admin", "admin")
                                           .getForObject("/authorities?username=admin", String[].class);
        assertThat(authorities.length).isEqualTo(2);
        assertThat(authorities.toString().contains("ROLE_ADMIN"));
        assertThat(authorities.toString().contains("ROLE_USER"));

    }

    // TODO-12b: Write a test that verifies that getting authorities
    //           using "/authorities?username=superadmin" with
    //           "superadmin"/"superadmin" credential should return
    //           three roles "ROLE_SUPERADMIN", "ROLE_ADMIN", and
    //           "ROLE_USER".
    @Test
    @Disabled
    public void getAuthoritiesForUser_should_return_authorities_for_superadmin() {



    }

}
