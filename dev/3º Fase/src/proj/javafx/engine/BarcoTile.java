package proj.javafx.engine;

public class BarcoTile extends Tile {
    private int pointsToGive;
    private BoatSize boatSize;
    private TileType currentType;
    private TileType actualType;

    /**
     *
     * @param x position of the tile in the x axis of the matrix
     * @param y position of the tile in the y axis of the matrix
     * @param boatSize size of the boat
     */
    public BarcoTile(int x, int y, BoatSize boatSize) {
        super(x, y);

        this.boatSize = boatSize;
        this.actualType = TileType.BARCO;
        this.currentType = TileType.DESCONHECIDA;

        switch (boatSize) {
            case PEQUENO:
                this.pointsToGive = 5;
                break;
            case MÉDIO:
                this.pointsToGive = 10;
                break;
            case GRANDE:
                this.pointsToGive = 15;
                break;
        }
    }

    /**
     *
     * @param boatSize change the size of the boat
     */
    public void setBoatSize(BoatSize boatSize) {
        this.boatSize = boatSize;

        switch (boatSize) {
            case PEQUENO:
                this.pointsToGive = 5;
                break;
            case MÉDIO:
                this.pointsToGive = 10;
                break;
            case GRANDE:
                this.pointsToGive = 15;
                break;
        }
    }

    /**
     *
     * @return get the size of the boat
     */
    public BoatSize getBoatSize() {
        return this.boatSize;
    }

    /**
     *
     * @return get the description about the size of the boat
     */
    public String getBoatSizeDescription() {
        return this.boatSize.name();
    }

    /**
     *
     * @return Get the atual type of tile
     */
    @Override
    public TileType getTileTypeExt() {
        return this.actualType;
    }

    /**
     *
     * @return get the type of tile
     */
    @Override
    public String getTileType() {
        return "Barco";
    }

    /**
     * Change the type of tile based on the current type of tile
     */
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

    /**
     *
     * @return Get the current type of tile
     */
    @Override
    public TileType getCurrentType() {
        return this.currentType;
    }

    /**
     *
     * @return Check if the tile is flagged as the correct type of tile. True if yes, False if not.
     */
    @Override
    public boolean isCorrectType() {
        if(this.actualType == this.currentType) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @return get the info about the tile
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Casa do tipo %s do tamanho %s", this.getTileType(), this.getBoatSizeDescription()));
        sb.append(super.toString());

        return sb.toString();
    }
}
