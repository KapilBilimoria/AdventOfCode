import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Day4 {


    public static void day4pt1() throws FileNotFoundException {
        try {
            File f = new File("src/day4.txt");
            Scanner scanner = new Scanner(f);
            int total = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] cards = line.split(":")[1].trim().split("\\|");
                Set<Integer>winningCards = new HashSet<>();
                Set<Integer>overlappingCards = new HashSet<>();
                Set<Integer>yourCards = new HashSet<>();
                for(String winningCard : cards[0].trim().split(" ")){
                    try {
                        winningCards.add(Integer.parseInt(winningCard));
                    }
                    catch(NumberFormatException ex) {
                        System.out.println("Can't do anything about this one");
                    }
                }
                for(String yourCard : cards[1].trim().split(" ")) {
                    try {

                        if (winningCards.contains(Integer.parseInt(yourCard)))
                            overlappingCards.add(Integer.parseInt(yourCard));
                    }
                    catch(NumberFormatException ex) {
                        System.out.println("Can't do anything about this one");
                    }
                }
                System.out.println("WinningCards =" + winningCards.toString()  + " Overlapping cards = " + overlappingCards.toString());
                System.out.println(" matches = " + overlappingCards.size() + " score = " + (1 << (overlappingCards.size() -1)));
                total += !overlappingCards.isEmpty() ? (1 << (overlappingCards.size() - 1)) : 0;
            }
            System.out.println(total);
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }


    public static void day4pt2() throws FileNotFoundException {
         try {
            File f = new File("src/day4.txt");
            Scanner scanner = new Scanner(f);
            int total = 0;
            List<Integer> wins = new ArrayList<>();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] cards = line.split(":")[1].trim().split("\\|");
                Set<Integer>winningCards = new HashSet<>();
                Set<Integer>overlappingCards = new HashSet<>();
                Set<Integer>yourCards = new HashSet<>();
                for(String winningCard : cards[0].trim().split(" ")){
                    try {
                        winningCards.add(Integer.parseInt(winningCard));
                    }
                    catch(NumberFormatException ex) {
                        System.out.println("Can't do anything about this one");
                    }
                }
                for(String yourCard : cards[1].trim().split(" ")) {
                    try {

                        if (winningCards.contains(Integer.parseInt(yourCard)))
                            overlappingCards.add(Integer.parseInt(yourCard));
                    }
                    catch(NumberFormatException ex) {
                        System.out.println("Can't do anything about this one");
                    }
                }
                System.out.println("WinningCards =" + winningCards.toString()  + " Overlapping cards = " + overlappingCards.toString());
                System.out.println(" matches = " + overlappingCards.size() + " score = " + (1 << (overlappingCards.size() -1)));
                //total += !overlappingCards.isEmpty() ? (1 << (overlappingCards.size() - 1)) : 0;
                wins.add(overlappingCards.size());
            }
             List<Integer> copiesOfCards = new ArrayList<>(Collections.nCopies(wins.size(), 1));
            System.out.println(copiesOfCards);
            for(int i = 0; i < wins.size(); ++i){
                int j = 1;
                while( (i+j) < wins.size() && (i+j) <= i+wins.get(i)) {
                    System.out.println("Setting " + (i+j) + " to " +  (copiesOfCards.get(i+j) + copiesOfCards.get(i)));

                    copiesOfCards.set(i+j, copiesOfCards.get(i+j) + copiesOfCards.get(i));
                    j++;
                }
            }
            System.out.println("CopiesOfCards: " + copiesOfCards);

            for(int i = 0; i < wins.size(); ++i) {
                total += copiesOfCards.get(i);
            }
            System.out.println(total);
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Press Opt+Enter with your caret at the highlighted text to see how
       //day4pt1();
        day4pt2();
    }
}