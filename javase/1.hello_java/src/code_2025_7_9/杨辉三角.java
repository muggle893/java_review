package code_2025_7_9;

import java.util.ArrayList;
import java.util.List;

public class 杨辉三角 {
    public List<List<Integer>> generate(int numRows) {
        // i行j列的元素由i-1行j-1列和i-1行j列生成
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> tmpList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    tmpList.add(1);
                } else {
                    int v1 = res.get(i - 1).get(j - 1);
                    int v2 = res.get(i - 1).get(j);
                    tmpList.add(v1 + v2);
                }
            }
            res.add(tmpList);
        }
        return res;
    }
}
