package space.yupi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private long pw = 0;
    private long pw2 = 0;

    void main() {
        File input = new File("input-day-3.txt");

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                pw += variableJolts(line, 2);
                pw2 += variableJolts(line, 12);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println(pw);
        System.out.println(pw2);
    }

    private long variableJolts(String line, int size) {
        int n = line.length();
        StringBuilder result = new StringBuilder();
        int start = 0;

        for (int i = 0; i < size; i++) {
            char maxChar = '0';
            int maxIndex = start;

            int remainingNeeded = size - i - 1;
            for (int j = start; j <= n - remainingNeeded - 1; j++) {
                if (Character.isDigit(line.charAt(j)) && line.charAt(j) > maxChar) {
                    maxChar = line.charAt(j);
                    maxIndex = j;
                }
            }

            result.append(maxChar);
            start = maxIndex + 1;
        }

        return Long.parseLong(result.toString());
    }

}
