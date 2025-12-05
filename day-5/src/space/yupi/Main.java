package space.yupi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    void main() {
        File inputFile = new File("input-day-5.txt");
        ArrayList<String> ranges = new ArrayList<>();
        ArrayList<Long> numbers = new ArrayList<>();
        long pw = 0;
        long pw2 = 0;

        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("-")) {
                    ranges.add(line);
                } else if (!line.isEmpty()){
                    numbers.add(Long.parseLong(line));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Part 1
        HashSet<Long> alreadyFound = new HashSet<>();
        for (String range : ranges) {
            String[] bounds = range.split("-");
            long lower = Long.parseLong(bounds[0]);
            long upper = Long.parseLong(bounds[1]);
            for (Long number : numbers) {
                if (number >= lower && number <= upper && !alreadyFound.contains(number)) {
                    pw++;
                    alreadyFound.add(number);
                }
            }
        }

        // Part 2
        List<long[]> parsedRanges = new ArrayList<>();
        for (String range : ranges) {
            String[] bounds = range.split("-");
            parsedRanges.add(new long[]{Long.parseLong(bounds[0]), Long.parseLong(bounds[1])});
        }

        parsedRanges.sort(Comparator.comparingLong(a -> a[0]));

        List<long[]> merged = new ArrayList<>();
        for (long[] range : parsedRanges) {
            if (merged.isEmpty() || merged.getLast()[1] < range[0] - 1) {
                merged.add(range);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], range[1]);
            }
        }

        for (long[] range : merged) {
            pw2 += range[1] - range[0] + 1;
        }

        System.out.println(pw);
        System.out.println(pw2);
    }
}