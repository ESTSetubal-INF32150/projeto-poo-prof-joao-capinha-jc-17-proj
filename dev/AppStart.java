

import java.util.Scanner;

public class AppStart{
	
    private Game gameManager;
    private static Menus menusManager;
    private static Ranking rankings;

    public static void main(String[] args) {
        Ranking ranking = Ranking.getInstance();
        Scanner sc = new Scanner(System.in);
        Player playerPlaying = null;
        boolean playerLoggedIn = false;
        boolean gameStart = false;
        int opc = -1;

        menusManager = new Menus();

        while(!playerLoggedIn) {
            menusManager.mainMenu();

            opc = sc.nextInt();

            switch(opc) {
                case 1: //Iniciar sessão de um player (Ir para Player Menu)
                    playerPlaying = menusManager.playerMenuPrep();

                    if(playerPlaying != null) {
                        playerLoggedIn = true;
                    }
                    break;
                case 2: //Registar Player
                    System.out.println("Insira o nome do Player (Case Sensitive)");
                    Scanner nameScanner = new Scanner(System.in);
                    String name = nameScanner.nextLine();
                    Player temp = new Player(name);
                    if(name == null){
                        System.out.println("Nome inválio");
                        break;
                    }else if(rankings.getInstance().getRanking().contains(temp)){
                        System.out.println("Nome já foi Registado");
                        break;
                    }
                    else {
                        ((rankings.getInstance()).getRanking()).add(temp);
                        System.out.println("Player criado com Sucesso");
                    }
                    break;
                case 0: //Sair
                    return;
                default:
                    System.out.print("O Valor Introduzido é inválido");
                    break;
            }
        }

        while(playerLoggedIn) {
            menusManager.playerMenu(playerPlaying.getUsername());

            opc = sc.nextInt();
            switch (opc) {
                case 1: //Começar Jogo (Menu Dificuldade)
                    gameStart = true;

                    while(gameStart){
                        menusManager.menuPlay();
                        opc = sc.nextInt();
                        Game game = new Game(playerPlaying);
                        switch (opc) {
                            case 1: //Jogo aleatório
                                break;
                            case 2: //Jogo Fácil
                                game.startGame(DificultyLevel.FÁCIL);
                                break;
                            case 3: //Jogo Médio
                                game.startGame(DificultyLevel.INTERMÉDIO);
                                break;
                            case 4: //Jogo Dificl
                                game.startGame(DificultyLevel.DIFÍCIL);
                                break;
                            case 0: //Voltar para o menu Player
                                gameStart = false;
                                return;
                            default:
                                System.out.println("Valor introduzido inválido");
                                break;
                        }
                    }
                    break;
                case 2: //Continuar jogo
                    break;
                case 3: //Pontuação Pessoal
                    menusManager.playerPontuation(playerPlaying);
                    break;
                case 4: //Pontuação Mundial
                    menusManager.rankingPontuation(rankings);
                    break;
                case 5: //Criar Mapa
                    playerPlaying = null;
                    playerLoggedIn = false;
                    break;
                case 0: //Voltar para o Main Menu
                    break;
                default:
                    System.out.println("Valor introduzido inválido");
                    break;
            }
        }
    }


}
