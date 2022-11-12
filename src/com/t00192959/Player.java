package com.t00192959;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The type Player.
 */
public class Player {

    private static String name;
    private static String moneyWon = "0";
    private File usr = new File("drnd_usrdetails.txt");

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     * @throws IOException the io exception
     */
    public Player(String name) throws IOException {
        setName(name);

        if(usr.exists()){
            usr = getUsr();
        }else{

            usr.createNewFile();
        }

    }

    /**
     * Gets money won.
     *
     * @return the money won
     */
    public static String getMoneyWon() {
        return moneyWon;
    }

    /**
     * Sets money won.
     *
     * @param bills the bills
     * @throws IOException the io exception
     */
    public static void setMoneyWon(String bills) throws IOException {

        Player.moneyWon = bills;
        Player.updateWinnings();

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public static String getName() {
        return name;
    }

    /**
     * Gets usr.
     *
     * @return the usr
     */
    public File getUsr() {
        return usr;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        Player.name = name;
    }

    /**
     * Get details string.
     *
     * @return the string
     */
    public static String getDetails(){

        return getName() + " " + getMoneyWon();

    }


    /**
     * Sets user details.
     *
     * @throws IOException the io exception
     */
    public void setUserDetails() throws IOException {

        FileWriter fileout = new FileWriter(usr.getPath());
        fileout.write(getDetails());
        fileout.close();

    }

    /**
     * Update winnings.
     *
     * @throws IOException the io exception
     */
    public static void updateWinnings() throws IOException {

        FileWriter fw = new FileWriter("drnd_usrdetails.txt", false);
        fw.write(getDetails());
        fw.close();
    }



}
