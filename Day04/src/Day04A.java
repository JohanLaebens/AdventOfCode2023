import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04A {

    List<String> allLines;
    int total = 0;

    public static void main(String[] args) {
        new Day04A();
    }


    Day04A() {
        readfile("input.txt");
        for (int i = 0; i < allLines.size(); i++) {
            int totalOfCard = parseLine(allLines.get(i));
            total += totalOfCard;
        }
        System.out.println("Total: " + total);
    }

    private int parseLine(String line) {
        int subTotal = 0;

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


        for (int i = 0; i < myNumbersArray.size(); i++) {
            if (winningNumbersArray.contains(myNumbersArray.get(i))) {
                if (subTotal == 0) {
                    subTotal = 1;
                } else {
                    subTotal = subTotal * 2;
                }
            }
        }
        System.out.println("subtotal = " + subTotal);
        return subTotal;
    }


    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize() + "\\Day04\\resources\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}