package cz.fi.muni.pa165.presentation.layer.mvc.config;

import cz.fi.muni.pa165.api.layer.dto.UserDTO;
import cz.fi.muni.pa165.api.layer.facade.UserFacade;
import java.util.Collection;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Jergus Fasanek
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
    private UserFacade userFacade;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        Collection<UserDTO> userDTOs = userFacade.getAllUsers();
        
        auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
	auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");

        for(UserDTO userDTO : userDTOs) {
            if(userDTO.isAdmin()) {
                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                        .withUser(userDTO.getEmail()).password(userDTO.getPasswordHash()).roles("ADMIN");
            } else {
                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                        .withUser(userDTO.getEmail()).password(userDTO.getPasswordHash()).roles("USER");
            }
        }
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/song/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/genre/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/album/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/musician/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error=invalidLoginAttempt")
                .usernameParameter("user").passwordParameter("pass")
                .and()
                .logout().logoutSuccessUrl("/home")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .csrf().disable();

    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new PasswordEncoding();
        return encoder;
    }
}
