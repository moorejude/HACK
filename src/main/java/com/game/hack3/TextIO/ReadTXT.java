package com.game.hack3.TextIO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;


public class ReadTXT {

    static Path IntroFile = Paths.get("src/main/java/com/game/hack3/TextIO/HACK_Intro.txt");
    static Path HACKStats = Paths.get("HACK_Stats.txt");

    static List<String> ListofLines = Collections.emptyList();

    /**A method that is able to read a file into a List (ListofLines).
     * @param in a .txt file to be read
     */
    public static void ReadFile(Path in) {
        Path file = Paths.get(String.valueOf(in));
        try {
            ListofLines = Files.readAllLines(file, StandardCharsets.UTF_8);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Reads the HACK_Intro.txt file.
     * @return each line of the file as a list.
     */
    public static List<String> GetIntro() {
        ReadFile(IntroFile);
        return ListofLines;
    }

    /**Reads the HACK_Stats.txt file
     * @return each line of the file as a list.
     */
    public static List<String> GetStats() throws IOException {
        ReadFile(HACKStats);
        return ListofLines;
    }

}
