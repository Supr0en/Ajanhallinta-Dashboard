package hh.harjoitustyo.ajanhallintadashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@Controller
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private final UserDetailsService UserDetailsService;

    public WebSecurityConfig(UserDetailsService UserDetailsService) {
        this.UserDetailsService = UserDetailsService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity https) throws Exception {
        https.authorizeHttpRequests(authorize -> authorize.
                requestMatchers(antMatcher("/css/**")).permitAll()
                .requestMatchers(toH2Console()).permitAll()
                .anyRequest().authenticated()
        ).formLogin(formLogin -> formLogin
                .defaultSuccessUrl("/", true)
                .permitAll()
        ).logout(logOut -> logOut.permitAll());
        return https.build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.userDetailsService(UserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
