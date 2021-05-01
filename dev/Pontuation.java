import java.time.LocalDateTime;

public class Pontuation {
    private LocalDateTime time;
    private int gamePoints;
    private int level;

    public Pontuation(int gamePoints, int level){
        time.now();
        this.gamePoints = gamePoints;
        this.level = level;
    }

    public LocalDateTime getTime(){
        return time;
    }

    public int getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
