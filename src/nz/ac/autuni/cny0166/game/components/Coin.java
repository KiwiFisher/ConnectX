package nz.ac.autuni.cny0166.game.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The coin object holds data on what type of coin it is and it's owning player
 */
public class Coin extends JPanel{

    private CoinType coinType;
    private Player owningPlayer;
    private SpringLayout layout;

    /**
     * Creates a coin belonging to the parsed player
     * @param player The player who owns the coin
     */
    public Coin(Player player) {
        super();
        setLayout(this.layout = new SpringLayout());
        this.coinType = player.getCoinType();
        this.owningPlayer = player;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(100, 100));
        setOpaque(true);
        setColour(CoinType.valueOf(coinType.name()).getColor());

        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("resources/coin.png"));
        JLabel label = new JLabel(image);
        label.setPreferredSize(new Dimension(100, 100));
        add(label);

        layout.putConstraint(SpringLayout.NORTH, label, -2, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, label, -2, SpringLayout.WEST, this);

    }

    public void setColour(Color colour) {
        setBackground(colour);
    }

    public Color getColour() {
        return coinType.getColor();
    }


    public CoinType getCoinType() {
        return coinType;
    }

    public Player getOwningPlayer() {
        return owningPlayer;
    }

    public String toString() {
        return coinType.toString();
    }

}
