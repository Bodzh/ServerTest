/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverdevelopment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Богдан
 */
public class TestServer implements Runnable{

//    TestServer() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(9060);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("thread go");
        if (serverSocket != null) {
            while (true) {
                try (Socket client = serverSocket.accept()) {
              

                    System.out.println("Connected IP: " + client.getInetAddress().getHostAddress());

                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                    oos.writeUTF("PONG");
                    String command = ois.readUTF();
                    
                    switch (command) {
                        case "PING":
                            oos.writeUTF("PONG");
                             oos.flush();
                            
                        case "TIME":
                            Date now = new Date();
                            SimpleDateFormat smp = new SimpleDateFormat("hh:mm:ss");
                            oos.writeUTF(smp.format(now));
                             oos.flush();
                            System.out.println("TIME sent");
                             break;
                    }
                } catch (SocketTimeoutException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    ; // NOOP
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
