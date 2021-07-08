package com.kowafi.socialDynamics;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ConfigLoader {


    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    public int loadConfigFromJson(String jsonFile) {

        String source = null;
        try {
            source = getResourceFileAsString(jsonFile);
        } catch (IOException e) {
            e.printStackTrace(); //TODO handle exception
        }
        int populationSize;
        JSONObject obj = new JSONObject(source);
        populationSize = obj.getJSONObject("population").getInt("size");

        return populationSize;
    }
}
