package alibek.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests(authorization -> {
            authorization
                    .requestMatchers("/security_controller/second_resource")
                    .authenticated();
            authorization
                    .anyRequest()
                    .permitAll();
        });
        httpSecurity.formLogin().defaultSuccessUrl("/products");
        return httpSecurity.build();
    }
}
