package nz.ac.autuni.cny0166.game.components;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;

/**
 * Represents one cells of the board. It has the ability to hold a Coin object.
 */
public class CoinSlot extends JPanel {

    private Coin coin;
    private BoardCoordinates boardCoordinates;
    SpringLayout layout;

    /**
     * Creates the object but assigns null to the coin slot. This is considered an empty coin slot.
     */
    public CoinSlot(BoardCoordinates boardCoordinates) {
        super();
        setLayout(layout = new SpringLayout());

        this.coin = null;
        this.boardCoordinates = boardCoordinates;

        /************************************
         * GUI BELOW HERE
         ************************************/



        setPreferredSize(new Dimension(100, 100));
        setOpaque(false);
        //add(new JLabel(getCoordinates().getRow() + ", " + getCoordinates().getColumn()));


    }

    /**
     * Creates a coin slot with a pre-existing coin in it.
     * @param coin The coin to go in the coin slot.
     */
    public CoinSlot(Coin coin, BoardCoordinates boardCoordinates) {

        this.boardCoordinates = boardCoordinates;
        this.coin = coin;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(100, 100));
        setOpaque(true);

    }

    /**
     * Check if anything is being held in the coin slot
     * @return Returns true if there is no coin being held
     */
    public boolean isEmpty() {
        return this.coin == null;
    }

    public Coin getCoin() {
        return coin;
    }

    public CoinSlot setCoin(Coin coin) {
        this.coin = coin;
        update();
        return this;
    }

    private void update() {
        /**
         * Define what the coin slot looks like after a coin has been placed
         */
        setOpaque(true);

        add(coin);
        layout.putConstraint(SpringLayout.NORTH, coin, -1, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, coin, -1, SpringLayout.WEST, this);

        validate();
    }

    public CoinSlot getGUI() {

        return this;

    }

    public BoardCoordinates getCoordinates() {
        return boardCoordinates;
    }
}

