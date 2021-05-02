public class AppStart{
	
	public static void main(String[] args){
	    Player user = new Player("Teste");
	    Game game = new Game(user);

	    game.startGame(DificultyLevel.FÁCIL);

	    System.out.println(game.getBoard().toString());
	
	}


}
