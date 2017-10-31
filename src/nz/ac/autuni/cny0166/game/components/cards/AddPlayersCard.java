package nz.ac.autuni.cny0166.game.components.cards;

import nz.ac.autuni.cny0166.game.components.logic.GameEngine;
import nz.ac.autuni.cny0166.game.components.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPlayersCard extends JPanel {

    private GameEngine gameEngine;

    private SpringLayout layout;

    public AddPlayersCard(GameEngine gameEngine) {
        super();
        layout = new SpringLayout();
        setLayout(layout);
        this.gameEngine = gameEngine;

        setBackground(Color.WHITE);

        JTextField newPlayerTextField = new JTextField("");
        Font bigFont = newPlayerTextField.getFont().deriveFont(Font.PLAIN, 25f);
        newPlayerTextField.setFont(bigFont);
        newPlayerTextField.setColumns(6);
        add(newPlayerTextField);
        newPlayerTextField.setVisible(false);

        newPlayerTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                newPlayerTextField.setText("");
            }
        });

        VanishingButton addPlayerButton = createImageButton("resources/addPlayerButton.png");
        add(addPlayerButton);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, newPlayerTextField, 0, SpringLayout.VERTICAL_CENTER, addPlayerButton);
        layout.putConstraint(SpringLayout.WEST, newPlayerTextField, 2, SpringLayout.WEST, addPlayerButton);

        layout.putConstraint(SpringLayout.WEST, addPlayerButton, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, addPlayerButton, 4, SpringLayout.NORTH, this);

        VanishingButton startGameButton = createImageButton("resources/startButton.png");
        add(startGameButton);
        startGameButton.setHidden(true);
        startGameButton.setPreferredSize(new Dimension(150, 80));
        layout.putConstraint(SpringLayout.NORTH, startGameButton, 5, SpringLayout.SOUTH, addPlayerButton);
        layout.putConstraint(SpringLayout.WEST, startGameButton, 5, SpringLayout.WEST, this);


        addPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                /*
                If the button is hidden and therefore not enabled, make sure nothing happens when it is clicked
                 */
                if (!addPlayerButton.isEnabled()) {
                    return;
                }

                     /*
                     If the button is hidden (Text field showing), then show button and hide text
                      */
                if (addPlayerButton.isHidden()) {
                    addPlayerButton.setHidden(false);
                    newPlayerTextField.setVisible(false);
                } else {

                         /*
                         Otherwise, hide the button and show the text field
                          */
                    addPlayerButton.setHidden(true);
                    newPlayerTextField.setVisible(true);
                    newPlayerTextField.requestFocus();

                }
            }
        });

        newPlayerTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!newPlayerTextField.getText().isEmpty()) {
                        gameEngine.addPlayer(newPlayerTextField.getText());

                        if (gameEngine.getPlayers().getSize() > 1) {
                            startGameButton.setHidden(false);
                        }

                    }
                    newPlayerTextField.setText("");
                    newPlayerTextField.setVisible(false);
                    addPlayerButton.setHidden(false);

                    //if (newPlayerTextField.getText().equals("")) return;

                         /*
                         HERE WE WISH TO LIST OUT ALL THE LOADED PLAYERS
                          */

                    JLabel latest = null;

                    for (Player player : gameEngine.getPlayers().getLinearStructure()) {

                        JLabel name = new JLabel(player.getName());

                        /*
                        Any modifications to the name layout should be done below here, but before add
                         */
                        name.setFont(new Font(name.getName(), Font.PLAIN, 22));

                        add(name);

                        if (latest == null) {
                            latest = name;
                            layout.putConstraint(SpringLayout.WEST, name, 15, SpringLayout.EAST, addPlayerButton);
                            layout.putConstraint(SpringLayout.VERTICAL_CENTER, name, 0, SpringLayout.VERTICAL_CENTER, addPlayerButton);
                        } else {
                            layout.putConstraint(SpringLayout.WEST, name, 15, SpringLayout.EAST, latest);
                            layout.putConstraint(SpringLayout.VERTICAL_CENTER, name, 0, SpringLayout.VERTICAL_CENTER, latest);
                            latest = name;
                        }

                        name.setBackground(Color.LIGHT_GRAY);

                    }

                    //@TODO Fixed player limit
                    if (gameEngine.getPlayers().getSize() == 4) {
                        addPlayerButton.setHidden(true);
                    }

                }
            }
        });




        startGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!startGameButton.isEnabled()) {
                    return;
                }
                gameEngine.getCurrentGame().getGameBoard().getControlPanel().startGame();
                gameEngine.getCurrentGame().getGameBoard().getControlPanel().updateInGamePlayer(gameEngine.getCurrentPlayer());
            }
        });

    }



    private VanishingButton createImageButton(String location) {

        Icon icon = new ImageIcon(getClass().getClassLoader().getResource(location));
        return new VanishingButton(icon);

    }

    private class VanishingButton extends JButton {

        private boolean hidden;
        private Icon icon;
        public VanishingButton(Icon imageIcon) {
        super(imageIcon);
        icon = imageIcon;
        hidden = false;

        setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

    }



        public void setHidden(boolean hidden) {

            if (hidden) {
                setIcon(null);
                this.setEnabled(false);
                this.hidden = hidden;
            } else {
                setIcon(icon);
                this.hidden = hidden;
                this.setEnabled(true);
            }

        }

        public boolean isHidden() {
            return hidden;
        }
    }
}
