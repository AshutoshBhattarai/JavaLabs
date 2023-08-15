package FileHandling;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileAnalyzer {
    public static void main(String[] args) {
        FileAnalyzer analyzer = new FileAnalyzer();
        File file = new File("TestFile2.txt");
        if (file.exists()) {
            try {
                analyzer.testingProcess(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
            System.out.println("File not found.");
    }

    public void testingProcess(File file) throws IOException {
        Scanner reader = new Scanner(file);

        String fileContent = "";
        int count = 0;
        while (reader.hasNextLine()) {
            fileContent += (reader.nextLine());
            count++;
        }
        System.out.println("No of lines found : " + count);
        System.out.println("File content : " + fileContent);
        String cleanedData = cleanData(fileContent);
        System.out.println("Cleaned up data : " + cleanedData);
        System.out.println("No words found : " + getWordCount(cleanedData));
        System.out.println("Array content : " + getWords(cleanedData));
        System.out.println("Search  result : " + searchThings(cleanedData, "Ten"));

        System.out.println("Frequent Words(Count) : ");
        getWordsWCount(cleanedData);
        System.out.println("Frequent Words(Frequency) : ");
        getWordsWFreq(cleanedData);


    }

    public int getWordCount(String data) {
        String[] words = data.split(" ");
        return words.length;
    }

    public String cleanData(String data) {
        String regex = "[.,'\"()!?\\-]";
        data = data.replaceAll(System.lineSeparator(), "");
        return data.replaceAll(regex, " ").toLowerCase();
    }

    public HashSet<String> getWords(String data) {
        String[] wordArray = data.split(" ");
        return new HashSet<>(Arrays.asList(wordArray));
    }

    public void getWordsWCount(String data) {
        getWordsOccurence(data).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));
    }

    public void getWordsWFreq(String data){
        Map<String, Integer> oldMap = getWordsOccurence(data);
        Map<String, Double> newMap = new HashMap<>();
        for(String key : oldMap.keySet())
        {
            newMap.put(key, Double.valueOf(oldMap.get(key)));
        }

        newMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(entry ->
                {
                    System.out.println(entry.getKey()
                            + ":" + (entry.getValue()/getWordCount(data))*100+"%");
                });


    }

    public Map<String, Integer> getWordsOccurence(String data)
    {
        Map<String, Integer> hashMap = Arrays
                .stream(data.split(" "))
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.toMap(val -> val, val -> 0));
        for (String word : data.split(" ")) {
            if (hashMap.containsKey(word))
                hashMap.put(word, hashMap.get(word) + 1);
        }
        hashMap.remove("");
        return hashMap;
    }

    public String searchThings(String allData, String search) {
        search = search.toLowerCase();
        String regexp = "\\b(" + search + ")\\b";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(allData);
        return matcher.find() ? search : "Not Found";
    }
}
