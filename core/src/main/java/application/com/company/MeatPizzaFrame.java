package application.com.company;

import application.pizza.MeatPizza;
import application.pizza.Cheese;
import application.pizza.Pizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeatPizzaFrame{
    JTextField nameField = new JTextField(20);
    JTextField sizeField = new JTextField(20);
    JTextField sauceField = new JTextField(20);
    JTextField meatField = new JTextField(20);
    JCheckBox mozzarella = new JCheckBox("Mozzarella");
    JCheckBox blueCheese = new JCheckBox("Blue cheese");
    JCheckBox parmesan = new JCheckBox("Parmesan");
    JCheckBox cheddar = new JCheckBox("Cheddar");
    JFrame frame = new JFrame("Meat pizza");
    JPanel contents = new JPanel();
    JLabel nameLabel = new JLabel("Name");
    JLabel sizeLabel = new JLabel("Size");
    JLabel sauceLabel = new JLabel("Sauce");
    JLabel meatLabel = new JLabel("Meat");
    Container container = new Container();
    JButton okBtn = new JButton("  Ok  ");
    MeatPizza meatPizza;
    int index;

    public MeatPizzaFrame(){
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
        contents.add(meatLabel);
        contents.add(meatField);
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

    public MeatPizzaFrame(MeatPizza meatPizza, int index) {
        this.meatPizza = meatPizza;
        this.index = index;
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLocationRelativeTo(null);
        contents.setLayout(new GridLayout(0, 2, -250, 20));
        contents.add(nameLabel);
        contents.add(nameField);
        nameField.setText(meatPizza.getName());
        contents.add(sizeLabel);
        sizeField.setText(Integer.toString(meatPizza.getSize()));
        contents.add(sizeField);
        contents.add(sauceLabel);
        sauceField.setText(meatPizza.getSauce());
        contents.add(sauceField);
        contents.add(meatLabel);
        meatField.setText(meatPizza.getMeat());
        contents.add(meatField);
        container.setLayout(new FlowLayout());
        contents.add(container);
        container.add(mozzarella);
        mozzarella.setSelected(meatPizza.getCheese().getMozzarella());
        container.add(blueCheese);
        blueCheese.setSelected(meatPizza.getCheese().getBlueCheese());
        container.add(parmesan);
        parmesan.setSelected(meatPizza.getCheese().getParmesan());
        container.add(cheddar);
        cheddar.setSelected(meatPizza.getCheese().getCheddar());
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
            meatPizza.setName(nameField.getText());
            meatPizza.setSauce(sauceField.getText());
            meatPizza.setMeat(meatField.getText());
            meatPizza.setSize(Integer.parseInt(sizeField.getText()));
            meatPizza.setCheese(new Cheese(mozzarella1,cheddar1,blueCheese1,parmesan1));
            Main.tableModel.setValueAt(meatPizza.getName(), index, 1);
            frame.setVisible(false);
        }
    }

    public class CreateActionListener implements ActionListener {
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
            Main.pizzas.add(new MeatPizza(nameField.getText(), Integer.parseInt(sizeField.getText()),
                    sauceField.getText(),new Cheese(mozzarella1,cheddar1,blueCheese1,parmesan1),meatField.getText()));
            Object[] obj = {MeatPizza.class, Main.pizzas.get(Main.pizzas.size()-1).getName()};
            Main.tableModel.addRow(obj);
            frame.setVisible(false);
        }
    }

}
