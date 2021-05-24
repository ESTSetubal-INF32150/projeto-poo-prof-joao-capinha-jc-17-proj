public class AppStart{
	
    private Game gameManager;
    private static Menus menusManager;
    private static Ranking rankings;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player playerPlaying = null;
        boolean playerLoggedIn = false;
        int opc = -1;

        menusManager = new Menus();

        while(!playerLoggedIn) {
            menusManager.mainMenu(rankings);

            opc = sc.nextInt();

            switch(opc) {
                case 1:
                    playerPlaying = menusManager.playerMenuPrep(rankings);

                    if(playerPlaying != null) {
                        playerLoggedIn = true;
                    }
                    break;
                case 2:
                    System.out.println("Insira o nome do Player (Case Sensitive)");
                    Scanner nameScanner = new Scanner(System.in);
                    String name = nameScanner.nextLine();
                    Player temp = new Player(name);
                    if(name == null){
                        System.out.println("Nome inválio");
                        break;
                    }else if(rankings.getRanking().contains(temp)){
                        System.out.println("Nome já foi Registado");
                        break;
                    }
                    rankings.getRanking().add(temp);
                    System.out.println("Player criado com Sucesso");
                    break;
                case 0:
                    return;
                default:
                    System.out.print("O Valor Introduzido é inválido");
                    break;
            }
        }

        while(true) {
            menusManager.playerMenu(playerPlaying.getUsername(), rankings);

            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    menusManager.rankingPontuation(rankings);
                    break;
                case 5:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Valor introduzido inválido");
                    break;
            }
        }
    }


}
