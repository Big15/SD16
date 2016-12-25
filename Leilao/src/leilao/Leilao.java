/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

public class Leilao {
    private int id;
    private String descricao;
    private String vendedor;
    private boolean estado;
    private float valor_actual;
    private float valor_inicial;

    public Leilao() {
        this.id = 0;
        this.descricao = "";
        this.vendedor = "";
        this.estado = false;
        this.valor_actual = 0;
        this.valor_inicial = 0;
    }

    public Leilao(int id,String descricao, float valor_inicial, String vendedor, boolean estado) {
        this.id = id;
        this.descricao = descricao;
        this.vendedor = vendedor;
        this.estado = estado;
        this.valor_actual = 0;
        this.valor_inicial = valor_inicial;
    }
    
    public Leilao(Leilao l) {
        this.id = l.getId();
        this.descricao = l.getDescricao();
        this.vendedor = l.getVendedor();
        this.estado = l.isEstado();
        this.valor_actual = l.getValor_actual();
        this.valor_inicial = l.getValor_inicial();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public float getValor_actual() {
        return valor_actual;
    }

    public void setValor_actual(float valor_actual) {
        this.valor_actual = valor_actual;
    }

    public float getValor_inicial() {
        return valor_inicial;
    }

    public void setValor_inicial(float valor_inicial) {
        this.valor_inicial = valor_inicial;
    }
    
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        } else {
            Leilao l = (Leilao) o;
            return (this.getId()==(l.getId()) && this.getDescricao().equals(l.getDescricao())
                    && this.getVendedor().equals(l.getVendedor()) && this.getValor_actual() == (l.getValor_actual())
                    && this.getValor_inicial() == (l.getValor_inicial()));
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("######Leilão######\n");
        
        s.append("Id do Leilão:"+ this.getId()+"\n");
        s.append("Criador do Leilão:"+ this.getVendedor()+"\n");
        s.append("Descrição do Item:"+ this.getDescricao()+"\n");
        s.append("Valor Actual:"+ this.getValor_actual()+"€\n");
        s.append("Valor Inicial:"+ this.getValor_inicial()+"€\n");
        s.append("Estado:"+ this.isEstado()+"\n");
        
        return s.toString();
    }

    public Leilao clone() {
        return new Leilao(this);
    }

    
}


