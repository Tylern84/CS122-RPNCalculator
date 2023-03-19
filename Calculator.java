import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    double[] stack;
    int index;



    /**
     * _Part 1: Implement this constructor_
     *
     * Create a new Calculator with d slots in the stack
     * @param d - number of spaces in the stack
     */
    public Calculator(int d) {
        stack= new double[d];
        index=0;

        // TODO: implement this
    }

    /**
     * _Part 2: Implement this method_
     *
     * Push the specified double onto the stack
     * @param d - the value to push
     * throw an IllegalStateException if the stack has 10 or more values.
     */
    private void push(double d) {
        if(index >= 10){
            throw new IllegalStateException("To many values");
        }
        stack[index] = d;
        index++;
        // TODO: implement this
        return;
    }

    /**
     * _Part 3: Implement this method_
     *
     * Pop the top value off the stack
     * throw an IllegalStateException if the stack is currently empty.
     */
    private double pop() {
        if(index <= 0){
            throw new IllegalStateException("List is Empty");
        }
        double topVaule = stack[index-1];
        index--;

        // TODO: implement this
        return topVaule;
    }

    /**
     * _Part 4: Implement this method_
     *
     * Calculate the value from a String of operations.
     *
     * Required operations:
     *  "+" - adds the top two entries on the stack
     *  "*" - multiplies the top two entries on the stack
     *  "-" - subtracts the top entry in the stack from the 2nd entry in the stack
     *  "/" - divides the 2nd entry in the stack by the top entry in the stack
     *  "^" - raises the 2nd entry in the stack to the power indicated by the top entry in the stack
     *  "lg" - takes the lg (log base 2) of the top entry in the stack
     *
     *  Operations for more practice: Variables
     *   expand the use of the calculator by supporting the use of
     *   three variables 'x', 'y', and 'z' in expressions. Specifically
     *   for each variable, there should be a way to set its value
     *   the tokens 'setx', 'sety', and 'setz' respectively, and a way to
     *   read its value -- the tokens: 'x', 'y', and 'z' respectively.
     *   With these new operators we should be able to evaluate
     *   expressions such as:
     *   "10 4 + setx" (set the 'x' variable to 14)
     *   "42 x /"      (divide 42 by the value stored for 'x' -- currently 14)
     *   "x x -"       (subtract 14 from 14)
     *
     * @param s - the string representing a mathematic expression
     * throw an IllegalArgumentException if a specified operator is unknown.
     */
    public double calculate(String s) {
        // TODO: implement this
        double a;
        double b;
        Scanner mathInput = new Scanner(s);


        while(mathInput.hasNext()){
            if(mathInput.hasNextDouble()){
                push(mathInput.nextDouble());
            }
            else{
                String operator = mathInput.next();

                switch(operator){
                    case "+":
                        a = pop();
                        b = pop();
                        push(a+b);
                        break;
                    case "-":
                        a = pop();
                        b = pop();
                        push(b-a);
                        break;
                    case "*":
                        a = pop();
                        b = pop();
                        push(a*b);
                        break;
                    case "/":
                        a = pop();
                        b = pop();
                        push(b/a);
                        break;
                    case "^":
                        a = pop();
                        b = pop();
                        push(Math.pow(b,a));
                        break;
                    case "lg":
                        a = pop();
                        push(Math.log(a)/Math.log(2));
                        break;
                }
            }
        }
        return pop();
    }

    public static void main(String[] args) {
        Calculator c = new Calculator(5);
        System.out.println(c.calculate("10 4 +") + " should equal 14");
        System.out.println(c.calculate("4 2 /") + " should equal 2");
        System.out.println(c.calculate("10 4 + 3 * 2 /") + " should equal 21");
        System.out.println(c.calculate("16 lg") + " should equal 4");
        System.out.println(c.calculate("16 4 -") + " should equal 12");
        System.out.println(c.calculate("5 16 4 + -") + " should equal -15");
        System.out.println(c.calculate("5 20 -") + " should equal -15");
        System.out.println(c.calculate("5") + " should equal 5");

    }
}
