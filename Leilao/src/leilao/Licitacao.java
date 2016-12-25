/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

/**
 *
 * @author Joao
 */
public class Licitacao {
    private int idleilao;
    private String comprador;
    private float valor;

    public Licitacao() {
        this.idleilao = 0;
        this.valor = 0;
        this.comprador ="";
    }
    
    public Licitacao(int leilao, String comprador, float valor) {
        this.idleilao = leilao;
        this.comprador = comprador;
        this.valor = valor;
    }
    
    public Licitacao(Licitacao l) {
        this.idleilao = l.getLeilao();
        this.comprador = l.getComprador();
        this.valor = l.getValor();
    }
    public int getLeilao() {
        return this.idleilao;
    }

    public void setLeilao(int leilao) {
        this.idleilao = leilao;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
    
    public String toString() {
        StringBuilder s = new StringBuilder("######Licitação######\n");
        
        s.append("Id do Leilão:"+ this.getLeilao()+"\n");
        s.append("Nome do Comprador:"+ this.getComprador()+"\n");
        s.append("Valor:"+ this.getValor()+"€\n");
        return s.toString();
    }

    
}
