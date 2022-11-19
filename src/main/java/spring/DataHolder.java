package spring;

import spring.anno.AwesomeService;

public class DataHolder {
    private String string;

    public DataHolder(String string){
        this.string = string;
        System.out.println("DataHolfer constr Called");
    }

    public String getString() {
        return string;
    }
}
