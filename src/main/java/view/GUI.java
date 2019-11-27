package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static controller.Universal.logTextArea;

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

    //Text Fields
    private JTextField input = new JTextField();

    //Generalized Button Options
    private JRadioButton rYes = new JRadioButton("Yes");
    private JRadioButton rNo = new JRadioButton("No");

    private JButton buttonChange = new JButton("Console View");
    private JButton buttonOk = new JButton("OK");

    //Scrolling Capabilities
    private JScrollPane scrollPane;

    //Images To Be Used
    private Image scaled;
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
        ///////////////////////
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
        logTextArea = new JTextArea("", 50, 25);
        logTextArea.setEditable(false);
        scrollPane = new JScrollPane(logTextArea);

//        ((GridLayout)panelMap.getLayout()).setVgap(0);
        ((FlowLayout)panelMap.getLayout()).setVgap(0);

//        BufferedImage img = ImageIO.read(url);
//        BufferedImage mainImage = view.images;
    }
}