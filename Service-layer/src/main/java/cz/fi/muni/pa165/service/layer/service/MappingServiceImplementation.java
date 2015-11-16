package cz.fi.muni.pa165.service.layer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    
    @Override
    public <T> List<T> mapTo(Collection<?> objectsToMap, Class<T> classToMapTo) {
        List<T> mappedObjects = new ArrayList<>();
        for (Object object : objectsToMap)
        {
            mappedObjects.add(mapper.map(object, classToMapTo));
        }
        return mappedObjects;
    }
    
}
