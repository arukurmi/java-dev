package Interview.NewProblem.Factory;

import Interview.NewProblem.RateLimitConfig;
import Interview.NewProblem.Strategy.FixedWindowStrategy;
import Interview.NewProblem.Strategy.LeakyBucketStrategy;
import Interview.NewProblem.Strategy.RateLimitStrategy;
import Interview.NewProblem.Strategy.TokenBucketStrategy;

public class RateLimiterFactory {
    public static RateLimitStrategy create(RateLimitConfig rlConfig){
        return switch (rlConfig.getStrategy()) {
            case FIXED_WINDOW -> new FixedWindowStrategy(rlConfig);
            case TOKEN_BUCKET -> new TokenBucketStrategy(rlConfig);
            case LEAKY_BUCKET -> new LeakyBucketStrategy(rlConfig);
        };
    }
}
