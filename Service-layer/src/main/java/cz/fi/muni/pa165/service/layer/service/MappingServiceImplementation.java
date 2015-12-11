package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.dto.UserDTO;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.entity.User;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author JaroslavDavidek
 */
@Service
public class MappingServiceImplementation implements MappingService {

    @Inject
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
    public <T> T mapToEnforceID(Object objectToMap, Class<T> classToMapTo) {     
        T mappedObjectWithId = (T) mapToDTOWithID(objectToMap);
        if(mappedObjectWithId != null) {
            return mappedObjectWithId;
        } 
        return mapper.map(objectToMap, classToMapTo);
    }
    
    @Override
    public <T> List<T> mapToCollection(Collection<?> objectsToMap, Class<T> classToMapTo) {
        List<T> mappedObjects = new ArrayList<>();
        for (Object object : objectsToMap)
        {
            mappedObjects.add(mapper.map(object, classToMapTo));
        }
        return mappedObjects;
    }
    
    @Override
    public <T> List<T> mapToCollectionEnforceID(Collection<?> objectsToMap, Class<T> classToMapTo) {
        
        List<T> mappedObjects = new ArrayList<>();
        for (Object object : objectsToMap)
        {
            T mappedObjectWithId = (T) mapToDTOWithID(object);
            if(mappedObjectWithId != null) {
                mappedObjects.add(mappedObjectWithId);
            }
            else {
                mappedObjects.add(mapper.map(object, classToMapTo));
            }
                       
        }
        return mappedObjects;
    }
    
    public Object mapToDTOWithID(Object objectToMap){
        
        if(objectToMap instanceof Song)
        {
            Song songToMap = (Song)objectToMap;
            Object mappedObject = mapper.map(songToMap, SongDTO.class);
            SongDTO mappedSong = (SongDTO) mappedObject;
            mappedSong.setId(songToMap.getId());
            return mappedSong;
        }
        if(objectToMap instanceof Album)
        {
            Album albumToMap = (Album)objectToMap;
            Object mappedObject = mapper.map(albumToMap, AlbumDTO.class);
            AlbumDTO mappedEntity = (AlbumDTO) mappedObject;
            mappedEntity.setId(albumToMap.getId());
            return mappedEntity;
        }
        if(objectToMap instanceof Musician)
        {
            Musician musicianToMap = (Musician)objectToMap;
            Object mappedObject = mapper.map(musicianToMap, MusicianDTO.class);
            MusicianDTO mappedEntity = (MusicianDTO) mappedObject;
            mappedEntity.setId(musicianToMap.getId());
            return mappedEntity;
        }
        if(objectToMap instanceof Genre)
        {
            Genre genreToMap = (Genre)objectToMap;
            Object mappedObject = mapper.map(genreToMap, GenreDTO.class);
            GenreDTO mappedEntity = (GenreDTO) mappedObject;
            mappedEntity.setId(genreToMap.getId());
            return mappedEntity;
        }
        if(objectToMap instanceof User)
        {
            User userToMap = (User)objectToMap;
            Object mappedObject = mapper.map(userToMap, UserDTO.class);
            UserDTO mappedEntity = (UserDTO) mappedObject;
            mappedEntity.setId(userToMap.getId());
            return mappedEntity;
        }
        return null;
    }
    
    
}
