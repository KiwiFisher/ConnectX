package nz.ac.autuni.cny0166.io.lang;

import nz.ac.autuni.cny0166.io.EzTXTValue;
import nz.ac.autuni.cny0166.io.settings.Settings;

/**
 * A collection of static final assignments that are drawn from the external txt files. For simple String retrieval with the CLI.
 */
public class Lang {

    private static EzTXTValue langFile = new EzTXTValue(Settings.SETTINGS_DIR + "/" + Settings.LANG_FILENAME);

    public static final String WIN_MESSAGE = langFile.getString("win-message");
    public static final String DRAW_MESSAGE = langFile.getString("draw-message");
    public static final String PROMPT_COIN_COLUMN = langFile.getString("prompt-coin-column");
    public static final String INVALID_COLUMN_SELECTION = langFile.getString("invalid-column-choice");
    public static final String PROMPT_USERNAME = langFile.getString("prompt-username");
    public static final String PROMPT_NEXT_GAME = langFile.getString("prompt-next-game");
    public static final String INVALID_INPUT = langFile.getString("invalid-input");
    public static final String GOODBYE_MESSAGE = langFile.getString("goodbye-message");
    public static final String START_MESSAGE = langFile.getString("start-message");
    public static final String PROMPT_PLAYER_NUMBERS = langFile.getString("prompt-number-of-players");



}
