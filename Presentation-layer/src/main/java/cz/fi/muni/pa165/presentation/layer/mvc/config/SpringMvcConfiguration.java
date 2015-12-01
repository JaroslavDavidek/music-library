package cz.fi.muni.pa165.presentation.layer.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author JaroslavDavidek
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "cz.fi.muni.pa165.presentation.layer.mvc.controllers")
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // set view and configure view resolver for jsp pages...
    }
}
