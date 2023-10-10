public class ValidMoves {
    public static boolean hasHigherPrecedence(String op1, String op2) {
        if(op1 == null || op2 == null){ //the stack is empty
            return false;
        }
        if((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))){
            return true;
        }
        if(op2.matches("[=!|<>&]") && !op1.matches("[=!|<>()&]")){
            return true;
        }
        return false;
    }

    public static String calculator(String n1, String n2, String op1) {
        try {
            double num1 = Double.parseDouble(n1);
            double num2 = Double.parseDouble(n2);

            double result = 0;

            if (op1.equals("*")) {
                result = num2 * num1;
            } else if (op1.equals("/")) {
                if (num1 == 0) {
                    return "Division by zero is not allowed";
                }
                result = num2 / num1;
            } else if (op1.equals("+")) {
                result = num2 + num1;
            } else if (op1.equals("-")) {
                result = num2 - num1;
            } else if (op1.equals("<")){
                if(num2 < num1){
                    result = 1;
                } else {
                    result = 0;
                }
            } else if (op1.equals(">")) {
                if(num2 > num1){
                    result = 1;
                } else {
                    result = 0;
                }
            } else if(op1.equals("!")) {
                if(num2!=num1){
                    result = 1;
                } else {
                    result = 0;
                }
            } else if(op1.equals("=")) {
                if(num2 == num1){
                    result = 1;
                } else {
                    result = 0;
                }
            } else if(op1.equals("&")){
                if(num2 == num1){
                    result = 1;
                } else {
                    result = 0;
                }
            } else if (op1.equals("|")) {
                if(num2 == num1) {
                    result = 0;
                }else{
                    result = 1;
                }
            } else {
                return "Invalid operator";
            }
            return Double.toString(result); // Double result
        } catch (NumberFormatException e) {
            return "Invalid input"; 
        }
    }

    public static String calculator(String n1, String op1) {
        double result = 0;
        double num1 = Double.parseDouble(n1);
        if(op1.equals("!")){
            if(num1 == 1){
                result = 0;
            } else {
                result = 1;
            }
        }
        return Double.toString(result);

    }
}
