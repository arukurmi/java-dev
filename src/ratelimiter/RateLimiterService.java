package ratelimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    public RateLimiterService(){
        rateLimiters.put(
                UserTier.FREE,
                RateLimiterFactory.createRateLimiter(
                        RateLimiterType.TOKEN_BUCKET,
                        new RateLimiterConfig(10, 60)
                )
        );
        rateLimiters.put(
                UserTier.PREMIUM,
                RateLimiterFactory.createRateLimiter(
                        RateLimiterType.FIXED_WINDOW,
                        new RateLimiterConfig(10, 60)
                )
        );
    }

    public boolean allowRequest(User user){
        RateLimiter limiter = rateLimiters.get(user.getUserTier());
        if(limiter == null) {
            throw new IllegalArgumentException("No limiter found for tier");
        }
        return limiter.allowRequest(user.getUserId());
    }

}
