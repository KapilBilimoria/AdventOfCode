import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Day5 {
    static List<RangeEntry> seedToSoilMap = new ArrayList<>();
    static List<RangeEntry> soilToFertilizerMap = new ArrayList<>();
    static List<RangeEntry> fertilizerToWaterMap = new ArrayList<>();
    static List<RangeEntry> waterToLightMap = new ArrayList<>();
    static List<RangeEntry> lightToTemperatureMap = new ArrayList<>();
    static List<RangeEntry> temperatureToHumidityMap = new ArrayList<>();
    static List<RangeEntry> humidityToLocationMap = new ArrayList<>();

    private static void writeToList(Scanner scanner, List<RangeEntry> list) {
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            if(s.isEmpty()) break;
            String[] lst = s.split(" ");
            list.add(new RangeEntry(Long.parseLong(lst[1]) , Long.parseLong(lst[2]), Long.parseLong(lst[0])));
        }
    }
    public static void day5pt1() throws FileNotFoundException {
        try {
            File f = new File("src/day5.txt");
            Scanner scanner = new Scanner(f);
            List<String> seeds = List.of(scanner.nextLine().split(":")[1].trim().split(" "));

            while(scanner.hasNextLine()){
               String s = scanner.nextLine();
                if(s.contains("seed-to-soil map:")) {
                    writeToList(scanner, seedToSoilMap);
                }
                else if (s.contains("soil-to-fertilizer map:")) {
                    writeToList(scanner, soilToFertilizerMap);
                }
                else if (s.contains("fertilizer-to-water map:")) {
                    writeToList(scanner, fertilizerToWaterMap);
                }
                else if (s.contains("water-to-light map:")) {
                    writeToList(scanner, waterToLightMap);
                }
                else if (s.contains("light-to-temperature map:")){
                    writeToList(scanner,lightToTemperatureMap);
                }
                else if (s.contains("temperature-to-humidity map:")){
                    writeToList(scanner, temperatureToHumidityMap);
                }
                else if (s.contains("humidity-to-location map:")){
                    writeToList(scanner, humidityToLocationMap);
                }
            }
            Collections.sort(seedToSoilMap);
            Collections.sort(soilToFertilizerMap);
            Collections.sort(fertilizerToWaterMap);
            Collections.sort(waterToLightMap);
            Collections.sort(lightToTemperatureMap);
            Collections.sort(temperatureToHumidityMap);
            Collections.sort(humidityToLocationMap);
            System.out.println(seedToSoilMap);
            Long smallestLocationNumber = Long.MAX_VALUE;
            for (String seed : seeds) {
                Long seedNumber = Long.parseLong(seed);
                System.out.println("Finding location for seed: " + seed + " seedNumber= " +seedNumber);
                Long soilNumber = generateMapping(seedNumber, seedToSoilMap);
                Long fertilizerNumber = generateMapping(soilNumber, soilToFertilizerMap);
                Long waterNumber = generateMapping(fertilizerNumber, fertilizerToWaterMap);
                Long lightNumber = generateMapping(waterNumber, waterToLightMap);
                Long temperatureNumber = generateMapping(lightNumber, lightToTemperatureMap);
                Long humidityNumber = generateMapping(temperatureNumber, temperatureToHumidityMap);
                Long locationNumber = generateMapping(humidityNumber, humidityToLocationMap);
                smallestLocationNumber = Math.min(smallestLocationNumber, locationNumber);
            }
            System.out.println(smallestLocationNumber);
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    static Long generateMapping(Long source, List<RangeEntry> listToSearchOn) {
        int idx = binarySearch(source, listToSearchOn);
        long diff = source - listToSearchOn.get(idx).sourceStart;
        if(diff > listToSearchOn.get(idx).range) {
            return source;
        }
        return listToSearchOn.get(idx).destStart + diff;
    }
    static Integer binarySearch(Long target, List<RangeEntry> listToSearchOn) {

            int start = 0, end = listToSearchOn.size()-1;
            // Minimum size of the array should be 1
            if(end == 0) return -1;
            // If target lies beyond the max element, than the index of strictly smaller
            // value than target should be (end - 1)
            if (target > listToSearchOn.get(end).sourceStart) return end;

            int ans = -1;
            while (start <= end) {
                int mid = (start + end) / 2;

                // Move to the left side if the target is smaller
                if (listToSearchOn.get(mid).sourceStart > target) {
                    end = mid - 1;
                }

                // Move right side
                else {
                    if(listToSearchOn.get(mid).sourceStart == target) return mid;
                    ans = mid;
                    start = mid + 1;
                }
            }
            return ans;
    }



    public static void day5pt2() throws FileNotFoundException {
        try {
            File f = new File("src/day5.txt");
            Scanner scanner = new Scanner(f);
            List<String> seeds = List.of(scanner.nextLine().split(":")[1].trim().split(" "));

            while(scanner.hasNextLine()){
                String s = scanner.nextLine();
                if(s.contains("seed-to-soil map:")) {
                    writeToList(scanner, seedToSoilMap);
                }
                else if (s.contains("soil-to-fertilizer map:")) {
                    writeToList(scanner, soilToFertilizerMap);
                }
                else if (s.contains("fertilizer-to-water map:")) {
                    writeToList(scanner, fertilizerToWaterMap);
                }
                else if (s.contains("water-to-light map:")) {
                    writeToList(scanner, waterToLightMap);
                }
                else if (s.contains("light-to-temperature map:")){
                    writeToList(scanner,lightToTemperatureMap);
                }
                else if (s.contains("temperature-to-humidity map:")){
                    writeToList(scanner, temperatureToHumidityMap);
                }
                else if (s.contains("humidity-to-location map:")){
                    writeToList(scanner, humidityToLocationMap);
                }
            }
            Collections.sort(seedToSoilMap);
            Collections.sort(soilToFertilizerMap);
            Collections.sort(fertilizerToWaterMap);
            Collections.sort(waterToLightMap);
            Collections.sort(lightToTemperatureMap);
            Collections.sort(temperatureToHumidityMap);
            Collections.sort(humidityToLocationMap);
            //System.out.println(seedToSoilMap);
            Long smallestLocationNumber = Long.MAX_VALUE;
            for(int i = 0; i < seeds.size(); i +=2) {

                Long seedNumber = Long.parseLong(seeds.get(i));

                Long range =  Long.parseLong(seeds.get(i+1));
                System.out.println("Onto SeedNumber: " + seedNumber + " for range: " + range);
                for(long j = seedNumber; j < seedNumber+range; ++j){
                    //System.out.println("Finding location for seed: " + j + " seedNumber= " +seedNumber);
                    Long soilNumber = generateMapping(j, seedToSoilMap);
                    Long fertilizerNumber = generateMapping(soilNumber, soilToFertilizerMap);
                    Long waterNumber = generateMapping(fertilizerNumber, fertilizerToWaterMap);
                    Long lightNumber = generateMapping(waterNumber, waterToLightMap);
                    Long temperatureNumber = generateMapping(lightNumber, lightToTemperatureMap);
                    Long humidityNumber = generateMapping(temperatureNumber, temperatureToHumidityMap);
                    Long locationNumber = generateMapping(humidityNumber, humidityToLocationMap);
                    smallestLocationNumber = Math.min(smallestLocationNumber, locationNumber);
                }
            }
            System.out.println(smallestLocationNumber);
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Press Opt+Enter with your caret at the highlighted text to see how
       //day5pt1();
       long startTime = System.currentTimeMillis();
       day5pt2();
       long endTime = System.currentTimeMillis();
       System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }
}