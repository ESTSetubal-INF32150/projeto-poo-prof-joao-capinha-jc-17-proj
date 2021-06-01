
public class DesconhecidaTile extends Tile {
    /**
     *
     * @param x position in the x axis of the matrix
     * @param y position in the y axis of the matrix
     */
    public DesconhecidaTile(int x, int y) {
        super(x, y);
    }

    /**
     *
     * @return the type of tile
     */
    @Override
    public String getTileType() {
        return "Desconhecida";
    }

    @Override
    public TileType getTileTypeExt() {
        return TileType.DESCONHECIDA;
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
