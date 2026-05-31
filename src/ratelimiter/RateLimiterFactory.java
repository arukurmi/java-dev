package ratelimiter;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterType algo, RateLimiterConfig config){
        return switch(algo){
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config);
            case SLIDING_WINDOW -> new SlidingWindowRateLimiter(config);
            case FIXED_WINDOW -> new FixedWindowRateLimiter(config);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algo);
        };
    }
}
