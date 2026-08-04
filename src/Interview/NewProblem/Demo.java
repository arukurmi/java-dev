package Interview.NewProblem;

import Interview.NewProblem.Factory.RateLimiterFactory;
import Interview.NewProblem.Strategy.RateLimitStrategy;
import Interview.NewProblem.Strategy.Strategy;

/*
*
* Rate Limiter:
*
* Functional Requirements
* - allow(key) -> true/false
* - key -> userId
* - limit requests per key per window
* - Pluggable Rate limiting strategies
* - Configurable (limit, window size)
* - Thread-safe under concurrency
*
* Strategies to support:
* - Fixed Window Counter
* - Token Bucket
* - Leaky Bucket
* - Sliding Window Log
* - Sliding Window Counter
*
* - Non-functional Requirements:
* - Latency (<10ms)
* - In-memory
*
* enum Strategy
* class RateLimitConfig
*
* interface RateLimitStrategy
*
* class FixedWindowStrategy implements RateLimit
*
*
*
*
*
*
* */

public class Demo {

    public static void main(String[] args) {
        // Demonstrate each pluggable strategy against the same request pattern.
        demo(Strategy.FIXED_WINDOW);
        demo(Strategy.TOKEN_BUCKET);
        demo(Strategy.LEAKY_BUCKET);
    }

    private static void demo(Strategy strategy) {
        RateLimitConfig config = RateLimitConfig.builder()
                .strategy(strategy)
                .maxRequests(5)
                .windowMillis(1000)
                .capacity(5)
                .build();

        RateLimitStrategy limiter = RateLimiterFactory.create(config);

        String key = "user-1";
        int allowed = 0;
        int blocked = 0;
        System.out.println("=== " + strategy + " (limit 5 / 1000ms, capacity 5) ===");
        for (int i = 1; i <= 8; i++) {
            boolean ok = limiter.allowRequests(key);
            if (ok) {
                allowed++;
            } else {
                blocked++;
            }
            System.out.println("  request " + i + " -> " + (ok ? "ALLOWED" : "BLOCKED"));
        }
        System.out.println("  summary: " + allowed + " allowed, " + blocked + " blocked\n");
    }
}
