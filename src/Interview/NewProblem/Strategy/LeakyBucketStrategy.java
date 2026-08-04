package Interview.NewProblem.Strategy;

import Interview.NewProblem.RateLimitConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Leaky Bucket (as a meter).
 *
 * Each key owns a bucket that "leaks" at a constant rate of
 * {@code maxRequests / windowMillis} units per millisecond. A request adds one
 * unit of water; it is allowed only if the bucket does not overflow its
 * {@code capacity}. This smooths bursts into a steady outflow.
 * Thread-safe: per-key leak/add is guarded by synchronizing on the bucket.
 */
public class LeakyBucketStrategy implements RateLimitStrategy {
    private final long capacity;
    private final double leakPerMilli;
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public LeakyBucketStrategy(RateLimitConfig config) {
        this.capacity = config.getCapacity();
        this.leakPerMilli = (double) config.getMaxRequests() / config.getWindowMillis();
    }

    @Override
    public boolean allowRequests(String key) {
        long now = System.currentTimeMillis();
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(now));
        synchronized (bucket) {
            long elapsed = now - bucket.lastLeak;
            bucket.level = Math.max(0.0, bucket.level - elapsed * leakPerMilli);
            bucket.lastLeak = now;
            if (bucket.level + 1.0 <= capacity) {
                bucket.level += 1.0;
                return true;
            }
            return false;
        }
    }

    private static class Bucket {
        double level;
        long lastLeak;

        Bucket(long lastLeak) {
            this.level = 0.0;
            this.lastLeak = lastLeak;
        }
    }
}
