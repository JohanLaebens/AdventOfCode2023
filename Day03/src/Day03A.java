import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day03A {

    List<String> allLines;
    String[][] grid;
    int maxSizeX = 0;
    int maxSizeY = 0;
    String DOT = ".";
    List<String> allowedChars = new ArrayList<String>();
    List<String> numbers = new ArrayList<String>();
    int total = 0;


    public static void main(String[] args) {
        new Day03A();
    }


    Day03A() {
        readfile("input.txt");
        addAllowedChars();
        addNumbers();

        maxSizeX = allLines.get(0).length() + 2; // adding borders, just to make the search easier
        maxSizeY = allLines.size() + 2; // adding borders, just to make the search easier

        grid = new String[maxSizeX][maxSizeY];
        fillGridWithDots();
        fillGrid();
//        printGrid(grid);

        searchForNumber();

    }

    private void searchForNumber() {
        int startXOfNumber = 0;
        int endXOfNumber = 0;


        searchForNumberLoop:
        for (int y = 1; y < maxSizeY - 1; y++) {             // no need to do the borders
            for (int x = 1; x < maxSizeX; x++) {         // no need to do the borders
//                System.out.println("Checking " + grid[x][y] + " for dot");

                if (numbers.contains(grid[x][y])) {
                    startXOfNumber = x;
                    // found a first number, let's see how long it is

                    searchForEndOfNumber:
                    for (int i = startXOfNumber; i < maxSizeX; i++) {
                        if (!numbers.contains(grid[i][y])) {
                            endXOfNumber = i;
                            break searchForEndOfNumber;
                        }
                    }

//                    printNumber(startXOfNumber, endXOfNumber, y);

                    // search around the received locations for any symbol
                    boolean symbolFound = false;

                    searchForSymbol:
                    // search in the line above and below
                    for (int y2 = y - 1; y2 <= y + 1; y2 = y2 + 2) {
                        for (int x2 = startXOfNumber - 1; x2 < endXOfNumber + 1; x2++) {
                            if (!allowedChars.contains(grid[x2][y2])) {
                                symbolFound = true;
                                break searchForSymbol;
                            }
                        }
                    }

                    //search for right before and right after the number
                    if (!allowedChars.contains(grid[startXOfNumber - 1][y]) || !allowedChars.contains(grid[endXOfNumber][y])) {
                        symbolFound = true;
                    }

                    // if there is no symbol around the number >> return the number
                    if (symbolFound) {
                        int number = printNumber(startXOfNumber, endXOfNumber, y);
                        total += number;
                    }

                    x = x + (endXOfNumber - startXOfNumber);
                }
            }
        }
        System.out.println("Total : " + total);
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