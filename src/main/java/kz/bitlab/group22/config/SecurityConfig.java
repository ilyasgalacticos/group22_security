package kz.bitlab.group22.config;

import kz.bitlab.group22.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }

    @Override
    public void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/moderator/**").hasAnyRole("MODERATOR")
                .antMatchers("/profile/**").hasAnyRole("USER")
                .antMatchers("/enter", "/*").permitAll();

        http.formLogin().
            loginProcessingUrl("/signin").
            loginPage("/enter").
            failureUrl("/enter?error").
            usernameParameter("user_email").
            passwordParameter("user_password").
            permitAll();

        http.logout().
            logoutUrl("/signout").
            logoutSuccessUrl("/enter").permitAll().
            invalidateHttpSession(true);

        http.csrf().disable();

    }

}
