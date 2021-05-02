
public class AguaTile extends Tile {
    private int pointsToGive;

    /**
     *
     * @param x position in the x axis of the matrix
     * @param y position in the y axis of the matrix
     */
    public AguaTile(int x, int y) {
        super(x, y);

        this.pointsToGive = 2;
    }

    /**
     *
     * @param pointsToGive change the points that a correct water tile found gives
     */
    public void setPointsToGive(int pointsToGive) {
        this.pointsToGive = pointsToGive;
    }

    /**
     *
     * @return the points that a correct water tile found gives
     */
    public int getPointsToGive() {
        return this.pointsToGive;
    }

    /**
     *
     * @return get the type of tile
     */
    @Override
    public String getTileType() {
        return "Água";
    }

    /**
     *
     * @return get the info about the tile
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Casa do tipo %s", this.getTileType()));
        sb.append(super.toString());

        return sb.toString();
    }
}
