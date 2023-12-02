import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day02B {

    static List<String> allLines;

    public static void main(String[] args) {
        readfile("input.txt");
        int total = 0;

        for (int i = 0; i < allLines.size(); i++) {
            total += parseLine(allLines.get(i));
        }
        System.out.println("Total: " + total);
    }

    private static int parseLine(String line) {
        int MAX_RED = Integer.MIN_VALUE;
        int MAX_GREEN = Integer.MIN_VALUE;
        int MAX_BLUE = Integer.MIN_VALUE;

        line = line.substring(line.indexOf(": ") + 2);
        String[] sets = line.split(";");

        for (int i = 0; i < sets.length; i++) {
            String[] subset = sets[i].split(",");
            for (int j = 0; j < subset.length; j++) {
                String[] singleGrab = subset[j].trim().split(" ");
                switch (singleGrab[1]) {
                    case "red":
                        if (Integer.parseInt(singleGrab[0]) > MAX_RED) {
                            MAX_RED = Integer.parseInt(singleGrab[0]);
                        }
                        break;
                    case "green":
                        if (Integer.parseInt(singleGrab[0]) > MAX_GREEN) {
                            MAX_GREEN = Integer.parseInt(singleGrab[0]);
                        }
                        break;
                    case "blue":
                        if (Integer.parseInt(singleGrab[0]) > MAX_BLUE) {
                            MAX_BLUE = Integer.parseInt(singleGrab[0]);
                        }
                        break;
                }
            }
        }
        System.out.println("Max Red: " + MAX_RED);
        System.out.println("Max Green: " + MAX_GREEN);
        System.out.println("Max Blue: " + MAX_BLUE);
        return (MAX_RED * MAX_GREEN * MAX_BLUE);
    }


    private static void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize() + "\\Day02\\resources\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}