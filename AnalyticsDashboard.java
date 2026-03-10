import java.util.*;

public class AnalyticsDashboard {

    static HashMap<String, Integer> pageViews = new HashMap<>();
    static HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    static HashMap<String, Integer> sources = new HashMap<>();

    static void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    static void getDashboard() {

        List<Map.Entry<String, Integer>> list = new ArrayList<>(pageViews.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Top Pages:");

        int count = 0;
        for (Map.Entry<String, Integer> e : list) {
            String page = e.getKey();
            int views = e.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println(page + " - " + views + " views (" + unique + " unique)");
            count++;
            if (count == 10) break;
        }

        System.out.println("\nTraffic Sources:");

        int total = 0;
        for (int v : sources.values()) total += v;

        for (String s : sources.keySet()) {
            double p = (sources.get(s) * 100.0) / total;
            System.out.println(s + ": " + p + "%");
        }
    }

    public static void main(String[] args) {

        processEvent("/article/breaking-news", "user1", "google");
        processEvent("/article/breaking-news", "user2", "facebook");
        processEvent("/sports/championship", "user3", "google");
        processEvent("/sports/championship", "user1", "direct");

        getDashboard();
    }
}