package cartola;

import java.io.*;
import java.util.*;

public class Rodada {

    private Scanner input = new Scanner(System.in);
    private Time time1, time2, time3, time4;
    private Partida partida1, partida2;

    public Rodada(Time time1, Time time2, Time time3, Time time4) {
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        partida1 = new Partida(time1, time2);
        partida2 = new Partida(time3, time4);

    }
    
    public void getInfo(){
        System.out.println("Partida 1: ");
        partida1.getPartidaNome();
        System.out.println("Partida 2: ");
        partida2.getPartidaNome();
    }
    
    public Partida getPartida1(){
        return partida1;
    }
    
    public Partida getPartida2(){
        return partida2;
    }
    
    public void setResultado(){
        partida1.getPartidaNome();
        partida1.setGolsJogo();
        partida2.getPartidaNome();
        partida2.setGolsJogo();
    }

    public void getResultadoRodada() {
        partida1.getResultadoPartida();
        partida2.getResultadoPartida();
    }
}
