package net.parwand.springregister.infrastructure.configuration;

import net.parwand.springregister.infrastructure.app.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Parwand Alsino
 * @Website https://www.parwand.net
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    UserDetailsServiceImpl userDetailsServiceImpl;

    public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsServiceImpl);
        return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/error", "/css/**", "/img/**").permitAll()
                .antMatchers("/student/registration").permitAll()
                .antMatchers("/student/register").permitAll()
                .antMatchers("/user/registration").permitAll()
                .antMatchers("/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/").permitAll()
                .and()
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll();
    }



}
