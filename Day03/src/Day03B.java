import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day03B {

    List<String> allLines;
    String[][] grid;
    int maxSizeX = 0;
    int maxSizeY = 0;
    String DOT = ".";
    List<String> allowedChars = new ArrayList<>();
    List<String> numbers = new ArrayList<>();
    int total = 0;

    String GEAR = "*";

    public static void main(String[] args) {
        new Day03B();
    }


    Day03B() {
        readfile("input.txt");
        addAllowedChars();
        addNumbers();

        maxSizeX = allLines.get(0).length() + 2; // adding borders, just to make the search easier
        maxSizeY = allLines.size() + 2; // adding borders, just to make the search easier

        grid = new String[maxSizeX][maxSizeY];
        fillGridWithDots();
        fillGrid();
        searchForGear();
    }

    private void searchForGear() {
        int startXOfNumber = 0;
        int endXOfNumber = 0;
        List<Integer> foundNumbers = new ArrayList<>();

        for (int y = 1; y < maxSizeY - 1; y++) {             // no need to do the borders
            for (int x = 1; x < maxSizeX; x++) {         // no need to do the borders
                if (GEAR.equals(grid[x][y])) {
                    System.out.println("Found gear at " + x + ":" + y);

                    // find a number around the gear
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (numbers.contains(grid[x + j][y + i])) {
//                                System.out.println(grid[x + j][y + i]);

                                foundBeginning:
                                for (int k = 0; ; k--) {
                                    if (!numbers.contains(grid[x + j + k][y + i])) {
                                        startXOfNumber = x + j + k + 1;
                                        break foundBeginning;
                                    }
                                }
                                foundEnding:
                                for (int k = 0; ; k++) {
                                    if (!numbers.contains(grid[x + j + k][y + i])) {
                                        endXOfNumber = x + j + k;
                                        break foundEnding;
                                    }
                                }
                                foundNumbers.add(printNumber(startXOfNumber, endXOfNumber, y + i));
                            }
                        }
                    }
                    List<Integer> listWithoutDuplicates = foundNumbers.stream().distinct().toList();
                    if (listWithoutDuplicates.size() > 1) {
                        for (int f = 0; f < listWithoutDuplicates.size(); f++) {
                            System.out.println("Number " + f + ": " + listWithoutDuplicates.get(f));
                        }
                        total = total + (listWithoutDuplicates.get(0) * listWithoutDuplicates.get(1));
                    }
                    foundNumbers.clear();
                }
            }
        }
        System.out.println("Total: " + total);

    }


    private int printNumber(int startXOfNumber, int endXOfNumber, int y) {
        StringBuilder number = new StringBuilder();
        if (startXOfNumber != endXOfNumber) {
            for (int i = startXOfNumber; i < endXOfNumber; i++) {
                number.append(grid[i][y]);
            }
            System.out.println("Found Number = " + number);
            return Integer.parseInt(number.toString());
        }
        return 0;
    }

    private void fillGridWithDots() {
        for (int y = 0; y < maxSizeY; y++) {
            for (int x = 0; x < maxSizeX; x++) {
                grid[x][y] = DOT;
            }
        }
    }

    private void fillGrid() {
        for (int y = 1; y < maxSizeY - 1; y++) {
            for (int x = 1; x < maxSizeX - 1; x++) {
                grid[x][y] = allLines.get(y - 1).substring(x - 1, x);
            }
        }
    }

    private void printGrid(String[][] map) {
        System.out.println();
        for (int y = 0; y < maxSizeY; y++) {
            for (int x = 0; x < maxSizeX; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
    }

    private void addNumbers() {
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");
        numbers.add("0");
    }

    private void addAllowedChars() {
        allowedChars.add("1");
        allowedChars.add("2");
        allowedChars.add("3");
        allowedChars.add("4");
        allowedChars.add("5");
        allowedChars.add("6");
        allowedChars.add("7");
        allowedChars.add("8");
        allowedChars.add("9");
        allowedChars.add("0");
        allowedChars.add(".");
    }

    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize() + "\\Day03\\resources\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}