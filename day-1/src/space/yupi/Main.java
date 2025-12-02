package space.yupi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private int dial = 50;
    private int counter = 0;
    private int counter2 = 0;

    void main() {
        File input = new File("input-day-1.txt");

        try(Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                rotateDial(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println(dial);
        System.out.println(counter);
        System.out.println(counter2);
    }


    private void rotateDial(String direction) {
        if (direction.startsWith("R")){
            for (int i = 0; i < Integer.parseInt(direction.substring(1)); i++) {
                if (dial == 99) {
                    dial = 0;
                } else {
                    dial += 1;
                }

                if (dial == 0) {
                    counter2 += 1;
                }
            }
        } else if (direction.startsWith("L")) {
            for (int i = 0; i < Integer.parseInt(direction.substring(1)); i++) {
                if (dial == 0) {
                    dial = 99;
                } else {
                    dial -= 1;
                }

                if (dial == 0) {
                    counter2 += 1;
                }
            }
        }

        if (dial == 0){
            counter += 1;
        }

    }

}
