package com.t00192959;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private int noChosen = 0;
    private Banker bnk = new Banker();
    public GameLogic(ArrayList<JTextField> moneyHolder) throws IOException {

        fillMoneyArray(moneyHolder);
        GameGui.moneyRandomHolder = GameGui.getMoneyHolder();

        //Collections.shuffle(GameGui.moneyRandomHolder);

        customShuffle(GameGui.moneyRandomHolder);


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

    public void boxLogic(ArrayList<JButton> jbx, ArrayList<JTextField> moneyRandomHolder) {

        try {

            ActionListener hideMoney = e -> {

                setNoChosen(getNoChosen() + 1);

                JButton clicked = (JButton) e.getSource();
                JTextField jtf = moneyRandomHolder.get(Integer.parseInt(clicked.getText()) - 1);

                double money = Double.parseDouble(jtf.getText());

                bnk.addMoney((int) money);

                jtf.setBackground(Color.BLACK);
                clicked.setEnabled(false);

                if (getNoChosen() < 28) {

                    bnk.generateOffer(GameGui.getMainGame(), getNoChosen());

                }


            };

            for (JButton jb : jbx) {

                jb.addActionListener(hideMoney);


            }

            Banker.fillArray(moneyRandomHolder);

        } catch(Exception e){

            //fail

        }

    }

    public void customShuffle(ArrayList<JTextField> arr){

        Random r = new Random();
        ArrayList<JTextField> temp = new ArrayList<>();

        while(temp.size() < arr.size()){

            int num = r.nextInt(arr.size());
            JTextField choice = arr.get(num);

            if(!temp.contains(choice)){

                temp.add(choice);

            }

        }

        GameGui.setMoneyRandomHolder(temp);

    }

    public static void resetGame(){

        Banker.remaining.clear();
        Banker.knows.clear();
        GameGui.moneyRandomHolder.clear();
        GameGui.boxHolder.clear();
        GameGui.boxContainer.removeAll();
        GameGui.moneyList.removeAll();
        GameGui.moneyHolder.clear();
        GameGui.hideGameUi();
        GameGui.showUI();

    }

    public static void newGame() throws IOException {


            GameGui.getGame().removeAll();
            GameGui.createGameGui(GameGui.getGame());

            for (JButton jb : GameGui.boxHolder) {

                if(jb.isEnabled() == false){

                    jb.setEnabled(true);

                }

            }





    }


}
