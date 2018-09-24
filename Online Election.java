/*
In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.

 

Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation: 
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.
*/
class TopVotedCandidate {
    private Map<Integer, Integer> timeLeader = new HashMap<>();
    private int[] time;
    public TopVotedCandidate(int[] persons, int[] times) {
        
        time = times;
        int leader = -1;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < persons.length; i++) {
            countMap.put(persons[i], countMap.getOrDefault(persons[i], 0) + 1);
            if (i == 0 || countMap.get(persons[i]) >= countMap.get(leader)) {
                leader = persons[i];
            }
            timeLeader.put(times[i], leader);
        }
    }
    
    public int q(int t) {
        int i = Arrays.binarySearch(time, t);
        return i < 0 ? timeLeader.get(time[-i - 2]) : timeLeader.get(time[i]);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
