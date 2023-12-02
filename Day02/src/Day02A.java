import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day02A {

    static List<String> allLines;
    static int MAX_RED = 12;
    static int MAX_GREEN = 13;
    static int MAX_BLUE = 14;

    public static void main(String[] args) {
        readfile("input.txt");
        int possibleGames = 0;

        for (int i = 0; i < allLines.size(); i++) {
            boolean lineIsPossible = parseLine(allLines.get(i));
            if (lineIsPossible) {
                possibleGames = possibleGames + (i + 1);
                System.out.println("Game " + (i + 1) + " is possible");
            }
        }
        System.out.println("Total sum of possible games: " + possibleGames);
    }

    private static boolean parseLine(String line) {
        boolean lineIsPossible = true;
        line = line.substring(line.indexOf(": ") + 2);
        String[] sets = line.split(";");

        outerloop:
        for (int i = 0; i < sets.length; i++) {
            String[] subset = sets[i].split(",");
            for (int j = 0; j < subset.length; j++) {
                String[] singleGrab = subset[j].trim().split(" ");
                switch (singleGrab[1]) {
                    case "red":
                        if (Integer.parseInt(singleGrab[0]) > MAX_RED) {
                            lineIsPossible = false;
                            break outerloop;
                        }
                        break;
                    case "green":
                        if (Integer.parseInt(singleGrab[0]) > MAX_GREEN) {
                            lineIsPossible = false;
                            break outerloop;
                        }
                        break;
                    case "blue":
                        if (Integer.parseInt(singleGrab[0]) > MAX_BLUE) {
                            lineIsPossible = false;
                            break outerloop;
                        }
                        break;
                }
            }
        }
        return lineIsPossible;
    }


    private static void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize() + "\\Day02\\resources\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}