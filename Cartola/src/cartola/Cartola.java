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
    static int maxPontos = 0;
    static String vencedor;
    static String artilheiro;
    static String timeArtilheiro;

    static Scanner input = new Scanner(System.in);

    /**
     * *************************************************************************
     * ----------------MÉTODOS DE CADASTRO USANDO ARQUIVOS DE TEXTO-------------
     * *************************************************************************
     * @param nomearq
     */
    //Ler arquivo e cadastrar participante
    public static void cadastraParticipante(String nomearq) {
        try {
            File arq = new File(nomearq);
            if (!arq.exists()) {
                System.out.println("Arquivo não existe.");
                controle();
            }
            if (!arq.canRead()) {
                System.out.println("Arquivo não pode ser lido.");
                controle();
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
    public static int cadastraAdm(String namearq) {
        try {
            //recebe nome do arquivo
            File arq = new File(namearq);

            if (!arq.exists()) { //verifica sua existência
                System.out.println("Arquivo não existe.");
                return 0;
            }

            if (!arq.canRead()) { //verifica se arquivo está corrompido
                System.out.println("Arquivo não pode ser lido.");
                return 0;
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

        return 1;

    }

    //Ler arquivo e cadastra jogador
    public static void cadastraJogador(Time time, String nomearq) {
        try {
            File arq = new File(nomearq);
            if (!arq.exists()) {
                System.out.println("Arquivo não existe.");
                controle();
            }
            if (!arq.canRead()) {
                System.out.println("Arquivo não pode ser lido.");
                controle();
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
                controle();
            }
            if (!arq.canRead()) {
                System.out.println("Arquivo não pode ser lido.");
                controle();
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
                System.out.println("Digite o caminho do arquivo que possui os jogadores do time: ");
                nomearq = input.nextLine();
                cadastraJogador(times, nomearq);
                listaTimes.add(times);
            }
            for (int i = 0; i < listaTimes.size(); i++) {
                if (listaTimes.get(i).getTamanhoLista() < 3) {
                    System.out.println("Número de jogadores insuficiente! Voltando para o começo do programa.");
                    controle();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo." + e.getMessage());
        }
    }
  
    /**
     * *************************************************************************
     * -----------MÉTODOS AUXILIARES PARA AUXILIAR O FUNCIONAMENTO DO-----------
     * -----------------------------PROGRAMA------------------------------------
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
            for (int j = 0; j < listaTimes.get(i).getJogadores().size(); j++) {
                /*Se o numero de gols registrado no jogador for maior que o maximo
                atribui esse valor ao maximo de gols e registra o nome do jogador e o nome do time*/
                if (listaTimes.get(i).getJogadores().get(j).numGols > maxGols) {
                    maxGols = listaTimes.get(i).getJogadores().get(j).numGols;
                    artilheiro = listaTimes.get(i).getJogadores().get(j).getNome();
                    timeArtilheiro = listaTimes.get(i).getNome();
                }
            }
        }
    }

    /*
    *   DETERMINANDO O VENCEDOR DO BOLÃO
     */
    public static void determinaVencedorBolao() {
        int i;

        for (i = 0; i < listaPart.size(); i++) {
            if (listaPart.get(i).getPontuacao() > maxPontos) {
                maxPontos = listaPart.get(i).getPontuacao();
                vencedor = listaPart.get(i).getNome();
            }
        }

        for (i = 0; i < listaAdm.size(); i++) {
            if (listaAdm.get(i).getPontuacao() > maxPontos) {
                maxPontos = listaAdm.get(i).getPontuacao();
                vencedor = listaAdm.get(i).getNome();
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
        String art, timeArt;

        System.out.println("Fazendo apostas: ");
        System.out.println("Rodada " + r);

        rodada.getInfo(); //mostrar as informações das rodadas para as apostas

        //Se for a primeira rodada fazendo apostas nos artilheiros
        if (r == 1) {
            System.out.println("Apostando nos artilheiros: ");
            for (i = 0; i < listaPart.size(); i++) {
                System.out.println("Participante: " + listaPart.get(i).getNome());
                System.out.println("Nome do artilheiro: ");
                art = input.nextLine();
                System.out.println("Time do artilheiro: ");
                timeArt = input.nextLine();
                listaPart.get(i).setArtilheiro(art, timeArt);
            }

            for (i = 0; i < listaAdm.size(); i++) {
                System.out.println("Participante: " + listaAdm.get(i).getNome());
                System.out.println("Nome do artilheiro: ");
                art = input.nextLine();
                System.out.println("Time do artilheiro: ");
                timeArt = input.nextLine();
                listaAdm.get(i).setArtilheiro(art, timeArt);
            }

        }

        for (i = 0; i < listaPart.size(); i++) {
            System.out.println("Participante: " + listaPart.get(i).getNome());
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
        
        String nomeArq;
        int dia, mes, ano;
        
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

            opc = Integer.parseInt(input.nextLine());

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
                opc = Integer.parseInt(input.nextLine());
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
            System.out.println("2 - Mostrar Artilheiro do Campeonato");
            System.out.println("3 - Mostrar Participantes");
            System.out.println("4 - Fazer aposta e cadastrar resultado");
            System.out.println("5 - Sair");

            opc = Integer.parseInt(input.nextLine());
            switch (opc) {
                case 1:
                    for (int i = 0; i < listaTimes.size(); i++) {
                        System.out.println("Nome do time: " + listaTimes.get(i).getNome());
                        System.out.println("Estádio: " + listaTimes.get(i).getEstadio());
                        System.out.println("Número de jogadores: " + listaTimes.get(i).getJogadores().size() + "\n");
                        System.out.println("Jogadores: ");
                        for(int j = 0; j < listaTimes.get(i).getJogadores().size(); j++){
                            System.out.println("Nome: " + listaTimes.get(i).getJogadores().get(j).getNome());
                            dia = listaTimes.get(i).getJogadores().get(j).getDia();
                            mes = listaTimes.get(i).getJogadores().get(j).getMes();
                            ano = listaTimes.get(i).getJogadores().get(j).getAno();
                            System.out.println("Data de nascimento: " + dia + "/" + mes + "/" + ano);
                            System.out.println("Gols: " + listaTimes.get(i).getJogadores().get(j).getGols());
                            System.out.print("\n"); //quebra de linha
                        }
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
                    System.out.println("Participantes: \n");
                    for(int i = 0; i < listaPart.size(); i++){
                        listaPart.get(i).getParticipante();
                    }
                    for(int i = 0; i < listaAdm.size(); i++){
                        listaAdm.get(i).getParticipante();
                    }
                    break;
                case 4:
                    //Criando os objetos de rodada, de acordo com as rodadas
                    //SIM, é força bruta :(
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

                    fazerApostas(r, rodada[r - 1], adm); //Fazendo as apostas de cada participante
                    rodada[r-1].getResultadoRodada();
                    determinaArtilheiro(); //determinando o artilheiro a cada rodada
                    
                    //passando para a próxima rodada
                    r = r+1;
                    break;
                case 5:
                    break;
            }

        } while (opc != 4 && r < 7);

        //Saindo do programa caso opc == 4
        if (opc == 4) {
            return;
        }

        //Somando os pontos finais ao final das rodadas
        for (int i = 0; i < listaPart.size(); i++) {
            listaPart.get(i).somaPontosFinais(artilheiro, timeArtilheiro);
        }

        for (int i = 0; i < listaAdm.size(); i++) {
            listaAdm.get(i).somaPontosFinais(artilheiro, timeArtilheiro);
        }

        determinaVencedorBolao();
        System.out.println("O vencedor do bolão é o participante " + vencedor + " com " + maxPontos + " pontos!");
        System.out.println("Obrigado por jogar o bolão do Esfera.com!");

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
    /*
     * *************************************************************************
     * ------------------MÉTODO PRINCIPAL E CONTROLE----------------------------
     * *************************************************************************
     * @param args
     */
    public static void controle() {
        listaTimes.clear();
        listaAdm.clear();
        listaPart.clear();
        String nomeArqAdm;
        System.out.println("Digite o caminho do arquivo de adm: ");
        nomeArqAdm = input.nextLine();
        if (cadastraAdm(nomeArqAdm) == 1) {
            loginAdm();
        } else {
            controle();
        }
    }

    //Serve apenas para chamar as outras funções auxiliares
    public static void main(String[] args) {
        System.out.println("Iniciando programa...\n\n");
        //Essa função desencadeia todas as outras funções!
        controle();
    }
}
