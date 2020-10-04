package com.senac.assister.backend.domain.config;

import com.senac.assister.backend.domain.exception.RestAuthenticationEntryPoint;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import com.senac.assister.backend.domain.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomerRepository customerRepository;

    WebSecurityConfig(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // method valid email user
        authProvider.setUserDetailsService(new UserDetailsServiceImpl(this.customerRepository));
        // method verify password tooth client is equals password user db
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set obj resposible per authentication
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // config endPoints
        http.httpBasic().and().authorizeRequests()
                //TODO - define paths and authority
                .antMatchers("/**").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());

    }


}
