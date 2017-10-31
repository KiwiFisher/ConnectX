package nz.ac.autuni.cny0166.game.components;

import nz.ac.autuni.cny0166.game.components.logic.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The column object will hold an array of CoinSlot objects with the size of Settings.BOARD_HEIGHT.
 */
public class Column extends JPanel {

    private CoinSlot[] columnSlots;
    private boolean full;
    private GameEngine gameEngine;
    private int columnNumber;

    /**
     * Creates a new column of CoinSlots from a given size
     * @param size How many rows there is in this column
     */
    public Column(int columnNumber, int size, GameEngine gameEngine) {
        super(new GridLayout(size, 1));

        this.gameEngine = gameEngine;
        this.full = false;
        this.columnNumber = columnNumber;
        this.columnSlots = new CoinSlot[size];

        /****************
         * GUI BELOW HERE
         ****************/

        for (int i = 0; i < size; i++) {

            CoinSlot newSlot = gameEngine.getGridArray().getCoinSlot(new BoardCoordinates(i, this.columnNumber));
            /*
            The color below will change the colour of the lines on the main grid
             */
            newSlot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setOpaque(true);
            this.columnSlots[i] = newSlot;
            add(this.columnSlots[i]);

        }

        setPreferredSize(new Dimension(100, 100 * size));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {setBackground(Color.LIGHT_GRAY);}

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (gameEngine.gameHasStarted() && !isFull() && !gameEngine.isWon()) {

                    gameEngine.getCurrentPlayer().addTurn();
                    gameEngine.placeCoin(columnNumber, gameEngine.getCurrentPlayer());

                    if (!gameEngine.isWon()) {

                        gameEngine.nextPlayer();
                    }
                   //gameEngine.updateInGamePlayer(gameEngine.getCurrentPlayer());
                }
            }
        });

    }

    public boolean isFull() {
        return this.full;
    }

    public int addCoin(Coin coin) {

        if (!isFull()) {

            /*
            For each of the coin slots in the column, we want to place the coin as far down as we can as if the
            coin was dropped by gravity.
             */
            for (int i = this.columnSlots.length - 1; i >= 0; i--) {

                CoinSlot slot = this.columnSlots[i];

                if (slot.isEmpty()) {
                    /*
                    If there isnt a coin, then place it
                     */
                    slot.setCoin(coin);

                    if (i == 0) {
                        /*
                        If i == 0, then it is the last slot in this column, so mark it as full.
                         */
                        this.full = true;
                    }

                    return i;

                }
            }

        } else {
            /*
            Column is full, so let the player try again.
             */
        }
        return -1;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public CoinSlot getCoinAtIndex(int index) {
        return this.columnSlots[index];
    }
}
