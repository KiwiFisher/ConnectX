package nz.ac.autuni.cny0166.game.components.logic;

import nz.ac.autuni.cny0166.game.components.BoardCoordinates;
import nz.ac.autuni.cny0166.game.components.Coin;
import nz.ac.autuni.cny0166.game.components.CoinSlot;
import nz.ac.autuni.cny0166.io.settings.Settings;

import java.awt.*;
import java.util.ArrayList;

/**
 * GridArray holds a logical copy of the game board in a 2D array. This helps hugely
 * when it comes to traversing the matrix checking for if the game has been won.
 */
public class GridArray {

    private GameEngine gameEngine;

    private CoinSlot[][] gridArrayModel;

    /**
     * Creating a grid array creates a logical board which can be checks for information as the GUI layout is not
     * easy to work with
     * @param gameEngine
     */
    public GridArray(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.gridArrayModel = new CoinSlot[Settings.BOARD_HEIGHT][Settings.BOARD_WIDTH];

         /*
        Propagate the empty game board with coin slots
         */
        for (int y = 0; y < Settings.BOARD_WIDTH; y++) {

            for (int x = 0; x < Settings.BOARD_HEIGHT; x++) {

                gridArrayModel[x][y] = new CoinSlot(new BoardCoordinates(x, y));

            }

        }

    }

    public CoinSlot setCoin(int row, int column, Coin coin) {
        return gridArrayModel[row][column].setCoin(coin);
    }

    /**
     * This method checks the board to see if a player has won the game.
     * It is passed the last placed coin to check around as it is the only deciding component from the last check.
     * @param coinSlot The latest coin placed
     * @return Returns true if a player has won the game
     */
    public boolean checkWin(CoinSlot coinSlot) {

        BoardCoordinates coordinates = coinSlot.getCoordinates();
        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        ArrayList<CoinSlot> surroundingCoins = getNearbyCoins(coinSlot);

        for (CoinSlot slot : surroundingCoins) {

            if (slot.getCoin().getCoinType().equals(coinSlot.getCoin().getCoinType())) {

                boolean won = checkLinear(coinSlot, slot.getCoordinates().getRow() - row, slot.getCoordinates().getColumn() - column, false);
                if (won) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method will take a given slot and direction and iterate to one extremity, then back again counting the full length of coins in a row.
     * @param coinSlot The coin to scan around
     * @param rVec The row component of the coordinate change vector
     * @param cVec The column component of the coordinate change vector
     * @param isRetry This is only for the method itself so that only two sweeps are done, else it will loop infinitely
     * @return Returns true if the right amount was found in a linear line, including diagonals.
     */
    public boolean checkLinear(CoinSlot coinSlot, int rVec, int cVec,  boolean isRetry) {


        ArrayList<BoardCoordinates> winningCoins = new ArrayList<>();

        if (!(rVec == 0 || cVec == 0) && !Settings.ALLOW_DIAGONAL_WIN) {

            return false;

        }

        /**
         * @TODO THE X VALUE IN BOARD COORDS APPEARS TO BE INCREASED BY ONE
         *
         * THIS IS EITHER HAPPENING BECAUSE OF THE WIN CHECK ALGORITHM ADDING MODIFIED COORDS
         * BUT COULD ALSO BE THAT THE WIN CHECK ALG IS BEING FED THE DATA OUT BY 1 TO START
         *
         * BOARD COORDS ARE BEING GENERATED CORRECTLY
         */

        BoardCoordinates slotCoords = coinSlot.getCoordinates();
        winningCoins.add(slotCoords);

        BoardCoordinates nextCoords = slotCoords.sum(new BoardCoordinates(rVec, cVec));
        CoinSlot nextSlot = getCoinSlot(nextCoords);
        //winningCoins.add(nextCoords);

        int count = 1;

        /**
         * At this point we have added the last placed coin to the winning coins
         * We have also added in the next coin found by moving across using the given vectors
         */

        while (nextSlot != null &&
                !nextSlot.isEmpty() &&
                nextSlot.getCoin().getCoinType().equals(coinSlot.getCoin().getCoinType())) {

            winningCoins.add(nextCoords);

            count++;
            slotCoords = nextCoords;
            nextCoords = slotCoords.sum(new BoardCoordinates(rVec, cVec));

            nextSlot = getCoinSlot(nextCoords);


        }

        if (count >= Settings.IN_A_ROW_TO_WIN || (!isRetry && checkLinear(getCoinSlot(slotCoords), -rVec, -count, true))) {

            gameEngine.setWon(true);

            for (BoardCoordinates slot : winningCoins) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 3; i++) {


                            try {
                                getCoinSlot(slot).getCoin().setColour(Color.RED);
                                Thread.sleep(200);
                                getCoinSlot(slot).getCoin().setColour(getCoinSlot(slot).getCoin().getColour());
                                Thread.sleep(200);
                            } catch (InterruptedException ignored) {

                            }

                        }

                    }
                }).start();

            }
            return true;
        }

        return false;

    }

    /**
     * Does a radial search for other coins 1 slot away from the passed CoinSlot. This method checks diagonals too.
     * @param coinSlot The coin to scan around
     * @return Returns and ArrayList of all the surrounding coins.
     */
    public ArrayList<CoinSlot> getNearbyCoins(CoinSlot coinSlot) {

        BoardCoordinates coordinates = coinSlot.getCoordinates();

        int r;
        int c;

        ArrayList<CoinSlot> surroundingCoins = new ArrayList<CoinSlot>();

        for (r = -1; r <= 1; r++) {

            for (c = -1; c <= 1; c++) {

                BoardCoordinates nearbyCoords = coordinates.sum(new BoardCoordinates(r, c));

                if (r == 0 && c == 0) {
                    continue;
                }


                if (validCoords(nearbyCoords) && !getCoinSlot(nearbyCoords).isEmpty()) {

                    /*
                    We have just found a coin nearby of the same type then we want to do a double linear iteration.
                     */
                    surroundingCoins.add(getCoinSlot(nearbyCoords));
                }
            }
        }


        return surroundingCoins;

    }

    /**
     * Returns a boolean representing whether the passed coordinates are valid for this game board
     * @param coordinates The coordinates to validate
     * @return Returns true if the coordinates are valid.
     */
    public boolean validCoords(BoardCoordinates coordinates) {

        return (coordinates.getColumn() < Settings.BOARD_WIDTH) && coordinates.getColumn() >= 0
                && (coordinates.getRow() < Settings.BOARD_HEIGHT && coordinates.getRow() >= 0);

    }

    /**
     * Gets the coin slot object from a set of coordinates on the game board
     * @param coordinates The coordinates of the coin slot
     * @return Returns the corresponding CoinSlot object
     */
    public CoinSlot getCoinSlot(BoardCoordinates coordinates) {

        if (validCoords(coordinates)) {
            return gridArrayModel[coordinates.getRow()][coordinates.getColumn()];

        }
        return null;
    }

    public String toString() {

        String toReturn = "";

        for (int y = 0; y < Settings.BOARD_HEIGHT; y++) {

            for (int x = 0; x < Settings.BOARD_WIDTH; x++) {

                if (gridArrayModel[y][x].isEmpty()) {
                    toReturn += "[ ]";
                } else {
                    toReturn += "[" + gridArrayModel[y][x].getCoin() + "]";
                }

            }

            toReturn += "\n";

        }

        return toReturn;

    }


}
