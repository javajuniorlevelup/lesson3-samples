package ru.levelp.solution2;

public class MainWithObjectFactory {
    public static void main(String[] args) {
        MovieListerWithObjectFactory lister = new MovieListerWithObjectFactory();
        lister.setSource("movies.csv");

        lister.showMoviesDirectedBy(args[0]);
    }
}
