package proj.gr17;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class AppStart{
    public static void main(String[] args) {
        Menus menusManager = new Menus();
        Ranking rankings = Ranking.getInstance();
        Scanner sc = new Scanner(System.in);
        Player playerPlaying = null;
        boolean playerLoggedIn = false;
        boolean gameStart = false;
        boolean sair = false;
        int opc = -1;

        while(!sair) {
            sc = new Scanner(System.in);

            while (!playerLoggedIn) {
                menusManager.mainMenu();

                opc = sc.nextInt();

                switch (opc) {
                    case 1: //Iniciar sessão de um player (Ir para Player Menu)
                        try {
                            playerPlaying = menusManager.playerMenuPrep();

                            if (playerPlaying != null) {
                                playerLoggedIn = true;
                            }
                        }
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 2: //Registar Player
                        try {
                            System.out.println("Insira o nome do Player (Case Sensitive)");
                            Scanner nameScanner = new Scanner(System.in);
                            String name = nameScanner.nextLine();
                            Player temp = new Player(name);
                            if (name == null) {
                                System.out.println("Nome inválio");
                                break;
                            } else if (rankings.getRanking().contains(temp)) {
                                System.out.println("Nome já foi Registado");
                                break;
                            } else {
                                ((rankings).getRanking()).add(temp);
                                System.out.println("Player criado com Sucesso");
                            }
                        }
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 0: //Sair
                        rankings.saveRanking(rankings.getRanking());
                        sair = true;
                        return;
                    default:
                        System.out.print("O Valor Introduzido é inválido");
                        break;
                }
            }

            sc = new Scanner(System.in);

            while (playerLoggedIn) {
                menusManager.playerMenu(playerPlaying.getUsername());

                opc = sc.nextInt();
                switch (opc) {
                    case 1: //Começar Jogo (Menu Dificuldade)
                        gameStart = true;

                        menusManager.menuPlay();
                        String casaEscolhida = "";
                        opc = sc.nextInt();
                        Game game = new Game(playerPlaying);

                        try {
                            switch (opc) {
                                case 1: //Jogo aleatório
                                    game.startGame(DificultyLevel.ALEATÓRIO);
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
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                        sc = new Scanner(System.in);

                        while (gameStart) {
                            System.out.print("Indique a próxima jogada (Horizontal, Vertical): ");
                            casaEscolhida = sc.nextLine();

                            if (casaEscolhida.equals("CANCEL")) {
                                game.endGame();
                                gameStart = false;
                            } else if (casaEscolhida.equals("SAVE")) {
                                //guardar o jogo para o ficheiro
                            } else if (casaEscolhida.equals("VALIDATE")) {
                                try {
                                    boolean canValidate = game.validateBoard();

                                    if (canValidate) {
                                        int qtdWrongTiles = game.checkTilePlacementCorrect();

                                        if (qtdWrongTiles > 0) {
                                            System.out.println("Há " + qtdWrongTiles + " casas incorretas!");
                                        } else {
                                            String username = playerPlaying.getUsername();
                                            Pontuation pontuation = new Pontuation(game.getBasePoints(), game.getDifficultyLevelOrdinal(), game.getGameStarted());

                                            rankings.getRanking().stream().filter(x -> x.getUsername().equals(username)).findAny().get().addGamePontuation(pontuation);

                                            playerPlaying = rankings.getRanking().stream().filter(x -> x.getUsername().equals(username)).findAny().get();

                                            game.endGame();
                                            gameStart = false;
                                        }
                                    } else {
                                        System.out.println("Ainda não atracaste um barco pelo menos em todos os portos!");
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            } else {
                                try {
                                    int x = Character.getNumericValue(casaEscolhida.charAt(0));
                                    int y = Character.getNumericValue(casaEscolhida.charAt(2));

                                    game.toggleTile(x, y);
                                }
                                catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
                        break;
                    case 2: //Continuar jogo
                        break;
                    case 3: //Pontuação Pessoal
                        try {
                            menusManager.playerPontuation(playerPlaying);
                        }
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 4: //Pontuação Mundial
                        menusManager.rankingPontuation(rankings);
                        break;
                    case 5: //Criar Mapa
                        gameStart = true;

                        menusManager.menuCreateLevel();
                        String nomeNivel = "";
                        opc = sc.nextInt();
                        Game levelCreator = new Game(playerPlaying);
                        int numPortsPossible = 0;
                        int qtdPortos = 0;

                        sc = new Scanner(System.in);

                        System.out.print("Nome do nível: ");
                        nomeNivel = sc.nextLine();

                        try {
                            switch (opc) {
                                case 1: //Jogo Fácil
                                    numPortsPossible = 5;
                                    levelCreator.startLevelCreator(DificultyLevel.FÁCIL);
                                    break;
                                case 2: //Jogo Médio
                                    numPortsPossible = 10;
                                    levelCreator.startLevelCreator(DificultyLevel.INTERMÉDIO);
                                    break;
                                case 3: //Jogo Dificl
                                    numPortsPossible = 17;
                                    levelCreator.startLevelCreator(DificultyLevel.DIFÍCIL);
                                    break;
                                case 0: //Voltar para o menu Player
                                    gameStart = false;
                                    return;
                                default:
                                    System.out.println("Valor introduzido inválido");
                                    break;
                            }
                        }
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                        boolean validated = false;

                        while (gameStart) {
                            casaEscolhida = sc.nextLine();

                            if (casaEscolhida.equals("CANCEL")) {
                                levelCreator.endGame();
                                gameStart = false;
                            } else if (casaEscolhida.equals("SAVE")) {
                                if(!validated) {
                                    System.out.println("Antes de conseguir gravar o nível tem que validar escrevendo 'VALIDATE'!");
                                }
                                else {
                                    try {
                                        levelCreator.saveLevel(nomeNivel, playerPlaying.getUsername());
                                        levelCreator.endGame();
                                        gameStart = false;
                                    }
                                    catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }
                            } else if (casaEscolhida.equals("VALIDATE")) {
                                if(qtdPortos > 0) {
                                    try {
                                        boolean canValidate = levelCreator.validateBoardLevelCreator();

                                        if (canValidate) {
                                            System.out.println("O tabuleiro está correto! Escreva 'SAVE' para guardar o nível criado!");
                                            validated = true;
                                        } else {
                                            System.out.println("Ainda não atracaste um barco pelo menos em todos os portos!");
                                        }
                                    }
                                    catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }
                                else {
                                    System.out.println("Tens que colocar pelo menos um porto!");
                                }
                            } else {
                                try {
                                    int x = Character.getNumericValue(casaEscolhida.charAt(0));
                                    int y = Character.getNumericValue(casaEscolhida.charAt(2));
                                    char casa = casaEscolhida.charAt(4);

                                    if (casa == 'B') {
                                        levelCreator.setTileType(x, y, TileType.BARCO);
                                    } else if (casa == 'P') {
                                        if(qtdPortos == numPortsPossible) {
                                            System.out.println("Já não pode colocar mais portos!");
                                        }
                                        else {
                                            qtdPortos++;
                                            levelCreator.setTileType(x, y, TileType.PORTO);
                                        }
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
                        break;
                    case 0: //Voltar para o Main Menu
                        playerLoggedIn = false;
                        break;
                    default:
                        System.out.println("Valor introduzido inválido");
                        break;
                }
            }
        }
    }
}
