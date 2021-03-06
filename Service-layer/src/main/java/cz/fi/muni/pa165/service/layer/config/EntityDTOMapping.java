package cz.fi.muni.pa165.service.layer.config;

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
import org.dozer.loader.api.BeanMappingBuilder;

/**
 *
 * @author JaroslavDavidek
 */
public class EntityDTOMapping extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(Song.class, SongDTO.class);
        mapping(Album.class, AlbumDTO.class);
        mapping(Genre.class, GenreDTO.class);
        mapping(Musician.class, MusicianDTO.class);
        mapping(User.class, UserDTO.class);
    }
    
}
