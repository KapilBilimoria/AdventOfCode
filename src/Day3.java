import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Day3 {
    public static List<Integer>dirsI = Arrays.asList(-1, 0, 1);
    public static List<Integer>dirsJ = Arrays.asList(-1,0,1);


    public static boolean processMatrix(List<String> matrix, int i, int j) {
        int total = 0;
        for(Integer addI : dirsI) {
            int newI = i + addI;
            if(newI >= 0 && newI < matrix.size()) {
                for (Integer addJ : dirsJ) {
                    int newJ = j + addJ;
                    if(newJ >= 0 && newJ < matrix.get(0).length()) {
                        if(!Character.isDigit(matrix.get(newI).charAt(newJ)) && matrix.get(newI).charAt(newJ) != '.') {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static void day3pt1() throws FileNotFoundException {
        try{

            File f = new File("src/day3.txt");
            Scanner scanner = new Scanner(f);
            Integer total = 0;
            List<String> matrix = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                matrix.add(line);
            }
            for(int i =0; i < matrix.size(); ++i){
                int j = 0;
                while(j < matrix.get(0).length()){
                    char c = matrix.get(i).charAt(j);
                    if(Character.isDigit(c)) {
                        boolean valid = false;
                        int beginIdx = j;
                        // this is a valid digit, now we have to check everything ahead for the whole number.
                        while(j < matrix.get(0).length() && Character.isDigit(matrix.get(i).charAt(j))) {
                            if(processMatrix(matrix, i,j)) valid = true;
                            ++j;
                        }
                        if(valid) total+= Integer.parseInt(matrix.get(i).substring(beginIdx,j));
                    }else {
                        ++j;
                    }

                }
            }
            System.out.println(total);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static Integer processStars(List<String> matrix, int i, int j, HashSet<Integer> visited) {
        for(Integer addI : dirsI) {
            int newI = i + addI;
            if(newI >= 0 && newI < matrix.size()) {
                for (Integer addJ : dirsJ) {
                    int newJ = j + addJ;
                    if(newJ >= 0 && newJ < matrix.get(0).length()) {
                        if(Character.isDigit(matrix.get(newI).charAt(newJ))) {
                            int beginIdx = newJ;
                            int endIdx = newJ;
                            while(beginIdx >=0 && Character.isDigit(matrix.get(newI).charAt(beginIdx))) --beginIdx;
                            while(endIdx < matrix.get(0).length() && Character.isDigit(matrix.get(newI).charAt(endIdx))) ++endIdx;
                            visited.add(Integer.parseInt(matrix.get(newI).substring(beginIdx+1, endIdx)));
                        }
                    }
                }
            }
        }
        if(visited.size() == 2){
            int total = 1;
            for(Integer value : visited) total *= value;
            return total;
        }
        return 0;
    }
    public static void day3pt2() throws FileNotFoundException {
        try{
            File f = new File("src/day3.txt");
            Scanner scanner = new Scanner(f);
            int total = 0;
            List<String> matrix = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                matrix.add(line);
            }
            for(int i =0; i < matrix.size(); ++i){
                int j = 0;
                while(j < matrix.get(0).length()){
                    char c = matrix.get(i).charAt(j);
                    if(c == '*') {
                        HashSet<Integer> visited = new HashSet<>();
                        total+= processStars(matrix, i, j, visited);

                    }
                    ++j;
                }
            }
            System.out.println(total);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Press Opt+Enter with your caret at the highlighted text to see how
       //day3pt1();
        day3pt2();
    }
}