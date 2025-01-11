package github.ezgikardes.loanapi.controller;


import github.ezgikardes.loanapi.config.security.JwtTokenProvider;
import github.ezgikardes.loanapi.dto.LoginRequest;
import github.ezgikardes.loanapi.dto.LoginResponse;
import github.ezgikardes.loanapi.entity.Customer;
import github.ezgikardes.loanapi.exceptions.ApiException;
import github.ezgikardes.loanapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerRepository customerRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        Customer customer = customerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ApiException("User not found!", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new ApiException("Password is wrong",HttpStatus.BAD_REQUEST);
        }

        String token = jwtTokenProvider.generateToken(
                customer.getId(),
                customer.getUsername(),
                customer.getRole()
        );

        return ResponseEntity.ok(new LoginResponse(token, customer.getId(), customer.getRole()));
    }

}