
import levelloader.LevelInfo;
import levelloader.LoadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {
    private DificultyLevel dificultyLevel;
    private int timeExpectancy;
    private int basePoints;
    private Board board;
    private ArrayList<LevelInfo> levels;
    private Player user;

    /**
     *
     * @param user the user that is playing the game
     */
    public Game(Player user) {
        this.dificultyLevel = null;
        this.board = new Board();
        LoadManager lm = new LoadManager("./levels");
        this.levels = lm.getLevels();
        this.user = user;
        this.timeExpectancy = 0;
        this.basePoints = 0;
    }

    /**
     *
     * @return get the difficulty level that is been played
     */
    public DificultyLevel getDificultyLevel() {
        return this.dificultyLevel;
    }

    /**
     *
     * @return get the board of the game that is being playing
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     *
     * @return get the time expectancy of the game that is been played at the moment based on the difficulty level
     */
    public int getTimeExpectancy() {
        return this.timeExpectancy;
    }

    /**
     *
     * @return get the base points of the game that is being played based on the difficulty level
     */
    public int getBasePoints() {
        return this.basePoints;
    }

    /**
     *
     * @return get the user object corresponding to the user that is playing at the moment
     */
    public Player getUser() {
        return this.user;
    }

    /**
     *
     * @param dificultyLevel the difficulty level chosen by the user or randomly to start the game
     */
    public void startGame(DificultyLevel dificultyLevel) {
        this.dificultyLevel = dificultyLevel;
        List<LevelInfo> levelsCapable = new ArrayList<>();
        int levelChosen = 0;
        int diffChosen = 0;
        Random rnd = new Random();
        int numberRange = 0;

        switch(dificultyLevel) {
            case ALEATÓRIO:
                diffChosen = rnd.nextInt(3);

                if(diffChosen == 1) {
                    this.basePoints = 50;
                    this.timeExpectancy = 30;
                    levelsCapable = this.levels.stream().filter(x -> x.getBoard().length == 5).collect(Collectors.toList());
                }
                else if(diffChosen == 2) {
                    this.basePoints = 100;
                    this.timeExpectancy = 100;
                    levelsCapable = this.levels.stream().filter(x -> x.getBoard().length == 7).collect(Collectors.toList());
                }else {
                    this.basePoints = 150;
                    this.timeExpectancy = 150;
                    levelsCapable = this.levels.stream().filter(x -> x.getBoard().length == 10).collect(Collectors.toList());
                }
            case FÁCIL:
                this.basePoints = 50;
                this.timeExpectancy = 30;
                levelsCapable = this.levels.stream().filter(x -> x.getBoard().length == 5).collect(Collectors.toList());
                break;
            case INTERMÉDIO:
                this.basePoints = 100;
                this.timeExpectancy = 100;
                levelsCapable = this.levels.stream().filter(x -> x.getBoard().length == 7).collect(Collectors.toList());
                break;
            case DIFÍCIL:
                this.basePoints = 150;
                this.timeExpectancy = 150;
                levelsCapable = this.levels.stream().filter(x -> x.getBoard().length == 10).collect(Collectors.toList());
                break;
        }

        levelChosen = rnd.nextInt(levelsCapable.size());

        this.board.startGame(levelsCapable.get(levelChosen).getBoard());

        this.board.printBoard();
    }

    public void endGame() {
        this.dificultyLevel = null;
        this.board = new Board();
        this.user = null;
        this.timeExpectancy = 0;
        this.basePoints = 0;
    }
}
