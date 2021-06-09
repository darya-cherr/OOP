package application.serialization;

import application.pizza.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Serializer implements Serialization{
    @Override
    public void serialise(ArrayList<Pizza> data, String name) throws IOException {
        FileWriter writer = new FileWriter(name);
        for (Pizza pizza: data) {
            writer.append(pizza.write());
            writer.append("\n");
        }
        writer.flush();
        writer.close();
        System.out.println(data);
    }

    @Override
    public ArrayList<Pizza> deserialise(String name) throws IOException, ClassNotFoundException, Exception {
        ArrayList<Pizza> newList = new ArrayList();
        String line = "";
        String COMMA_DELIMITER = ",";
        BufferedReader fileReader = new BufferedReader(new FileReader(name));
        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) {
                switch (tokens[0]) {
                    case ("MeatPizza"):
                        newList.add(MeatPizza.read(tokens));
                        break;
                    case ("Barbecue"):
                        newList.add(Barbecue.read(tokens));
                        break;
                    case ("VegetarianPizza"):
                        newList.add(VegetarianPizza.read(tokens));
                        break;
                    case ("Vegetable"):
                        newList.add(Vegetable.read(tokens));
                        break;
                    case ("SeafoodPizza"):
                        newList.add(SeafoodPizza.read(tokens));
                        break;
                    case ("ShrimpPizza"):
                        newList.add(ShrimpPizza.read(tokens));
                        break;
                }
            }
        }
        fileReader.close();
        return newList;
    }
}
