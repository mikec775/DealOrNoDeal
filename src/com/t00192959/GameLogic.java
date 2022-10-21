package com.t00192959;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GameLogic {

    private int noChosen = 0;
    private Banker bnk = new Banker();
    public GameLogic(ArrayList<JTextField> moneyHolder) throws IOException {

        fillMoneyArray(moneyHolder);

    }
    public int getNoChosen() {
        return noChosen;
    }

    public void setNoChosen(int noChosen) {
        this.noChosen = noChosen;
    }

    public void fillMoneyArray(ArrayList<JTextField> moneyHolder) throws IOException {

        File moneyList  = new File("money.txt");
        BufferedReader br = new BufferedReader(new FileReader(moneyList));

        for (JTextField jtf: moneyHolder) {

            jtf.setText(br.readLine());

        }

    }

    public void boxLogic(ArrayList<JButton> jbx, ArrayList<JTextField> moneyHolder){

        ActionListener hideMoney = e -> {

            setNoChosen(getNoChosen()+1);

            if(getNoChosen()<28){

                bnk.generateOffer(GameGui.getMainGame(),getNoChosen());

            }

            JButton clicked = (JButton) e.getSource();
            JTextField jtf = moneyHolder.get(Integer.parseInt(clicked.getText()) - 1);

            bnk.addMoney(Integer.parseInt(jtf.getText()));


            jtf.setBackground(Color.BLACK);
            clicked.setEnabled(false);

        };

        for (JButton jb: jbx) {

            jb.addActionListener(hideMoney);


        }



    }


}
