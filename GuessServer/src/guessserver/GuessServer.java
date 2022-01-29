/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package guessserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ajla
 */
public class GuessServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        ServerSocket server = new ServerSocket(4999);
        while(true){
         Socket socket = server.accept();
         Thread t = new Thread(() ->{
                try{
                    Scanner scanner = new Scanner(socket.getInputStream());
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    
                    while(true){
                    String pogodi = scanner.nextLine();
                    Random rand = new Random();
                    int zamisli = rand.nextInt(3);
                    
                    if(Integer.parseInt(pogodi) == zamisli)
                        writer.println("Bravo"+zamisli);
                    else
                        writer.println("Greska "+zamisli);
                    }
                
                }   catch (IOException ex) { }
            });
            t.start();
        }
        }catch(IOException e){
        System.err.println("Error creating socker in port 4999");
    }
        
        }
    }
    

