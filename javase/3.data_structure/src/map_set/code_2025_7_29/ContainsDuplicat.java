package map_set.code_2025_7_29;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicat {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int a : nums) {
            if (set.contains(a)) {
                return true;
            } else {
                set.add(a);
            }
        }
        return false;
    }
}
