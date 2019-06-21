package io.github.brunogabriel.apimicroservice.config;

import io.github.brunogabriel.apimicroservice.filter.JWTAuthenticationFilter;
import io.github.brunogabriel.apimicroservice.filter.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Created by brunogabriel on 2019-06-13.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // memory users to simulate authentication

        // admins
        auth.inMemoryAuthentication().withUser("admin").password("{noop}rootroot").roles("ADMIN");

        // users
        auth.inMemoryAuthentication().withUser("bruno").password("{noop}qwerty").roles("USER");
        auth.inMemoryAuthentication().withUser("professor").password("{noop}123456").roles("USER");
        auth.inMemoryAuthentication().withUser("pucminas").password("{noop}rootroot").roles("USER");
    }
}
