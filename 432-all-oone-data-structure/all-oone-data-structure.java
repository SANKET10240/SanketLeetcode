class AllOne {
    Map<String, CustomString> freq;
    PriorityQueue<CustomString> maxQ;
    PriorityQueue<CustomString> minQ;
    public AllOne() {
        freq = new HashMap<>();
        minQ = new PriorityQueue<>((a, b) -> {
            if (a.val == b.val) a.body.compareTo(b.body);

            return a.val - b.val;
        });
        maxQ = new PriorityQueue<>((a, b) -> {
            if (a.val == b.val) b.body.compareTo(a.body);

            return b.val - a.val;
        });
    }
    
    public void inc(String key) {
        if (!freq.containsKey(key)) {
            freq.put(key, new CustomString(key, 1));
        } else {
            freq.get(key).val++;
            maxQ.remove(freq.get(key));
            minQ.remove(freq.get(key));
        }
        maxQ.add(freq.get(key));
        minQ.add(freq.get(key));
    }
    
    public void dec(String key) {
        freq.get(key).val--;
        maxQ.remove(freq.get(key));
        minQ.remove(freq.get(key));
        
        if (freq.get(key).val == 0) {
            freq.remove(key);
        } else {
            maxQ.add(freq.get(key));
            minQ.add(freq.get(key));
        }

    }
    
    public String getMaxKey() {
        return maxQ.peek() == null ? "" : maxQ.peek().body;
    }
    
    public String getMinKey() {
        return minQ.peek() == null ? "" : minQ.peek().body;
    }
}

class CustomString {
    String body;
    int val;

    public CustomString(String body, int val) {
        this.body = body;
        this.val = val;
    }
}