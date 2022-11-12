package com.t00192959;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    private JButton btnStart;
    private JButton btnHelp;
    private JButton btnExit;
    private JButton btnMute;

    static float volume = 50f;

    static ArrayList<JTextField> moneyHolder;

    public static void setBoxHolder(ArrayList<JButton> boxHolder) {
        GameGui.boxHolder = boxHolder;
    }

    public static JPanel getFullMenu() {
        return fullMenu;
    }

    public void setFullMenu(JPanel fullMenu) {
        GameGui.fullMenu = fullMenu;
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

    public static void setGame(JPanel game) {
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

    public JButton getBtnMute() {
        return btnMute;
    }

    public void setBtnMute(JButton btnMute) {
        this.btnMute = btnMute;
    }

    public GameGui(JFrame mainGame, JPanel mainMenu, JPanel mainTopMenu, JButton btnStart, JButton btnMute, JButton btnHelp, JButton btnExit) {

        setMainGame(mainGame);
        setMainMenu(mainMenu);
        setFullMenu(new JPanel());
        setGame(new JPanel());
        setMainTopMenu(mainTopMenu);
        setBtnMute(btnMute);
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
        JButton btnMute = getBtnMute();
        JButton btnHelp = getBtnHelp();
        JButton btnExit = getBtnExit();

        btnMute.addActionListener(e -> {

            if(btnMute.getText().equals("Unmute")){

                GameDriver.clips.get(0).start();
                btnMute.setText("Mute");


            }else{

                GameDriver.clips.get(0).stop();
                btnMute.setText("Unmute");

            }

        });

        btnExit.addActionListener(e -> System.exit(0));

        btnStart.addActionListener(e -> {

            hideUI();

                try {
                    createGameGui(new JPanel());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

        });

        btnHelp.addActionListener(e -> {
            hideUI();

            JPanel jp = new JPanel();

            try {
                BufferedReader br = new BufferedReader(new FileReader("helptext.txt"));

                String helpText;

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

                JOptionPane.showMessageDialog(null, "File Not Found");

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(null, "File Error");
            }

            JButton jb = new JButton("Exit");
            jb.setPreferredSize(new Dimension(290,100));
            jb.setFont(new Font("Arial", Font.PLAIN, 20));
            jp.add(jb);
            jp.setBorder(new EmptyBorder(100,20,50,20));
            getMainGame().add(jp);

            jb.addActionListener(e1 -> {

                getMainGame().remove(jp);
                showUI();

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

    public void createGameGui(JPanel game) throws IOException {

        JFrame jf = getMainGame();

        if(!getGame().isVisible()){

            getGame().setVisible(true);

            for (JButton jb : boxHolder) {

                jb.setEnabled(true);

            }

            for (JTextField jtf : moneyHolder) {

                if(Integer.parseInt(jtf.getText()) < 1000){
                    jtf.setBackground(Color.blue);
                }else{
                    jtf.setBackground(Color.red);
                }

                jtf.setBorder(null);
                jtf.setEnabled(true);

            }

            GameLogic gL = new GameLogic(getMoneyHolder());
            gL.boxLogic(getBoxHolder());


        } else{

            setGame(game);

            FlowLayout fl = new FlowLayout();

            ImageIcon briefcase = new ImageIcon("resources/briefase.png");

            JPanel housing = new JPanel();

            JPanel moneyList = new JPanel();
            moneyList.setPreferredSize(new Dimension(300,700));

            JPanel boxContainer = new JPanel();
            housing.setLayout(fl);
            boxContainer.setLayout(fl);
            moneyList.setLayout(fl);
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

            JPanel settings = new JPanel();
            settings.setLayout(fl);
            settings.setPreferredSize(new Dimension(200,130));

            JPanel leftCol = new JPanel();
            leftCol.setLayout(fl);
            leftCol.setPreferredSize(new Dimension(300,900));

            JLabel volbl = new JLabel();
            String temp = "Volume        " + (int)volume;
            String vol = "Volume        ";
            volbl.setText(temp);
            volbl.setBorder(new EmptyBorder(20,0,5,0));
            JSlider jsl = new JSlider(0,100);
            jsl.setBorder(new EmptyBorder(5,0,20,0));
            settings.add(volbl);
            settings.add(jsl);

            leftCol.add(moneyList);
            leftCol.add(settings);
            housing.add(leftCol);
            housing.add(boxContainer);
            game.add(housing);
            jf.add(game);

            GameLogic gL = new GameLogic(getMoneyHolder());
            gL.boxLogic(getBoxHolder());

            jsl.addChangeListener(e -> {

                volume = jsl.getValue();
                GameLogic.volumeControl(volume);

                volbl.setText(vol + (int)volume);


            });


        }

    }

    public void createGui(){

        JFrame jf = getMainGame();
        jf.setSize(1000,1000);
        jf.setTitle("Deal Or No Deal");
        JPanel fullMenu = getFullMenu();

        createMenu(jf);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createMenu(JFrame jf){

        JLabel logo = new JLabel("DEAL OR NO DEAL");
        logo.setFont(new Font("Arial", Font.PLAIN, 80));

        JPanel menu = getMainMenu();
        JPanel topMenu = getMainTopMenu();

        BoxLayout bl = new BoxLayout(menu, BoxLayout.Y_AXIS);
        BoxLayout bl2 = new BoxLayout(topMenu, BoxLayout.X_AXIS);
        BoxLayout bl3 = new BoxLayout(jf, BoxLayout.Y_AXIS);

        fullMenu.setSize(new Dimension(1000,1000));

        menu.setPreferredSize(new Dimension(700,600));
        topMenu.setSize(new Dimension(1000,200));

        JButton start = getBtnStart();
        JButton help = getBtnHelp();
        JButton exit = getBtnExit();
        JButton mute = getBtnMute();

        start.setText("Start");
        help.setText("Help");
        exit.setText("Exit");
        mute.setText("Mute");

        Font btnFont = new Font("Arial", Font.PLAIN, 30);
        Dimension btnSize = new Dimension(300,100);

        start.setPreferredSize(btnSize);
        help.setPreferredSize(btnSize);
        exit.setPreferredSize(btnSize);
        mute.setPreferredSize(btnSize);

        start.setFont(btnFont);
        help.setFont(btnFont);
        exit.setFont(btnFont);
        mute.setFont(btnFont);

        topMenu.setBorder(new EmptyBorder(100,20,50,20));
        menu.setBorder(new EmptyBorder(100,400,100,400));

        topMenu.add(logo);
        menu.add(start);
        menu.add(help);
        menu.add(mute);
        menu.add(exit);

        fullMenu.add(topMenu);
        fullMenu.add(menu);

        jf.add(fullMenu);


    }


}
