package application.pizza;

import java.io.Serializable;
import java.io.*;


abstract public class Pizza implements Serializable {
    private String name;
    private int size;
    private String sauce;
    private Cheese cheese;

    public Pizza(){}

    public Pizza(String name, int size, String sauce, Cheese cheese) {
        this.name = name;
        this.size = size;
        this.sauce = sauce;
        this.cheese = cheese;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getSauce() {
        return sauce;
    }

    public Cheese getCheese(){
        return cheese;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    @Override
    public String toString() {
        return name;
    }

    public String write() throws IOException{
        return "";
    }

}
