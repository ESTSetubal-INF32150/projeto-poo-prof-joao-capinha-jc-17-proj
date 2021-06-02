
public class AguaTile extends Tile {
    private int pointsToGive;
    private TileType actualType;
    private TileType currentType;

    /**
     *
     * @param x position in the x axis of the matrix
     * @param y position in the y axis of the matrix
     */
    public AguaTile(int x, int y) {
        super(x, y);

        this.pointsToGive = 2;
        this.actualType = TileType.ÁGUA;
        this.currentType = TileType.DESCONHECIDA;
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

    @Override
    public TileType getTileTypeExt() {
        return this.actualType;
    }

    @Override
    public boolean correctType() {
        if(this.actualType == this.currentType) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void toogleType() {
        switch (this.currentType) {
            case DESCONHECIDA:
                this.currentType = TileType.ÁGUA;
                break;
            case ÁGUA:
                this.currentType = TileType.BARCO;
                break;
            case BARCO:
                this.currentType = TileType.DESCONHECIDA;
        }
    }

    @Override
    public TileType getCurrentType() {
        return this.currentType;
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
