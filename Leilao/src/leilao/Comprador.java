/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;
import java.util.*;

public class Comprador extends Utilizador{
    private static int tipo = 2;
    private HashMap<String,ArrayList<Leilao>> leiloes_Licitados;

    public Comprador() {
        super();
        this.leiloes_Licitados = new HashMap<>(); 
    }

    public Comprador(String nome, String password, int tipo) {
        super(nome, password);
        this.leiloes_Licitados = new HashMap<>(); 
    }
     
    public Comprador(Utilizador u ,Comprador c) {
        super(u);
        
    }

    public String listaLeiloados(String id) {
      StringBuilder s = new StringBuilder("######Itens Licitados por Mim######\n");
      ArrayList<Leilao> minhas = new ArrayList<>();
        for(String e: this.leiloes_Licitados.keySet())
                if(e.equals(id)){
                   minhas = leiloes_Licitados.get(e);
                   for(Leilao l: minhas)
                       s.append(l.toString());
                }
        return s.toString();
    }
    
}
