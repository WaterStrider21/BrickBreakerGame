


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mleda
 *///Create a new source file called gameplay 
//Extend the gameplay file to include J panel 
package com.mycompany.brickbreaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import java.awt.event.KeyListener;
//add listeners that will dictate the actions of the ball
//create mapGenerator method


public class GamePlay extends JPanel implements KeyListener, ActionListener{
//create boolean for play - so the game doesn't start playing by itself
//create a starting score of 0
//create starting number of brickers
  private boolean play = false;
  private int score = 0;
  private int totalBricks = 21;
  //create a timer for how fast the ball moves
  
  private Timer timer;
  private int delay = 8;
  
 //starting position

  private int playerY = 310;
  
  private int ballposX = 120;
  private int ballposY = 320;
  private int ballDirection = -2;
  private int ballDirection2 = -4;
  private MapGenerator map;
  //add a constructor for Gameplay to add objects
  public GamePlay(){
      map = new MapGenerator(3, 7);
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      timer = new Timer(delay, this);
      timer.start();
  }
//create methods for the action listener
  //add a function

    /**    *
     * @param g
     */
  @SuppressWarnings("override")
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1, 692, 592);
        //drawing map
        map.draw((Graphics2D)g);
        
        //borders
        g.setColor(Color.white);
        g.fillRect(0, 0, 3,592);
        g.fillRect(0, 0, 692,3);
        g.fillRect(691,0, 3,592);
        
        //scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD,25));
        g.drawString("" + score, 590, 30);
       
        //the paddle
        g.setColor(Color.red);
        g.fillRect(playerY,550,100,8);
       
        //the ball
         g.setColor(Color.pink);
        g.fillOval(ballDirection, ballDirection2,20,20);
        
        if(totalBricks <= 0){
        play = false;
        ballDirection = 0;
        ballDirection2 = 0;
        g.setColor(Color.RED);
          g.setFont(new Font("serif", Font.BOLD,30));
          g.drawString("You Won", 260, 300);
          
           g.setFont(new Font("serif", Font.BOLD,20));
          g.drawString("Press Enter to Retart", 230, 350);
        }
        
        //check if ballY position is >
        if(ballposY > 570){
        play = false;
        ballDirection = 0;
        ballDirection2 = 0;
        g.setColor(Color.RED);
          g.setFont(new Font("serif", Font.BOLD,30));
          g.drawString("Game Over, Scores:", 190, 300);
          
           g.setFont(new Font("serif", Font.BOLD,20));
          g.drawString("Press Enter to Retart", 230, 350);
        }
        

        g.dispose();
       
    }
    
    
    
       @Override
    public void actionPerformed(ActionEvent e) {
        //recall the paint method with the repaint function and timer
        timer.start();
        //create boundaries for ball position
        if(play){
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerY, 550, 100, 8))){
            ballDirection2 = -ballDirection2;
            }
            //accessing 2D array for map
            A: for(int i = 0; i<map.map.length; i++){
                for(int j =0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                    //detect intersection
                       int brickX = j* map.brickWidth + 80;
                       int brickY = i * map.brickHeight + 50;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;
                       
                       Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                       Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                       Rectangle brickRect = rect;
                       
                       if(ballRect.intersects(brickRect)){
                           map.setBrickValue(0, i, j);
                           totalBricks--;
                           score += 5;
                           
                           if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                               ballDirection = -ballDirection; 
                           } 
                           else {
                           ballDirection2 = -ballDirection2;
                           }
                       }
                       break A;
                    }
                }
            }

           ballposX += ballDirection;
           ballposY += ballDirection2;
           
           
           if(ballposX < 0){
           ballDirection = -ballDirection;
           }
           if(ballposY < 0){
           ballDirection2 = -ballDirection2;
           }
           
           if(ballposX > 670){
           ballDirection = -ballDirection;
           }
          if(ballposY > 670){
           ballDirection2 = -ballDirection2;
           }
        }
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
      //pressed left arrow key or right arrow key
      if(e.getKeyCode()== KeyEvent.VK_RIGHT){
      if(playerY >= 600){
          playerY = 600;
      }else {
          moveRight();
      }
      }
      if(e.getKeyCode() == KeyEvent.VK_LEFT){
      if(playerY < 10){
      playerY = 10;
      }else{
      moveLeft();
      }
      }
      if(e.getKeyCode() == KeyEvent.VK_ENTER){
      if(!play){
      play = true;
      ballposX = 120;
      ballposY = 350;
      ballDirection = -1;
      ballDirection2 = -2;
      playerY = 310;
      score = 0;
      totalBricks = 21;
      map = new MapGenerator(3,7);
      repaint();
      }
     }
    }
    //create methods for moveRight and moveLeft
    public void moveRight(){
        play = true;
        playerY += 40;
    }
    public void moveLeft(){
     play = true;
     playerY -= 40;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

