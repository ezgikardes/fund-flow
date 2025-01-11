package github.ezgikardes.loanapi.service;


import github.ezgikardes.loanapi.config.security.CustomerPrincipal;
import github.ezgikardes.loanapi.entity.Customer;
import github.ezgikardes.loanapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new CustomerPrincipal(
                customer.getId(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getRole()
        );
    }
}