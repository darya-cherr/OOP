package application.com.company;

import application.pizza.Cheese;
import application.pizza.ShrimpPizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShrimpPizzaFrame {
    JTextField nameField = new JTextField(20);
    JTextField sizeField = new JTextField(20);
    JTextField sauceField = new JTextField(20);
    JTextField seafoodField = new JTextField(20);
    JTextField pineappleField = new JTextField(20);
    JCheckBox mozzarella = new JCheckBox("Mozzarella");
    JCheckBox blueCheese = new JCheckBox("Blue cheese");
    JCheckBox parmesan = new JCheckBox("Parmesan");
    JCheckBox cheddar = new JCheckBox("Cheddar");
    JFrame frame = new JFrame("Shrimp pizza");
    JPanel contents = new JPanel();
    Container container = new Container();
    JLabel nameLabel = new JLabel("Name");
    JLabel sizeLabel = new JLabel("Size");
    JLabel sauceLabel = new JLabel("Sauce");
    JLabel seafoodLabel = new JLabel("Seafood");
    JLabel pineappleLabel = new JLabel("Pineapple");
    JButton okBtn = new JButton("  Ok  ");
    ShrimpPizza shrimpPizza;
    int index;

    public ShrimpPizzaFrame(){
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
        contents.add(seafoodLabel);
        contents.add(seafoodField);
        contents.add(pineappleLabel);
        contents.add(pineappleField);
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

    public ShrimpPizzaFrame(ShrimpPizza shrimpPizza, int index) {
        this.shrimpPizza = shrimpPizza;
        this.index = index;
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLocationRelativeTo(null);
        contents.setLayout(new GridLayout(0, 2, -250, 20));
        contents.add(nameLabel);
        contents.add(nameField);
        nameField.setText(shrimpPizza.getName());
        contents.add(sizeLabel);
        contents.add(sizeField);
        sizeField.setText(Integer.toString(shrimpPizza.getSize()));
        contents.add(sauceLabel);
        contents.add(sauceField);
        sauceField.setText(shrimpPizza.getSauce());
        contents.add(seafoodLabel);
        contents.add(seafoodField);
        seafoodField.setText(shrimpPizza.getSeafood());
        contents.add(pineappleLabel);
        contents.add(pineappleField);
        pineappleField.setText(shrimpPizza.getPineapple());
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
            shrimpPizza.setName(nameField.getText());
            shrimpPizza.setSauce(sauceField.getText());
            shrimpPizza.setSeafood(seafoodField.getText());
            shrimpPizza.setPineapple(pineappleField.getText());
            shrimpPizza.setSize(Integer.parseInt(sizeField.getText()));
            shrimpPizza.setCheese(new Cheese(mozzarella1,cheddar1,blueCheese1,parmesan1));
            Main.tableModel.setValueAt(shrimpPizza.getName(), index, 1);
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
            Main.pizzas.add(new ShrimpPizza(nameField.getText(), Integer.parseInt(sizeField.getText()),
                    sauceField.getText(), new Cheese(mozzarella1, cheddar1, blueCheese1, parmesan1),
                    seafoodField.getText(), pineappleField.getText()));
            Object[] obj = {ShrimpPizza.class, Main.pizzas.get(Main.pizzas.size()-1).getName()};
            Main.tableModel.addRow(obj);
            frame.setVisible(false);
        }
    }
}
