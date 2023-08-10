package sg.edu.rp.c346.id22027176.mymovies;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.io.StringReader;

public class Movies implements Serializable {

    private int id;
    private String title;
    private String genre;
    private Integer year;
    private String rating;

    public Movies(int id, String title, String genre, Integer year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public void setTitle(String title) { this.title = title; }

    public void setGenre(String genre) { this.genre = genre;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + "\n" + "title: " + title + "\n" + "genre: " + genre + "\n" + "year: " + year + "\n" + "rating: " + rating;
    }

}