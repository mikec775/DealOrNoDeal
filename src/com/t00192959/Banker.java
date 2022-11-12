package com.t00192959;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Banker {

    private ArrayList<Integer> knows = new ArrayList<>();
    static ArrayList<String> remaining;
    static ArrayList<JTextField> moneyRandomHolder;

    public Banker() throws IOException {

        remaining = new ArrayList<>();
        remaining.clear();

        File moneyList  = new File("money.txt");
        BufferedReader br = new BufferedReader(new FileReader(moneyList));

        for (int i = 0; i < 27; i++) {

            remaining.add(br.readLine());

        }

    }

    public static void setMoneyRandomHolder(ArrayList<JTextField> moneyRandomHolder) {
        Banker.moneyRandomHolder = moneyRandomHolder;
    }

    public static void generateOffer(JFrame jf, int n) throws IOException {

        int choice = -1;
        int genMoney = 0;

        if(n<19 && n%6==0 || n==21 || n==24) {

            GameDriver.musicPlayer("telephone.wav", 0);
            genMoney = generateMoney();
            choice = JOptionPane.showConfirmDialog(jf, genMoney, "Offer", 0);

        } else if(n==25) {

            ArrayList<String> strHolder = new ArrayList<>();

            for(JButton jbx: GameGui.getBoxHolder()){

                if(jbx.isEnabled()){

                    strHolder.add(jbx.getText());

                }

            }

            Swap swap = new Swap(jf, strHolder.get(0), strHolder.get(1), moneyRandomHolder);

        }

        if(choice == 0){

            int total = Integer.parseInt(Player.getMoneyWon());
            total += genMoney;

            Player.setMoneyWon(String.valueOf(total));

            //reset game
            remaining.clear();
            moneyRandomHolder.clear();
            GameLogic.resetGame();
            GameGui.hideGameUi();

            for (JButton jb : GameGui.getBoxHolder()) {

                ActionListener[] al = jb.getActionListeners();
                jb.removeActionListener(al[0]);


            }

        }

    }
    public static ArrayList<String> getRemaining() {
        return remaining;
    }

    public static ArrayList<Integer> fillArray(ArrayList<JTextField> xyz){

        ArrayList<Integer> arrai = new ArrayList<>();

        for (JTextField jtf: xyz) {

            int value = Integer.parseInt(jtf.getText());
            arrai.add(value);

        }

        return arrai;

    }

    public void addMoney(int money){

        knows.add(money);
        remaining.remove(String.valueOf(money));

    }

    public static int generateMoney(){

        ArrayList<Integer> moneyLeft = new ArrayList<>();
        for (String s: getRemaining()) {

            moneyLeft.add(Integer.parseInt(s));

        }

        int max = customMax(moneyLeft);
        int low = customMin(moneyLeft);

        int sum = 0;

        for (int n:moneyLeft) {

            sum+=n;

        }

        //game theory
        int total = sum/moneyLeft.size();
        int offer = (int) Math.sqrt(total*total+max/moneyLeft.size());

        if(offer<=0){

            offer = max / moneyLeft.size() + low*moneyLeft.size();

        }

        int count = 0;
        while(offer>=max || offer<=low) {
            count++;
            offer = moneyLeft.get(count) + 5000;

            if(offer>=max){

                offer-=1000;

            }


        }

        return (int) offer;

    }

    public static int customMax(ArrayList<Integer> moneyLeft){

        int max = Integer.MIN_VALUE;

        for (int m: moneyLeft) {

            if(m > max) {

                max = m;

            }

        }

        return max;

    }

    public static int customMin(ArrayList<Integer> moneyLeft){

        int min = Integer.MAX_VALUE;

        for (int m: moneyLeft) {

            if(m < min) {

                min = m;

            }

        }

        return min;

    }



}
