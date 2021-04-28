package com.example.med_id.config;


import com.example.med_id.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/", "/home", "/api/v1/user/**", "/api/v1/token/**",
                "/api/v1/roles", "/logout").permitAll();

        http.authorizeRequests().antMatchers("/welcome").authenticated();

        // For ADMIN.
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

        // For DOKTER.
        http.authorizeRequests().antMatchers("/dokter/**").access("hasRole('ROLE_DOKTER')");

        // For PASIEN.
        http.authorizeRequests().antMatchers("/pasien/**").access("hasRole('ROLE_PASIEN')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/")
                .defaultSuccessUrl("/success")
                .failureUrl("/?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }

}
