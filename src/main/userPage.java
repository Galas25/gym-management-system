/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class userPage {
    JFrame mainFrame = new JFrame();
    
    
    
    public userPage(int id){
        SwingUtilities.invokeLater(() -> {
           createUI(id);
        });
    }
    public void createUI(int id){
         mainFrame.setSize(500,500);
         mainFrame.setLocationRelativeTo(null);
         mainFrame.setVisible(true);
         mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JLabel d = new JLabel(String.valueOf(id));
         mainFrame.add(d);
        
    }
}
