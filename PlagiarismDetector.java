import java.util.*;

public class PlagiarismDetector {

    static HashMap<String, Set<String>> index = new HashMap<>();

    static List<String> getNGrams(String text, int n) {
        String[] words = text.split(" ");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {
            String gram = "";
            for (int j = 0; j < n; j++) {
                gram += words[i + j] + " ";
            }
            grams.add(gram.trim());
        }

        return grams;
    }

    static void addDocument(String docId, String text) {
        List<String> grams = getNGrams(text, 3);

        for (String g : grams) {
            index.putIfAbsent(g, new HashSet<>());
            index.get(g).add(docId);
        }
    }

    static void analyzeDocument(String docId, String text) {
        List<String> grams = getNGrams(text, 3);
        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String g : grams) {
            if (index.containsKey(g)) {
                for (String d : index.get(g)) {
                    matchCount.put(d, matchCount.getOrDefault(d, 0) + 1);
                }
            }
        }

        for (String d : matchCount.keySet()) {
            int matches = matchCount.get(d);
            double similarity = (matches * 100.0) / grams.size();
            System.out.println(d + " similarity: " + similarity + "%");
        }
    }

    public static void main(String[] args) {

        addDocument("essay1", "data structures and algorithms are important for programming");
        addDocument("essay2", "machine learning and data structures are widely used");

        analyzeDocument("essay3", "data structures and algorithms are used in machine learning");
    }
}