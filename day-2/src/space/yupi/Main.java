package space.yupi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    void main() {
        File inputFile = new File("input-day-2.txt");
        String input;
        String[] ranges;
        long pw = 0;
        long pw2 = 0;

        try {
            Scanner scanner = new Scanner(inputFile);
            input = scanner.next();
            ranges = input.split(",");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (String range : ranges) {
            String[] bounds = range.split("-");
            for (long i = Long.parseLong(bounds[0]); i <= Long.parseLong(bounds[1]); i++) {
                if(String.valueOf(i).matches("^(.+)\\1$")) {
                    pw += i;
                }
                if(String.valueOf(i).matches("^(.+)\\1+$")) {
                    pw2 += i;
                }
            }
        }

        System.out.println(pw);
        System.out.println(pw2);
    }
}
