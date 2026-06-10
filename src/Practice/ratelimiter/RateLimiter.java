package Practice.ratelimiter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RateLimiter {
    protected final RateLimiterConfig config;
    protected final RateLimiterType type;

    public abstract boolean allowRequest(String userId);
}
