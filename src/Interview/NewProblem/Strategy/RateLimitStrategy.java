package Interview.NewProblem.Strategy;

public interface RateLimitStrategy {
    boolean allowRequests(String key);
}
