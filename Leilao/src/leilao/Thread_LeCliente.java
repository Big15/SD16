/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Joao
 */
public class Thread_LeCliente implements Runnable{
    private BufferedReader inputServer;

    public Thread_LeCliente(BufferedReader in) {
        this.inputServer = in;
    }

    public void run() {
        try {
            //recebe mensagens do servidor e imprime
            String line;
            while ((line = inputServer.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
