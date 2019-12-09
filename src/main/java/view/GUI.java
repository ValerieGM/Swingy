package view;

import controller.EntityFactory;
import controller.MapFactory;
import jdk.nashorn.internal.scripts.JO;
import model.database.Database;
import model.entities.Entity;
import model.entities.angels.Angel;
import model.entities.angels.Cherub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.List;

import static model.Universal.*;

public class  GUI  extends JFrame {

    //Grid Layout
    private GridLayout grid = new GridLayout();

    //Hero Creation And Selection
    private JComboBox<String> listCreate = new JComboBox<>();
    private JComboBox<String> listSelect = new JComboBox<>();

    private JLabel labelCreate = new JLabel("Create Angel");
    private JLabel labelSelect = new JLabel("Select Angel");
    private JLabel labelStats = new JLabel("Statistics");
    private JLabel labelInput = new JLabel("Name");
    private JLabel labelAction = new JLabel("Action");
    private JLabel picLabel = new JLabel();

    private JPanel panelCreate = new JPanel();
    private JPanel panelSelect = new JPanel();

    private JButton buttonCreate = new JButton("Create New Angel");
    private JButton buttonSelect = new JButton("Select Existing Angel");
    private JButton buttonCreate2 = new JButton("Create");
    private JButton buttonDelete = new JButton("Delete");

    private JButton buttonCancel = new JButton("Cancel");
    private JButton buttonCancelMain = new JButton("Cancel");

    //Interactions
    private JPanel panelEncounter = new JPanel();
    private JPanel panelEncounter2 = new JPanel();

    private JButton buttonStart = new JButton("Start");

    //Generalized Panels
    private JPanel panelMain = new JPanel();
    private JPanel panelMenu = new JPanel();
    private JPanel panelMap = new JPanel();
    private JPanel panelGrid = new JPanel();
    private JPanel panelStats = new JPanel();
    private JPanel panelInput = new JPanel();

    //Text Fields
    private JTextField input = new JTextField();

    //Generalized Button Options
    private JRadioButton rYes = new JRadioButton("Yes");
    private JRadioButton rNo = new JRadioButton("No");
    private JRadioButton rFlee = new JRadioButton("Flee");
    private JRadioButton rBattle = new JRadioButton("Battle");

    private JButton buttonChange = new JButton("Console View");
    private JButton buttonOkEncounter = new JButton("OK");
    private ButtonGroup buttonAllEncounter = new ButtonGroup();

    //Scrolling Capabilities
    private JScrollPane scrollPane;

    //Images To Be Used
    private Image scaledImage;
    private BufferedImage lilithImage;
    private BufferedImage draculaImage;
    private BufferedImage archangelImage;
    private BufferedImage seraphImage;
    private BufferedImage cherubImage;
    private BufferedImage battlefield;

    public GUI() {
        setTitle("Swingy");
//        setBackground(Color.black);
        setSize(1300, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelMain.setLayout(new BorderLayout());
        //initialise components
        initialise(this);
        System.out.println("Welcome to 42 Heaven (Or Hell)!!!!!!!");
        begin(this);
        this.setVisible(true);
    }

    private void begin(GUI window){
        panelMenu.add(buttonCreate);
        panelMenu.add(buttonSelect);
        panelMenu.add(buttonChange);

        panelMenu.add(panelStats);
        panelMenu.add(panelCreate);
        panelMenu.add(panelSelect);
        panelMenu.add(panelEncounter);

//        panelMenu.add(scrollPane);
        panelMenu.add(picLabel);
        panelMenu.add(panelGrid);
        panelMenu.add(buttonCancelMain);

        panelMain.add(panelMenu, BorderLayout.EAST);
        panelMain.add(panelMenu, BorderLayout.WEST);
        panelMain.add(panelMenu, BorderLayout.CENTER);

        window.setContentPane(panelMain);
    }

    private void initialise(GUI window) {
        logTextArea = new JTextArea("", 40, 25);
        logTextArea.setEditable(false);
        scrollPane = new JScrollPane(logTextArea);

        ((FlowLayout)panelMap.getLayout()).setVgap(0);

        //Reading Image Files::  BufferedImage img = ImageIO.read(url);
        BufferedImage mainImage = controller.ImageException.imageUpload("src/main/java/view/images/game.png");
        archangelImage = controller.ImageException.imageUpload("src/main/java/view/images/archangel.png");
        seraphImage = controller.ImageException.imageUpload("src/main/java/view/images/seraph.png");
        cherubImage = controller.ImageException.imageUpload("src/main/java/view/images/cherub.png");
        draculaImage = controller.ImageException.imageUpload("src/main/java/view/images/dracula.png");
        lilithImage = controller.ImageException.imageUpload("src/main/java/view/images/lilith.png");
        battlefield = controller.ImageException.imageUpload("src/main/java/view/images/battlefield.png");
        scaledImage = mainImage.getScaledInstance(window.getWidth() / 2 , window.getHeight(),Image.SCALE_DEFAULT );
        picLabel = new JLabel(new ImageIcon(scaledImage));

        //Panel Initialisations
        panelMenu.setPreferredSize(new Dimension(window.getWidth() / 4, window.getHeight()));
        panelMenu.setBackground(Color.gray
        );
        panelMap.setPreferredSize(new Dimension(window.getWidth() / 2, window.getHeight()));
        panelMap.setBackground(Color.gray);
        panelGrid.setPreferredSize(new Dimension(window.getWidth() / 2, window.getHeight()));
        panelGrid.setBackground(Color.gray);

        listCreate.addItem("Archangel");
        listCreate.addItem("Seraph");
        listCreate.addItem("Cherub");
        listCreate.addActionListener(new listListener());
        listCreate.setPreferredSize(new Dimension(180, 50));
        listSelect.setPreferredSize(new Dimension(180, 50));

        //Button Initialisations
        buttonCreate.setPreferredSize(new Dimension(180, 90));
        buttonCreate.addActionListener(new createListener());
        buttonSelect.setPreferredSize(new Dimension(180, 90));
        buttonSelect.addActionListener(new selectListener());
        buttonChange.setPreferredSize(new Dimension(180, 90));
        buttonChange.addActionListener(new changeListener());

        buttonCreate2.setPreferredSize(new Dimension(180, 30));
        buttonCreate2.addActionListener(new angelCreateListener());
        buttonCreate2.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCreate2.setMaximumSize(getSize());

        buttonOkEncounter.setPreferredSize(new Dimension(180, 30));
//        buttonOkEncounter.addActionListener(new angelEncounterListener());
        buttonOkEncounter.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonOkEncounter.setMaximumSize(getSize());

        buttonStart.setPreferredSize(new Dimension(180, 30));
        buttonStart.addActionListener(new startListener());
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonStart.setMaximumSize(getSize());

        buttonDelete.setPreferredSize(new Dimension(180, 30));
        buttonDelete.addActionListener(new deleteListener());
        buttonDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDelete.setMaximumSize(getSize());
        buttonDelete.setForeground(Color.red);

        buttonCancel.setPreferredSize(new Dimension(180, 30));
        buttonCancel.addActionListener(new cancelListener());
        buttonCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCancel.setMaximumSize(getSize());

        buttonCancelMain.setPreferredSize(new Dimension(180, 30));
        buttonCancelMain.addActionListener(new cancelMainListener());
        buttonCancelMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCancel.setMaximumSize(getSize());

        labelCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAction.setAlignmentX(Component.CENTER_ALIGNMENT);
        input.setPreferredSize(new Dimension(160, 55));

        Box box = Box.createVerticalBox();
        panelStats.setPreferredSize(new Dimension(250, 150));
        labelStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(labelStats);
        panelStats.add(box);
        panelStats.setVisible(false);
        panelInput.add(labelInput);
        panelInput.add(input);

        panelCreate.setPreferredSize(new Dimension(300, 200));
        panelCreate.setLayout(new BoxLayout(panelCreate, BoxLayout.Y_AXIS));
        panelCreate.add(labelCreate);
        panelCreate.add(listCreate);
        panelCreate.add(panelInput);
        panelCreate.add(buttonCreate2);
        panelCreate.setVisible(false);
        listCreate.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelSelect.setPreferredSize(new Dimension(300, 200));
        panelSelect.setLayout(new BoxLayout(panelSelect, BoxLayout.Y_AXIS));
        panelSelect.add(labelSelect);
        panelSelect.add(listSelect);
        panelSelect.add(buttonStart);
        panelSelect.add(buttonDelete);
        panelSelect.setVisible(false);
        listSelect.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelEncounter.setPreferredSize(new Dimension(300, 100));
        panelEncounter.setLayout(new BoxLayout(panelEncounter, BoxLayout.Y_AXIS));
        panelEncounter2.setLayout(new BoxLayout(panelEncounter2, BoxLayout.X_AXIS));
        buttonAllEncounter.add(rBattle);
        buttonAllEncounter.add(rFlee);
        rBattle.setSelected(true);
        panelEncounter2.add(rBattle);
        panelEncounter2.add(new Box.Filler(new Dimension(50, 0), new Dimension(50, 0), new Dimension(50, 0)));
        panelEncounter2.add(rFlee);
        panelEncounter.add(labelAction);
        panelEncounter.add(new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(0, 10)));
        panelEncounter.add(panelEncounter2);
        panelEncounter.add(new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(0, 10)));
        panelEncounter.add(buttonOkEncounter);

        panelEncounter.setVisible(false);
        panelGrid.setLayout(grid);
        panelGrid.setVisible(false);
        buttonCancelMain.setVisible(false);
    }

    //Map Controller
    private void map() {
        int v = 0;
        while (v < squareMap.getMapSize()) {
            int t = 0;
            while (t < squareMap.getMapSize()) {
                int x = t;
                int y = v;
                int position = squareMap.getMap()[t][v];

                final JPanel block = new JPanel();
                ((FlowLayout)block.getLayout()).setHgap(0);
                ((FlowLayout)block.getLayout()).setVgap(0);
                block.setBorder(BorderFactory.createLineBorder(Color.darkGray));
                if (position == 1) {
                    block.setBackground(new Color(70, 196, 222));
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (angel.getType() == "Archangel")
                                scaledImage = archangelImage.getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_DEFAULT);
                            else if (angel.getType() == "Seraph")
                                scaledImage = seraphImage.getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_DEFAULT);
                            else if (angel.getType() == "Cherub")
                                scaledImage = cherubImage.getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_DEFAULT);

                            if (scaledImage != null) {
                                JLabel pic = new JLabel(new ImageIcon(scaledImage));
                                block.add(pic);
                                block.setSize(block.getWidth(), block.getHeight());
//                                pack();
                            }
                            else
                                System.out.println("Oops:: Angel Image Unable To Load");
                        }
                    });
                }
                else if (position == 2) {
                    block.setBackground(new Color(72, 61, 139));
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            scaledImage = draculaImage.getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_DEFAULT);
                            if (scaledImage != null) {
                                JLabel pic = new JLabel(new ImageIcon(scaledImage));
                                block.add(pic);
//                                pack();
                            }
                            else
                                System.out.println("Oops:: Angel Image Unable To Load");
                        }
                    });
                }
                else if (position == 8){
                    block.setBackground(new Color(143, 188, 143));
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            scaledImage = battlefield.getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_DEFAULT);
                            if (scaledImage != null) {
                                JLabel pic = new JLabel(new ImageIcon(scaledImage));
                                block.add(pic);
//                                pack();
                            }
                            else
                                System.out.println("Oops:: Angel Image Unable To Load");
                        }
                    });
                }
                else
                    block.setBackground(new Color(230, 230, 250));
                panelGrid.add(block);
                t++;
            }
            v++;
        }
    }

    //Action Listeners To Be Put Into their Own Class Within The Controller

    private class listListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int v =listCreate.getSelectedIndex();
//            Display.displayAngel();
        }
    }

    private class createListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelSelect.remove(buttonCancel);
            panelCreate.add(buttonCancel);
            buttonCreate.setVisible(false);
            buttonSelect.setVisible(false);
            buttonChange.setVisible(false);
            panelCreate.setVisible(true);

//            Display.displayAngel();
        }
    }

    private class selectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            if (!bIsAngel)
//                logTextArea.setText("Angels Don't Exist");
//            else {
                panelCreate.remove(buttonCancel);
                panelSelect.add(buttonCancel);
                buttonCreate.setVisible(false);
                buttonSelect.setVisible(false);
                buttonChange.setVisible(false);
                listSelect.removeAllItems();
                try {
                    List<Angel> angelList = Database.getInstance().extractDatabase();
                    for (Angel a : angelList) {
                        listSelect.addItem(a.getName());
                    }
                    panelSelect.setVisible(true);
                    bIsAngel = false;
                } catch (SQLException error) {
                    System.out.println("selectListener:: " + error.getMessage());
                    System.exit(0);
                }

//            }
        }
    }

    private class changeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                GUI.this.dispose();
                try {
                    Console.begin();
                } catch (SQLException error) {
                    System.out.println("ChangeListener:: " + error);
                }
            }
    }

    private class angelCreateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selection = listSelect.getSelectedIndex();
            Entity entity = null;
            if (selection == 0)
                entity = EntityFactory.newAngel(input.getText(), "Archangel");
            else if (selection == 1)
                entity = EntityFactory.newAngel(input.getText(), "Seraph");
            else if (selection == 2)
                entity = EntityFactory.newAngel(input.getText(), "Cherub");

            if (entity != null) {
                try {
                    Database.getInstance().insertAngel((Angel) entity);
                } catch (SQLException ex) {
                    System.out.println("cancelListener:: " + ex.getMessage());
                    System.exit(0);
                }
                panelCreate.setVisible(false);
                buttonCreate.setVisible(true);
                buttonSelect.setVisible(true);
                buttonChange.setVisible(true);
            }
        }
    }

    private class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelSelect.setVisible(false);
            picLabel.setVisible(false);
            try {
                angel = Database.getInstance().angelDetails(listSelect.getSelectedItem().toString());
            } catch (SQLException ex) {
                System.out.println("startListener:: " + ex.getMessage());
                System.exit(0);
            }
            squareMap = MapFactory.generateMap(angel);
            System.out.println(angel.getName() + " is here!!!");
            grid.setRows(squareMap.getMapSize());
            grid.setColumns(squareMap.getMapSize());
            grid.setHgap(-1);
            grid.setVgap(-1);
            panelGrid.setVisible(true);
            picLabel.setVisible(false);
            labelStats.setText("<html> "+ "Name:: " + angel.getName() + "</br>" +
                    "Type" + angel.getType() + "</br>" +
                    "Level" + angel.getLevel() + "</br>" +
                    "XP" + angel.getX() + "</br>" +
                    "Attack" + angel.getAttack() + "</br>" +
                    "Defense" + angel.getHp() + "</br>" +
                    "HP" + angel.getHp() + " </html>");
            panelStats.setVisible(true);
            buttonCancelMain.setVisible(true);
            //Map Functionality
            map();
        }
    }

    private class deleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.YES_OPTION;
            int selection = JOptionPane.showConfirmDialog(GUI.this, "Are you sure?!?!", "Death", option);

            if (selection == JOptionPane.YES_OPTION) {
                try {
                    Database.getInstance().deleteAngel(listCreate.getSelectedItem().toString());
                    Database.getInstance().printDatabase();
                } catch (SQLException ex) {
                    System.out.println("deleteListener:: " + ex.getMessage());
                }
                if (nbAngel == 0) {
                    panelSelect.setVisible(false);
                    buttonCreate.setVisible(true);
                    buttonSelect.setVisible(true);
                    buttonChange.setVisible(true);
                    bIsAngel = false;
                } else {
                    listSelect.removeAllItems();
                    try {
                        List<Angel> angelList = Database.getInstance().extractDatabase();
                        for (Angel a : angelList) {
                            listSelect.addItem(a.getName());
                        }
                    } catch (SQLException ex) {
                        System.out.println("innerDeleteListener:: " + ex.getMessage());
                        System.exit(0);
                    }
                }
            }
        }
    }

    private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelEncounter.setVisible(false);
            panelCreate.setVisible(false);
            panelSelect.setVisible(false);
            buttonCreate.setVisible(true);
            buttonSelect.setVisible(true);
            buttonChange.setVisible(true);
        }
    }

    private class cancelMainListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelStats.setVisible(false);
            panelEncounter.setVisible(false);
            panelGrid.removeAll();
            panelGrid.setVisible(false);
            picLabel.setVisible(true);

            buttonCancelMain.setVisible(false);
            buttonCancelMain.setVisible(true);
            buttonSelect.setVisible(true);
            buttonChange.setVisible(true);
        }
    }
}