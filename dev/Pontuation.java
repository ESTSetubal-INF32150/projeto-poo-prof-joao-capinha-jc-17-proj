import java.time.LocalDateTime;

public class Pontuation {
    private LocalDateTime time;
    private int gamePoints;
    private int level;

    /**
     *
     * Construct to register a new game pontuation
     *
     * @param gamePoints point that the player get in the game
     * @param level Level that the player played
     */
    public Pontuation(int gamePoints, int level){
        time.now();
        this.gamePoints = gamePoints;
        this.level = level;
    }

    /**
     *
     * @return Date that the game was finished
     */
    public LocalDateTime getTime(){
        return time;
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
     *
     * @param time change the time that the game was finished (use LocalDateTime)
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
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
