package Interview.NewProblem;


/*
*
* Rate Limiter:
*
* Functional Requirements
* - allow(key) -> true/false
* - key -> userId
* - limit requests per key per window
* - Pluggable Rate limiting strategies
* - Configurable (limit, window size)
* - Thread-safe under concurrency
*
* Strategies to support:
* - Fixed Window Counter
* - Token Bucket
* - Leaky Bucket
* - Sliding Window Log
* - Sliding Window Counter
*
* - Non-functional Requirements:
* - Latency (<10ms)
* - In-memory
*
* enum Strategy
* class RateLimitConfig
*
* interface RateLimitStrategy
*
* class FixedWindowStrategy implements RateLimit
*
*
*
*
*
*
* */

public class Demo {

}
