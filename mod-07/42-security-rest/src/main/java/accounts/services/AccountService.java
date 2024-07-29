package accounts.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    // TODO-09: Add method security annotation to a method
    // - Uncomment and complete PreAuthorize annotation below
    //   so that the method is permitted to be invoked only
    //   when both of following two run-time conditions are met:
    //   (Use SpEL to specify these conditions.)
    //
    //   (a) the logged-in user belongs to "ADMIN" role
    //   (b) the value of the "username" argument matches
    //       the value of the logged-in principal's
    //       username, which can be accessed as
    //       principal.username or authentication.name.
    //
    //@PreAuthorize(/* Add code here */)
    public List<String> getAuthoritiesForUser(String username) {
        Collection<? extends GrantedAuthority> grantedAuthorities
                = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getAuthorities();

        return grantedAuthorities.stream()
                                 .map(GrantedAuthority::getAuthority)
                                 .collect(Collectors.toList());
    }

}
