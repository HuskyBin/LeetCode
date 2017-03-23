/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
*/
public class Twitter {

    /** Initialize your data structure here. */
    public int time = 1;
    
    class Tweet {
        public int t;
        public int id;
        
        public Tweet(int id) {
            this.t = time;
            this.id = id;
        }
    }
    public Map<Integer, List<Tweet>> tweetMap = new HashMap<>();
    public Map<Integer, Set<Integer>> followMap = new HashMap<>();
    public Twitter() {
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId);
        time++;
        List<Tweet> curTweets = tweetMap.getOrDefault(userId, new ArrayList<Tweet>());
        curTweets.add(newTweet);
        tweetMap.put(userId, curTweets);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Queue<Tweet> heap = new PriorityQueue<>(10, new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {
                return t2.t - t1.t;
            }
        });
        Set<Integer> followerList = followMap.getOrDefault(userId, new HashSet<Integer>());
        for (int id : followerList) {
            for (Tweet t : tweetMap.getOrDefault(id, new ArrayList<Tweet>())) {
                heap.add(t);
            }
        }
        for (Tweet t : tweetMap.getOrDefault(userId, new ArrayList<Tweet>())) {
            heap.add(t);
        }
        int count = 0;
        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty() && count < 10) {
            result.add(heap.poll().id);
            count++;
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followList = followMap.getOrDefault(followerId, new HashSet<Integer>());
        followList.add(followeeId);
        followMap.put(followerId, followList);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return ;
        Set<Integer> followList = followMap.getOrDefault(followerId, new HashSet<Integer>());
        followList.remove(followeeId);
        followMap.put(followerId, followList);
    }
}
