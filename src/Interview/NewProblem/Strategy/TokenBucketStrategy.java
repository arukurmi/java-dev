package Interview.NewProblem.Strategy;

import Interview.NewProblem.RateLimitConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Token Bucket.
 *
 * Each key owns a bucket holding up to {@code capacity} tokens. Tokens refill
 * continuously at a rate of {@code maxRequests / windowMillis} tokens per
 * millisecond. A request consumes one token; it is allowed only when a whole
 * token is available. Bursts up to the bucket capacity are permitted.
 * Thread-safe: per-key refill/consume is guarded by synchronizing on the bucket.
 */
public class TokenBucketStrategy implements RateLimitStrategy {
    private final long capacity;
    private final double refillTokensPerMilli;
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketStrategy(RateLimitConfig config) {
        this.capacity = config.getCapacity();
        this.refillTokensPerMilli = (double) config.getMaxRequests() / config.getWindowMillis();
    }

    @Override
    public boolean allowRequests(String key) {
        long now = System.currentTimeMillis();
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(capacity, now));
        synchronized (bucket) {
            long elapsed = now - bucket.lastRefill;
            bucket.tokens = Math.min(capacity, bucket.tokens + elapsed * refillTokensPerMilli);
            bucket.lastRefill = now;
            if (bucket.tokens >= 1.0) {
                bucket.tokens -= 1.0;
                return true;
            }
            return false;
        }
    }

    private static class Bucket {
        double tokens;
        long lastRefill;

        Bucket(double tokens, long lastRefill) {
            this.tokens = tokens;
            this.lastRefill = lastRefill;
        }
    }
}
