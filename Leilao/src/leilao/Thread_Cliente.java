/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao
 */
public class Thread_Cliente implements Runnable {
    private Gestor gestor;
    private final Socket mySocket;
    HashMap<String,Vendedor> vend;
    HashMap<String,Comprador> comp;
    Utilizador user;
    PrintWriter out;
    BufferedReader in;
    
    /*No servidor depois do accept, é criada a Thread*/
    public Thread_Cliente(Gestor g, Socket s, HashMap<String,Vendedor> vendedores,HashMap<String,Comprador> compradores){
        this.gestor = g; 
         this.vend = vendedores;
        this.comp = compradores;
        this.mySocket = s;
    
    }    
    
    public void run(){
        try {
            this.out = new PrintWriter(mySocket.getOutputStream());            
            this.in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            String pedido;
            String l;
            boolean continua = true;
            while( continua && ((l = in.readLine()) != null)){
                String parse[] =  l.split(":");
                if( parse[0].equals("login") && (parse.length >= 3) ){ 
                    if(vend.containsKey(parse[1])||comp.containsKey(parse[1]) ){ //verifica se user existe
                        Utilizador u = vend.get(parse[1]);
                        if(!u.getloged()){ //verifica se já esta loged
                            if(u.validaPass(parse[2])){ // verifica pass              
                                if(u.login()){
                                    out.println("Loged: ..."); 
                                    this.user = u;
                                    out.flush();          
                                    continua = false;
                                } else {out.println("Utilizador já de encontra autenticado!"); out.flush();}                                  
                            } else { out.println("Password Errada!!"); out.flush();}
                        } else { out.println("Utilizador já de encontra autenticado!"); out.flush();}
                    }else { out.println("Utilizador não existe!"); out.flush();} 
                } else { out.println("Comando errado!"); out.flush();}
            }
            
            //le o pedido do Cliente e cria Thread para o tartar
            while( (pedido=in.readLine()) != null ){
                Thread cr = new Thread( new Thread_Comandos(this.gestor, this.user.getNome(), out, pedido));
                cr.start();                 
            }
            
            this.user.logout();
            
            this.mySocket.shutdownInput();
            this.mySocket.shutdownOutput();
            this.mySocket.close();            
        } catch (IOException ex) {
            Logger.getLogger(Thread_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
}
