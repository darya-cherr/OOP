package application.pizza;

import java.io.IOException;

public class Barbecue extends MeatPizza{
    private String onion;

    public Barbecue(){}
    public Barbecue(String name, int size, String sauce,Cheese cheese, String meat, String onion) {
        super(name, size, sauce,cheese, meat);
        this.onion = onion;
    }

    public String getOnion() {
        return onion;
    }

    public void setOnion(String onion) {
        this.onion = onion;
    }

    @Override
    public String write() throws IOException {
        String comma = ",";
        String result= this.getClass().getSimpleName() + comma + super.getName() + comma+ String.valueOf(super.getSize())+
                comma+ super.getSauce()+ comma+ String.valueOf(super.getCheese().getMozzarella())+
                comma+ String.valueOf(super.getCheese().getCheddar())+ comma+ String.valueOf(super.getCheese().getBlueCheese())+ comma+
                String.valueOf(super.getCheese().getParmesan())+ comma+ super.getMeat()+ comma+ onion;
        return result;
    }

    public static Barbecue read(String[] tokens) throws IOException{
        Barbecue pizza = new Barbecue(tokens[1], Integer.parseInt(tokens[2]),
                tokens[3],new Cheese(Boolean.parseBoolean(tokens[4]),Boolean.parseBoolean(tokens[5]),
                Boolean.parseBoolean(tokens[6]),Boolean.parseBoolean(tokens[7])),
                tokens[8], tokens[9]);
        return pizza;
    }
}
