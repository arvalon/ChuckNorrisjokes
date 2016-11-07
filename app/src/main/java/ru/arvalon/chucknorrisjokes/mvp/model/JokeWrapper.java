package ru.arvalon.chucknorrisjokes.mvp.model;

/**
 * Created by arvalon on 07.11.2016.
 */

public class JokeWrapper {
    private String type;
    private Joke value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Joke getValue() {
        return value;
    }

    public void setValue(Joke value) {
        this.value = value;
    }
}
