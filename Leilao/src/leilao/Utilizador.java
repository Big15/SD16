/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.util.concurrent.locks.ReentrantLock;

public class Utilizador {
    private String nome; //id
    private String password;
    public ReentrantLock l;
    private boolean loged; 
    
    public Utilizador() {
        this.nome = "";
        this.password = "";
        this.l = new ReentrantLock();
        this.loged = false;
    }

    public Utilizador(String nome, String password) {
        this.nome = nome;
        this.password = password;
        this.l = new ReentrantLock();
        this.loged = false;
    }
    
    public Utilizador(Utilizador u) {
        this.nome = u.getNome();
        this.password = u.getPassword();  
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        } else {
            Utilizador u = (Utilizador) o;
            return (this.getNome().equals(u.getNome()) && this.getPassword().equals(u.getPassword()));
        }
    }
    
    public boolean validaPass(String passwd) {
        return (this.password.equals(passwd));
    }
    
   
    public boolean login() {
        l.lock();
        try {
            if (!this.loged) {
                this.loged = true;
                return true;
            } else {
                return false;
            }
        } finally {
            l.unlock();
        }
    }

    public void logout() {
        l.lock();
        try {
            this.loged = false;
        } finally {
            l.unlock();
        }
    }

    public boolean getloged() {
        l.lock();
        try {
            return this.loged;
        } finally {
            l.unlock();
        }
    }
    
    @Override
    public String toString() {
        return "Utilizador{" + "nome=" + nome + ", password=" + password + '}';
    }
   
    public Utilizador clone() {
        return new Utilizador(this);
    }

}
