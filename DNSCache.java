import java.util.*;

class DNSEntry {
    String ip;
    long expiryTime;

    DNSEntry(String ip, int ttl) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttl * 1000;
    }
}

public class DNSCache {

    static HashMap<String, DNSEntry> cache = new HashMap<>();
    static int hits = 0;
    static int misses = 0;

    static String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (System.currentTimeMillis() < entry.expiryTime) {
                hits++;
                return "Cache HIT → " + entry.ip;
            }
        }

        misses++;

        String newIP = queryUpstream(domain);

        cache.put(domain, new DNSEntry(newIP, 10)); 

        return "Cache MISS → " + newIP;
    }

    static String queryUpstream(String domain) {
        return "172.217.14." + (new Random().nextInt(100));
    }

    static void getCacheStats() {

        int total = hits + misses;
        double hitRate = (total == 0) ? 0 : (hits * 100.0) / total;

        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) throws Exception {

        System.out.println(resolve("google.com"));
        System.out.println(resolve("google.com"));

        Thread.sleep(11000); 

        System.out.println(resolve("google.com"));

        getCacheStats();
    }
}