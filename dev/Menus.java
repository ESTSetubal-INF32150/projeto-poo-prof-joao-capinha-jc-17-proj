
import java.util.Scanner;

public class Menus {

    public void mainMenu(Ranking ranking){
        ranking = Ranking.getInstance();
        System.out.println("Bem Vindo ao Boats and Docks");
        System.out.println("");
        System.out.println("1 - Iniciar Sessão");
        System.out.println("2 - Registar Utilizador");
        System.out.println("0 - Sair");
        System.out.println("");
    }

    public Player playerMenuPrep(Ranking ranking){
        System.out.println("Realize o seu login");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();
        Player temp = new Player(name);
        if(ranking.getRanking().contains(temp.getUsername())){
            return temp;
        }else{
            System.out.println("O seu username não se encontra registado");
            return null;
        }
    }
    public void playerMenu(String name, Ranking ranking){
        System.out.println("Olá " + name);
        System.out.println(" ");
        System.out.println("1 – Iniciar Jogo");
        System.out.println("2 – Carregar Jogo");
        System.out.println("3 – Pontuações Pessoais");
        System.out.println("4 – Pontuações Gerais");
        System.out.println("5 – Criação de Níveis");
        System.out.println("0 – Sair ");
    }

    public void rankingPontuation(Ranking ranking){
        System.out.println(ranking.toString());
    }
}
