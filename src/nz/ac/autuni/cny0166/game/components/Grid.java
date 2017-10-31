package nz.ac.autuni.cny0166.game.components;

import nz.ac.autuni.cny0166.game.components.logic.GameEngine;
import nz.ac.autuni.cny0166.io.settings.Settings;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {


    private Column[] boardColumns;
    private GameEngine gameEngine;


    public Grid(GameEngine gameEngine) {

        super(new GridLayout(1, Settings.BOARD_WIDTH));
        this.gameEngine = gameEngine;

        this.boardColumns = new Column[Settings.BOARD_WIDTH];

        for (int i = 0; i < Settings.BOARD_WIDTH; i++) {

            this.boardColumns[i] = new Column(i, Settings.BOARD_HEIGHT, gameEngine);
            add(this.boardColumns[i]);

        }

    }

    /**
     * Takes a column as an index value, meaning column 1 is referenced as 0.
     * All coin placement is done through this method
     * @param column The column to place the coin in
     * @param player The player who is placing the coin
     */
    public CoinSlot setCoin(int column, Player player) {

        Coin coin = new Coin(player);
        int row = boardColumns[column].addCoin(coin);
        return gameEngine.getGridArray().setCoin(row, column, coin);

    }

    public CoinSlot getCoinSlot(BoardCoordinates coordinates) {
        return this.gameEngine.getGridArray().getCoinSlot(coordinates);
    }

    public Column[] getBoardColumns() {
        return boardColumns;
    }

    /**
     * Will return the coin object stored at a given location
     * @param x The x value to look at. Furthest left is 0
     * @param y The y value to look at. Furthest up is 0;
     * @return WIll return the oin stored at the location. If there isn't one, null is returned.
     */
    public CoinSlot getCoinSlot(int x, int y) {

        return boardColumns[x].getCoinAtIndex(y);

    }

}
