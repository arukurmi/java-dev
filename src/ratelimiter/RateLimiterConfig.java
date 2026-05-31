package ratelimiter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RateLimiterConfig {
    private final int maxRequests;
    private final int windowInSeconds;
}
