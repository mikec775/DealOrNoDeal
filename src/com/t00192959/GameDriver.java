package com.t00192959;

import javax.swing.*;
import java.io.IOException;


public class GameDriver {

    public static void main(String[] args) throws IOException {

        GameGui home = new GameGui(new JFrame(), new JPanel(), new JPanel(), new JButton(), new JButton(), new JButton());

        Player p1 = new Player("Joe");
        p1.setUserDetails();






    }

}
