package proj.gr17;

import java.util.Scanner;

public class Menus {

    /**
     * Show the main menu
     */
    public void mainMenu(){
        System.out.println("Bem Vindo ao Boats and Docks");
        System.out.println("");
        System.out.println("1 - Iniciar Sessão");
        System.out.println("2 - Registar Utilizador");
        System.out.println("0 - Sair");
        System.out.println("");
    }

    /**
     * Show the menu for the user to login in
     * @return the player object corresponding to the username the player specified or null in case the player doesn't exist
     */
    public Player playerMenuPrep(){
        System.out.println("Realize o seu login");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();
        Player temp = Ranking.getInstance().getPlayer(name);
        if(temp != null){
            return temp;
        }else{
            System.out.println("O seu username não se encontra registado");
            return null;
        }
    }

    /**
     * Show the menu regarding the option about the game
     * @param name the name of tha player playing
     */
    public void playerMenu(String name){
        System.out.println("Olá " + name);
        System.out.println(" ");
        System.out.println("1 – Iniciar Jogo");
        System.out.println("2 – Carregar Jogo");
        System.out.println("3 – Pontuações Pessoais");
        System.out.println("4 – Pontuações Gerais");
        System.out.println("5 – Criação de Níveis");
        System.out.println("0 – Sair ");
    }

    /**
     * Show the options menu when a player chooses to start a game
     */
    public void menuPlay(){
        System.out.println("Escolha a Dificuldade");
        System.out.println(" ");
        System.out.println("1 – Iniciar Jogo aleatório");
        System.out.println("2 – Iniciar Jogo – Fácil");
        System.out.println("3 – Iniciar Jogo – Médio");
        System.out.println("4 – Iniciar Jogo - Difícil");
        System.out.println("0 – Sair ");
    }

    /**
     * Show the options menu when a player chooses to create a new level
     */
    public void menuCreateLevel(){
        System.out.println("Escolha a Dificuldade");
        System.out.println(" ");
        System.out.println("1 – Iniciar Jogo – Fácil");
        System.out.println("2 – Iniciar Jogo – Médio");
        System.out.println("3 – Iniciar Jogo - Difícil");
        System.out.println("0 – Sair ");
    }

    /**
     * Show the ranking table of all players
     * @param ranking the instance with information regarding all players
     */
    public void rankingPontuation(Ranking ranking){
        Scanner scan = new Scanner(System.in);

        System.out.println("Pontuações Gerais");

        System.out.println(ranking.toString());

        System.out.println("Prima qualquer tecla para voltar ao menu principal!");
        scan.nextLine();
    }

    /**
     * Show the ranking table with all of the specified player rankings
     * @param player the player to show all his rankings
     */
    public void playerPontuation(Player player) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Pontuações de " + player.getUsername());
        System.out.println("     Pontuação Geral: " + player.pontuationSum(player.getGamePontuation()));

        System.out.println(player.toString());

        System.out.println("Prima qualquer tecla para voltar ao menu principal!");
        scan.nextLine();
    }

}
