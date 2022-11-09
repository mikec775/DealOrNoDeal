package com.t00192959;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameGui {

    private static JFrame mainGame;
    private JPanel mainMenu;
    private static JPanel fullMenu;
    private JPanel mainTopMenu;
    private static JPanel game;
    static JPanel moneyList;
    static JPanel boxContainer;
    private JButton btnStart;
    private JButton btnHelp;
    private JButton btnExit;

    static ArrayList<JTextField> moneyHolder;
    static ArrayList<JTextField> moneyRandomHolder;

    public static void setBoxHolder(ArrayList<JButton> boxHolder) {
        GameGui.boxHolder = boxHolder;
    }

    public static JPanel getFullMenu() {
        return fullMenu;
    }

    public void setFullMenu(JPanel fullMenu) {
        this.fullMenu = fullMenu;
    }

    public static void setMoneyRandomHolder(ArrayList<JTextField> moneyRandomHolder) {
        GameGui.moneyRandomHolder = moneyRandomHolder;
    }

    public static ArrayList<JButton> getBoxHolder() {
        return boxHolder;
    }

    static ArrayList<JButton> boxHolder;

    public static ArrayList<JTextField> getMoneyHolder() {
        return moneyHolder;
    }

    public void setMoneyHolder(ArrayList<JTextField> moneyHolder) {
        GameGui.moneyHolder = moneyHolder;
    }

    public static JFrame getMainGame() {
        return mainGame;
    }

    public void setMainGame(JFrame mainGame) {
        GameGui.mainGame = mainGame;
    }
    public static JPanel getGame() {
        return game;
    }

    public void setGame(JPanel game) {
        GameGui.game = game;
    }

    public JPanel getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(JPanel mainMenu) {
        this.mainMenu = mainMenu;
    }

    public JPanel getMainTopMenu() {
        return mainTopMenu;
    }

    public void setMainTopMenu(JPanel mainTopMenu) {
        this.mainTopMenu = mainTopMenu;
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public void setBtnStart(JButton btnStart) {
        this.btnStart = btnStart;
    }

    public JButton getBtnHelp() {
        return btnHelp;
    }

    public void setBtnHelp(JButton btnHelp) {
        this.btnHelp = btnHelp;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
        this.btnExit = btnExit;
    }

    public GameGui(JFrame mainGame, JPanel mainMenu, JPanel mainTopMenu, JButton btnStart, JButton btnHelp, JButton btnExit) {

        setMainGame(mainGame);
        setMainMenu(mainMenu);
        setFullMenu(new JPanel());
        setGame(new JPanel());
        setMainTopMenu(mainTopMenu);
        setBtnStart(btnStart);
        setBtnExit(btnExit);
        setBtnHelp(btnHelp);
        setMoneyHolder(new ArrayList<>());
        setBoxHolder(new ArrayList<>());
        createGui();
        menuLogic();

    }

    private void menuLogic() {

        JButton btnStart = getBtnStart();
        JButton btnHelp = getBtnHelp();
        JButton btnExit = getBtnExit();

        btnExit.addActionListener(e -> System.exit(0));

        btnStart.addActionListener(e -> {

            hideUI();

            if(GameGui.getGame().isVisible() == false){

                GameGui.getGame().setVisible(true);
                try {
                    GameLogic.newGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else{

                try {
                    createGameGui(getGame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }

        });

        btnHelp.addActionListener(e -> {
            hideUI();

            JPanel jp = new JPanel();

            try {
                BufferedReader br = new BufferedReader(new FileReader("helptext.txt"));

                String helpText = "";

                helpText = br.readLine();

                JTextArea jta = new JTextArea(5,5);
                jta.setFont(new Font("Arial", Font.PLAIN, 20));
                jta.setPreferredSize(new Dimension(650,200));
                jta.append(helpText + "\n");
                jta.append(helpText + "\n");
                jta.append(helpText + "\n");
                jta.append(helpText + "\n");
                jta.append(helpText + "\n");
                jp.add(jta);

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            JButton jb = new JButton("Exit");
            jb.setPreferredSize(new Dimension(290,100));
            jb.setFont(new Font("Arial", Font.PLAIN, 20));
            jp.add(jb);
            jp.setBorder(new EmptyBorder(100,20,50,20));
            getMainGame().add(jp);

            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    getMainGame().remove(jp);
                    showUI();

                }
            });


        });


    }

    private void hideUI() {

       getFullMenu().setVisible(false);


    }

    static void hideGameUi(){

        getGame().setVisible(false);

    }


   static void showUI() {

        getFullMenu().setVisible(true);

    }


    static void createGameGui(JPanel game) throws IOException {

        JFrame jf = getMainGame();

        ImageIcon briefcase = new ImageIcon("resources/briefase.png");

        moneyList = new JPanel();
        moneyList.setPreferredSize(new Dimension(300,900));

        boxContainer = new JPanel();
        boxContainer.setPreferredSize(new Dimension(600,900));


        for (int i = 0; i < 27; i++) {

            JTextField jtf = new JTextField();
            jtf.setPreferredSize(new Dimension(200,20));
            jtf.setText("Money");
            jtf.setHorizontalAlignment(SwingConstants.RIGHT);

            if(i<14){
                jtf.setBackground(Color.blue);
            }else{
                jtf.setBackground(Color.red);
            }

            jtf.setForeground(Color.white);
            jtf.setEnabled(false);
            moneyHolder.add(jtf);
            moneyList.add(jtf);

        }

        for (int i = 0; i < 27; i++) {

            JButton jb = new JButton();
            jb.setPreferredSize(new Dimension(190,90));

            //https://stackoverflow.com/questions/19663009/overriding-button-background
            jb.setContentAreaFilled(false);

            jb.setBorderPainted(false);
            jb.setIcon(briefcase);
            jb.setText(String.valueOf(i+1));
            jb.setForeground(Color.white);
            jb.setHorizontalTextPosition(JButton.CENTER);
            boxHolder.add(jb);
            boxContainer.add(jb);

        }

        game.add(moneyList);
        game.add(boxContainer);
        jf.add(game);
        jf.setResizable(false);
        GameLogic gL = new GameLogic(getMoneyHolder());
        gL.boxLogic(getBoxHolder(), moneyRandomHolder);

    }

    public void createGui(){

        JFrame jf = getMainGame();
        jf.setSize(1000,1000);
        jf.setTitle("Deal Or No Deal");
        JPanel fullMenu = getFullMenu();

        createMenu(jf, fullMenu);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createMenu(JFrame jf, JPanel fullMenu){

        JLabel logo = new JLabel("DEAL OR NO DEAL");
        logo.setFont(new Font("Arial", Font.PLAIN, 80));

        JPanel menu = getMainMenu();
        JPanel topMenu = getMainTopMenu();

        BoxLayout bl = new BoxLayout(menu, BoxLayout.Y_AXIS);
        BoxLayout bl2 = new BoxLayout(topMenu, BoxLayout.X_AXIS);
        BoxLayout bl3 = new BoxLayout(jf, BoxLayout.Y_AXIS);

        fullMenu.setSize(new Dimension(1000,1000));

        menu.setPreferredSize(new Dimension(700,500));
        topMenu.setSize(new Dimension(1000,200));

        JButton start = getBtnStart();
        JButton help = getBtnHelp();
        JButton exit = getBtnExit();

        start.setText("Start");
        help.setText("Help");
        exit.setText("Exit");

        Font btnFont = new Font("Arial", Font.PLAIN, 30);
        Dimension btnSize = new Dimension(300,100);

        start.setPreferredSize(btnSize);
        help.setPreferredSize(btnSize);
        exit.setPreferredSize(btnSize);

        start.setFont(btnFont);
        help.setFont(btnFont);
        exit.setFont(btnFont);

        topMenu.setBorder(new EmptyBorder(100,20,50,20));
        menu.setBorder(new EmptyBorder(100,400,100,400));

        topMenu.add(logo);
        menu.add(start);
        menu.add(help);
        menu.add(exit);

        fullMenu.add(topMenu);
        fullMenu.add(menu);

        jf.add(fullMenu);


    }



}
