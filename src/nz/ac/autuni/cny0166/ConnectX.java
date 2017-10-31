package nz.ac.autuni.cny0166;

import nz.ac.autuni.cny0166.game.components.logic.GameEngine;
import nz.ac.autuni.cny0166.game.components.GameBoard;
import nz.ac.autuni.cny0166.util.FileUtil;

/**
 * The ConnectX class is the main class. It will create the board object and initialise files, databases, etc. Anything required for game play.
 */
public class ConnectX {

    private GameBoard gameBoard;
    private ConnectX currentGame;
    private GameEngine gameEngine;

    public static void main(String[] args) {
        new ConnectX();
    }

    private ConnectX() {
        FileUtil.createDefaultFiles();
        this.currentGame = this;
        this.gameEngine = new GameEngine(currentGame);
        gameBoard = new GameBoard(currentGame, gameEngine);

        gameBoard.buildGUI();

    }

    public GameEngine newGameEngine() {
        this.gameEngine = new GameEngine(currentGame);
        return gameEngine;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
