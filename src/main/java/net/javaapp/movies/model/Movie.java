package net.javaapp.movies.model;

public class Movie {
    private int id;
    private String title;
    private String director;
    private float price;

    public Movie() {
    }

    public Movie(String title, String director, float price) {
        this.title = title;
        this.director = director;
        this.price = price;
    }

    public Movie(int id, String title, String director, float price) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
