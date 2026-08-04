package Interview.NewProblem;

import Interview.NewProblem.Strategy.Strategy;
import lombok.Getter;
import lombok.Setter;

public class RateLimitConfig {
    @Getter
    @Setter
    private long maxRequests;
    @Getter
    @Setter
    private long windowMillis;
    @Getter
    @Setter
    private Strategy strategy;
    @Getter
    @Setter
    private long capacity;

    private RateLimitConfig(){

    }

    static class RateLimitBuilder{
        Strategy strategy = Strategy.FIXED_WINDOW;
        long maxRequests = 100;
        long windowMillis = 10000;
        long capacity = 50;


    }

}
