package nz.ac.autuni.cny0166.game.components;

import java.awt.*;

/**
 * Coin type enum. Either X, O, Y or Z.
 */
public enum CoinType {

    O (Color.CYAN),
    X (Color.YELLOW),
    Y (Color.GREEN),
    Z (Color.MAGENTA);

    private Color color;

    CoinType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
