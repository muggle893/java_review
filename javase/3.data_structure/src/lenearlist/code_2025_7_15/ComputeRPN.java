package lenearlist.code_2025_7_15;

import java.util.Stack;

public class ComputeRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                Integer v2 = st.pop();
                Integer v1 = st.pop();
                switch (s){
                    case "+":
                        st.push(v1 + v2);
                        break;
                    case "-":
                        st.push(v1 - v2);
                        break;
                    case "*":
                        st.push(v1 * v2);
                        break;
                    case "/":
                        st.push(v1 / v2);
                        break;
                }
            } else {
                st.push(Integer.valueOf(s));
            }
        }
        return st.peek();
    }
}
