/*
 * @Name: My Mathematics Interpreter Implementation In Java
 * @Author: Max Base
 * @Date: 2022-10-31
 * @Repository: https://github.com/BaseMax/StackMathematicsInterpreter
 */

import java.util.Stack;

class StackMathematicsInterpreter {
    private String input;
    private Stack<Character> stack;
    private Stack<Integer> tokens;

    public Interpreter(String str) {
        this.input = str;
        this.stack = new Stack<Character>();
        this.tokens = new Stack<Integer>();

        this.parse();
    }

    private void parse() {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                continue;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    int b = tokens.pop();
                    int a = tokens.pop();
                    char op = stack.pop();
                    int result = calculate(a, b, op);
                    tokens.push(result);
                }
                stack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.isEmpty() && hasPrecedence(c, stack.peek())) {
                    int b = tokens.pop();
                    int a = tokens.pop();
                    char op = stack.pop();
                    int result = calculate(a, b, op);
                    tokens.push(result);
                }
                stack.push(c);
            } else {
                // number
                int number = 0;
                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    number = number * 10 + (input.charAt(i) - '0');
                    i++;
                }
                i--;
                tokens.push(number);
            }
        }
        
        while (!stack.isEmpty()) {
            int b = tokens.pop();
            int a = tokens.pop();
            char op = stack.pop();
            int result = calculate(a, b, op);
            tokens.push(result);
        }
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private int calculate(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

    public int getResult() {
        return tokens.pop();
    }
}

public class HelloWorld {
    public static void main(String []args){
        System.out.println("Hello, World!");

        Interpreter interpreter = new StackMathematicsInterpreter("1+2*3");
        System.out.println(interpreter.getResult());

        interpreter = new Interpreter("1+2*3+4");
        System.out.println(interpreter.getResult());

        interpreter = new Interpreter("1+2*3+4*5");
        System.out.println(interpreter.getResult());
    }
}
