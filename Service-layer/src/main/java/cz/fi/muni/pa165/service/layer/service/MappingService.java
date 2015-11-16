package cz.fi.muni.pa165.service.layer.service;

import org.dozer.Mapper;

/**
 *
 * @author JaroslavDavidek
 */
public interface MappingService {   
    public Mapper getMapper();    
    public  <T> T mapTo(Object objectToMap, Class<T> classToMapTo);  
}
