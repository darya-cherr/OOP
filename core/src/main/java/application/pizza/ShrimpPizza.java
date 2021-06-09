package application.pizza;

import java.io.IOException;

public class ShrimpPizza extends SeafoodPizza{
    private String pineapple;

    public ShrimpPizza(){}

    public ShrimpPizza(String name, int size, String sauce,Cheese cheese, String seafood, String pineapple) {
        super(name, size, sauce, cheese, seafood);
        this.pineapple = pineapple;
    }

    public String getPineapple() {
        return pineapple;
    }

    public void setPineapple(String  pineapple) {
        this.pineapple = pineapple;
    }

    @Override
    public String write() throws IOException {
        String comma = ",";
        String result = this.getClass().getSimpleName() + comma + super.getName()+ comma+ String.valueOf(super.getSize())+
                comma+ super.getSauce()+ comma+ String.valueOf(super.getCheese().getMozzarella())+
                comma+ String.valueOf(super.getCheese().getCheddar())+ comma+ String.valueOf(super.getCheese().getBlueCheese())+ comma+
                String.valueOf(super.getCheese().getParmesan())+ comma+ super.getSeafood()+ comma+ pineapple;
        return result;
    }

    public static ShrimpPizza read(String[] tokens) throws IOException{
        ShrimpPizza pizza = new ShrimpPizza(tokens[1], Integer.parseInt(tokens[2]),
                tokens[3],new Cheese(Boolean.parseBoolean(tokens[4]),Boolean.parseBoolean(tokens[5]),
                Boolean.parseBoolean(tokens[6]),Boolean.parseBoolean(tokens[7])),
                tokens[8], tokens[9]);
        return pizza;
    }
}
