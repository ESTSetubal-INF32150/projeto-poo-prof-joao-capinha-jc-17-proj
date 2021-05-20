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
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch(i) {
            case 1:
                playerMenuPrep(ranking);
                break;
            case 2:
                System.out.println("Insira o nome do Player (Case Sensitive)");
                Scanner nameScanner = new Scanner(System.in);
                String name = nameScanner.nextLine();
                Player temp = new Player(name);
                    if(name == null){
                        System.out.println("Nome inválio");
                        mainMenu(ranking);
                        break;
                    }else if(ranking.getRanking().contains(temp.getUsername())){
                        System.out.println("Nome já foi Registado");
                        mainMenu(ranking);
                        break;
                    }
                    ranking.getRanking().add(temp);
                System.out.println("Player criado com Sucesso");
                mainMenu(ranking);
                break;
            case 0:
                break;
            default:
                System.out.print("O Valor Introduzido é inválido");
                break;

        }
    }

    public void playerMenuPrep(Ranking ranking){
        System.out.println("Realize o seu login");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();
        Player temp = new Player(name);
        if(ranking.getRanking().contains(temp.getUsername())){
            playerMenu(name, ranking);
        }else{
            System.out.println("O seu username não se encontra registado");
            mainMenu(ranking);
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
    Scanner sc = new Scanner(System.in);
    int i = sc.nextInt();
            switch (i){
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            rankingPontuation(ranking);
            break;
        case 5:
            break;
        case 0:
            mainMenu(ranking);
            break;
        default:
            System.out.println("Valor introduzido inválido");
            playerMenu(name, ranking);
            break;
    }
    }

    public void rankingPontuation(Ranking ranking){
        System.out.println(ranking.toString());
    }
}
