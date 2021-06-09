package application.com.company;

import application.pizza.Cheese;
import application.pizza.VegetarianPizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VegetarianFrame{
    JTextField nameField = new JTextField(20);
    JTextField sizeField = new JTextField(20);
    JTextField sauceField = new JTextField(20);
    JTextField pepperField = new JTextField(20);
    JTextField tomatoField = new JTextField(20);
    JTextField mushroomsField = new JTextField(20);
    JCheckBox mozzarella = new JCheckBox("Mozzarella");
    JCheckBox blueCheese = new JCheckBox("Blue cheese");
    JCheckBox parmesan = new JCheckBox("Parmesan");
    JCheckBox cheddar = new JCheckBox("Cheddar");
    JFrame frame = new JFrame("Vegetarian pizza");
    JPanel contents = new JPanel();
    JLabel nameLabel = new JLabel("Name");
    JLabel sizeLabel = new JLabel("Size");
    JLabel sauceLabel = new JLabel("Sauce");
    JLabel pepperLabel = new JLabel("Pepper");
    JLabel tomatoLabel = new JLabel("Tomato");
    JLabel mushroomsLabel = new JLabel("Mushrooms");
    Container container = new Container();
    JButton okBtn = new JButton("  Ok  ");
    VegetarianPizza vegetarianPizza;
    int index;

    public VegetarianFrame(){
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLocationRelativeTo(null);
        contents.setLayout(new GridLayout(0, 2, -250, 20));
        contents.add(nameLabel);
        contents.add(nameField);
        contents.add(sizeLabel);
        contents.add(sizeField);
        contents.add(sauceLabel);
        contents.add(sauceField);
        contents.add(pepperLabel);
        contents.add(pepperField);
        contents.add(tomatoLabel);
        contents.add(tomatoField);
        contents.add(mushroomsLabel);
        contents.add(mushroomsField);
        container.setLayout(new FlowLayout());
        contents.add(container);
        container.add(mozzarella);
        container.add(blueCheese);
        container.add(parmesan);
        container.add(cheddar);
        ActionListener actionListener = new CreateActionListener();
        okBtn.addActionListener(actionListener);
        container.add(okBtn);
        frame.getContentPane().add(contents);
        frame.pack();
    }
    public VegetarianFrame(VegetarianPizza vegetarianPizza, int index){
        this.vegetarianPizza = vegetarianPizza;
        this.index = index;
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLocationRelativeTo(null);
        contents.setLayout(new GridLayout(0, 2, -250, 20));
        contents.add(nameLabel);
        contents.add(nameField);
        nameField.setText(vegetarianPizza.getName());
        contents.add(sizeLabel);
        contents.add(sizeField);
        sizeField.setText(Integer.toString(vegetarianPizza.getSize()));
        contents.add(sauceLabel);
        contents.add(sauceField);
        sauceField.setText(vegetarianPizza.getSauce());
        contents.add(pepperLabel);
        contents.add(pepperField);
        pepperField.setText(vegetarianPizza.getPepper());
        contents.add(tomatoLabel);
        contents.add(tomatoField);
        tomatoField.setText(vegetarianPizza.getTomato());
        contents.add(mushroomsLabel);
        contents.add(mushroomsField);
        mushroomsField.setText(vegetarianPizza.getMushrooms());
        container.setLayout(new FlowLayout());
        contents.add(container);
        container.add(mozzarella);
        container.add(blueCheese);
        container.add(parmesan);
        container.add(cheddar);
        ActionListener editListener = new EditActionListener();
        okBtn.addActionListener(editListener);
        container.add(okBtn);
        frame.getContentPane().add(contents);
        frame.pack();
    }

    public class EditActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean mozzarella1 = false, cheddar1 = false, blueCheese1 = false, parmesan1 = false;
            if(mozzarella.isSelected()){
                mozzarella1 = true;
            }
            if(cheddar.isSelected()){
                cheddar1 = true;
            }
            if(blueCheese.isSelected()){
                blueCheese1 = true;
            }
            if(parmesan.isSelected()){
                parmesan1 = true;
            }
            vegetarianPizza.setName(nameField.getText());
            vegetarianPizza.setSauce(sauceField.getText());
            vegetarianPizza.setPepper(pepperField.getText());
            vegetarianPizza.setTomato(tomatoField.getText());
            vegetarianPizza.setMushrooms(mushroomsField.getText());
            vegetarianPizza.setSize(Integer.parseInt(sizeField.getText()));
            vegetarianPizza.setCheese(new Cheese(mozzarella1,cheddar1,blueCheese1,parmesan1));
            Main.tableModel.setValueAt(vegetarianPizza.getName(), index, 1);
            frame.setVisible(false);
        }
    }
    public class CreateActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean mozzarella1 = false, cheddar1 = false, blueCheese1 = false, parmesan1 = false;
            if (mozzarella.isSelected()) {
                mozzarella1 = true;
            }
            if (cheddar.isSelected()) {
                cheddar1 = true;
            }
            if (blueCheese.isSelected()) {
                blueCheese1 = true;
            }
            if (parmesan.isSelected()) {
                parmesan1 = true;
            }
            Main.pizzas.add(new VegetarianPizza(nameField.getText(), Integer.parseInt(sizeField.getText()),
                    sauceField.getText(), new Cheese(mozzarella1, cheddar1, blueCheese1, parmesan1),
                    tomatoField.getText(), pepperField.getText(), mushroomsField.getText()));
            Object[] obj = {VegetarianPizza.class, Main.pizzas.get(Main.pizzas.size()-1).getName()};
            Main.tableModel.addRow(obj);
            frame.setVisible(false);
        }
    }

}
