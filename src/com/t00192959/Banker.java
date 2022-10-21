package com.t00192959;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.IntStream;

public class Banker {

    ArrayList<Integer> knows = new ArrayList<>();
    static ArrayList<Integer> remaining = new ArrayList<>();

    public Banker(){

        remaining = new ArrayList<>();

        for (JTextField jtf : GameGui.getMoneyHolder()) {

            remaining.add(Integer.parseInt(jtf.getText()));

        }



    }

    public static void generateOffer(JFrame jf, int n){

        if(n<19 && n%6==0){

            JOptionPane.showMessageDialog(jf, generateMoney(), "Offer", 1);

        } else if(n==21){

            JOptionPane.showMessageDialog(jf, "Hi want money", "Offer", 1);

        } else if(n==24){

            JOptionPane.showMessageDialog(jf, "Hi want money", "Offer", 1);

        } else if(n==25) {

            JOptionPane.showMessageDialog(jf, "Swap", "Offer", 1);

        }


    }

    public void addMoney(int money){

        knows.add(money);
        remaining.remove(money);
    }

    public static String generateMoney(){

        int max = Collections.max(remaining);
        int low = Collections.min(remaining);
        return String.valueOf(max/low);
    }


}
