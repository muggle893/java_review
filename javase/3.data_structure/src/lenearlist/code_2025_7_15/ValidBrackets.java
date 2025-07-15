package lenearlist.code_2025_7_15;

import java.util.LinkedList;

public class ValidBrackets {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topC = stack.peek();
                if ((topC == '(' && c == ')')
                        || (topC == '{' && c == '}')
                        || (topC == '[' && c == ']')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
