package com.t00192959;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Swap {

    public Swap(JFrame jf, String box1, String box2, ArrayList<JTextField> moneyHolder){

        GameGui.hideGameUi();

        JPanel swap = new JPanel();
        JPanel win = new JPanel();
        JPanel housing = new JPanel();

        swap.setVisible(true);
        swap.setPreferredSize(new Dimension(1000,500));
        win.setPreferredSize(new Dimension(1000,500));

        ImageIcon briefcase = new ImageIcon("resources/briefase.png");

        JButton jb1 = new JButton();
        jb1.setPreferredSize(new Dimension(500,200));

        /**  Title: Overriding button background
         Author: sage
         Site owner/sponsor: stackoverflow
         Date: 29-10-13
         Availability: https://stackoverflow.com/questions/19663009/overriding-button-background
         (Accessed 12-10-22)

         **/

        jb1.setContentAreaFilled(false);

        jb1.setBorderPainted(false);
        jb1.setIcon(briefcase);
        jb1.setText(box1);
        jb1.setForeground(Color.white);
        jb1.setHorizontalTextPosition(JButton.CENTER);

        JButton jb2 = new JButton();
        jb2.setPreferredSize(new Dimension(500,200));

        /**  Title: Overriding button background
         Author: sage
         Site owner/sponsor: stackoverflow
         Date: 29-10-13
         Availability: https://stackoverflow.com/questions/19663009/overriding-button-background
         (Accessed 12-10-22)

         **/
        jb2.setContentAreaFilled(false);

        jb2.setBorderPainted(false);
        jb2.setIcon(briefcase);
        jb2.setText(box2);
        jb2.setForeground(Color.white);
        jb2.setHorizontalTextPosition(JButton.CENTER);


        JLabel winnings = new JLabel();
        winnings.setText("Choose 1");
        winnings.setFont(new Font("Arial", Font.BOLD, 25));
        winnings.setPreferredSize(new Dimension(900,200));
        winnings.setHorizontalAlignment(SwingConstants.CENTER);

        swap.add(jb1);
        swap.add(jb2);
        win.add(winnings);
        housing.add(swap);
        housing.add(win);


        ActionListener winner = e -> {

            JButton jb = (JButton) e.getSource();
            JTextField jtf = moneyHolder.get(Integer.parseInt(jb.getText()) - 1);
            String winStr = "Congratulations you have won ???" + jtf.getText();

            winnings.setText(winStr);

            int total = Integer.parseInt(Player.getMoneyWon());
            int won = Integer.parseInt(jtf.getText());

            total += won;
            try {
                Player.setMoneyWon(String.valueOf(total));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            jb1.setEnabled(false);
            jb2.setEnabled(false);

            JButton home = new JButton("Home");
            home.setPreferredSize(new Dimension(190,80));
            home.setFont(new Font("Arial", Font.PLAIN, 18));
            win.add(home);
            home.addActionListener(e1 -> {

                swap.removeAll();
                housing.removeAll();
                GameGui.getGame().remove(housing);
                GameLogic.resetGame();

            });



        };

        jf.add(housing);

        jb1.addActionListener(winner);
        jb2.addActionListener(winner);






    }




}
