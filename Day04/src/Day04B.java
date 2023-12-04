import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04B {

    List<String> allLines;
    Integer[] numberOfCopies;

    public static void main(String[] args) {
        new Day04B();
    }


    Day04B() {
        readfile("input.txt");
        numberOfCopies = new Integer[allLines.size()];
//        numberOfCopies = new Integer[400];

        Arrays.fill(numberOfCopies, 1);

        for (int i = 0; i < allLines.size(); i++) {
            int winningMatches = parseLine(allLines.get(i));

            for (int k = 0; k < numberOfCopies[i]; k++) {
                for (int j = 0; j < winningMatches; j++) {
                    numberOfCopies[i + j + 1] = numberOfCopies[i + j + 1] + 1;
                }
            }
            printCards();

        }

        // count all scratchCards
        int total = 0;
        for (int i = 0; i < numberOfCopies.length; i++) {
            System.out.println("scratchCard " + i + ":" + numberOfCopies[i]);
            total += numberOfCopies[i];
        }

        System.out.println("Total = " + total);
    }

    private void printCards() {
        for (int i = 0; i < numberOfCopies.length; i++) {
            System.out.println("scratchCard " + i + ":" + numberOfCopies[i]);
//            total += numberOfCopies[i];
        }
        System.out.println("");
    }

    private int parseLine(String line) {
        String game = line.substring(line.indexOf(":") + 1).trim();
        String winningNumbers = game.substring(0, game.indexOf("|")).trim();
        List<String> winningNumbersArray = new ArrayList<>(Arrays.stream(winningNumbers.trim().split(" ")).toList());

        String myNumbers = line.substring(line.indexOf("|") + 1);
        List<String> myNumbersArray = new ArrayList<>(Arrays.stream(myNumbers.trim().split(" ")).toList());

        for (int i = 0; i < winningNumbersArray.size(); i++) {
            if (winningNumbersArray.get(i).equals("")) {
                winningNumbersArray.remove(i);
                i = i - 1;
            }
        }

        for (int i = 0; i < myNumbersArray.size(); i++) {
            if (myNumbersArray.get(i).equals("")) {
                myNumbersArray.remove(i);
                i = i - 1;
            }
        }

        int numberOfWinningMatches = 0;
        for (int i = 0; i < myNumbersArray.size(); i++) {
            if (winningNumbersArray.contains(myNumbersArray.get(i))) {
                numberOfWinningMatches++;
            }
        }
//        System.out.println("Winning Numbers = " + numberOfWinningMatches);
//        numberOfWinningMatches = numberOfWinningMatches * numberOfCopies;
        return numberOfWinningMatches;
    }


    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize() + "\\Day04\\resources\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}