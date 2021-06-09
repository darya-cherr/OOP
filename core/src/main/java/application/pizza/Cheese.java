package application.pizza;

import java.io.Serializable;

public class Cheese implements Serializable {
    private boolean mozzarella;
    private boolean cheddar;
    private boolean blueCheese;
    private boolean parmesan;

    public Cheese(){}

    public Cheese(boolean mozzarella, boolean cheddar, boolean blueCheese, boolean parmesan) {
        this.mozzarella = mozzarella;
        this.cheddar = cheddar;
        this.blueCheese = blueCheese;
        this.parmesan = parmesan;
    }

    public boolean getMozzarella() {
        return mozzarella;
    }

    public boolean getCheddar() {
        return cheddar;
    }

    public boolean getBlueCheese() {
        return blueCheese;
    }

    public boolean getParmesan() {
        return parmesan;
    }

    public void setBlueCheese(boolean blueCheese) {
        this.blueCheese = blueCheese;
    }

    public void setCheddar(boolean cheddar) {
        this.cheddar = cheddar;
    }

    public void setParmesan(boolean parmesan) {
        this.parmesan = parmesan;
    }

    public void setMozzarella(boolean mozzarella) {
        this.mozzarella = mozzarella;
    }

}
