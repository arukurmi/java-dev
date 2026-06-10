package Practice.ratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowRateLimiter extends RateLimiter{
    private final Map<String, Integer> requestCount = new ConcurrentHashMap<>();
    private final Map<String, Long> windowStart = new HashMap<>();

    public FixedWindowRateLimiter(RateLimiterConfig config){
        super(config, RateLimiterType.FIXED_WINDOW);
    }

    @Override
    public boolean allowRequest(String userId){
        AtomicBoolean allowed = new AtomicBoolean(false);
        long currentReqWindow = System.currentTimeMillis();
        requestCount.compute(userId, (id, count) -> {
            long lastReqWindow = windowStart.getOrDefault(id, currentReqWindow);
            if(lastReqWindow != currentReqWindow){
                windowStart.put(id, currentReqWindow);
                allowed.set(true);
                return 1;
            }
            if(count == null) count = 0;

            if(count < config.getMaxRequests()){
                allowed.set(true);
                return count+1;
            }
            return count;
        });

        return allowed.get();
    }
}
