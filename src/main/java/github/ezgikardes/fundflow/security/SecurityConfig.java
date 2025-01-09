package github.ezgikardes.fundflow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableMethodSecurity // for @PreAuthorize annotation
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF korumasını kapatıyoruz (örnek bir yapılandırma)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/register").permitAll() // Register endpoint'ine herkese izin ver
                        .requestMatchers("/api/users/**").hasRole("ADMIN") // Diğer kullanıcı işlemleri için sadece ADMIN yetkisi
                        .anyRequest().authenticated() // Tüm diğer istekler için kimlik doğrulaması gerekli
                )
                .httpBasic(httpBasic -> {}); // Basit HTTP kimlik doğrulaması kullanılıyor
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Şifreleme için BCrypt kullanılıyor
    }
}