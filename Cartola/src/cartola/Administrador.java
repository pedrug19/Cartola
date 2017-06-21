package cartola;

public class Administrador extends Participante {

    public Administrador(String nome, int ano, int mes, int dia, String login, String senha) {
        super(nome, ano, mes, dia, login, senha);
    }
    
    public void setResultadoRodada(Rodada rodada){
        rodada.setResultado();
    }
}
