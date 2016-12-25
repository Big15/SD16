/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Gestor {
    private TreeMap<Integer,Leilao> emCurso;
    private HashMap<Integer,Leilao> terminados;
    private TreeMap<Integer,TreeSet<Licitacao>> historicoLicitacao;
    private HashMap<String,Vendedor> vendedores;
    private HashMap<String,Comprador> compradores;
    private ReentrantLock lockLei;
    private int idLgera = 1;

    public Gestor() {
       this.emCurso = new TreeMap<>();
       this.terminados = new HashMap<>();
       this.historicoLicitacao = new TreeMap<>();
       this.vendedores =  new HashMap<>();
       this.compradores =  new HashMap<>();
       this.lockLei = new ReentrantLock();
      
    }
    
    public Gestor(TreeMap<Integer, Leilao> emCurso, HashMap<Integer, Leilao> terminados, TreeMap<Integer, TreeSet<Licitacao>> historicoLicitacao, HashMap<String,Vendedor> vendedores,HashMap<String,Comprador> compradores) {
        this.emCurso = emCurso;
        this.terminados = terminados;
        this.historicoLicitacao = historicoLicitacao;
        this.vendedores = vendedores;
        this.compradores = compradores;
        this.lockLei = new ReentrantLock();
    }
    
    public Gestor(Gestor g) {
        this.emCurso = g.getEmCurso();
        this.terminados = g.getTerminados();
        this.historicoLicitacao = g.getHistoricoLicitacao();
        this.vendedores = g.getVendedores();
        this.compradores = g.getCompradores();
    }

    public HashMap<String, Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(HashMap<String, Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public HashMap<String, Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(HashMap<String, Comprador> compradores) {
        this.compradores = compradores;
    }
    
    public TreeMap<Integer, Leilao> getEmCurso() {
        return emCurso;
    }

    public void setEmCurso(TreeMap<Integer, Leilao> emCurso) {
        this.emCurso = emCurso;
    }

    public HashMap<Integer, Leilao> getTerminados() {
        return terminados;
    }

    public void setTerminados(HashMap<Integer, Leilao> terminados) {
        this.terminados = terminados;
    }

    public TreeMap<Integer, TreeSet<Licitacao>> getHistoricoLicitacao() {
        return historicoLicitacao;
    }

    public void setHistoricoLicitacao(TreeMap<Integer, TreeSet<Licitacao>> historicoLicitacao) {
        this.historicoLicitacao = historicoLicitacao;
    }

    public int getIdLgera() {
        return idLgera;
    }

    public void setIdLgera(int idLgera) {
        this.idLgera = idLgera;
    }
    
    public Utilizador getUtilizador(String n){
    Utilizador u = null;
        
        if(this.vendedores.containsKey(n))
            u = this.vendedores.get(n);
        else
            u = this.compradores.get(n);
    
    return u;
    }
    
    public Leilao constroi_Leilao(String parse[],String idUti){
    Leilao l;
    
        int id = this.idLgera;
        boolean est = true;
        this.idLgera++;
        String descricao = parse[1];
        Float valorinicial= Float.parseFloat(parse[2]);
        l = new Leilao(id,descricao,valorinicial,idUti,est);
        return l;
    }
    
    public void inicia_Leilao(Leilao l , String idUti){
        lockLei.lock();
        HashMap<String,ArrayList<Leilao>> leiloados = new HashMap<>();  
        ArrayList<Leilao> lei = new ArrayList<>();
        try{
        if(this.vendedores.containsKey(idUti)){
           leiloados = this.vendedores.get(idUti).getItens_Leiloados();
           if(leiloados.containsKey(idUti)){
              leiloados.get(idUti).add(l);
              this.vendedores.get(idUti).setItens_Leiloados(leiloados);
           }else{
              lei.add(l);
              leiloados.put(idUti,lei);
              this.vendedores.get(idUti).setItens_Leiloados(leiloados);
           }    
        }
        this.emCurso.put(l.getId(), l);
        }finally{
        lockLei.unlock();
        }
    }
    
    public void termina_Leilao(String id,String idUti){
        lockLei.lock();
        Leilao l = new Leilao();
        Licitacao b = new Licitacao();
        Iterator it;
        try{
        if(this.emCurso.containsKey(id)){
            l = this.emCurso.get(id);
            if(l.getVendedor().equals(idUti)){
            this.emCurso.remove(id);
            l.setEstado(false);
            this.terminados.put(l.getId(),l);
            if(this.historicoLicitacao.containsKey(id)){
                it = this.historicoLicitacao.get(id).iterator();
                b = (Licitacao) it.next();
                System.out.println("O Vencedor é "+b.getComprador()+" e dispendeu "+ b.getValor()+" €\n");
            }
            }else{System.out.println("O Leilão para o id = " +id+" não lhe pertence!!");  }
        }else{
        System.out.println("O Leilão para o id = " +id+" não existe!!");
        }
        
        }finally{
        
        lockLei.unlock();
        
        }
           
    }
    
    public Licitacao constroi_Licitacao(String parse[],String idUti){
    Licitacao l;
        int id = Integer.parseInt(parse[1]);
        Float bid = Float.parseFloat(parse[2]);
        l = new Licitacao(id,idUti,bid);
        return l;
    }
    
    public void fazLicitacao(Licitacao l){
    }
}

