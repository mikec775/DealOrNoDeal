package com.t00192959;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameDriver {

    static Clip clip;
    static ArrayList<Clip> clips = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        new GameGui(new JFrame(), new JPanel(), new JPanel(), new JButton(), new JButton(), new JButton(), new JButton());

        Player p1 = new Player("Joe");
        p1.setUserDetails();

        musicPlayer("theme.wav", -1);

    }

    public static void musicPlayer(String p, int ifLoop){

        try{

            File music_io = new File(p);

            if(music_io.exists()){

                AudioInputStream music = AudioSystem.getAudioInputStream(music_io);
                clip = AudioSystem.getClip();
                clip.open(music);
                clips.add(clip);

                if(clips.size()>=2){
                    /**  Title: How to use getControl method in javax.sound.sampled.Line
                     Author: tabnine
                     Site owner/sponsor: tabnine.com
                     Date: 2022
                     Availability: https://www.tabnine.com/code/java/methods/javax.sound.sampled.Line/getControl
                     (Accessed 12-10-22)

                     Modified: repurposed formula to make it nicer to work with
                     **/
                    GameLogic.volumeControl(GameGui.volume);

                }

                clip.start();
                clip.loop(ifLoop);


            } else{

                System.out.println("Music not found");

            }


        }catch(Exception ex){

            ex.printStackTrace();

        }

    }


}
