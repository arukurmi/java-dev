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

    public static RateLimitBuilder builder(){
        return new RateLimitBuilder();
    }

    public static class RateLimitBuilder{
        private Strategy strategy = Strategy.FIXED_WINDOW;
        private long maxRequests = 100;
        private long windowMillis = 10000;
        private long capacity = 50;

        public RateLimitBuilder strategy(Strategy strategy){
            this.strategy = strategy;
            return this;
        }

        public RateLimitBuilder maxRequests(long maxRequests){
            this.maxRequests = maxRequests;
            return this;
        }

        public RateLimitBuilder windowMillis(long windowMillis){
            this.windowMillis = windowMillis;
            return this;
        }

        public RateLimitBuilder capacity(long capacity){
            this.capacity = capacity;
            return this;
        }

        public RateLimitConfig build(){
            RateLimitConfig config = new RateLimitConfig();
            config.maxRequests = this.maxRequests;
            config.windowMillis = this.windowMillis;
            config.strategy = this.strategy;
            config.capacity = this.capacity;
            return config;
        }
    }

}
