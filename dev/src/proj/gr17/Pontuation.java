package proj.gr17;

import java.time.LocalDateTime;

public class Pontuation {
    private int gamePoints;
    private int level; //change to class level
    private LocalDateTime date;

    /**
     *
     * Construct to register a new game pontuation
     *
     * @param gamePoints point that the player get in the game
     * @param level Level that the player played
     */
    public Pontuation(int gamePoints, int level, LocalDateTime date){
        this.gamePoints = gamePoints;
        this.level = level;
        this.date = date;
    }

    /**
     *
     * @return the points that the player get in the game
     */
    public int getGamePoints() {
        return gamePoints;
    }

    /**
     *
     * @param gamePoints change the amount of points gained by the player (use int)
     */
    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    /**
     * Get the date the game started
     * @return the date the game started
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     *
     * @return get the level that was played
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level Change the level that was played (use int)
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
