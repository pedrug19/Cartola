package cartola;

import java.util.*;
import java.io.*;

/*
*   TODO: VERIFICAR SE TEM PELO MENOS 3 JOGADORES
*   CADASTRAR RESULTADOS
 */
public class Cartola {

    static String nome, estadio, login, senha;
    static int dia, mes, ano;

    static ArrayList<Participante> listaPart = new ArrayList<>();
    static ArrayList<Administrador> listaAdm = new ArrayList<>();
    public static ArrayList<Time> listaTimes = new ArrayList<>();

    static Participante part;
    static Administrador adm;
    static Jogador jogador;
    static Time times;
    static Rodada rodada[] = new Rodada[6];

    static int maxGols = 0;
    static String artilheiro;
    static String timeArtilheiro;

    static Scanner input = new Scanner(System.in);

    /**
     * *************************************************************************
     * ----------------MÉTODOS DE CADASTRO USANDO ARQUIVOS DE
     * TEXTO---------------
     * *************************************************************************
     * @param nomearq
     */
    //Ler arquivo e cadastrar participante
    public static void cadastraParticipante(String nomearq) {
        try {
            File arq = new File(nomearq);
            if (!arq.exists()) {
                System.out.println("Arquivo não existe.");
                return;
            }
            if (!arq.canRead()) {
                System.out.println("Arquivo não pode ser lido.");
                return;
            }
            FileReader arqRead = new FileReader(arq);
            BufferedReader a = new BufferedReader(arqRead);
            String linha = a.readLine();
            while (linha != null) {
                nome = linha;
                linha = a.readLine();
                dia = Integer.parseInt(linha);
                linha = a.readLine();
                mes = Integer.parseInt(linha);
                linha = a.readLine();
                ano = Integer.parseInt(linha);
                linha = a.readLine();
                login = linha;
                linha = a.readLine();
                senha = linha;
                linha = a.readLine();
                part = new Participante(nome, ano, mes, dia, login, senha);
                listaPart.add(part);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo." + e.getMessage());
        }
    }

    //Ler arquivo e cadastra administrador
    public static void cadastraAdm() {
        try {
            //recebe nome do arquivo
            File arq = new File("administrador.txt");

            if (!arq.exists()) { //verifica sua existência
                System.out.println("Arquivo não existe.");
                return;
            }

            if (!arq.canRead()) { //verifica se arquivo está corrompido
                System.out.println("Arquivo não pode ser lido.");
                return;
            }
            //inicia a leitura
            FileReader arqRead = new FileReader(arq);
            BufferedReader a = new BufferedReader(arqRead);
            String linha = a.readLine();
            //Lê e registra linha por linha
            while (linha != null) {
                nome = linha;
                linha = a.readLine();
                dia = Integer.parseInt(linha);
                linha = a.readLine();
                mes = Integer.parseInt(linha);
                linha = a.readLine();
                ano = Integer.parseInt(linha);
                linha = a.readLine();
                login = linha;
                linha = a.readLine();
                senha = linha;
                linha = a.readLine();
                adm = new Administrador(nome, ano, mes, dia, login, senha); //cria objeto para armazenar na arraylist
                listaAdm.add(adm);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo." + e.getMessage());
        }

    }

    //Ler arquivo e cadastra jogador
    public static void cadastraJogador(Time time, String nomearq) {
        try {
            File arq = new File(nomearq);
            if (!arq.exists()) {
                System.out.println("Arquivo não existe.");
                return;
            }
            if (!arq.canRead()) {
                System.out.println("Arquivo não pode ser lido.");
                return;
            }
            FileReader arqRead = new FileReader(arq);
            BufferedReader a = new BufferedReader(arqRead);
            String linha = a.readLine();
            while (linha != null) {
                nome = linha;
                linha = a.readLine();
                dia = Integer.parseInt(linha);
                linha = a.readLine();
                mes = Integer.parseInt(linha);
                linha = a.readLine();
                ano = Integer.parseInt(linha);
                linha = a.readLine();
                jogador = new Jogador(nome, ano, mes, dia);
                time.addJogador(jogador);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo." + e.getMessage());
        }
    }

    //Lê arquivo e cadastra time
    public static void cadastraTime(String arqNome) {
        String nomearq;
        try {
            File arq = new File(arqNome);
            if (!arq.exists()) {
                System.out.println("Arquivo não existe.");
                return;
            }
            if (!arq.canRead()) {
                System.out.println("Arquivo não pode ser lido.");
                return;
            }
            FileReader arqRead = new FileReader(arq);
            BufferedReader a = new BufferedReader(arqRead);
            String linha = a.readLine();
            while (linha != null) {
                nome = linha;
                linha = a.readLine();
                estadio = linha;
                linha = a.readLine();
                times = new Time(nome, estadio);
                System.out.println("Digite o caminho do arquivo que possui os times do jogador: ");
                nomearq = input.nextLine();
                cadastraJogador(times, nomearq);
                listaTimes.add(times);
            }
            if (times.listaJogador.size() < 3) { //TA ERRADO
                System.out.println("Numero de jogadores insuficiente!");
                System.out.println("Tente novamente.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo." + e.getMessage());
        }
    }

    /**
     * *************************************************************************
     * -----------MÉTODOS AUXILIARES PARA AUXILIAR O
     * FUNCIONAMENTO---------------- ----------------------------DO
     * PROGRAMA------------------------------------
     * *************************************************************************
     */
    //DETERMINAR ARTILHEIRO
    /*
    *       Esse método determina o Artilheiro do campeonato
    *       Será executada ao fim das rodadas
     */
    public static void determinaArtilheiro() {
        //Busca time por time
        for (int i = 0; i < listaTimes.size(); i++) {
            //Em cada time, busca cada jogador e a quantidade de gols feitos no campeonato
            for (int j = 0; j < listaTimes.get(i).listaJogador.size(); j++) {
                /*Se o numero de gols registrado no jogador for maior que o maximo
                atribui esse valor ao maximo de gols e registra o nome do jogador e o nome do time*/
                if (listaTimes.get(i).listaJogador.get(j).numGols > maxGols) {
                    maxGols = listaTimes.get(i).listaJogador.get(j).numGols;
                    artilheiro = listaTimes.get(i).listaJogador.get(j).getNome();
                    timeArtilheiro = listaTimes.get(i).getNome();
                }
            }
        }
    }

    //MÉTODO PARA CADASTRAR AS APOSTAS DE CADA PARTICIPANTE
    /*
    *       Esse método cadastra cada palpite dos participantes
    *       Incluindo os administradores
    *       Não é preciso fazer login
     */
    public static void fazerApostas(int r, Rodada rodada, Administrador adm) {
        int i;

        System.out.println("Fazendo apostas: ");
        System.out.println("Rodada " + r);

        rodada.getInfo(); //mostrar as informações das rodadas para as apostas

        for (i = 0; i < listaPart.size(); i++) {
            listaPart.get(i).setResultadosPartida(rodada.getPartida1()); //Cada Participante dá palpites para as partidas
            listaPart.get(i).setResultadosPartida(rodada.getPartida2());
        }

        System.out.println("Administrador fazendo apostas: ");
        for (i = 0; i < listaAdm.size(); i++) {
            System.out.println("Administrador: " + listaAdm.get(i).getNome());
            listaAdm.get(i).setResultadosPartida(rodada.getPartida1());
            listaAdm.get(i).setResultadosPartida(rodada.getPartida2());
        }

        //Cadastrando o resultado real da partida
        System.out.println("Administrador, cadastre o resultado real das partidas: ");
        adm.setResultadoRodada(rodada);

        //Contando os pontos para cada Participante
        for (i = 0; i < listaPart.size(); i++) {
            listaPart.get(i).contaPontos(rodada.getPartida1()); //verificando se o participante acertou
            listaPart.get(i).contaPontos(rodada.getPartida2());
        }

        //Contando os pontos para cada Administrador
        for (i = 0; i < listaAdm.size(); i++) {
            listaAdm.get(i).contaPontos(rodada.getPartida1()); //verificando se o administrador acertou
            listaAdm.get(i).contaPontos(rodada.getPartida2());
        }

    }

    //MENU DE OPÇÕES DO ADMINISTRADOR
    /*
    *       Esse método é tecnicamente o corpo do programa
     */
    public static void menuOpcoesAdm(Administrador adm) {
        int opc = 0, contaRodada = 1;
        int r = 1; //contador de rodadas
        boolean ap = false; //verificar se apostas foram feitas
        String nomeArq;
        System.out.println("Bem vindo ao esfera.com\n\n");
        System.out.println("Este é o menu de opções de administrador. Digite o que deseja fazer: ");

        /*
        *   CADASTRO DE PARTICIPANTES E TIMES. CASO O NUMERO DE TIMES CHEGUE A 4, O 
        *   SISTEMA NÃO DEIXA MAIS NENHUM ADMINISTRADOR CADASTRAR TIMES, APENAS PARTICIPANTES
         */
        do {
            System.out.println("1 - Cadastrar Time");
            System.out.println("2 - Cadastrar Participante");
            System.out.println("3 - Sair");

            opc = input.nextInt();
            input.next();

            switch (opc) {
                case 1:
                    if (listaTimes.size() == 4) {
                        System.out.println("Número máximo de times atingido");
                    } else {
                        System.out.println("Digite o caminho completo do arquivo de times: ");
                        nomeArq = input.nextLine();
                        cadastraTime(nomeArq);
                    }
                    break;
                case 2:
                    System.out.println("Digite o caminho completo do arquivo de participantes: ");
                    nomeArq = input.nextLine();
                    cadastraParticipante(nomeArq);
                    break;
                case 3:
                    loginAdm();
                    break;
            }

            if (listaTimes.size() == 4 && listaPart.isEmpty() == false) {
                System.out.println("Continuar cadastrando participantes? 2 - Sim. 4 - Não.");
                opc = input.nextInt();
                input.next();
            }

        } while (opc != 4);

        /*
        *   !!!!!!!INÍCIO DO CAMPEONATO, NÃO PODE MAIS MUDAR DE ADM!!!!!!!!!!!
        *         (OBS: DARIA MUITO TRABALHO SE PUDESSE MUDAR DE ADM)
         */
        System.out.println("\n\nO campeonato começou! Registre os resultados e apostas dos participantes!");
        opc = 0;

        do {
            System.out.println("\nRODADA " + r);
            System.out.println("1 - Mostrar Times");
            System.out.println("2 - Mostrar Artilheiro");
            System.out.println("3 - Fazer aposta e cadastrar resultado");
            System.out.println("4 - Alterar informações de time");

            opc = input.nextInt();
            input.next();
            switch (opc) {
                case 1:
                    for (int i = 0; i < listaTimes.size(); i++) {
                        System.out.println("Nome do time: " + listaTimes.get(i).getNome());
                        System.out.println("Estádio: " + listaTimes.get(i).getEstadio());
                        System.out.println("Número de jogadores: " + listaTimes.get(i).listaJogador.size() + "\n");
                    }
                    break;
                case 2:
                    if (r == 1) {
                        System.out.println("Nenhum artilheiro ainda!");
                    } else {
                        System.out.println("Nome: " + artilheiro);
                        System.out.println("Time: " + timeArtilheiro + "\n");
                    }
                    break;
                case 3:
                    //Criando os objetos de rodada, de acordo com as rodadas
                    if (r == 1) {
                        rodada[0] = new Rodada(listaTimes.get(0), listaTimes.get(1), listaTimes.get(2), listaTimes.get(3));
                    }
                    if (r == 2) {
                        rodada[1] = new Rodada(listaTimes.get(0), listaTimes.get(2), listaTimes.get(1), listaTimes.get(3));
                    }
                    if (r == 3) {
                        rodada[2] = new Rodada(listaTimes.get(0), listaTimes.get(3), listaTimes.get(1), listaTimes.get(2));
                    }
                    if (r == 4) {
                        rodada[3] = new Rodada(listaTimes.get(1), listaTimes.get(0), listaTimes.get(3), listaTimes.get(2));
                    }
                    if (r == 5) {
                        rodada[4] = new Rodada(listaTimes.get(2), listaTimes.get(0), listaTimes.get(3), listaTimes.get(1));
                    }
                    if (r == 6) {
                        rodada[5] = new Rodada(listaTimes.get(2), listaTimes.get(1), listaTimes.get(3), listaTimes.get(0));
                    }

            }

        } while (opc != -1);

    }

    /*
    *       Método para fazer login como administrador
    *       Você pode sair do programa principal algumas vezes antes da primeira
    *       rodada começar.
    *       Mas assim que a rodada começa, não é possivel mais fazer login
     */
    public static void loginAdm() {
        String log, passw;
        int i;
        boolean loginCorreto = false;

        System.out.println("Digite o login de administrador: ");
        log = input.nextLine();
        System.out.println("Digite a senha do administrador: ");
        passw = input.nextLine();

        //Verifica se o login está correto
        for (i = 0; i < listaAdm.size(); i++) {
            if (listaAdm.get(i).getLogin().equals(log)) {
                if (listaAdm.get(i).getSenha().equals(passw)) {
                    loginCorreto = true;
                    break;
                }
            }
        }

        if (loginCorreto) {
            System.out.println("Login realizado com sucesso");
            menuOpcoesAdm(listaAdm.get(i));
        } else {
            System.out.println("Login ou Senha inválidos, tente novamente.");
            loginAdm();
        }
    }

    /**
     * *************************************************************************
     * ------------------------MÉTODO PRINCIPAL---------------------------------
     * *************************************************************************
     * @param args
     */
    //Serve apenas para chamar as outras funções auxiliares
    public static void main(String[] args) {

        cadastraAdm();
        loginAdm();

    }
}
