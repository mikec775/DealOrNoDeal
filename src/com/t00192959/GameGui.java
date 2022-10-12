package com.t00192959;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGui {

    private JFrame mainGame;
    private JPanel mainMenu;
    private JPanel mainTopMenu;
    private JPanel game;
    private JButton btnStart;
    private JButton btnHelp;
    private JButton btnExit;


    public JFrame getMainGame() {
        return mainGame;
    }

    public void setMainGame(JFrame mainGame) {
        this.mainGame = mainGame;
    }
    public JPanel getGame() {
        return game;
    }

    public void setGame(JPanel game) {
        this.game = game;
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
        setMainTopMenu(mainTopMenu);
        setBtnStart(btnStart);
        setBtnExit(btnExit);
        setBtnHelp(btnHelp);
        createGui();
        menuLogic();

    }

    private void menuLogic() {

        JButton btnStart = getBtnStart();
        JButton btnHelp = getBtnHelp();
        JButton btnExit = getBtnExit();

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hideUI();

            }
        });

        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hideUI();

            }
        });


    }

    private void hideUI() {

        JPanel mm = getMainMenu();
        JPanel mtp = getMainTopMenu();

        mm.setVisible(false);
        mtp.setVisible(false);

    }

    private void createGameGui(JPanel game) {

        System.out.println("Hello World");
        //todo

    }

    public void createGui(){

        JFrame jf = getMainGame();
        jf.setSize(1000,1000);

        createMenu(jf);

        jf.setVisible(true);

    }

    public void createMenu(JFrame jf){

        JLabel logo = new JLabel("DEAL OR NO DEAL");
        logo.setFont(new Font("Arial", Font.PLAIN, 80));

        JPanel menu = getMainMenu();
        JPanel topMenu = getMainTopMenu();

        BoxLayout bl = new BoxLayout(menu, BoxLayout.Y_AXIS);
        BoxLayout bl2 = new BoxLayout(topMenu, BoxLayout.X_AXIS);
        BoxLayout bl3 = new BoxLayout(jf, BoxLayout.Y_AXIS);

        menu.setBorder(new EmptyBorder(400,300,100,300));
        topMenu.setBorder(new EmptyBorder(200,20,100,20));
        menu.setSize(new Dimension(1000,500));
        topMenu.setSize(new Dimension(1000,300));

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

        topMenu.add(logo);
        menu.add(start);
        menu.add(help);
        menu.add(exit);

        jf.add(topMenu);
        jf.add(menu);


    }



}
