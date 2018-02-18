package pl.erbel;

public class Greeting {

    private static final String PREFIX = "HELLO: ";

    private String name;

    public Greeting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "name='" + PREFIX + name + '\'' +
                '}';
    }
}
