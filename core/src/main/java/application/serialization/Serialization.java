package application.serialization;

import application.pizza.Pizza;

import java.io.IOException;
import java.util.ArrayList;

public interface Serialization {
    public void serialise(ArrayList<Pizza> data, String name) throws IOException;
    public ArrayList<Pizza> deserialise(String name) throws IOException, ClassNotFoundException, Exception;

}
