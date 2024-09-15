/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickbreaker;

/**
 *
 * @author mleda
 */

import javax.swing.JFrame;


public class BrickBreaker{
    //create JFrame
    /**
     * @param args
     */
    public static void main(String[] args){
        JFrame obj = new JFrame();
        obj.setBounds(10,10,700, 600);
        
        obj.setLocationRelativeTo(null);
        obj.setTitle("Break Bricks");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create an object called gameplay in the main job of file 
//Then add the object inside of the J frame 
        GamePlay gamePlay = new GamePlay();
        obj.add(gamePlay);
       obj.addKeyListener(gamePlay);

    }
}

