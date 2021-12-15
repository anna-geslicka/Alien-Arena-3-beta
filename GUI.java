import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    Game game = new Game();
    private final Color mainColor = new Color(0x00FF41);
    private final Font buttonFont = new Font("Lucida Console", Font.PLAIN, 30);
    private final Font regularFont = new Font("Lucida Console", Font.PLAIN, 15);
    private JPanel startPagePanel, startButtonPanel, mainTextPanel, choiceButtonPanel;
    private JButton startButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private String position;
    private ArrayList<String> currFight = null;
    private int currFightLineCounter = 0;

    public GUI() {
        setMainFrame();
        setStartPanel();

        this.pack();
        this.setVisible(true);
    }

    private void setMainFrame() {
        this.setTitle("Alien Arena 3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 600));
        this.getContentPane().setBackground(Color.BLACK);

        ImageIcon icon = new ImageIcon("alien.png");
        this.setIconImage(icon.getImage());
    }

    private void setStartPanel() {
        startPagePanel = new JPanel();
        startPagePanel.setBounds(100, 100, 600, 150);
        startPagePanel.setLayout(new BorderLayout());
        startPagePanel.setBackground(Color.BLACK);

        JLabel gameTitle = new JLabel();
        gameTitle.setText("ALIEN ARENA 3");
        gameTitle.setForeground(mainColor);
        gameTitle.setFont(new Font("Lucida Console", Font.BOLD, 70));
        startPagePanel.add(gameTitle);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.BLACK);

        startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(mainColor);
        startButton.setFont(buttonFont);
        startButton.addActionListener(this);
        startButtonPanel.add(startButton);

        this.add(startPagePanel);
        this.add(startButtonPanel);
    }


    private void setGameScreen() {
        position = "intro";
        startPagePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.BLACK);

        mainTextArea = new JTextArea(game.intro());
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(mainColor);
        mainTextArea.setFont(regularFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        startButtonPanel.setBackground(Color.BLACK);

        choice1 = new JButton(">>");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(mainColor);
        choice1.setFont(regularFont);
        choice1.setActionCommand("1");
        choiceButtonPanel.add(choice1);
        choice1.addActionListener(this);

        choice2 = new JButton();
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(mainColor);
        choice2.setFont(regularFont);
        choice2.setActionCommand("2");
        choiceButtonPanel.add(choice2);
        choice2.addActionListener(this);

        choice3 = new JButton();
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(mainColor);
        choice3.setFont(regularFont);
        choice3.setActionCommand("3");
        choiceButtonPanel.add(choice3);
        choice3.addActionListener(this);

        choice4 = new JButton("Exit");
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(mainColor);
        choice4.setFont(regularFont);
        choiceButtonPanel.add(choice4);
        choice4.addActionListener(this);

        this.add(mainTextPanel);
        this.add(choiceButtonPanel);
    }

    private void classChoice() {
        position = "classChoice";
        mainTextArea.setText(game.classChoice());
        choice1.setText("Yes");
        choice2.setText("No");
        choice3.setText("");
    }

    private void showClass(String message) {
        position = "showClass";
        mainTextArea.setText(message);
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }

    private void showStats() {
        position = "showStats";
        mainTextArea.setText(game.showStats());
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }


    private void capitalCity() {
        position = "capitalCity";
        mainTextArea.setText(game.capitalCity());
        choice1.setText("Visit trade platform");
        choice2.setText("Go to your inventory");
        choice3.setText("Fight on the Arena");
    }

    private void tradePlatform() {
        position = "tradePlatform";
        mainTextArea.setText(game.tradePlatform());
        choice1.setText("Mickey's Rifles");
        choice2.setText("Robotic Surgery Centre");
        choice3.setText("Leave");
    }

    private void mickeysRifles(){
        position = "mickeysRifles";
        mainTextArea.setText(game.mickeysRifles());
        choice1.setText("Upgrade weapon (200 GC)");
        choice2.setText("Buy First Aid Kit (20 GC)");
        choice3.setText("Leave");
    }

    private void surgeryCentre(){
        position = "surgeryCentre";
        mainTextArea.setText(game.surgeryCentre());
        choice1.setText("Install Robotic Arms (200 GC)");
        choice2.setText("Attach Life Sustaining Systems");
        choice3.setText("Leave");
    }

    private void upgradeArms(){
        position = "upgradeArms";
        mainTextArea.setText(game.upgradeArms());
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }

    private void attachSystems(){
        position = "attachSystems";
        mainTextArea.setText(game.attachSystems());
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }

    private void inventoryGate(){
        if (game.havePotion())
            inventoryAid();
        else
            inventoryNoAid();
    }

    private void inventoryNoAid() {
        position = "inventoryNoAid";
        mainTextArea.setText(game.inventoryNoAid());
        choice1.setText("View your stats");
        choice2.setText("Leave");
        choice3.setText("");
    }
    private void inventoryAid() {
        position = "inventoryAid";
        mainTextArea.setText(game.inventoryAid());
        choice1.setText("Yes");
        choice2.setText("No");
        choice3.setText("View your stats");
    }

    private void aidKitUsed(){
        position = "aidKitUsed";
        mainTextArea.setText(game.drinkPotion());
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }

    private void upgradeWeapon(){
        position = "upgradeWeapon";
        mainTextArea.setText(game.upgradeWeapon());
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }

    private void buyAidKit(){
        position = "buyAidKit";
        mainTextArea.setText(game.buyPotion());
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }
    private void arena(){
        position = "arena";
        mainTextArea.setText(game.arena());
        choice1.setText("Void Parasite");
        choice2.setText("Mind Eater");
        choice3.setText("Xenomorph");
    }

    private void fightVoidParasite(){
        position = "voidParasite";
        mainTextArea.setText(game.fightVoidParasite());
        choice1.setText("Yes");
        choice2.setText("No");
        choice3.setText("");
    }

    private void voidParasiteYes(){
        position = "voidParasiteYes";
        mainTextArea.setText("Bring it on!");
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
        currFight = game.fight("Void Parasite");
    }

    private void voidParasiteNo(){
        position = "voidParasiteNo";
        mainTextArea.setText("Ooops, you must have left your glasses in the Spaceship! Maybe you'll try later.");
        choice1.setText(">>");
        choice2.setText("");
        choice3.setText("");
    }

    private void fightMindEater(){

    }

    private void fightXenomorph(){

    }

    private void fightGate(){
        mainTextArea.setText("");
        if (currFight != null)
            fight();
        else
            capitalCity();
    }

    private void fight(){
        position = "fight";
        String currFightLine;
        if (this.currFight.size() > this.currFightLineCounter) {
            currFightLine = this.currFight.get(this.currFightLineCounter);
            this.currFightLineCounter++;
            mainTextArea.append(currFightLine);
            choice1.setText(">>");
            choice2.setText("");
            choice3.setText("");
        }
        else
            this.currFight = null;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        if (e.getSource() == startButton)
            setGameScreen();
        else if (e.getSource() == choice4)
            System.exit(0);

        switch (position) {
            case "intro": {
                if ("1".equals(choice))
                    classChoice();
                break;
            }
            case "classChoice": {
                switch (choice) {
                    case "1":
                        showClass(game.setAvatar(2));
                        break;
                    case "2":
                        showClass(game.setAvatar(1));
                        break;
                }
            }
            break;
            case "voidParasiteNo":
            case "showClass":
            case "aidKitUsed": {
                if ("1".equals(choice))
                    capitalCity();
            }
            break;
            case "capitalCity": {
                switch (choice) {
                    case "1":
                        tradePlatform();
                        break;
                    case "2":
                        inventoryGate();
                        break;
                    case "3":
                        arena();
                }
            }
            break;
            case "tradePlatform": {
                switch (choice) {
                    case "1":
                        mickeysRifles();
                        break;
                    case "2":
                        surgeryCentre();
                        break;
                    case "3":
                        capitalCity();
                }
            }
            break;
            case "inventoryAid": {
                switch (choice) {
                    case "1":
                        aidKitUsed();
                        break;
                    case "2":
                        capitalCity();
                        break;
                    case "3":
                        showStats();
                        break;
                }
            }
            break;
            case "inventoryNoAid": {
                switch (choice) {
                    case "1":
                        showStats();
                        break;
                    case "2":
                        capitalCity();
                        break;
                }
            }
            break;
            case "mickeysRifles": {
                switch (choice) {
                    case "1": upgradeWeapon();
                        break;
                    case "2": buyAidKit();
                        break;
                    case "3": tradePlatform();
                        break;
                }
            }
            break;
            case "upgradeWeapon":
            case "buyAidKit": {
                if ("1".equals(choice))
                    mickeysRifles();
            }
            break;
            case "surgeryCentre": {
                switch (choice) {
                    case "1": upgradeArms();
                        break;
                    case "2": attachSystems();
                        break;
                    case "3": tradePlatform();
                        break;
                }
            }
            break;
            case "attachSystems":
            case "upgradeArms": {
                if ("1".equals(choice))
                    surgeryCentre();
            }
            break;
            case "showStats": {
                if ("1".equals(choice))
                    inventoryGate();
            }
            break;
            case "arena": {
                switch (choice) {
                    case "1": fightVoidParasite();
                        break;
                    case "2": fightMindEater();
                        break;
                    case "3": fightXenomorph();
                        break;
                }
            }
            break;
            case "voidParasite": {
                switch (choice) {
                    case "1": voidParasiteYes();
                        break;
                    case "2": voidParasiteNo();
                        break;
                }
            }
            break;
            case "fight" :
            case "voidParasiteYes": {
                if ("1".equals(choice))
                    fightGate();
            }
            break;
        }
    }
}