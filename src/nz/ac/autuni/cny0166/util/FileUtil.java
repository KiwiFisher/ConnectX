package nz.ac.autuni.cny0166.util;

import nz.ac.autuni.cny0166.io.settings.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    /**
     * Will attempt to generate the default settings and lang files if they don't exist already.
     */
    public static void createDefaultFiles() {

        final Path settingsDir = Paths.get(Settings.SETTINGS_DIR);

        final Path settingsPath = Paths.get(settingsDir + "/settings.txt");
        final Path langPath = Paths.get(settingsDir + "/EN.txt");


        if (!settingsDir.toFile().exists()) {

            settingsDir.toFile().mkdir();

        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream settingsStream = classLoader.getResourceAsStream("resources/settings.txt");
        InputStream langStream = classLoader.getResourceAsStream("resources/EN.txt");

        try {

            if (!settingsPath.toFile().exists()) {
                Files.copy(settingsStream, settingsPath);
            }

            if (!langPath.toFile().exists()) {
                Files.copy(langStream, langPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
