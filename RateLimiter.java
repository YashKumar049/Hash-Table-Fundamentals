import java.util.*;

class TokenBucket {
    int tokens;
    long lastRefill;

    TokenBucket(int max) {
        tokens = max;
        lastRefill = System.currentTimeMillis();
    }
}

public class RateLimiter {

    static HashMap<String, TokenBucket> clients = new HashMap<>();
    static int MAX = 5;

    static void checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(MAX));
        TokenBucket bucket = clients.get(clientId);

        long now = System.currentTimeMillis();

        if (now - bucket.lastRefill >= 3600000) {
            bucket.tokens = MAX;
            bucket.lastRefill = now;
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            System.out.println("Allowed (" + bucket.tokens + " remaining)");
        } else {
            System.out.println("Denied (limit reached)");
        }
    }

    static void getRateLimitStatus(String clientId) {

        TokenBucket bucket = clients.get(clientId);
        int used = MAX - bucket.tokens;

        System.out.println("{used: " + used + ", limit: " + MAX + "}");
    }

    public static void main(String[] args) {

        checkRateLimit("abc123");
        checkRateLimit("abc123");
        checkRateLimit("abc123");
        checkRateLimit("abc123");
        checkRateLimit("abc123");
        checkRateLimit("abc123");

        getRateLimitStatus("abc123");
    }
}