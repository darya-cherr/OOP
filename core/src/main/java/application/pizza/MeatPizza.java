package application.pizza;

import java.io.IOException;

public class MeatPizza extends Pizza{
        private String meat;

    public MeatPizza(){}
    public MeatPizza(String name, int size, String sauce, Cheese cheese, String meat) {
        super(name, size, sauce, cheese);
        this.meat = meat;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    @Override
    public String write() throws IOException {
        String comma = ",";
        String result= this.getClass().getSimpleName() + comma+ super.getName()+ comma+ String.valueOf(super.getSize())+
                comma+ super.getSauce()+ comma+ String.valueOf(super.getCheese().getMozzarella())+
                comma+ String.valueOf(super.getCheese().getCheddar())+ comma+ String.valueOf(super.getCheese().getBlueCheese())+ comma+
                String.valueOf(super.getCheese().getParmesan())+ comma+ meat;
        return result;
    }

    public static MeatPizza read(String[] tokens) throws IOException{
        MeatPizza pizza = new MeatPizza(tokens[1], Integer.parseInt(tokens[2]),
                tokens[3],new Cheese(Boolean.parseBoolean(tokens[4]),Boolean.parseBoolean(tokens[5]),
                Boolean.parseBoolean(tokens[6]),Boolean.parseBoolean(tokens[7])),
                tokens[8]);
        return pizza;
    }

}
