/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guessclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ajla
 */
class GuessClient extends Thread{
      private String pogodi;
      private JLabel label;
      
      GuessClient(JLabel label){
      this.label = label;
      }
    
      public synchronized void setNumber(String l){
      pogodi =l;
notifyAll();      
      }
      
      public void run(){
      try{
      Socket socket = new Socket("localhost",4999);
      Scanner scanner = new Scanner(socket.getInputStream());
      PrintWriter writer= new PrintWriter(socket.getOutputStream(), true);
      
      while(true){
      try{
      if(pogodi !=null){
      writer.println(pogodi);
      String zamisli = scanner.nextLine();
      label.setText(zamisli);
      setNumber(null);
      }else{
       synchronized (this) {
                            wait();
                        }
      }
      
      }
      catch(InterruptedException e){}
      
      } 
      }
      catch(IOException e){
      e.printStackTrace();
      }
      }
    }
public class Client extends javax.swing.JFrame {

    /**
     * Creates new form Client
     */
    JLabel label = new JLabel("");
    JTextField textField = new JTextField(10);
    JButton button = new JButton("Posalji");
    
    
    public Client() {
        super("Guess the Number Client");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel pane= new JPanel();
        pane.add(label);
        pane.add(textField);
        pane.add(button);
        add(pane);
        
        GuessClient guessThread = new GuessClient(label);
        guessThread.start();
        
        button.addActionListener(action  ->{
        guessThread.setNumber(textField.getText());
        
        
        });
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}