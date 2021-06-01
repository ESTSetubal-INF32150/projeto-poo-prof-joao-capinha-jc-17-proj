
public class PortoTile extends Tile {
    /**
     *
     * @param x position of the tile in the x axis of the matrix
     * @param y position of the tile in the y axis of the matrix
     */
    public PortoTile(int x, int y) {
        super(x, y);
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
        return TileType.PORTO;
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
