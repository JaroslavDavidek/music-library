package cz.fi.muni.pa165.service.layer.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author JaroslavDavidek
 */
public interface MappingService {   
    public Mapper getMapper();    
    public <T> T mapTo(Object objectToMap, Class<T> classToMapTo);
    public <T> List<T> mapToCollection(Collection<?> objects, Class<T> mapToClass);
}
