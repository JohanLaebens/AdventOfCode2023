import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day01B {

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
        String inputBackup = input;

        // only the first and last digit need to be returned
        String firstDigit = "";
        String lastDigit = "";

        // from start to end
        for (int i = 0; i < input.length(); i++) {
            try {
                if (input.substring(i, i + 3).equals("one")) {
                    input = input.replaceFirst("one", "1");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 3).equals("two")) {
                    input = input.replaceFirst("two", "2");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 5).equals("three")) {
                    input = input.replaceFirst("three", "3");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 4).equals("four")) {
                    input = input.replaceFirst("four", "4");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 4).equals("five")) {
                    input = input.replaceFirst("five", "5");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 3).equals("six")) {
                    input = input.replaceFirst("six", "6");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 5).equals("seven")) {
                    input = input.replaceFirst("seven", "7");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 5).equals("eight")) {
                    input = input.replaceFirst("eight", "8");
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i, i + 4).equals("nine")) {
                    input = input.replaceFirst("nine", "9");
                }
            } catch (Exception ignored) {
            }
        }

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
        input = inputBackup; // restore the original

        for (int i = input.length(); i >= 0; i--) {
            try {
                if (input.substring(i - 3, i).equals("one")) {
                    input = input.substring(0, input.lastIndexOf("one")) + "1" + input.substring(input.lastIndexOf("one") + 3);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 3, i).equals("two")) {
                    input = input.substring(0, input.lastIndexOf("two")) + "2" + input.substring(input.lastIndexOf("two") + 3);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 5, i).equals("three")) {
                    input = input.substring(0, input.lastIndexOf("three")) + "3" + input.substring(input.lastIndexOf("three") + 5);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 4, i).equals("four")) {
                    input = input.substring(0, input.lastIndexOf("four")) + "4" + input.substring(input.lastIndexOf("four") + 4);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 4, i).equals("five")) {
                    input = input.substring(0, input.lastIndexOf("five")) + "5" + input.substring(input.lastIndexOf("five") + 4);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 3, i).equals("six")) {
                    input = input.substring(0, input.lastIndexOf("six")) + "6" + input.substring(input.lastIndexOf("six") + 3);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 5, i).equals("seven")) {
                    input = input.substring(0, input.lastIndexOf("seven")) + "7" + input.substring(input.lastIndexOf("seven") + 5);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 5, i).equals("eight")) {
                    input = input.substring(0, input.lastIndexOf("eight")) + "8" + input.substring(input.lastIndexOf("eight") + 5);
                }
            } catch (Exception ignored) {
            }
            try {
                if (input.substring(i - 4, i).equals("nine")) {
                    input = input.substring(0, input.lastIndexOf("nine")) + "9" + input.substring(input.lastIndexOf("nine") + 4);
                }
            } catch (Exception ignored) {
            }
        }


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