package Interview.NewProblem.Strategy;

import Interview.NewProblem.RateLimitConfig;

public class FixedWindowStrategy implements RateLimitStrategy{
    private long maxRequests;
    private long windowMillis;
    FixedWindowStrategy(RateLimitConfig rlconfig){
        this.maxRequests = rlconfig.getMaxRequests();
        this.windowMillis = rlconfig.getWindowMillis();
    }
    public boolean allowRequests(String key){
        // Logic for fixed window
    }

}
