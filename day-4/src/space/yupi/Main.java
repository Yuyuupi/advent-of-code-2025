package space.yupi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    char[][] grid;

    void main() {
        File input = new File("input-day-4.txt");

        loadGrid(input);
        checkGrid();
        loadGrid(input);
        checkGridPart2();
    }

    private void loadGrid(File input) {
        int lineCount = 0;
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        grid = new char[lineCount][];

        try (Scanner scanner = new Scanner(input)) {
            int currentLine = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                addToGrid(currentLine, line);
                currentLine++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void addToGrid(int index, String line) {
        char[] chars = line.toCharArray();
        grid[index] = chars;
    }

    private void checkGrid() {
        int count = checkAllNeighbors();

        System.out.println("Accessible roles: " + count);
    }

    private void checkGridPart2() {
        int totalCount = 0;

        while (true) {
            int count = checkAllNeighbors();

            if (count == 0) {
                break;
            }
            totalCount += count;
        }

        System.out.println("Total accessible rolls (Part 2): " + totalCount);
    }


    private boolean checkNeighbors(int x, int y, int max) {
        int counter = 0;

        // eight direct neighbors
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newY >= 0 && newY < grid.length && newX >= 0 && newX < grid[newY].length && grid[newY][newX] == '@') {
                counter++;
            }
        }

        return counter < max;
    }

    private int checkAllNeighbors() {
        boolean[][] toReplace = new boolean[grid.length][];

        // initialize toReplace
        for (int y = 0; y < grid.length; y++) {
            toReplace[y] = new boolean[grid[y].length];
        }

        // find all positions to replace
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == '@' && checkNeighbors(x, y, 4)) {
                    toReplace[y][x] = true;
                }
            }
        }

        // replace marked positions
        int count = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (toReplace[y][x]) {
                    grid[y][x] = 'x';
                    count++;
                }
            }
        }

        return count;
    }

}
