package spring;

public class TwoParamConstrDataClass {
    private String name;
    private int counter;

    public TwoParamConstrDataClass(String name, int counter) {
        this.name = name;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }
}
