
public class PortoTile extends Tile {
    private TileType actualType;
    private TileType currentType;

    /**
     *
     * @param x position of the tile in the x axis of the matrix
     * @param y position of the tile in the y axis of the matrix
     */
    public PortoTile(int x, int y) {
        super(x, y);

        this.actualType = TileType.PORTO;
        this.currentType = TileType.PORTO;
    }

    /**
     *
     * @return type of tile
     */
    @Override
    public String getTileType() {
        return "Porto";
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
        return;
    }

    @Override
    public TileType getCurrentType() {
        return this.currentType;
    }

    /**
     *
     * @return information about the tile
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Casa do tipo %s", this.getTileType()));
        sb.append(super.toString());

        return sb.toString();
    }
}
