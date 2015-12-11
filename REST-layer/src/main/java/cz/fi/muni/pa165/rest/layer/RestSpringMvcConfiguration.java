package cz.fi.muni.pa165.rest.layer;

import cz.fi.muni.pa165.sample.data.config.SampleDataSpringConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author JaroslavDavidek
 */
@EnableWebMvc
@Configuration
@Import({SampleDataSpringConfiguration.class})
@ComponentScan(basePackages = "cz.fi.muni.pa165.rest.layer.rest.controllers")
public class RestSpringMvcConfiguration extends WebMvcConfigurerAdapter {
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
