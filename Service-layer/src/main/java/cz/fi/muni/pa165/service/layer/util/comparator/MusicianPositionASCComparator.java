package cz.fi.muni.pa165.service.layer.util.comparator;

import cz.fi.muni.pa165.entity.Musician;
import java.util.Comparator;

/**
 *
 * @author Jergus Fasanek
 */
public class MusicianPositionASCComparator implements Comparator<Musician> {
    @Override
    public int compare(Musician m1, Musician m2) {
        return m1.getDateOfBirth().compareTo(m2.getDateOfBirth());
    }
}
