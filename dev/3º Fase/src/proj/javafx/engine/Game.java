package proj.javafx.engine;

import levelloader.LevelInfo;
import levelloader.LoadManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    private int validateAttempts;
    private LocalDateTime gameStarted;

    /**
     *
     * @param user the user that is playing the game
     */
    public Game(Player user) {
        this.dificultyLevel = null;
        this.board = new Board();
        LoadManager lm = new LoadManager("././levels");
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
     * Get the difficulty level of the game in ordinal
     * @return the ordinal number corresponding to the difficulty level ( 1 - Facil, 2 - Intermedio, 3 - Dificil)
     */
    public int getDifficultyLevelOrdinal() {
        if(this.dificultyLevel == DificultyLevel.FÁCIL) {
            return 1;
        }
        else if(this.dificultyLevel == DificultyLevel.INTERMÉDIO) {
            return 2;
        }
        else {
            return 3;
        }
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
     * Set up game
     * @param dificultyLevel the difficulty level chosen by the user or randomly to start the game
     */
    public void startGame(DificultyLevel dificultyLevel) {
        this.dificultyLevel = dificultyLevel;
        List<LevelInfo> levelsCapable = new ArrayList<>();
        int levelChosen = 0;
        int diffChosen = 0;
        Random rnd = new Random();
        int numberRange = 0;
        this.gameStarted = LocalDateTime.now();
        this.validateAttempts = 0;

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
    }

    /**
     * Set up level creator
     * @param dificultyLevel the difficulty level chosen by the user to create a level
     */
    public void startLevelCreator(DificultyLevel dificultyLevel) {
        switch(dificultyLevel) {
            case FÁCIL:
                this.basePoints = 50;
                this.timeExpectancy = 30;
                break;
            case INTERMÉDIO:
                this.basePoints = 100;
                this.timeExpectancy = 100;
                break;
            case DIFÍCIL:
                this.basePoints = 150;
                this.timeExpectancy = 150;
                break;
        }

        this.board.startLevelCreator(dificultyLevel);
    }

    /**
     * Toggle between types of tile
     * @param x the position horizontally of the tile
     * @param y the position vertically of the tile
     */
    public void toggleTile(int x, int y) {
        this.board.toggleTile(x, y);
    }

    /**
     * Change the tile type of a tile in a determined vertical and horizontal position
     * @param x the position horizontally of the tile
     * @param y the position vertically of the tile
     * @param tileType the new tile type
     */
    public void setTileType(int x, int y, TileType tileType) {
        switch (tileType) {
            case BARCO:
                BarcoTile barco = new BarcoTile(x, y, BoatSize.PEQUENO);
                this.board.changeTileType(barco, x, y);
                break;
            case PORTO:
                PortoTile porto = new PortoTile(x, y);
                this.board.changeTileType(porto, x, y);
                break;
            case ÁGUA:
                AguaTile agua = new AguaTile(x, y);
                this.board.changeTileType(agua, x, y);
                break;
        }
    }

    /**
     * Get the time that the game started
     * @return the time that the game started
     */
    public LocalDateTime getGameStarted() {
        return this.gameStarted;
    }

    /**
     * Define the time the game started
     * @param gameStarted the time the game started
     */
    public void setGameStarted(LocalDateTime gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * Check if the board is correct in a normal game
     * @return true if yes, false if not
     */
    public boolean validateBoard() {
        return this.board.validateBoard();
    }

    /**
     * Check if the board is correct in the level creator
     * @return true if yes, false if not
     */
    public boolean validateBoardLevelCreator() {
        return this.board.validateBoardLevelCreator();
    }

    /**
     * Check if the user correctly flagged the tiles as their correct type
     * @return the ammount of tile that are incorrectly flagged
     */
    public int checkTilePlacementCorrect() {
        int qtd = 0;

        this.validateAttempts++;

        qtd = this.board.checkCorrectPlacement(this.basePoints);

        if(qtd == 0) {
            long secondsPassed = (this.gameStarted.until(LocalDateTime.now(), ChronoUnit.SECONDS));
            int secondsLeft = this.timeExpectancy - (int)secondsPassed;

            if(secondsLeft < 0) {
                secondsLeft *= -1;
                this.basePoints -= secondsLeft;
            }
            else {
                this.basePoints += secondsLeft;
            }

            if(this.validateAttempts > 1) {
                this.basePoints -= ((this.validateAttempts - 1) * 10);
            }
            else {
                this.basePoints += 20;
            }
        }

        return qtd;
    }

    /**
     * Save the level that the user created
     * @param fileName the name of the file that the user specified
     * @param userName the name of the user creating the level
     */
    public void saveLevel(String fileName, String userName) {
        try {
            File newLevel = new File("././levels/BoatsAndDocks_" + fileName + ".lvl");
            if (newLevel.createNewFile()) {
                FileWriter myWriter = new FileWriter("././levels/BoatsAndDocks_" + fileName + ".lvl");
                String boardPrinted = this.board.toString();
                StringBuilder sb = new StringBuilder(boardPrinted);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime date = LocalDateTime.now();
                String data = date.format(formatter);

                sb.append("#" + fileName + "\n");
                sb.append("#" + userName + "\n");
                sb.append("#" + data);

                myWriter.write(sb.toString());
                myWriter.close();
            } else {
                System.out.println("Já existe um ficheiro com esse nome!.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * End the game and restart all object to allow a new game to start
     */
    public void endGame() {
        this.dificultyLevel = null;
        this.board = new Board();
        this.user = null;
        this.timeExpectancy = 0;
        this.basePoints = 0;
    }
}
