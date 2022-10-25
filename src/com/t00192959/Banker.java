package com.t00192959;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Banker {

    static ArrayList<Integer> knows = new ArrayList<>();
    static ArrayList<String> remaining = new ArrayList<>();

    public Banker() throws IOException {

        File moneyList  = new File("money.txt");
        BufferedReader br = new BufferedReader(new FileReader(moneyList));

        for (int i = 0; i < 27; i++) {

            remaining.add(br.readLine());

        }

    }

    public static void generateOffer(JFrame jf, int n){

        int choice = -1;
        int genMoney = 0;

        if(n<19 && n%6==0){

            genMoney = Integer.parseInt(generateMoney());
            choice = JOptionPane.showConfirmDialog(jf, genMoney, "Offer", 0);

        } else if(n==21){

            genMoney = Integer.parseInt(generateMoney());
            choice = JOptionPane.showConfirmDialog(jf, genMoney, "Offer", 0);

        } else if(n==24){

            genMoney = Integer.parseInt(generateMoney());
            choice = JOptionPane.showConfirmDialog(jf, genMoney, "Offer", 0);

        } else if(n==25) {

            ArrayList<String> strHolder = new ArrayList<>();

            for(JButton jbx: GameGui.getBoxHolder()){

                if(jbx.isEnabled()){

                    strHolder.add(jbx.getText());

                }

            }


            Swap swap = new Swap(jf, strHolder.get(0), strHolder.get(1), GameGui.moneyRandomHolder);


        }

        if(choice == 0){

            int total = Integer.parseInt(Player.getMoneyWon());
            total += genMoney;

            Player.moneyWon = String.valueOf(total);

            //reset game
            GameLogic.resetGame();

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

    public static String generateMoney(){

        ArrayList<Integer> moneyLeft = new ArrayList<>();
        for (String s: getRemaining()) {

            moneyLeft.add(Integer.parseInt(s));

        }

        int max = customMax(moneyLeft);
        int low = customMin(moneyLeft);

        Random r = new Random();

        return String.valueOf(r.nextInt(low, max));

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
