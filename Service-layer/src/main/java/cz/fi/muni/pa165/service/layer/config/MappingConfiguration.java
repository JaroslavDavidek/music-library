package cz.fi.muni.pa165.service.layer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import cz.fi.muni.pa165.PersistenceAplicationContext;
import cz.fi.muni.pa165.service.layer.facade.SongFacadeImplementation;
import cz.fi.muni.pa165.service.layer.facade.AlbumFacadeImplementation;
import cz.fi.muni.pa165.service.layer.facade.GenreFacadeImplementation;
import cz.fi.muni.pa165.service.layer.facade.MusicianFacadeImplementation;

import cz.fi.muni.pa165.service.layer.service.SongServiceImplementation;
import cz.fi.muni.pa165.service.layer.service.AlbumServiceImplementation;
import cz.fi.muni.pa165.service.layer.service.GenreServiceImplementation;
import cz.fi.muni.pa165.service.layer.service.MusicianServiceImplementation;
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
@ComponentScan(basePackageClasses={GenreServiceImplementation.class, GenreFacadeImplementation.class})
public class MappingConfiguration {
    
    @Bean
    public Mapper dozer() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(new EntityDTOMapping());
        return mapper;
    }  
}
