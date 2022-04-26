package ru.levelp;

import ru.levelp.model.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieFinder {
    void setSource(String source);
    List<Movie> findAll() throws IOException;
}
