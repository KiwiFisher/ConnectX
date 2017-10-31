package nz.ac.autuni.cny0166.io;

import nz.ac.autuni.cny0166.io.exceptions.EzTXTSyntaxException;
import nz.ac.autuni.cny0166.io.exceptions.NoSuchIDException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * A basic class assembled to make the reading of external txt files very simple when being used to store key : value pairs, such as
 * settings, strings for use in game, etc.
 */
public class EzTXTValue {

    private BufferedReader bufferedReader;
    private String fileLocation;
    private HashMap<String, String> settingsMap;

    public EzTXTValue(String fileLocation) {

        this.fileLocation = fileLocation;
        this.settingsMap = new HashMap<>();

        initFile();

    }

    /**
     * Initialises the files and loads it all in to the object.
     */
    private void initFile() {

        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.fileLocation));

            String line;
            int count = 0;

            while ((line = bufferedReader.readLine()) != null) {

                count++;

                if (line.startsWith("#")) {

                    try {

                        String settingName = line.substring(line.indexOf('#') + 1, line.indexOf(':'));
                        String settingValue = line.substring(line.indexOf(':') + 1, line.indexOf(';')).trim().replace('~', ' ');

                        this.settingsMap.put(settingName, settingValue);

                    } catch (StringIndexOutOfBoundsException e) {

                        throw new EzTXTSyntaxException("There was an error in " + fileLocation + " on line " + count);

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Used to get a value from a key that will be returned as a String.
     * @param settingID The ID of which the value is stored under.
     * @return Returns the value as a string
     */
    public String getString(String settingID) {

        if (this.settingsMap.containsKey(settingID)) {

            return this.settingsMap.get(settingID);

        } else {

            try {
                throw new NoSuchIDException("There is no stringID called \"" + settingID + "\"");
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    /**
     * Used to get a value from a key that will be returned as an int.
     * @param settingID The ID of which the value is stored under.
     * @return Returns the value as an int
     */
    public int getInt(String settingID) {

        if (this.settingsMap.containsKey(settingID) && this.settingsMap.get(settingID).matches("[0-9]+")) {

            return new Integer(this.settingsMap.get(settingID));

        } else {

            try {
                throw new NoSuchIDException("There is no stringID called \"" + settingID + "\"");
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }

        }

        return -1;
    }

    /**
     * Used to get a value from a key that will be returned as a boolean.
     * @param settingID The ID of which the value is stored under.
     * @return Returns the value as a boolean
     */
    public boolean getBoolean(String settingID) {

        if (this.settingsMap.containsKey(settingID)) {

            String settingValue = this.settingsMap.get(settingID);

            if (settingValue.equalsIgnoreCase("true")) {
                return true;
            } else if (settingValue.equalsIgnoreCase("false")) {
                return false;
            }

        } else {

            try {
                throw new NoSuchIDException("There is no stringID called \"" + settingID + "\"");
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }

        }

        return false;

    }

}
