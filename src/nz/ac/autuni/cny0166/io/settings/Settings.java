package nz.ac.autuni.cny0166.io.settings;

import nz.ac.autuni.cny0166.io.EzTXTValue;

/**
 * Contains all settings which customise how the program runs.
 */
public class Settings {

    public static final String SETTINGS_DIR = "settings";

    private static final EzTXTValue settings = new EzTXTValue(SETTINGS_DIR + "/settings.txt");
    public static final int IN_A_ROW_TO_WIN = settings.getInt("in-a-row-to-win");
    public static final int BOARD_WIDTH = settings.getInt("board-width");
    public static final int BOARD_HEIGHT = settings.getInt("board-height");
    public static final boolean ALLOW_DIAGONAL_WIN = settings.getBoolean("allow-diagonal-win");
    public static final String LANG_FILENAME = settings.getString("lang-filename");


}
