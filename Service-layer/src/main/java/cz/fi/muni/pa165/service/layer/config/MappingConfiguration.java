package cz.fi.muni.pa165.service.layer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import cz.fi.muni.pa165.PersistenceAplicationContext;
import cz.fi.muni.pa165.service.layer.facade.SongFacadeImplementation;
import cz.fi.muni.pa165.service.layer.service.SongServiceImplementation;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author JaroslavDavidek
 */
@Configuration
@Import(PersistenceAplicationContext.class)
@ComponentScan(basePackageClasses={SongServiceImplementation.class, SongFacadeImplementation.class})
public class MappingConfiguration {
    
    @Bean
    public Mapper dozer() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(new EntityDTOMapping());
        return mapper;
    }  
}
