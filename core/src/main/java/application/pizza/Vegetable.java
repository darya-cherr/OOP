package application.pizza;

import java.io.IOException;

public class Vegetable extends VegetarianPizza{
    private String olives;
    public Vegetable(){}

    public Vegetable(String name, int size, String sauce,Cheese cheese, String tomato, String pepper, String mushrooms, String olives) {
        super(name, size, sauce, cheese, tomato, pepper, mushrooms);
        this.olives = olives;
    }

    public String getOlives() {
        return olives;
    }

    public void setOlives(String  olives) {
        this.olives = olives;
    }

    @Override
    public String write() throws IOException {
        String comma = ",";
        String result= this.getClass().getSimpleName() + comma + super.getName()+ comma+ String.valueOf(super.getSize())+ comma+
                super.getSauce()+ comma+ String.valueOf(super.getCheese().getMozzarella())+
                comma+ String.valueOf(super.getCheese().getCheddar())+ comma+ String.valueOf(super.getCheese().getBlueCheese())+ comma+
                String.valueOf(super.getCheese().getParmesan())+ comma+ super.getTomato()+ comma+
                super.getPepper()+ comma+ super.getMushrooms()+ comma+ olives;
        return result;
    }

    public static Vegetable read(String[] tokens) throws IOException{
        Vegetable pizza = new Vegetable(tokens[1], Integer.parseInt(tokens[2]),
                tokens[3],new Cheese(Boolean.parseBoolean(tokens[4]),Boolean.parseBoolean(tokens[5]),
                Boolean.parseBoolean(tokens[6]),Boolean.parseBoolean(tokens[7])),
                tokens[8], tokens[9],tokens[10], tokens[11]);
        return pizza;
    }
}
