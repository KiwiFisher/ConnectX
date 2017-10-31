package nz.ac.autuni.cny0166.game.components.cards;

import nz.ac.autuni.cny0166.game.components.Player;
import nz.ac.autuni.cny0166.game.components.logic.GameEngine;

import javax.swing.*;

public class InGameCard extends JPanel {
    private JLabel currentPlayerLabel;
    private Player currentPlayer;

    public InGameCard(GameEngine gameEngine) {


        add(currentPlayerLabel = new JLabel("THE GAME HAS STARTED"));

    }

    public void updateCurrentPlayer(Player player) {
        this.currentPlayer = player;
        this.currentPlayerLabel.setText(this.currentPlayer.getName());
    }

}
