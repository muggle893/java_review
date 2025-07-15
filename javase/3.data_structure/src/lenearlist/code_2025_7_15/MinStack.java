package lenearlist.code_2025_7_15;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> st1;
    private Stack<Integer> st2;
    public MinStack() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    public void push(int val) {
        st1.push(val);
        if (st2.empty()) {
            st2.push(val);
        } else {
            if (val <= st2.peek()) {
                st2.push(val);
            }
        }
    }

    public void pop() {
        int val = st1.pop();
        if (val == st2.peek()) {
            st2.pop();
        }
    }

    public int top() {
        return st1.peek();
    }

    public int getMin() {
        return st2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
