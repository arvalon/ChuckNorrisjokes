package ru.arvalon.chucknorrisjokes.mvp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvalon on 02.11.2016.
 */

public class JokeList {

    private String type;
    private List<Joke> value =new ArrayList<Joke>();

    public JokeList(String type, List<Joke> value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Joke> getValue() {
        return value;
    }

    public void setValue(List<Joke> value) {
        this.value = value;
    }
}
