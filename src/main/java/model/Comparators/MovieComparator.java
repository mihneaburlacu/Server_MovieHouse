package model.Comparators;

import model.Movie;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie m1, Movie m2) {
        int typeCompare = m1.getType().compareTo(m2.getType());
        if (typeCompare == 0) {
            return Integer.compare(m1.getYear(), m2.getYear());
        }
        return typeCompare;
    }
}
