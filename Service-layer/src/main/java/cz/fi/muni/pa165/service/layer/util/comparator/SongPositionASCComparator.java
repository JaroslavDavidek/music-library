package cz.fi.muni.pa165.service.layer.util.comparator;

import cz.fi.muni.pa165.entity.Song;
import java.util.Comparator;

/**
 *
 * @author JaroslavDavidek
 */
public class SongPositionASCComparator implements Comparator<Song> {

    @Override
    public int compare(Song firstOne, Song secondOne) {
        return firstOne.getAlbumPosition() - secondOne.getAlbumPosition();
    }
}

