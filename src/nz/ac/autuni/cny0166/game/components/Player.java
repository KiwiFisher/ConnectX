package nz.ac.autuni.cny0166.game.components;

import javax.swing.*;
import java.awt.*;

/**
 * The Player class models a player in the game.
 */
public class Player extends JPanel{

    private String name;
    private CoinType coinType;
    private int count;

    public Player(String name, CoinType coinType) {

        this.coinType = coinType;
        this.name = name;
        this.count = 0;

    }

    public String getName() {
        return name;
    }

    public JPanel getNameGraphically() {

        JPanel namePanel = this;


        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.PLAIN, 25f));


        namePanel.setPreferredSize(new Dimension(name.length() * 12, 50));
        namePanel.add(nameLabel);

        paintComponent(getGraphics());



        return namePanel;
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(coinType.getColor());
        g.fillOval(0, 0, 10, 10);
    }

    public void addTurn() {
        this.count++;
    }

    public int getTurns() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public String toString() {
        return this.name;
    }
}
