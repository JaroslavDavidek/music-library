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
        /*
        auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
	auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        */
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
                .antMatchers("/song/detail").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/song/find").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/song/index").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/song/list").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/song/new").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/song/create").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/song/listAsAdmin").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/song/detailAsAdmin").access("hasAnyRole('ROLE_ADMIN')")

                .antMatchers("/genre/detail").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/genre/find").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/genre/index").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/genre/list").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/genre/new").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/genre/create").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/genre/listAsAdmin").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/genre/detailAsAdmin").access("hasAnyRole('ROLE_ADMIN')")

                .antMatchers("/album/detail").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/album/find").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/album/index").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/album/list").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/album/new").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/album/create").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/album/listAsAdmin").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/album/detailAsAdmin").access("hasAnyRole('ROLE_ADMIN')")

                .antMatchers("/musician/detail").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/musician/find").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/musician/index").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/musician/list").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/musician/new").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/musician/create").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/musician/listAsAdmin").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/musician/detailAsAdmin").access("hasAnyRole('ROLE_ADMIN')")
                
                .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?loginError=true")
                .usernameParameter("user").passwordParameter("pass")
                .and()
                .logout().logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling().accessDeniedPage("/loginAccessDenied")
                .and()
                .csrf().disable();

    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new PasswordEncoding();
        return encoder;
    }
    
    
}
