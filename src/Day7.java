import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Day7 {
/*
2, 0
3, 1
4, 2
5, 3
6, 4
7, 5
8, 6
9, 7
T, 8
J, 9
Q, 10
K, 11
A, 12
 */


    public static void day7pt1() throws FileNotFoundException {
        try {
            File f = new File("src/day7.txt");
            Scanner scanner = new Scanner(f);
            List<Card> cards = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String hand = line.split(" ")[0];
                String bid = line.split(" ")[1];
                cards.add(new Card(hand, bid));
            }
            Collections.sort(cards);
            int n = cards.size();
            int total = 0;

            for(int i =n-1 ; i >= 0; --i) {
                System.out.println(cards.get(i));
                int mult = n-(n-i-1);
                int value = cards.get(i).bid * mult;
                total += value;
            }
            System.out.println(total);
            // sort the cards based on hand -> tiebreaker is
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void day7pt2() throws FileNotFoundException {
        try {
            File f = new File("src/day7.txt");
            Scanner scanner = new Scanner(f);
            List<CardPart2> cards = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String hand = line.split(" ")[0];
                String bid = line.split(" ")[1];
                cards.add(new CardPart2(hand, bid));
            }
            Collections.sort(cards);
            int n = cards.size();
            int total = 0;

            for(int i =n-1 ; i >= 0; --i) {
                System.out.println(cards.get(i));
                int mult = n-(n-i-1);
                int value = cards.get(i).bid * mult;
                total += value;
            }
            System.out.println(total);
            // sort the cards based on hand -> tiebreaker is
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }





    public static void main(String[] args) throws FileNotFoundException {
        // Press Opt+Enter with your caret at the highlighted text to see how

       long startTime = System.currentTimeMillis();
        //day7pt1();
        day7pt2();
       long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }
}