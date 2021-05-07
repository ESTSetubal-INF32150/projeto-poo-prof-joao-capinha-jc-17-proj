import levelloader.LevelInfo;
import levelloader.LoadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @org.junit.jupiter.api.Test
    void getDificultyLevel() {
    }

    @org.junit.jupiter.api.Test
    void getBoard() {
    }

    @org.junit.jupiter.api.Test
    void getTimeExpectancy() {
    }

    @org.junit.jupiter.api.Test
    void getBasePoints() {
    }

    @org.junit.jupiter.api.Test
    void getUser() {
        Player user = new Player("Tomás");

        assertNotNull(user);
    }

    @org.junit.jupiter.api.Test
    void startGame() {
        DificultyLevel dificultyLevel = DificultyLevel.DIFÍCIL;
        List<LevelInfo> levelsCapable = new ArrayList<>();
        int levelChosen = 0;
        Random rnd = new Random();
        int numberRange = 0;
        int basePoints = 0;
        int timeExpectancy = 0;
        LoadManager lm = new LoadManager("./contents/levels");
        ArrayList<LevelInfo> levels;
        Board board = new Board();

        levels = lm.getLevels();

        switch(dificultyLevel) {
            case FÁCIL:
                basePoints = 50;
                timeExpectancy = 30;
                levelsCapable = levels.stream().filter(x -> x.getBoard().length == 5).collect(Collectors.toList());
                break;
            case INTERMÉDIO:
                basePoints = 100;
                timeExpectancy = 100;
                levelsCapable = levels.stream().filter(x -> x.getBoard().length == 7).collect(Collectors.toList());
                break;
            case DIFÍCIL:
                basePoints = 150;
                timeExpectancy = 150;
                levelsCapable = levels.stream().filter(x -> x.getBoard().length == 10).collect(Collectors.toList());
                break;
        }

        levelChosen = rnd.nextInt(levelsCapable.size());

        board.startGame(levelsCapable.get(levelChosen).getBoard());

        assertNotNull(board.getTile(0,0));
    }

    @org.junit.jupiter.api.Test
    void endGame() {
        DificultyLevel dificultyLevel;
        int timeExpectancy;
        int basePoints;
        Board board;
        Player user;

        dificultyLevel = null;
        board = new Board();
        user = null;
        timeExpectancy = 0;
        basePoints = 0;

        assertNull(dificultyLevel);
        assertNull(user);
    }
}