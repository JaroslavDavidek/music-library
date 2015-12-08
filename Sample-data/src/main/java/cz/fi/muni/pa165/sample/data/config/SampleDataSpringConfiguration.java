package cz.fi.muni.pa165.sample.data.config;

import cz.fi.muni.pa165.sample.data.facade.SampleDataLoadFacade;
import cz.fi.muni.pa165.sample.data.facade.SampleDataLoadFacadeImplementation;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author JaroslavDavidek
 */
@Configuration
@Import(MappingConfiguration.class)
@ComponentScan(basePackageClasses = SampleDataLoadFacadeImplementation.class)
public class SampleDataSpringConfiguration {
    
    @Inject
    private SampleDataLoadFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoad() throws IOException {

        sampleDataLoadingFacade.loadSampleData();
    }
}
