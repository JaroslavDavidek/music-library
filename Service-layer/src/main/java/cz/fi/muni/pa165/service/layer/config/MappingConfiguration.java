package cz.fi.muni.pa165.service.layer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import cz.fi.muni.pa165.PersistenceAplicationContext;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.dozer.DozerBeanMapper;

/**
 *
 * @author JaroslavDavidek
 */
@Configuration
@Import(PersistenceAplicationContext.class)
public class MappingConfiguration {
    
    @Bean
    public Mapper dozer() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(new EntityDTOMapping());
        return mapper;
    }  
}
