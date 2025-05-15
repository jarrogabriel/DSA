package challenges.LC20_ValidBrackets;

import java.util.Stack;

public class ValidBrackets {


    public static void main(String[] args) {
        String s = "}[{}])";
        System.out.println(isValid(s));
    }

    public static String isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty()) {
                    return "NO";
                }
                char top = stack.pop();
                if ((ch == '}' && top != '{') ||
                        (ch == ']' && top != '[') ||
                        (ch == ')' && top != '(')) {
                    return "NO";
                }
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }
}
