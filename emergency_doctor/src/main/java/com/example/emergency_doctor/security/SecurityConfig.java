package com.example.emergency_doctor.security;

import com.example.emergency_doctor.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login").permitAll();
        /*http.authorizeRequests()
                .antMatchers("/api/sensor/uploadfile/**")
                .hasAuthority(Role.DOCTOR.name());*/
        //http.authorizeRequests().antMatchers("/api/**").permitAll();//.hasAuthority(Role.USER_ADMIN.name());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFiletr(authenticationManagerBean()));
        http.addFilterBefore(
                new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
