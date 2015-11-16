package cz.fi.muni.pa165.service.layer.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author JaroslavDavidek
 */
public class MappingServiceImplementation implements MappingService {

    @Autowired
    private Mapper mapper;
    
    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public <T> T mapTo(Object objectToMap, Class<T> classToMapTo) {
        return mapper.map(objectToMap, classToMapTo);
    }
    
}
