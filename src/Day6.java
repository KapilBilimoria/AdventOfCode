import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Day6 {


    public static void day6pt1() throws FileNotFoundException {
        try {
            File f = new File("src/day6.txt");
            Scanner scanner = new Scanner(f);

            List<String> times = Arrays.stream(scanner.nextLine().split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).toList();
            List<String> distances = Arrays.stream(scanner.nextLine().split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).toList();
            List<Integer> ways = new ArrayList<>();
            for(int i = 0; i < times.size(); ++i) {
                int time = Integer.parseInt(times.get(i));
                int targetDistance = Integer.parseInt(distances.get(i));
                int recordsBeaten = 0;
                for(int accel = 0; accel < time; ++accel){
                    if(accel * (time - accel) > targetDistance) ++recordsBeaten;
                }
                ways.add(recordsBeaten);
            }
            int result = 1;
            for(int way: ways){
                result *= way;
            }
            System.out.println(times);
            System.out.println(distances);
            System.out.println(result);



        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void day6pt2() throws FileNotFoundException {
        try {
            File f = new File("src/day6.txt");
            Scanner scanner = new Scanner(f);

            Long time = Long.parseLong(scanner.nextLine().split(":")[1].trim().replaceAll(" ", ""));
            Long distance = Long.parseLong(scanner.nextLine().split(":")[1].trim().replaceAll(" ", ""));
            int recordsBeaten = 0;
            for(long accel = 0; accel < time; ++accel) {
                if(accel * (time - accel) > distance) ++recordsBeaten;
            }
            System.out.println(time);
            System.out.println(distance);
            System.out.println(recordsBeaten);


        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }



    public static void main(String[] args) throws FileNotFoundException {
        // Press Opt+Enter with your caret at the highlighted text to see how
       long startTime = System.currentTimeMillis();
       //day6pt1();
       day6pt2();
       long endTime = System.currentTimeMillis();
       System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }
}