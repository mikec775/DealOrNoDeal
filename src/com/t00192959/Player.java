package com.t00192959;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player {

    private static String name;
    private static String moneyWon = "0";
    private File usr = new File("drnd_usrdetails.txt");

    public Player(String name) throws IOException {
        setName(name);

        if(usr.exists()){
            usr = getUsr();
        }else{

            usr.createNewFile();
        }

    }

    public static String getMoneyWon() {
        return moneyWon;
    }

     public static void setMoneyWon(String bills) throws IOException {

        Player.moneyWon = bills;
        Player.updateWinnings();

    }

    public static String getName() {
        return name;
    }

    public File getUsr() {
        return usr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDetails(){

        return getName() + " " + getMoneyWon();

    }


    public void setUserDetails() throws IOException {

        FileWriter fileout = new FileWriter(usr.getPath());
        fileout.write(getDetails());
        fileout.close();

    }

    public static void updateWinnings() throws IOException {

        FileWriter fw = new FileWriter("drnd_usrdetails.txt", false);
        fw.write(getDetails());
        System.out.println("here");
        fw.close();
    }



}
