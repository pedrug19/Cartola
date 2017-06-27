package cartola;

import java.util.*;

public class Partida {

    private Scanner input = new Scanner(System.in);
    private int qtdGolsPrimTime;
    private int qtdGolsSegunTime;
    private Time time1, time2;

    public Partida(Time time1, Time time2) {
        this.time1 = time1;
        this.time2 = time2;
    }

    public void setGolsJogo() {
        System.out.println("Determinando o numero de gols no jogo.\n\n");
        qtdGolsPrimTime = 0;
        qtdGolsSegunTime = 0;
        int gols;
        
        System.out.println(time1.getNome());
        //Quantidade de gols do primeiro time
        for (int i = 0; i < time1.listaJogador.size(); i++) {
            gols = 0;
            System.out.println("Nome do jogador: " + time1.listaJogador.get(i).getNome());
            System.out.println("Número de gols na partida: ");
            gols = Integer.parseInt(input.nextLine());
            time1.listaJogador.get(i).setGols(gols);
            qtdGolsPrimTime = gols + qtdGolsPrimTime;
        }
        
        System.out.println("\n");
        
        System.out.println(time2.getNome());
        //Quantidade de gols do segundo time
        for (int i = 0; i < time2.listaJogador.size(); i++) {
            gols = 0;
            System.out.println("Nome do jogador: " + time2.listaJogador.get(i).getNome());
            System.out.println("Número de gols na partida: ");
            gols = Integer.parseInt(input.nextLine());
            time2.listaJogador.get(i).setGols(gols);
            qtdGolsSegunTime = gols + qtdGolsSegunTime;
        }
    }

    public void getResultadoPartida() {
        System.out.println(time1.getNome() + " " + qtdGolsPrimTime + " X " + qtdGolsSegunTime + " " + time2.getNome());
        System.out.println(time1.getEstadio());
    }

    public int timeVencedor() {
        if (qtdGolsPrimTime > qtdGolsSegunTime) {
            return 1;
        }
        if (qtdGolsPrimTime < qtdGolsSegunTime) {
            return 0;
        }
        return -1;
    }
    
    public String getNomeTime1(){
        return time1.getNome();
    }
    
    public String getNomeTime2(){
        return time2.getNome();
    }
    
    public void getPartidaNome(){
        System.out.println(time1.getNome() + " X " + time2.getNome());
        System.out.println("Estádio: " + time1.getEstadio());
    }
    
    public String getEstadioPartida(){
        return time1.getEstadio();
    }

    public int getGolsTime1() {
        return qtdGolsPrimTime;
    }

    public int getGolsTime2() {
        return qtdGolsSegunTime;
    }

}
