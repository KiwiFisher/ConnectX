package nz.ac.autuni.cny0166.game.components;

public class BoardCoordinates {

    private int column;
    private int row;

    public BoardCoordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String toString() {
        return row + ", " + column;
    }

    public BoardCoordinates sum(BoardCoordinates boardCoordinates) {
        return new BoardCoordinates(this.row + boardCoordinates.getRow(), this.column + boardCoordinates.getColumn());
    }
}
