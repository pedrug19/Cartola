package cartola;

import java.util.*;

public class Participante extends Pessoa {
    protected Scanner e = new Scanner(System.in);
    protected String login;
    protected String senha;
    protected int pontuacao;
    
    protected int golsTime1;
    protected int golsTime2;
    
    protected String artilheiro;
    protected String timeArtilheiro;

    public Participante(String nome, int ano, int mes, int dia, String login, String senha) {
        super(nome, ano, mes, dia);
        this.login = login;
        this.senha = senha;
        pontuacao = 0;
        golsTime1 = 0;
        golsTime2 = 0;
    }
    
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPontuacao(int add) {
        pontuacao += add;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getGolsTime1() {
        return golsTime1;
    }

    public int getGolsTime2() {
        return golsTime2;
    }

    public void setGolsTime1(int golsTime1) {
        this.golsTime1 = golsTime1;
    }

    public void setGolsTime2(int golsTime2) {
        this.golsTime2 = golsTime2;
    }
    
    public void setResultadosPartida(Partida partida){
        System.out.println("Participante: " + this.getNome());
        System.out.println("Resultado previsto: ");
        System.out.println(partida.getNomeTime1() + ":");
        setGolsTime1(Integer.parseInt(e.nextLine()));
        System.out.println(partida.getNomeTime2() + ":");
        setGolsTime2(Integer.parseInt(e.nextLine()));
    }

    public void getParticipante() {
        System.out.println("Nome: " + nome);
        System.out.println("Data de nascimento: " + dia + "/" + mes + "/" + ano);
        System.out.println("Login: " + login);
        System.out.println("Senha: " + senha);
    }

    public void contaPontos(Partida partida) {
        int qtdGolsTime1 = partida.getGolsTime1();
        int qtdGolsTime2 = partida.getGolsTime2();
        int vencedor = 0;

        if (golsTime1 > golsTime2) {
            vencedor = 1;
        }
        if (golsTime2 > golsTime2) {
            vencedor = 0;
        }
        if (golsTime1 == golsTime2) {
            vencedor = -1;
        }

        if (golsTime1 == qtdGolsTime1 && golsTime2 == qtdGolsTime2) {
            this.setPontuacao(10);
        } else {
            if (vencedor == partida.timeVencedor()) {
                this.setPontuacao(5);
            }
        }
    }
    
    public void somaPontosFinais(String artilheiro, String timeArtilheiro){
        if(this.artilheiro.equals(artilheiro) && this.timeArtilheiro.equals(timeArtilheiro)){
            this.setPontuacao(25);
        }
    }
}
