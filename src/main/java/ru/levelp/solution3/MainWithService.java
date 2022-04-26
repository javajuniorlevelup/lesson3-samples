package ru.levelp.solution3;

import ru.levelp.Main;
import ru.levelp.solution3.impl.MovieListerService;

public class MainWithService {
    public static void main(String[] args) {
        AppLoader loader = AppLoader.run(Main.class);
        MovieListerService lister = (MovieListerService) loader.getInstance(MovieListerService.class);
        lister.setSource("movies.csv");

        lister.showMoviesDirectedBy(args[0]);
    }
}
