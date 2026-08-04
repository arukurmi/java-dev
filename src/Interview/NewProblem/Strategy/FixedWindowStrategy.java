package Interview.NewProblem.Strategy;

import Interview.NewProblem.RateLimitConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Fixed Window Counter.
 *
 * Each key gets a window of {@code windowMillis}. Within a window at most
 * {@code maxRequests} are allowed. When the window elapses the counter resets.
 * Thread-safe: per-key state is guarded by synchronizing on the window object.
 */
public class FixedWindowStrategy implements RateLimitStrategy{
    private final long maxRequests;
    private final long windowMillis;
    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    public FixedWindowStrategy(RateLimitConfig rlconfig){
        this.maxRequests = rlconfig.getMaxRequests();
        this.windowMillis = rlconfig.getWindowMillis();
    }

    @Override
    public boolean allowRequests(String key){
        long now = System.currentTimeMillis();
        Window window = windows.computeIfAbsent(key, k -> new Window(now));
        synchronized (window) {
            if (now - window.windowStart >= windowMillis) {
                window.windowStart = now;
                window.count = 0;
            }
            if (window.count < maxRequests) {
                window.count++;
                return true;
            }
            return false;
        }
    }

    private static class Window {
        long windowStart;
        long count;

        Window(long windowStart) {
            this.windowStart = windowStart;
            this.count = 0;
        }
    }
}
