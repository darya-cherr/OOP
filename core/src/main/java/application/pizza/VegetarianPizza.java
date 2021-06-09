package application.pizza;

import java.io.IOException;

public class VegetarianPizza extends Pizza{
    private String tomato;
    private String pepper;
    private String mushrooms;

    public VegetarianPizza(){}

    public VegetarianPizza(String name, int size, String sauce,Cheese cheese, String tomato, String pepper, String mushrooms) {
        super(name,size, sauce,cheese);
        this.tomato = tomato;
        this.pepper = pepper;
        this.mushrooms = mushrooms;
    }

    public String getTomato() {
        return tomato;
    }

    public String getMushrooms() {
        return mushrooms;
    }

    public String getPepper() {
        return pepper;
    }

    public void setTomato(String tomato) {
        this.tomato = tomato;
    }

    public void setMushrooms(String mushrooms) {
        this.mushrooms = mushrooms;
    }

    public void setPepper(String pepper) {
        this.pepper = pepper;
    }

    @Override
    public String write() throws IOException {
        String comma = ",";
        String result= this.getClass().getSimpleName() + comma + super.getName()+ comma+ String.valueOf(super.getSize())+ comma+ super.getSauce()+
                comma+ String.valueOf(super.getCheese().getMozzarella())+
                comma+ String.valueOf(super.getCheese().getCheddar())+ comma+ String.valueOf(super.getCheese().getBlueCheese())+ comma+
                String.valueOf(super.getCheese().getParmesan())+ comma+ tomato+ comma+ pepper+ comma+ mushrooms;
        return result;
    }

    public static VegetarianPizza read(String[] tokens) throws IOException{
        VegetarianPizza pizza = new VegetarianPizza(tokens[1], Integer.parseInt(tokens[2]),
                tokens[3],new Cheese(Boolean.parseBoolean(tokens[4]),Boolean.parseBoolean(tokens[5]),
                Boolean.parseBoolean(tokens[6]),Boolean.parseBoolean(tokens[7])),
                tokens[8], tokens[9],tokens[10]);
        return pizza;
    }

}
