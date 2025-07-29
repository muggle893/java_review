package map_set.code_2025_7_29;

import java.util.HashSet;

public class DiamondAndStone {
    public int numJewelsInStones(String jewels, String stones) {
        int cnt = 0;
        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        for (int i = 0; i < stones.length(); i++) {
            if (set.contains(stones.charAt(i))) {
                cnt++;
            }
        }
        return cnt;
    }
}
