/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class SummaryRanges {

    private TreeMap<Integer, Interval> map;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (map.containsKey(val)) {
            return;
        }
        Integer low = map.lowerKey(val);
        Integer high = map.higherKey(val);
        if (low != null && map.get(low).end + 1 == val && high != null && map.get(high).start == val + 1) {
            map.get(low).end = map.get(high).end;
            map.remove(high);
        }
        else if (low != null && map.get(low).end + 1 >= val) {
            map.get(low).end = Math.max(map.get(low).end, val);
        }
        else if (high != null && map.get(high).start - 1 == val) {
            map.put(val, new Interval(val, map.get(high).end));
            map.remove(high);
        }
        else {
            map.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<>(map.values());
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */