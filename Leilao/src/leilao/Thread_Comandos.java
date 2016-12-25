/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Joao
 */
public class Thread_Comandos implements Runnable{
    private Gestor gestor;
    private PrintWriter out;
    private String pedido;
    private String idUti;

    public Thread_Comandos(Gestor g, String id, PrintWriter out, String pedido) {
        this.gestor = g;
        this.out = out;
        this.pedido = pedido;
        this.idUti = id;
    }

    public void run() {
        String msg = null;
        String lista[] = pedido.split(":");
        int ie = 0;
        for (String s : lista) {
            lista[ie] = comeEspaco(s);
            ie++;
        }

        switch (lista[0]) {
            case "Leilao":
                try {
                    Leilao l = this.gestor.constroi_Leilao(lista, this.idUti);
                    if (this.gestor.getVendedores().containsKey(idUti)) {
                        this.gestor.inicia_Leilao(l, this.idUti);
                       // System.out.println(this.gestor.getEmCurso().toString());
                       // System.out.println(this.gestor.getVendedores().get(idUti).getItens_Leiloados().toString());
                        msg = "Criado Leilão com o id " + l.getId() + " com o valor inicial de " + l.getValor_inicial() + " €\n";
                    } else {
                        msg = "Não tem permissões para iniciar um leilão!!";
                    }
                }catch (IndexOutOfBoundsException e) {
                    msg = "comando errado: faltam argumentos";
                }catch (NumberFormatException e) {
                    msg = "comando errado: argumento não numerico";
                }
                break;

            case "Licitacao":
                try {
                    TreeSet<Licitacao> bids = new TreeSet<>();
                    if (this.gestor.getCompradores().containsKey(this.idUti)) {
                        Licitacao b = this.gestor.constroi_Licitacao(lista, this.idUti);
                        System.out.println(b.toString());
                        if (this.gestor.getEmCurso().containsKey(b.getLeilao())) {
                            bids = this.gestor.getHistoricoLicitacao().get(b.getLeilao());
                            Iterator it = bids.iterator();
                            Licitacao l = (Licitacao) it.next();
                            if (b.getValor() <= l.getValor()) {
                                msg = "Valor introduzido já foi ultrapassado!!";
                            } else {
                                this.gestor.fazLicitacao(b);
                            }

                        } else {
                            msg = "O id do Leilão introduzido não existe ou já terminou!!";
                        }
                    } else {
                        msg = "Não tem permissão para fazer Licitação";

                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.toString());
                    msg = "comando errado: faltam argumentos";
                } catch (NumberFormatException e) {
                    System.out.println(e.toString());
                    msg = "comando errado: argumento não numerico";
                }
                break;

            case "Termina":
                try {
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.toString());
                    msg = "comando errado: faltam argumentos";}
               // msg = this.armazem.listagemTarefas();
                break;
                
            case "ListagemEmCurso":
               // msg = this.armazem.listagemTarefas();
                break;
            case "ListagemTerminados":
               // msg = this.armazem.listagemTarefas();
                break;
    
            //comando de teste, imprime todo o armazem
            case "print":
              //  msg = this.armazem.toString();
                break;

            default:
                msg = "comando errado!";
                break;
        }

        //envia resposta ao Cliente
        out.println(msg);
        out.flush();
    }
    
    public String comeEspaco(String s) {
        int tam = s.length();
        char[] st = s.toCharArray();
        char res[] = new char[tam + 1];
        String msg = "";
        int i, j;
        i = 0;
        j = 0;
        while (j < tam && st[j] != ' ') {
            res[i] = st[j];
            i++;
            j++;
        }

        for (; j < tam; j++) {
            if (i > 0) {
                if (res[i - 1] == ' ' && st[j] == ' '); else if (j == tam - 1 && st[j] == ' '); else {
                    res[i] = st[j];
                    i++;
                }
            } else {
                if (st[j] != ' ') {
                    res[i] = st[j];
                    i++;
                }
            }
        }
        if (res[i - 1] == ' ') {
            i -= 1;
        }

        tam = i;
        for (i = 0; i < tam; i++) {
            msg = msg + res[i];
        }
        return msg;
    }

}
