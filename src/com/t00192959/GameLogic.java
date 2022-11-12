package com.t00192959;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private int noChosen = 0;
    static Banker bnk;

    public GameLogic(ArrayList<JTextField> moneyHolder) throws IOException {

        bnk = new Banker();

        fillMoneyArray(moneyHolder);

        Banker.moneyRandomHolder = GameGui.getMoneyHolder();

        customShuffle(Banker.moneyRandomHolder);

        setNoChosen(0);

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

    public void boxLogic(ArrayList<JButton> jbx) {

        try {

            ActionListener hideMoney = e -> {

                setNoChosen(getNoChosen() + 1);

                JButton clicked = (JButton) e.getSource();
                JTextField jtf = Banker.moneyRandomHolder.get(Integer.parseInt(clicked.getText()) - 1);

                double money = Double.parseDouble(jtf.getText());

                bnk.addMoney((int) money);

                jtf.setBackground(Color.BLACK);
                clicked.setEnabled(false);

                if (getNoChosen() < 28) {

                    try {

                        Banker.generateOffer(GameGui.getMainGame(), getNoChosen());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }


            };

            for (JButton jb : jbx) {

                jb.addActionListener(hideMoney);


            }

            bnk.fillArray(Banker.moneyRandomHolder);

        } catch(Exception e){

            System.out.println("Failed");

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

       Banker.setMoneyRandomHolder(temp);

    }

    public static void resetGame(){

        GameGui.showUI();

    }

    public static void volumeControl(float volume){

        for (Clip clip: GameDriver.clips) {

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volumeUpdate = (float) (Math.log((double)volume / 100.0) / Math.log(10.0) * 20.0);
            gainControl.setValue(volumeUpdate);

        }

    }


}
