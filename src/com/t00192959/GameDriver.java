package com.t00192959;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class GameDriver {

    static Clip clip;

    public static void main(String[] args) throws IOException {

        GameGui home = new GameGui(new JFrame(), new JPanel(), new JPanel(), new JButton(), new JButton(), new JButton(), new JButton());

        Player p1 = new Player("Joe");
        p1.setUserDetails();

        musicPlayer("theme.wav");

    }

    public static void musicPlayer(String p){

        try{

            File music_io = new File(p);

            if(music_io.exists()){

                AudioInputStream music = AudioSystem.getAudioInputStream(music_io);
                clip = AudioSystem.getClip();
                clip.open(music);
                clip.start();
                clip.loop(-1);

            } else{

                System.out.println("Music not found");

            }


        }catch(Exception ex){

            ex.printStackTrace();

        }

    }


}
