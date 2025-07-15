package lenearlist.code_2025_7_15;

import java.util.Stack;

public class CheckSequence {
    public boolean IsPopOrder (int[] pushV, int[] popV) {
        // write code here
        Stack<Integer> stack = new Stack<>();
        int j = 0; // j指向popV数组元素
        for (int i = 0; i <pushV.length; i++) {
            stack.push(pushV[i]);
            if (pushV[i] == popV[j]) {
                while (!stack.isEmpty() && stack.peek() == popV[j]) {
                    stack.pop();
                    j++;
                }
            }
        }
        return stack.empty();
    }
}
