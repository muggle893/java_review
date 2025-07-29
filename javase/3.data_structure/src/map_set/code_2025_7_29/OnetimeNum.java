package map_set.code_2025_7_29;

import java.util.HashSet;
import java.util.Iterator;

public class OnetimeNum {
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }
        Iterator<Integer> iterator = set.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return -1;
    }
}
