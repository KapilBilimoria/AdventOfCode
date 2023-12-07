import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Day1 {
    public static HashMap<String, String> s = new HashMap<>();
    static {
        s.put("one", "1");
        s.put("two", "2");
        s.put("three", "3");
        s.put("four", "4");
        s.put("five", "5");
        s.put("six", "6");
        s.put("seven", "7");
        s.put("eight", "8");
        s.put("nine", "9");
    }
    private static void day1() throws FileNotFoundException {
        try {
            File f = new File("src/day1.txt");
            Scanner scanner = new Scanner(f);
            int result = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringBuilder num = new StringBuilder();
                StringBuilder temp = new StringBuilder();
                for(int i = 0; i < line.length(); ++i) {
                    if(Character.isDigit(line.charAt(i))) {
                        num.append(line.charAt(i));
                        temp.replace(0, temp.length(),"");
                    }
                    else{
                        temp.append(line.charAt(i));
                        for(Map.Entry<String, String> number : s.entrySet()) {
                            if(temp.toString().contains(number.getKey())) {
                                System.out.println("Found match with "+ number.getKey());
                                num.append(number.getValue());
                                temp.replace(0, temp.length()-1, "");
                            }
                        }
                    }
                }
                // get first and last
                String t = num.toString();
                 if(num.length() == 1) num.append(num.charAt(0));
                 else if(num.length() > 2) num.replace(1, num.length()-1, "");
                int i = Integer.parseInt(num.toString());
                System.out.println("line = " + line + ", num = " + t + " i = " + i);
                result += i;
            }
            System.out.println(result);
        }catch(FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        // Press Opt+Enter with your caret at the highlighted text to see how
        day1();
    }
}