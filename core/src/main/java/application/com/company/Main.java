package application.com.company;

import application.pizza.*;
import application.pluginsInterface.Archivator;
import application.pluginsInterface.XMLtoJson;
import application.serialization.Serializer;
import java.util.Optional;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public JPanel panel = new JPanel();
    ArrayList<Class> classes = new ArrayList();
    public JComboBox comboBox;
    public static DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);
    public static ArrayList<Pizza> pizzas = new ArrayList<>();
    ButtonGroup group = new ButtonGroup();
    ButtonGroup group2 = new ButtonGroup();
    JRadioButton binaryBtn = new JRadioButton("Бинарная");
    JRadioButton xmlBtn = new JRadioButton("XML");
    JRadioButton Btn = new JRadioButton("Текстовый формат");

    private static XMLtoJson xmlToJson;
    private static Archivator archivator;

    int counter = 0;
    Class<?>[] availablePlugins = null;
    JComboBox plugComboBox = new JComboBox();

    public Main(){

        Path path = Paths.get("plugins");
        ModuleFinder pluginsFinder = ModuleFinder.of(path);
        List<String> plugins = pluginsFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());

        Configuration pluginsConfiguration = ModuleLayer
                .boot()
                .configuration()
                .resolve(pluginsFinder, ModuleFinder.of(), plugins);

        ModuleLayer moduleLayer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(
                        pluginsConfiguration,
                        ClassLoader.getSystemClassLoader()
                );

        Optional<XMLtoJson> xmlService = XMLtoJson.getService(moduleLayer);
        Optional<Archivator> archivatorService = Archivator.getService(moduleLayer);
        xmlService.ifPresent(Main::setXmlToJsonMapper);
        archivatorService.ifPresent(Main::setDataArchivator);

        classes.add(MeatPizza.class);
        classes.add(Barbecue.class);
        classes.add(VegetarianPizza.class);
        classes.add(Vegetable.class);
        classes.add(SeafoodPizza.class);
        classes.add(ShrimpPizza.class);

        System.out.println(moduleLayer);


        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,500));
        //frame.pack();
        frame.setLocationRelativeTo(null);
        Container content = new Container();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));


        group.add(binaryBtn);
        group.add(xmlBtn);
        group.add(Btn);


        JButton btnCreate = new JButton("Создать");
        ActionListener actionListener = new CreateActionListener();
        btnCreate.addActionListener(actionListener);
        JButton btnDelete = new JButton("Удалить");
        btnDelete.addActionListener(new DeleteActionListener());
        JButton btnEdit = new JButton("Редактировать");
        btnEdit.addActionListener(new EditActionListener());
        JButton btnSer = new JButton("Сериализация");
        ActionListener serListener = new SerActionListener();
        btnSer.addActionListener(serListener);
        JButton btnDes = new JButton("Десериализация");
        btnDes.addActionListener(new DesActionListener());

        Container container = new Container();
        container.setLayout(new FlowLayout());
        container.add(btnCreate);
        container.add(btnDelete);
        container.add(btnEdit);
        container.add(btnSer);
        container.add(btnDes);
        content.add(container);

        Container container1 = new Container();
        container1.setLayout(new FlowLayout());
        container1.add(Btn);
        container1.add(binaryBtn);
        container1.add(xmlBtn);
        content.add(container1);
        xmlBtn.setSelected(true);

        Container container2 = new Container();
        container2.setLayout(new FlowLayout());

        content.add(plugComboBox);


        tableModel.addColumn("Class");
        tableModel.addColumn("Name");
        JScrollPane scrollPane = new JScrollPane(table);

        content.add(scrollPane);
        table.setRowHeight(30);

        comboBox = new JComboBox(classes.toArray());
        content.add(comboBox);
        frame.getContentPane().add(content);
        tableModel.setRowCount(0);
        frame.pack();
    }

    public static void setDataArchivator(Archivator dataArchivator) {
        archivator = dataArchivator;
    }

    public static void setXmlToJsonMapper(XMLtoJson xmlToJsonMapper) {
        xmlToJson = xmlToJsonMapper;
    }

    public class SerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(binaryBtn.isSelected()){
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(pizzas);
                    objectOutputStream.close();
                    FileInputStream fin = new FileInputStream("file.txt");
                    FileOutputStream fout = new FileOutputStream("list1.zip");
                    archivator.archivate(fin,fout);
                }catch (IOException ee) {
                    ee.printStackTrace();
                }
            }else if(xmlBtn.isSelected()){
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("file.xml");
                    XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
                    xmlEncoder.writeObject(pizzas);
                    xmlEncoder.close();
                    FileInputStream fin = new FileInputStream("file.xml");
                    FileOutputStream fout = new FileOutputStream("file.JSON");
                    xmlToJson.convert(fin,fout);
                    FileOutputStream fout1 = new FileOutputStream("list2.zip");
                    archivator.archivate(fin, fout1);
                }catch (IOException ee) {
                    ee.printStackTrace();
                }
            }else if(Btn.isSelected()){
                try {
                    Serializer tex = new Serializer();
                    tex.serialise(pizzas,"file2.txt");
                    FileInputStream fin = new FileInputStream("file2.txt");
                    FileOutputStream fout = new FileOutputStream("list3.zip");
                    archivator.archivate(fin, fout);
                }catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
    }

    public class DesActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(binaryBtn.isSelected()){
                try {
                    FileInputStream fileInputStream = new FileInputStream("file.txt");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    pizzas = (ArrayList) objectInputStream.readObject();
                    objectInputStream.close();
                }catch (IOException | ClassNotFoundException ee ) {
                    ee.printStackTrace();
                }
            }else if(xmlBtn.isSelected()){
                try {
                    FileInputStream fileInputStream = new FileInputStream("file.xml");
                    XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
                    pizzas = (ArrayList) xmlDecoder.readObject();
                    xmlDecoder.close();
                }catch (IOException ee) {
                    ee.printStackTrace();
                }
            }else if(Btn.isSelected()){
                try {
                    Serializer tex = new Serializer();
                    pizzas = tex.deserialise("file2.txt");
                }catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
            for(int i = counter; i < pizzas.size(); i++){
                Object[] obj = {pizzas.get(i).getClass(), Main.pizzas.get(i).getName()};
                Main.tableModel.addRow(obj);
            }
        }
    }

    public class CreateActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(comboBox.getSelectedItem() == MeatPizza.class){
                new MeatPizzaFrame();
            }else if(comboBox.getSelectedItem() == Barbecue.class){
                new BarbecueFrame();
            }else if(comboBox.getSelectedItem() == VegetarianPizza.class){
            new VegetarianFrame();
            }else if(comboBox.getSelectedItem() == Vegetable.class){
            new VegetableFrame();
            }else if(comboBox.getSelectedItem() == SeafoodPizza.class){
                new SeafoodPizzaFrame();
            }else if(comboBox.getSelectedItem() == ShrimpPizza.class){
                new ShrimpPizzaFrame();
            }
        }
    }

    public class DeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = table.getSelectedRow();
            pizzas.remove(index);
            tableModel.removeRow(index);
        }
    }

    public class EditActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = table.getSelectedRow();
            Object value = table.getModel().getValueAt(index, 0);
            if(value == MeatPizza.class){
                new MeatPizzaFrame((MeatPizza) pizzas.get(index), index);
            }else if(value == VegetarianPizza.class){
                new VegetarianFrame((VegetarianPizza) pizzas.get(index), index);
            }else if(value == Vegetable.class){
                new VegetableFrame((Vegetable) pizzas.get(index), index);
            }else if(value == Barbecue.class){
                new BarbecueFrame((Barbecue) pizzas.get(index), index);
            }else if(value == ShrimpPizza.class){
                new ShrimpPizzaFrame((ShrimpPizza) pizzas.get(index), index);
            }else if(value == SeafoodPizza.class){
                new SeafoodPizzaFrame((SeafoodPizza) pizzas.get(index), index);
            }
        }
    }

    public static Class<?>[] getClassesArray() throws IOException {
        ArrayList<Class<?>> list = new ArrayList<Class<?>>(0);
        for(File f : getPackageContent("plugins")) {
            String name = f.getName();
            if (name.contains(".")) {
                name = name.substring(0, name.lastIndexOf('.'));
            }
            try {
                Class<?> cl = Class.forName("plugins." + name);
                list.add(cl);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list.toArray(new Class<?>[]{});
    }

    private static File[] getPackageContent(String packageName) throws IOException {
        ArrayList<File> list = new ArrayList<File>(0);
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            File dir = new File(url.getFile());

            Collections.addAll(list, Objects.requireNonNull(dir.listFiles()));
        }
        return list.toArray(new File[]{});
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

}
