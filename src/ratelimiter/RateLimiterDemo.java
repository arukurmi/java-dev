package ratelimiter;

public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterService rateLimiterService = new RateLimiterService();
        User userf = new User("userid1", UserTier.FREE);
        User userp = new User("userid2", UserTier.PREMIUM);

        System.out.println("+++ Free User Requests +++");
        for(int i=0; i<=15; i++){
            boolean allowed = rateLimiterService.allowRequest(userf);
            System.out.println("Request" + i + " for Free User: " + (allowed ? "ALLOWED" : "NOT ALLOWED" ));
            Thread.sleep(100);
        }
        System.out.println("+++ Premium User Requests +++");
        for(int i=0; i<=120; i++){
            boolean allowed = rateLimiterService.allowRequest(userp);
            System.out.println("Request" + i + " for Premium User: " + (allowed ? "ALLOWED" : "NOT ALLOWED" ));
            Thread.sleep(100);
        }

    }
}
