package cartola;
import java.util.*;

public class Time {
    private String nome, estadio;
    private Jogador jogador;
    protected ArrayList<Jogador> listaJogador = new ArrayList<>();
    private int qtdGols;
    
    public Time(String nome, String estadio){
        this.nome = nome;
        this.estadio = estadio;
        qtdGols = 0;
    }
    
    public void addJogador(Jogador jogador){
        listaJogador.add(jogador);
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setEstadio(String estadio){
        this.estadio = estadio;
    }
    
    public int qtdJogador(){
        return listaJogador.size();
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getEstadio(){
        return estadio;
    }
    
    public int getQtdGols(){
        return qtdGols;
    }
    
    public void setQtdGols(int gols){
        qtdGols = gols;
    }
    
    public Jogador getJogador(Jogador jogador){
        for(int i = 0; i < listaJogador.size(); i++){
            if(jogador.getNome().equals(listaJogador.get(i).getNome())){
                return listaJogador.get(i);
            }
        }
        
        return null;
    }
}
