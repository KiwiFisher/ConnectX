package nz.ac.autuni.cny0166.game.components.logic;

import nz.ac.autuni.cny0166.ConnectX;
import nz.ac.autuni.cny0166.datastructs.LinkedRing;
import nz.ac.autuni.cny0166.game.components.*;

/**
 * GameEngine is designed ot be the control centre of the game. Everything from checking who's turn it is, to placing a coin and seeing if
 * someone has one is handled from here.
 */
public class GameEngine {

    private ConnectX currentGame;
    private LinkedRing<Player> players;
    private boolean gameStarted;
    private GridArray gridArray;
    private boolean gameWon;

    public GameEngine(ConnectX connectX) {
        this.gridArray = new GridArray(this);
        this.currentGame = connectX;
        this.players = new LinkedRing<>();
        this.gameStarted = false;
        this.gameWon = false;
    }

    /**
     * Places a coin on the board of a specified type and location.
     * @param column The column to drop the coin in
     * @param player The player making the turn
     */
    public void placeCoin(int column, Player player) {

        CoinSlot latestCoinSlot = currentGame.getGameBoard().getGrid().setCoin(column, player);
        checkWin(latestCoinSlot);
    }

    public void placeCoin(Column column, Player player) {

        placeCoin(column.getColumnNumber(), player);

    }

    /**
     * Get the current player
     * @return The object of the current player
     */
    public Player getCurrentPlayer() {
        return players.getCurrent();
    }

    /**
     * Advance to the next player and return the new current player
     * @return The new players turn
     */
    public Player nextPlayer() {
        Player nextPlayer = this.players.next();
        updateInGamePlayer(nextPlayer);
        return nextPlayer;
    }

    public boolean checkWin(CoinSlot coinSlot) {
        return this.gridArray.checkWin(coinSlot);
    }

    public void addPlayer(String player) {

        this.players.add(new Player(player, getUnusedCoinType()));
    }

    /**
     * Get an unused coin type for creating new players
     * @return
     */
    private CoinType getUnusedCoinType() {

        outer:
        for (CoinType coinType : CoinType.values()) {


            boolean found = false;

            for (Player player : getPlayers().getLinearStructure()) {

                if (coinType == player.getCoinType()) {
                    found = true;
                }

            }

            if (!found) return coinType;

        }

        return null;
    }

    public LinkedRing<Player> getPlayers() {
        return players;
    }

    public ConnectX getCurrentGame() {
        return currentGame;
    }

    public void setGameStarted(boolean started) {
        this.gameStarted = started;
    }

    public boolean gameHasStarted() {
        return this.gameStarted;
    }

    public GridArray getGridArray() {
        return gridArray;
    }

    public void updateInGamePlayer(Player player) {
        currentGame.getGameBoard().getControlPanel().updateInGamePlayer(player);
    }

    public void setWon(boolean won) {
        this.gameWon = won;
        actionsOnWin();
    }

    public boolean isWon() {
        return this.gameWon;
    }

    /**
     * All game info will be wiped. Will be just like starting a new game
     */
    public void startNewGame() {

        currentGame.getGameBoard().clearAll();


    }

    public void actionsOnWin() {
        getCurrentGame().getGameBoard().getControlPanel().setWinnerCard();
    }
}
