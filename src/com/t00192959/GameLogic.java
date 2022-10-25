package com.t00192959;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.GlyphMetrics;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {

    private int noChosen = 0;
    private Banker bnk = new Banker();
    public GameLogic(ArrayList<JTextField> moneyHolder) throws IOException {

        fillMoneyArray(moneyHolder);
        GameGui.moneyRandomHolder = GameGui.getMoneyHolder();
        Collections.shuffle(GameGui.moneyRandomHolder);

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

    public void boxLogic(ArrayList<JButton> jbx, ArrayList<JTextField> moneyRandomHolder){



        ActionListener hideMoney = e -> {

            setNoChosen(getNoChosen()+1);

            if(getNoChosen()<28){

                bnk.generateOffer(GameGui.getMainGame(),getNoChosen());

            }

            JButton clicked = (JButton) e.getSource();
            JTextField jtf = moneyRandomHolder.get(Integer.parseInt(clicked.getText()) - 1);

            double money = Double.parseDouble(jtf.getText());



            bnk.addMoney((int)money);


            jtf.setBackground(Color.BLACK);
            clicked.setEnabled(false);

        };

        for (JButton jb: jbx) {

            jb.addActionListener(hideMoney);


        }

        Banker.fillArray(moneyRandomHolder);


    }


}
