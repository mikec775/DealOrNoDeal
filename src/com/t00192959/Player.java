package com.t00192959;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player {

    private String name;

    private File usr = new File("drnd_usrdetails.txt");

    public Player(String name) throws IOException {
        setName(name);

        if(usr.exists()){
            usr = getUsr();
        }else{

            usr.createNewFile();
        }

    }

    public String getName() {
        return name;
    }

    public File getUsr() {
        return usr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails(){

        return getName();

    }


    public void setUserDetails() throws IOException {

        FileWriter fileout = new FileWriter(usr.getPath());
        fileout.write(getDetails());
        fileout.close();

    }

}
