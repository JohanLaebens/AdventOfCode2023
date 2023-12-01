import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day01A {

    static List<String> allLines;

    public static void main(String[] args) {
        readfile("input.txt");
        int total = 0;

        for (int i = 0; i < allLines.size(); i++) {
            total = total + getFirstAndLastDigit(allLines.get(i));
        }
        System.out.println("Total: " + total);
    }

    private static int getFirstAndLastDigit(String input) {
        // only the first and last digit need to be returned
        String firstDigit = "";
        String lastDigit = "";

        // from start to end
        startToEnd:
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (temp >= 48 && temp <= 57) // it's a number
            {
                firstDigit = String.valueOf(temp);
                break startToEnd;
            }
        }

        // from end to start
        endToStart:
        for (int i = input.length() - 1; i >= 0; i--) {
            char temp = input.charAt(i);
            if (temp >= 48 && temp <= 57) // it's a number
            {
                lastDigit = String.valueOf(temp);
                break endToStart;
            }
        }
        System.out.println(firstDigit + lastDigit);
        return Integer.parseInt(String.valueOf(firstDigit + lastDigit));
    }

    private static void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize() + "\\Day01\\resources\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}