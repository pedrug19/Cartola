package cartola;

public abstract class Pessoa {
    protected String nome;
    protected int ano, mes, dia;
    
    public Pessoa(String nome, int ano, int mes, int dia){
        this.nome = nome;
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getDia(){
        return dia;
    }
    
    public int getMes(){
        return mes;
    }
    
    public int getAno(){
        return ano;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setDia(int dia){
        this.dia = dia;
    }
    public void setMes(int mes){
        this.mes = mes;
    }
    public void setAno(int ano){
        this.ano = ano;
    }
}