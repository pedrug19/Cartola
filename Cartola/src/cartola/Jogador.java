package cartola;

public class Jogador extends Pessoa{
    int numGols;
    
    public Jogador(String nome, int ano, int mes, int dia) {
        super(nome, ano, mes, dia);
        numGols = 0;
    }
    
    public void setGols(int numGols){
        this.numGols += numGols;
    }
    
    public int getGols(){
        return numGols;
    }
}
