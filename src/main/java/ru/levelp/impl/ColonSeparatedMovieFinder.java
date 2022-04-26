package ru.levelp.impl;

import ru.levelp.MovieFinder;
import ru.levelp.annotations.Service;
import ru.levelp.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ColonSeparatedMovieFinder implements MovieFinder {

    private String source;

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
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

}
