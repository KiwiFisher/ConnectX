package nz.ac.autuni.cny0166.game.components;

import nz.ac.autuni.cny0166.ConnectX;
import nz.ac.autuni.cny0166.game.components.logic.GameEngine;

import javax.swing.*;
import java.awt.*;

/**
 * GameBoard will hold an array of Column objects with size of Settings.BOARD_WIDTH
 */
public class GameBoard extends JPanel {


    private ConnectX currentGame;
    private GameEngine gameEngine;
    private Grid grid;
    private ControlPanel controlPanel;
    private JFrame mainFrame;

    public GameBoard(ConnectX currentGame, GameEngine gameEngine) {
        super(new BorderLayout());

        this.grid = new Grid(gameEngine);
        this.controlPanel = new ControlPanel(gameEngine);
        this.currentGame = currentGame;
        this.gameEngine = gameEngine;

        add(this.grid);
        add(this.controlPanel, BorderLayout.SOUTH);

        /*****************
         * GUI BELOW HERE
         *****************/
    }

    public GameBoard(ConnectX currentGame, GameEngine gameEngine, JFrame frame) {
        super(new BorderLayout());

        this.mainFrame = frame;

        this.grid = new Grid(gameEngine);
        this.controlPanel = new ControlPanel(gameEngine);
        this.currentGame = currentGame;
        this.gameEngine = gameEngine;

        add(this.grid);
        add(this.controlPanel, BorderLayout.SOUTH);

        /*****************
         * GUI BELOW HERE
         *****************/
    }

    public void clearAll() {
        removeAll();

        this.gameEngine = currentGame.newGameEngine();

        this.grid = new Grid(gameEngine);
        this.controlPanel = new ControlPanel(gameEngine);
        add(this.grid);
        add(this.controlPanel, BorderLayout.SOUTH);

        refreshDisplay();
    }


    public void buildGUI() {

        mainFrame = new JFrame("ConnectX");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.getContentPane().add(currentGame.getGameBoard());


        /*
        Pack frame and set it to the centre of the users screen
         */

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    public void refreshDisplay() {


        mainFrame.revalidate();
        mainFrame.repaint();

    }


    public Column[] getBoardColumns() {
        return this.grid.getBoardColumns();
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    /**
     * Will return the coin object stored at a given location
     * @param x The x value to look at. Furthest left is 0
     * @param y The y value to look at. Furthest up is 0;
     * @return WIll return the oin stored at the location. If there isn't one, null is returned.
     */
    public CoinSlot getCoinSlot(int x, int y) {

        return grid.getCoinSlot(x, y);
    }

    public CoinSlot getCoinSlot(BoardCoordinates boardCoordinates) {
        return getCoinSlot(boardCoordinates.getColumn(), boardCoordinates.getRow());
    }

    public Grid getGrid() {
        return this.grid;
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }
}
