//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
//public class mainCopy {
//    public static String postFixCalc(Queue<String> q2){
//        Stack<String> s2 = new Stack<>();
//        String results = "";
//        while(!q2.isEmpty()){
//            String token = q2.peek();
//            System.out.println("curr token: " + token);
//            try {
//                if (token.matches("-?\\d+")) { // Check if the token is an integer
//                    Integer.parseInt(token);
//                    System.out.println("good (Integer)");
//                    System.out.println("I just enqueued: " + token);
//                    s2.push(token);                  // If it is a number push it on the stack
//                    q2.dequeue();                    // I also remove it from the queue
//                } else if (token.matches("-?\\d+\\.\\d+")) { // Check if the token is a double
//                    Double.parseDouble(token);
//                    System.out.println("good (Double)");
//                    s2.push(token);                  // If it is a number push it on the stack
//                    q2.dequeue();                    // I also remove it from the queue
//                } else if (token.matches("[+-/*<>=&!|]")) { // Check if the token is an operator
//                    System.out.println("good (Operator)");
//                    try {
//                        if (!s2.isEmpty()) { // Check if the stack is not empty before calling peek()
//                            String n1 = s2.peek();
//                            s2.pop();
//                            if(s2.isEmpty()){
//                                s2.push(ValidMoves.calculator(n1, token));
//                            } else {
//                                String n2 = s2.peek();
//                                s2.pop();
//                                s2.push(ValidMoves.calculator(n1, n2, token)); //make the calculation and push it
//                            }
//                        }
//                    } catch(EmptyStackException E) {
//                        System.out.println("Empty stack");
//                    }
//                    q2.dequeue();
//                } else {
//                    System.out.println("bad (Invalid Token)");
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("bad (Number Format Exception)");
//            }
//        }
//        results = results + s2.peek() + "0\n";
//        return results;
//    }
//    public static void main(String[] args) throws IOException {
//        File input = new File("C:/Users/osa1p/OneDrive/Desktop/CSC172/Project01/src/infix_expr_short.txt.txt");
//        File outputFile = new File("OUTPUT.txt");
//        FileWriter fileWriter = new FileWriter(outputFile);
//
//        Scanner scnr = new Scanner(input);
//
//        Stack<String> s1 = new Stack<>();
//        Queue<String> q1 = new Queue<>();
//
//        while (scnr.hasNextLine()) {
//            String expression = scnr.nextLine();
//            System.out.println("Expression: " + expression);
//            String[] currExpr = expression.split(" ");
//            for(String token : currExpr){
//                System.out.print(token + " ");
//                try {
//                    if (token.matches("-?\\d+")) { // Check if the token is an integer
//                        Integer.parseInt(token);
//                        System.out.println("good (Integer)");
//                        System.out.println("I just enqueued: " + token);
//                        q1.enqueue(token);               // If it is a number enqueue it
//                    } else if (token.matches("-?\\d+\\.\\d+")) { // Check if the token is a double
//                        Double.parseDouble(token);
//                        System.out.println("good (Double)");
//                        q1.enqueue(token);              //If it is a number enqueue it
//                    } else if (token.matches("[+-/*]")) { // Check if the token is an operator
//                        System.out.println("good (Operator)");
//                        try {
//                            if (!s1.isEmpty()) { // Check if the stack is not empty before calling peek()
//                                /*if the token operator has a higher precedence (PEMDAS) then we want to pop it from the
//                                stack and enqueue the higher precedent operator in the queue
//                                then we add the curr token to the stack*/
//                                while (!s1.isEmpty() && ValidMoves.hasHigherPrecedence(s1.peek(), token)) {
//                                    System.out.println("the head token head has a higher precedence");
//                                    System.out.println("I just enqueued: " + s1.peek());
//                                    q1.enqueue(s1.peek());
//                                    s1.pop();
//                                }
//                            }
//                            s1.push(token);
//                            System.out.println("I just pushed: " + s1.peek());
//                        } catch (EmptyStackException E) {
//                            System.out.println("Empty stack");
//                            s1.push(token);
//                            System.out.println("I just pushed: " + token);
//                        }
//                    } else if(token.matches("[=!|<>&]")){
//                        System.out.println("good (=,!,<>,&,|)");
//                        try{
//                            if(!s1.isEmpty()){
//                                while(!s1.isEmpty() && ValidMoves.hasHigherPrecedence(s1.peek(),token)){
//                                    System.out.println("the head token head has a higher precedence");
//                                    System.out.println("I just enqueued: " + s1.peek());
//                                    q1.enqueue(s1.peek());
//                                    s1.pop();
//                                }
//                            }
//                            s1.push(token);
//                            System.out.println("I just pushed: " + s1.peek());
//                        } catch(EmptyStackException E){
//                            System.out.println("Empty Stack");
//                            s1.push(token);
//                            System.out.println("I just pushed: " + token);
//                        }
//                    } else if (token.matches("[()<>]")) { // Check if the token is a parenthesis or angle bracket
//                        System.out.println("good (Parenthesis/Angle Bracket)");
//                        if (token.equals("(")) {
//                            System.out.println("I just pushed: " + "(");
//                            s1.push(token); // Push opening parenthesis onto the stack
//                        } else if (token.equals(")")) {
//                            System.out.println("I'm at a: " + token);
//                            while(!s1.isEmpty() && !s1.peek().equals("(")){ //keep enqueuing until you see the matching "("
//                                String currToken = s1.peek();
//                                System.out.println("This is the head of stack: " + currToken);
//                                q1.enqueue(currToken);
//                                System.out.println("I just enqueued this: " + currToken);
//                                s1.pop();
//                            }
//                            System.out.println("The curr token should be ( : " + s1.peek());
//                            s1.pop();
//                        }
//                    } else {
//                        System.out.println("bad (Invalid Token)");
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("bad (Number Format Exception)");
//                }
//            }
//            //pop any remaining operators and enqueue it
//            while(!s1.isEmpty()){
//                System.out.println("I'm enqueueing the remaining operators in the stack");
//                q1.enqueue(s1.peek());
//                s1.pop();
//            }
//            System.out.println("I'm running the postfix method");
//            fileWriter.write(postFixCalc(q1));
//        }
//        fileWriter.close();
//    }
//}