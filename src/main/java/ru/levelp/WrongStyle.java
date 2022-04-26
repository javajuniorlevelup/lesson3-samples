package ru.levelp;

import lombok.SneakyThrows;
import ru.levelp.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class WrongStyle {

    private String source;

    public WrongStyle(String source) {
        this.source = source;
    }

    public static void main(String[] args) {
        WrongStyle app = new WrongStyle("movies.csv");
        app.showMoviesDirectedBy(args[0]);
    }

    public List<Movie> findAll() throws IOException {
        final boolean[] isHeaderRow = {true};
        List<String> columnNames = new ArrayList<>();

        return Files.readAllLines(Path.of(source)).stream().map(line -> {

                    Map<String, String> data = new HashMap<>();
                    String[] tokens = line.split(",");

                    for (int i = 0; i < tokens.length; i++) {
                        if (isHeaderRow[0]) {
                            columnNames.add(tokens[i]);
                        } else {
                            if (i < (columnNames).size()) {
                                data.put(columnNames.get(i), tokens[i]);
                            }
                        }
                    }
                    isHeaderRow[0] = false;
                    return data;
                }).map(movieProps -> new Movie(movieProps.get("genre"), movieProps.get("name"), movieProps.get("director")))
                .collect(Collectors.toList());

    }

    public List<Movie> moviesDirectedBy(String director) throws IOException {
        List<Movie> allMovies = findAll();

        return allMovies.stream()
                .filter(movie -> Objects.equals(movie.getDirector(), director))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void showMoviesDirectedBy(String director) {
        moviesDirectedBy(director).forEach(System.out::println);
    }
}
