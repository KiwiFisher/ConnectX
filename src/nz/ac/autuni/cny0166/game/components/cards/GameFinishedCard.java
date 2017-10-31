package nz.ac.autuni.cny0166.game.components.cards;

import nz.ac.autuni.cny0166.game.components.Player;
import nz.ac.autuni.cny0166.game.components.logic.GameEngine;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFinishedCard extends JPanel {

    private GameEngine gameEngine;
    private Player winner;
    private JLabel message;
    private JButton playAgainButton;

    public GameFinishedCard(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        add(message = new JLabel("THE GAME HAS BEEN WON"));

        this.playAgainButton = new JButton("Play Again");
        add(playAgainButton);
        playAgainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                gameEngine.startNewGame();
            }
        });
    }

    /**
     * This method will set the current player as the winner. It will only
     * be called once the game has been won
     */
    public void setWinner() {
        winner = gameEngine.getCurrentPlayer();
        message.setText(winner.getName() + " has won the game in " + winner.getTurns() + " turns!");
    }

}
