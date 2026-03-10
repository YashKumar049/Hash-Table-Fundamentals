import java.util.*;

public class Autocomplete {

    static HashMap<String, Integer> queries = new HashMap<>();

    static void addQuery(String q) {
        queries.put(q, queries.getOrDefault(q, 0) + 1);
    }

    static void search(String prefix) {

        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        for (Map.Entry<String, Integer> e : queries.entrySet()) {
            if (e.getKey().startsWith(prefix)) {
                list.add(e);
            }
        }

        list.sort((a, b) -> b.getValue() - a.getValue());

        int count = 0;
        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + " (" + e.getValue() + ")");
            count++;
            if (count == 10) break;
        }
    }

    public static void main(String[] args) {

        addQuery("java tutorial");
        addQuery("javascript");
        addQuery("java download");
        addQuery("java tutorial");
        addQuery("java 21 features");

        search("jav");

        addQuery("java 21 features");
        addQuery("java 21 features");

        search("java");
    }
}