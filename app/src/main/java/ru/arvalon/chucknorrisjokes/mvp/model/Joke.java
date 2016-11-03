package ru.arvalon.chucknorrisjokes.mvp.model;

/**
 * Created by arvalon on 02.11.2016.
 */

public class Joke {
    private int id;
    private String joke;
    private String[] categories;

    public Joke(int id, String joke, String[] categories) {
        this.id = id;
        this.joke = joke;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
