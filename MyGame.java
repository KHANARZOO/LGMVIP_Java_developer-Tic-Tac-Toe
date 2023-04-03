/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author hp
 */
public class MyGame extends JFrame implements ActionListener
{
    JLabel heading,clockLabel;
    Font font = new Font("",Font.BOLD,40);
    JPanel mainPanel;
    
    
    JButton[] bts = new JButton[9];
    
    int gameChance[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    
    int wps[][] = {
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    boolean gameOver = false;
    int winner = 2;
    MyGame(){
        setTitle("My Tic Tac Toe Game");
        setSize(850,850);
        ImageIcon icon =new ImageIcon("src/img/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }
    public void createGUI(){
        this.getContentPane().setBackground(Color.decode("#2196f3"));
     
        this.setLayout(new BorderLayout());
        heading = new JLabel("Tic Tac Toe");
        heading.setIcon(new ImageIcon("src/img/head.png"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.WHITE);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(heading,BorderLayout.NORTH);
        
        
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.WHITE);
        this.add(clockLabel,BorderLayout.SOUTH);
        
        Thread t = new Thread(){
            public void run(){
                try{
                    while(true){
                        String datetime = new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        
                        Thread.sleep(1000);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        };
        t.start();
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        for(int i=1;i<=9;i++){
            JButton btn = new JButton();
            btn.setBackground(Color.WHITE);
            btn.setFont(font);
            mainPanel.add(btn);
            bts[i-1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
            
        }
        this.add(mainPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JButton currentButton = (JButton)e.getSource();
       String nameStr = currentButton.getName();
      
       int name = Integer.parseInt(nameStr.trim());
       
       if(gameOver){
           JOptionPane.showMessageDialog(this, "Game Already Over");
           return;
       }
       if(gameChance[name] ==2){
           if(activePlayer==1){
               currentButton.setIcon(new ImageIcon("src/img/B.jpeg"));
               gameChance[name] = activePlayer;
               activePlayer =0;
           }else{
               currentButton.setIcon(new ImageIcon("src/img/a.png"));
               gameChance[name] = activePlayer;
               activePlayer =1;
           }
           
           for(int []temp:wps){
               if((gameChance[temp[0]]==gameChance[temp[1]]) && 
                  (gameChance[temp[1]]==gameChance[temp[2]]) &&
                   gameChance[temp[2]]!=2){
                   
                   winner  = gameChance[temp[0]];
                   gameOver = true;
                   
                   JOptionPane.showMessageDialog(null,"Player "+winner+" has won the game");
                   int i = JOptionPane.showConfirmDialog(this,"Do you want to play again");
                   if(i==0){
                       this.setVisible(false);
                       new MyGame();
                   }else if(i==1){
                       System.exit(34234);
                   }else{
                           
                           }
                   
                   System.out.println(i);
                   break;
               }
           }
          int counter = 0;
          for(int x : gameChance){
              if(x==2){
                  counter++;
                  break;
              }
          }
          if(counter==0 && gameOver==false){
              JOptionPane.showMessageDialog(null,"It's Draw..!!");
              int i = JOptionPane.showConfirmDialog(this,"Play Again ??");
              if(i==0){
                  this.setVisible(false);
                  new MyGame();
              }else if(i==1){
                  System.exit(1212);
              }else{
                  
              }
              gameOver = true;
          }
       }else{
           JOptionPane.showMessageDialog(this,"Position already occupied");
       }
       
       
    }
}
