package pl.erbel;

import java.util.List;

public class JokeValue {

    Integer id;

    String joke;

    List<String> categories;

    public JokeValue(Integer id, String joke, List<String> categories) {
        this.id = id;
        this.joke = joke;
        this.categories = categories;
    }

    public JokeValue() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "JokeValue{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", categories=" + categories +
                '}';
    }
}
