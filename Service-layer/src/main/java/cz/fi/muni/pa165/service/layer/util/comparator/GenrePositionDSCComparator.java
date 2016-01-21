package cz.fi.muni.pa165.service.layer.util.comparator;

import cz.fi.muni.pa165.entity.Genre;
import java.util.Comparator;

/**
 *
 * @author Jergus Fasanek
 */
public class GenrePositionDSCComparator implements Comparator<Genre> {
    @Override
    public int compare(Genre g1, Genre g2) {
        return g2.getYearOfOrigin()- g1.getYearOfOrigin();
    }
}
