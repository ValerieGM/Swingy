package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static model.Universal.logTextArea;

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

    private JButton buttonFlee = new JButton("Flee");

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

    private JButton buttonChange = new JButton("Console View");
    private JButton buttonOkEncounter = new JButton("OK");

    //Scrolling Capabilities
    private JScrollPane scrollPane;

    //Images To Be Used
    private Image scaledImage;
    private BufferedImage lilithImage;
    private BufferedImage draculaImage;
    private BufferedImage archangelImage;
    private BufferedImage seraphImage;
    private BufferedImage cherubImage;

    public GUI() {
        setTitle("Swingy");
//        setBackground(Color.black);
        setSize(800, 800);
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

        panelMenu.add(scrollPane);
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

//        ((GridLayout)panelMap.getLayout()).setVgap(0);
        ((FlowLayout)panelMap.getLayout()).setVgap(0);

        //Reading Image Files::  BufferedImage img = ImageIO.read(url);
        BufferedImage mainImage = controller.ImageException.imageUpload("src/main/java/view/images/game.png");
        archangelImage = controller.ImageException.imageUpload("src/main/java/view/images/archangel.png");
        seraphImage = controller.ImageException.imageUpload("src/main/java/view/images/seraph.png");
        cherubImage = controller.ImageException.imageUpload("src/main/java/view/images/cherub.png");
        draculaImage = controller.ImageException.imageUpload("src/main/java/view/images/dracula.png");
        lilithImage = controller.ImageException.imageUpload("src/main/java/view/images/lilith.png");
        scaledImage = mainImage.getScaledInstance(window.getWidth() / 2, window.getHeight(),Image.SCALE_DEFAULT );
        picLabel = new JLabel(new ImageIcon(scaledImage));

        //Panel Initialisations
        panelMenu.setPreferredSize(new Dimension(window.getWidth() / 4, window.getHeight()));
        panelMenu.setBackground(Color.DARK_GRAY);
        panelMap.setPreferredSize(new Dimension(window.getWidth() / 2, window.getHeight()));
        panelMap.setBackground(Color.DARK_GRAY);
        panelGrid.setPreferredSize(new Dimension(window.getWidth() / 2, window.getHeight()));
        panelGrid.setBackground(Color.DARK_GRAY);

        listCreate.addItem("Archangel");
        listCreate.addItem("Seraph");
        listCreate.addItem("Cherub");
//        listCreate.addActionListener(new listListener());
        listCreate.setPreferredSize(new Dimension(180, 50));
        listSelect.setPreferredSize(new Dimension(180, 50));

        //Button Initialisations
        buttonCreate.setPreferredSize(new Dimension(180, 90));
//        buttonCreate.addActionListener(new createListener());
        buttonSelect.setPreferredSize(new Dimension(180, 90));
//        buttonSelect.addActionListener(new selectListener());
        buttonChange.setPreferredSize(new Dimension(180, 90));
//        buttonChange.addActionListener(new changeListener());

        buttonCreate2.setPreferredSize(new Dimension(180, 30));
//        buttonCreate2.addActionListener(new angelCreateListener());
        buttonCreate2.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCreate2.setMaximumSize(getSize());

        buttonOkEncounter.setPreferredSize(new Dimension(180, 30));
//        buttonOkEncounter.addActionListener(new angelEncounterListener());
        buttonOkEncounter.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonOkEncounter.setMaximumSize(getSize());

        buttonFlee.setPreferredSize(new Dimension(180, 30));
//        buttonFlee.addActionListener(new fleeListener());
        buttonFlee.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFlee.setMaximumSize(getSize());

        buttonDelete.setPreferredSize(new Dimension(180, 30));
//        buttonDelete.addActionListener(new deleteAngelListener());
        buttonDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDelete.setMaximumSize(getSize());
        buttonDelete.setForeground(Color.red);

        buttonCancel.setPreferredSize(new Dimension(180, 30));
//        buttonCancel.addActionListener(new cancelListener());
        buttonCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCancel.setMaximumSize(getSize());

        buttonCancelMain.setPreferredSize(new Dimension(180, 30));
//        buttonCancelMain.addActionListener(new cancelMainListener());
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
        panelSelect.add(buttonFlee);
        panelSelect.add(buttonDelete);
        panelSelect.setVisible(false);
        listSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}