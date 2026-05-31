package ratelimiter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RateLimiter {
    protected final RateLimitConfig config;
    protected final RateLimiterType type;

    public abstract boolean allowRequest(String userId);
}
