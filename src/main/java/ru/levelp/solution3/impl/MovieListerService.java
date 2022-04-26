package ru.levelp.solution3.impl;

import lombok.SneakyThrows;
import ru.levelp.MovieFinder;
import ru.levelp.MovieLister;
import ru.levelp.annotations.Autowired;
import ru.levelp.annotations.Service;
import ru.levelp.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovieListerService {

    @Autowired
    MovieFinder finder;

    public void setSource(String source) {
        this.finder.setSource(source);
    }

    public List<Movie> moviesDirectedBy(String director) throws IOException {
        List<Movie> allMovies = finder.findAll();

        List<Movie> result = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (Objects.equals(movie.getDirector(), director)) {
                result.add(movie);
            }
        }

        return allMovies.stream()
                .filter(movie -> Objects.equals(movie.getDirector(), director))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void showMoviesDirectedBy(String director) {
        moviesDirectedBy(director).forEach(System.out::println);
    }
}
