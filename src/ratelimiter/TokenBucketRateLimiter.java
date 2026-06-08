package ratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiter{
    private final Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTime = new HashMap<>();

    public TokenBucketRateLimiter(RateLimiterConfig config) {
        super(config, RateLimiterType.TOKEN_BUCKET);
    }

    @Override
    public boolean allowRequest(String userId){
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();

        tokens.compute(userId, (id, availableTokens) -> {
            int currentToken = refillTokens(userId, now);
            if(currentToken > 0){
                allowed.set(true);
                return currentToken - 1;
            } else return currentToken;
        });
        return allowed.get();
    }

    private int refillTokens(String userId, long now){
        double refillRate = (double) config.getWindowInSeconds() / config.getMaxRequests();
        long lastRefill = lastRefillTime.getOrDefault(userId, now);
        long elapsedSeconds = (now - lastRefill) / 1000;
        int refillTokens = (int) (elapsedSeconds / refillRate);

        int currentTokens = tokens.getOrDefault(userId, config.getMaxRequests());
        currentTokens = Math.min(config.getMaxRequests(), currentTokens + refillTokens);
        if(refillTokens > 0) lastRefillTime.put(userId, now);

        return currentTokens;
    }
}
