package com.t00192959;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameGui {

    private JFrame mainGame;

    public JFrame getMainGame() {
        return mainGame;
    }

    public void setMainGame(JFrame mainGame) {
        this.mainGame = mainGame;
    }

    public GameGui(JFrame mainGame) {

        setMainGame(mainGame);
        createGui();

    }

    public void createGui(){

        JFrame jf = getMainGame();
        jf.setSize(1000,1000);

        JLabel logo = new JLabel("DEAL OR NO DEAL");
        logo.setFont(new Font("Arial", Font.PLAIN, 80));

        JPanel menu = new JPanel();
        JPanel topMenu = new JPanel();

        BoxLayout bl = new BoxLayout(menu, BoxLayout.Y_AXIS);
        BoxLayout bl2 = new BoxLayout(topMenu, BoxLayout.X_AXIS);
        BoxLayout bl3 = new BoxLayout(jf, BoxLayout.Y_AXIS);

        menu.setBorder(new EmptyBorder(400,300,100,300));
        menu.setSize(new Dimension(1000,500));
        topMenu.setSize(new Dimension(1000,300));

        JButton start = new JButton("Start");
        JButton help = new JButton("Start");
        JButton exit = new JButton("Start");

        topMenu.add(logo);
        menu.add(start);
        menu.add(help);
        menu.add(exit);

        jf.add(topMenu);
        jf.add(menu);

        jf.setVisible(true);

    }


}
