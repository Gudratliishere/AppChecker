package com.gudratli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @mailto : d.qudretli@gmail.com
 * @since : 12.09.2023
 **/
public class Processes {

    public static BufferedReader getProcessReader(Process p) {
        InputStream in = p.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);
        return new BufferedReader(reader);
    }

    public static List<String> getAllLines(Process p) {
        try (BufferedReader reader = getProcessReader(p)) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            String msg = "Reader is not in a readable state";
            throw new IllegalStateException(msg, e);
        }
    }

    private Processes() {
        // This is a utility method
    }
}