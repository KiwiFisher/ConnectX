package nz.ac.autuni.cny0166.game.components;

import nz.ac.autuni.cny0166.game.components.cards.GameFinishedCard;
import nz.ac.autuni.cny0166.game.components.logic.GameEngine;
import nz.ac.autuni.cny0166.game.components.cards.AddPlayersCard;
import nz.ac.autuni.cny0166.game.components.cards.InGameCard;
import nz.ac.autuni.cny0166.io.settings.Settings;

import javax.swing.*;
import java.awt.*;


public class ControlPanel extends JPanel {

    private GameEngine gameEngine;
    private CardLayout layout;
    private AddPlayersCard addPlayersCard;
    private InGameCard inGameCard;
    private GameFinishedCard gameFinishedCard;

    public ControlPanel(GameEngine gameEngine) {
        super();
        this.layout = new CardLayout();
        setLayout(this.layout);
        this.gameEngine = gameEngine;

        setPreferredSize(new Dimension(Settings.BOARD_WIDTH * 100, 150));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        addPlayersCard = new AddPlayersCard(gameEngine);
        add(addPlayersCard, "ADD PLAYERS");

        inGameCard = new InGameCard(gameEngine);
        add(inGameCard, "IN GAME");

        gameFinishedCard = new GameFinishedCard(gameEngine);
        add(gameFinishedCard, "GAME FINISHED");

    }

    public void updateInGamePlayer(Player player) {
        inGameCard.updateCurrentPlayer(player);


    }

    /**
     * Being called from the add player card when the start button is pressed. Call is then carried through to the game engine.
     */
    public void startGame() {

        layout.next(this);
        gameEngine.setGameStarted(true);

    }

    public void setWinnerCard() {
        layout.next(this);
        gameFinishedCard.setWinner();
    }


}
