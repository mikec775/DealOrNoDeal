package com.t00192959;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Banker {

    ArrayList<Integer> knows = new ArrayList<>();
    static ArrayList<String> remaining = new ArrayList<>();

    public Banker() throws IOException {

        File moneyList  = new File("money.txt");
        BufferedReader br = new BufferedReader(new FileReader(moneyList));

        for (int i = 0; i < 27; i++) {

            remaining.add(br.readLine());

        }

    }

    public static void generateOffer(JFrame jf, int n){

        if(n<19 && n%6==0){

            JOptionPane.showMessageDialog(jf, generateMoney(), "Offer", 1);

        } else if(n==21){

            JOptionPane.showMessageDialog(jf, generateMoney(), "Offer", 1);

        } else if(n==24){

            JOptionPane.showMessageDialog(jf, generateMoney(), "Offer", 1);

        } else if(n==25) {

            ArrayList<String> strHolder = new ArrayList<>();

            for(JButton jbx: GameGui.getBoxHolder()){

                if(jbx.isEnabled()){

                    strHolder.add(jbx.getText());

                }

            }


            Swap swap = new Swap(jf, strHolder.get(0), strHolder.get(1), GameGui.moneyRandomHolder);


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
        for (String s: remaining) {

            moneyLeft.add(Integer.parseInt(s));

        }

        int max = Collections.max(moneyLeft);
        int low = Collections.min(moneyLeft);

        Random r = new Random();

        return String.valueOf(r.nextInt(low, max));

    }


}
