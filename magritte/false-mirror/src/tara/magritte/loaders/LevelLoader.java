package tara.magritte.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    public static String[] load(String level) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(LevelLoader.class.getResourceAsStream(pathOf(level))));
        List<String> lines = readWith(reader);
        return lines.toArray(new String[lines.size()]);
    }

    private static List<String> readWith(BufferedReader reader) {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException ignored) {
        }
        return lines;
    }

    private static String pathOf(String level) {
        return "/" + level + ".level";
    }


}
