package ru.levelp;

import lombok.SneakyThrows;
import ru.levelp.impl.ColonSeparatedMovieFinder;
import ru.levelp.model.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovieLister {

    private final MovieFinder finder = new ColonSeparatedMovieFinder();

    public void setSource(String source) {
        this.finder.setSource(source);
    }

    public List<Movie> moviesDirectedBy(String director) throws IOException {
        List<Movie> allMovies = finder.findAll();

        return allMovies.stream()
                .filter(movie -> Objects.equals(movie.getDirector(), director))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void showMoviesDirectedBy(String director) {
        moviesDirectedBy(director).forEach(System.out::println);
    }

}
