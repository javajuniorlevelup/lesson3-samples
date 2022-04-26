package ru.levelp.model;

public class Movie {
    private String genre;
    private String name;
    private String director;

    public Movie(String genre, String name, String director) {
        this.genre = genre;
        this.name = name;
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
