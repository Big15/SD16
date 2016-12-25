/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.io.PrintWriter;
import static java.lang.Thread.MIN_PRIORITY;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Joao
 */

public class Thread_Consola extends Thread{
    private Gestor gestor;
    boolean exit;
    Utilizador user;
    ServerSocket ss;
    
    public Thread_Consola(Gestor g, boolean exit,ServerSocket ss){
        this.gestor = g;
        this.exit = exit;
        this.ss = ss;
    }
    
    
    
    public boolean login(){
        boolean continua = true;
        Scanner in = new Scanner(System.in);
        String l = "";
        System.out.println("###EFECTUE LOGIN PARA USAR O TERMINAL###");
        System.out.print(">");
            while( continua && (!"sair".equals(l = in.nextLine()))){
                String parse[] =  l.split(":");
                if( parse[0].equals("login") && (parse.length >= 3) ){ 
                    if(!(this.gestor.getUtilizador(parse[1])).equals(null)){ //verifica se user existe
                        Utilizador u = this.gestor.getUtilizador(parse[1]) ;
                        if(!u.getloged()){ //verifica se já esta loged
                            if(u.validaPass(parse[2])){ // verifica pass              
                                if(u.login()){
                                    System.out.println("Loged: ..."); 
                                    this.user = u;         
                                    continua = false;
                                } else {System.out.println("Utilizador já de encontra autenticado!");}                                  
                            } else { System.out.println("Password Errada!!");}
                        } else { System.out.println("Utilizador já de encontra autenticado!");}
                    }else { System.out.println("Utilizador não existe!");} 
                } else { System.out.println("Comando errado!");}
                if(continua){
                    System.out.println("###EFECTUE LOGIN PARA USAR O TERMINAL###");
                    System.out.print(">");
                } 
            }
        return !l.equals("sair");
    }
    
    @Override
    public void run(){
        Scanner in = new Scanner(System.in);
        if(login()) {
        } else {
            this.exit = true;
        }
        String pedido;
        while(!exit){
            System.out.print(">");
            pedido = in.nextLine();
            switch (pedido) {
                case "sair":
                    exit = true;
                    user.logout();
                    break;
                case "logout":
                    this.user.logout();
                    if(!login())
                        this.exit = true;
                    break;
                default:
                    //System.out da consola vai ser ou out da Thread TrataPedido
                    PrintWriter out = new PrintWriter(System.out);
                    Thread cr = new Thread( new Thread_Comandos(this.gestor, this.user.getNome(), out, pedido)); 
                    cr.start();
                    break;
            }
        }        
        System.exit(MIN_PRIORITY);
    }
    
}
