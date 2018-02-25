package group144.kidyankin;

public class Calculator {
    public int calculate(String string) {
        String[] tokens = string.split(" ");
        Stack<Integer> stack = new LinkedStack<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                Integer secondOperand = stack.pop();
                Integer firstOperand = stack.pop();
                stack.push(operate(firstOperand, secondOperand, token));
            }
        }
        return stack.pop();
    }

    private Integer operate(Integer firstOperand, Integer secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                return firstOperand / secondOperand;
        }
        return -1;
    }

    private boolean isNumber(String string) {
        for (int i = 1; i < string.length(); i++) {
            if(!Character.isDigit(string.charAt(i)))
                return false;
        }
        return (string.charAt(0) == '-' && string.length() > 1) || Character.isDigit(string.charAt(0));
    }

}
