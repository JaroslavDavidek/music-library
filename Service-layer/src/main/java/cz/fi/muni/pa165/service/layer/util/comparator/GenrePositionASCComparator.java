package cz.fi.muni.pa165.service.layer.util.comparator;

import cz.fi.muni.pa165.entity.Genre;
import java.util.Comparator;

/**
 *
 * @author Jergus Fasanek
 */
public class GenrePositionASCComparator implements Comparator<Genre> {
    @Override
    public int compare(Genre g1, Genre g2) {
        return g1.getYearOfOrigin()- g2.getYearOfOrigin();
    }
}
