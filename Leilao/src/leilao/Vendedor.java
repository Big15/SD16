/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;
import java.util.*;

public class Vendedor extends Utilizador{
    private static int tipo = 1;
    private HashMap<String,ArrayList<Leilao>> itens_Leiloados;

    public Vendedor() {
        super();
        this.itens_Leiloados=new HashMap<>();
    }

    public Vendedor(String nome, String password, int tipo) {
        super(nome, password);
        this.itens_Leiloados=new HashMap<>();
    }
    
    public Vendedor(Vendedor v, Utilizador u) {
        super(u);
        this.itens_Leiloados = v.getItens_Leiloados();
    }

    public HashMap<String, ArrayList<Leilao>> getItens_Leiloados() {
        return itens_Leiloados;
    }

    public void setItens_Leiloados(HashMap<String, ArrayList<Leilao>> itens_Leiloados) {
        this.itens_Leiloados = itens_Leiloados;
    }
    

    public String listaLeiloados(String id) {
      StringBuilder s = new StringBuilder("######Itens Leiloados por Mim######\n");
      ArrayList<Leilao> meus = new ArrayList<>();
        for(String e: this.itens_Leiloados.keySet())
                if(e.equals(id)){
                   meus = itens_Leiloados.get(e);
                   for(Leilao l: meus)
                       s.append(l.toString());
                }
        return s.toString();
    }
    
    
}
