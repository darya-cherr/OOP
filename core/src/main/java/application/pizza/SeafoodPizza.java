package application.pizza;

import java.io.IOException;

public class SeafoodPizza extends Pizza{
    private String seafood;

    public SeafoodPizza(){}

    public SeafoodPizza(String name, int size, String sauce,Cheese cheese, String seafood) {
        super(name, size, sauce, cheese);
        this.seafood = seafood;
    }

    public String getSeafood() {
        return seafood;
    }

    public void setSeafood(String seafood) {
        this.seafood = seafood;
    }

    @Override
    public String write() throws IOException {
        String comma = ",";
        String result= this.getClass().getSimpleName() + comma + super.getName()+ comma+ String.valueOf(super.getSize())+
                comma+ super.getSauce()+ comma+ String.valueOf(super.getCheese().getMozzarella())+
                comma+ String.valueOf(super.getCheese().getCheddar())+ comma+ String.valueOf(super.getCheese().getBlueCheese())+ comma+
                String.valueOf(super.getCheese().getParmesan())+ comma+ seafood;
        return result;
    }

    public static SeafoodPizza read(String[] tokens) throws IOException{
        SeafoodPizza pizza = new SeafoodPizza(tokens[1], Integer.parseInt(tokens[2]),
                tokens[3],new Cheese(Boolean.parseBoolean(tokens[4]),Boolean.parseBoolean(tokens[5]),
                Boolean.parseBoolean(tokens[6]),Boolean.parseBoolean(tokens[7])),
                tokens[8]);
        return pizza;
    }
}
