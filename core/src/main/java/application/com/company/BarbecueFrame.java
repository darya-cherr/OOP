package application.com.company;

import application.pizza.Barbecue;
import application.pizza.Cheese;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarbecueFrame {
    JTextField nameField = new JTextField(20);
    JTextField sizeField = new JTextField(20);
    JTextField sauceField = new JTextField(20);
    JTextField meatField = new JTextField(20);
    JTextField onionField = new JTextField(20);
    JCheckBox mozzarella = new JCheckBox("Mozzarella");
    JCheckBox blueCheese = new JCheckBox("Blue cheese");
    JCheckBox parmesan = new JCheckBox("Parmesan");
    JCheckBox cheddar = new JCheckBox("Cheddar");
    JFrame frame = new JFrame("Barbecue");
    JPanel contents = new JPanel();
    JLabel nameLabel = new JLabel("Name");
    JLabel sizeLabel = new JLabel("Size");
    JLabel sauceLabel = new JLabel("Sauce");
    JLabel meatLabel = new JLabel("Meat");
    JLabel onionLabel = new JLabel("Onion");
    Container container = new Container();
    JButton okBtn = new JButton("  Ok  ");
    Barbecue barbecue;
    int index;

    public BarbecueFrame(){
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLocationRelativeTo(null);
        JPanel contents = new JPanel();
        contents.setLayout(new GridLayout(0, 2, -250, 20));
        contents.add(nameLabel);
        contents.add(nameField);
        contents.add(sizeLabel);
        contents.add(sizeField);
        contents.add(sauceLabel);
        contents.add(sauceField);
        contents.add(meatLabel);
        contents.add(meatField);
        contents.add(onionLabel);
        contents.add(onionField);
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

    public BarbecueFrame(Barbecue barbecue, int index){
        this.barbecue = barbecue;
        this.index = index;
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLocationRelativeTo(null);
        contents.setLayout(new GridLayout(0, 2, -250, 20));
        contents.add(nameLabel);
        contents.add(nameField);
        nameField.setText(barbecue.getName());
        contents.add(sizeLabel);
        sizeField.setText(Integer.toString(barbecue.getSize()));
        contents.add(sizeField);
        contents.add(sauceLabel);
        sauceField.setText(barbecue.getSauce());
        contents.add(sauceField);
        contents.add(meatLabel);
        meatField.setText(barbecue.getMeat());
        contents.add(meatField);
        contents.add(onionLabel);
        contents.add(onionField);
        onionField.setText(barbecue.getOnion());
        container.setLayout(new FlowLayout());
        contents.add(container);
        container.add(mozzarella);
        mozzarella.setSelected(barbecue.getCheese().getMozzarella());
        container.add(blueCheese);
        blueCheese.setSelected(barbecue.getCheese().getBlueCheese());
        container.add(parmesan);
        parmesan.setSelected(barbecue.getCheese().getParmesan());
        container.add(cheddar);
        cheddar.setSelected(barbecue.getCheese().getCheddar());
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
            barbecue.setName(nameField.getText());
            barbecue.setSauce(sauceField.getText());
            barbecue.setMeat(meatField.getText());
            barbecue.setOnion(onionField.getText());
            barbecue.setSize(Integer.parseInt(sizeField.getText()));
            barbecue.setCheese(new Cheese(mozzarella1,cheddar1,blueCheese1,parmesan1));
            Main.tableModel.setValueAt(barbecue.getName(), index, 1);
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
            Main.pizzas.add(new Barbecue(nameField.getText(), Integer.parseInt(sizeField.getText()),
                    sauceField.getText(), new Cheese(mozzarella1, cheddar1, blueCheese1, parmesan1), meatField.getText(), onionField.getText()));
            Object[] obj = {Barbecue.class, Main.pizzas.get(Main.pizzas.size()-1).getName()};
            Main.tableModel.addRow(obj);
            frame.setVisible(false);
        }
    }
}
