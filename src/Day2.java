import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Day2 {

    public static void day2pt1() throws FileNotFoundException {
        try{
            File f = new File("src/day2.txt");
            Scanner scanner = new Scanner(f);
            int validGames =0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Integer id = Integer.parseInt(line.split(":")[0].trim().split(" ")[1]);
                String[] games = line.split(":")[1].trim().split(";");
                System.out.println(Arrays.toString(games));
                boolean valid = true;
                for(int i = 0; i < games.length; ++i) {
                    String[] balls = games[i].split(",");
                    for(int j = 0; j < balls.length; ++j){
                        String[] kvp = balls[j].trim().split(" ");
                        // num = kvp[0], colour = kvp[1]

                        if(kvp[1].equals("red") && Integer.parseInt(kvp[0]) > 12) {
                            System.out.println(Arrays.toString(kvp));
                            valid = false;
                            break;
                        } else if (kvp[1].equals("green") && Integer.parseInt(kvp[0]) > 13) {
                            System.out.println(Arrays.toString(kvp));
                            valid = false;
                            break;
                        } else if (kvp[1].equals("blue") && Integer.parseInt(kvp[0]) > 14) {
                            System.out.println(Arrays.toString(kvp));
                            valid = false;
                            break;
                        }
                    }
                    if(!valid) break;
                }
                if(valid) validGames += id;
            }
            System.out.println(validGames);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void day2pt2() throws FileNotFoundException {
        try{
            File f = new File("src/day2.txt");
            Scanner scanner = new Scanner(f);
            int result =0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Integer id = Integer.parseInt(line.split(":")[0].trim().split(" ")[1]);
                String[] games = line.split(":")[1].trim().split(";");
                //System.out.println(Arrays.toString(games));
                HashMap<String, Integer> minBallsPerColour = new HashMap<>();
                minBallsPerColour.put("red", Integer.MIN_VALUE);
                minBallsPerColour.put("green", Integer.MIN_VALUE);
                minBallsPerColour.put("blue", Integer.MIN_VALUE);
                boolean valid = true;

                for(int i = 0; i < games.length; ++i) {
                    String[] balls = games[i].split(",");
                    for(int j = 0; j < balls.length; ++j) {
                        String[] kvp = balls[j].trim().split(" ");
                        // num = kvp[0], colour = kvp[1]
                        Integer minCandidate = Integer.parseInt(kvp[0]);
                        if (minBallsPerColour.get(kvp[1]) < minCandidate) {
                            minBallsPerColour.put(kvp[1], minCandidate);
                        }
                    }
                }
                int power = 1;
                System.out.println(minBallsPerColour.toString());
                for(Map.Entry<String,Integer> entry : minBallsPerColour.entrySet()) {
                    power *= entry.getValue();
                }
                result += power;
            }
            System.out.println(result);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Press Opt+Enter with your caret at the highlighted text to see how
        //day2pt1();
        day2pt2();
    }
}