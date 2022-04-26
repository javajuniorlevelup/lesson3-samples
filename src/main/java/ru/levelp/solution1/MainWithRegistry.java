package ru.levelp.solution1;

import ru.levelp.MovieFinder;
import ru.levelp.annotations.Service;
import ru.levelp.impl.ColonSeparatedMovieFinder;

import java.lang.reflect.Field;

public class MainWithRegistry {
    public static void main(String[] args) {
        SimpleContainer.getInstance().register(MovieFinder.class, ColonSeparatedMovieFinder.class);

        MovieListerWithRegistry lister = new MovieListerWithRegistry();
        lister.setSource("movies.csv");

        lister.showMoviesDirectedBy(args[0]);
    }
}
