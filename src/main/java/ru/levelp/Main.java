package ru.levelp;

public class Main {

    public static void main(String[] args) {
        MovieLister lister = new MovieLister();
        lister.setSource("movies.csv");

        lister.showMoviesDirectedBy(args[0]);
    }
}
