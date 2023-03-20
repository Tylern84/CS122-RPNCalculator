import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    //declaring an array of doubles
    double[] stack;
    //creating an index variable to input doubles in the correct slot
    int index;



    /**
     * _Part 1: Implement this constructor_
     *
     * Create a new Calculator with d slots in the stack
     * @param d - number of spaces in the stack
     */
    public Calculator(int d) {
        //initializing a new array and setting index to 0 (beginning of the array)
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

        //throws exception if the stack has 10 or more values
        if(index >= 10){
            throw new IllegalStateException("To many values");
        }
        //will push the value onto the stack at the current index
        stack[index] = d;
        //moves the index 1 to the right for the next push
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
        //throws exception if the stack is empty
        if(index <= 0){
            throw new IllegalStateException("List is Empty");
        }
        //setting topValue to the index that we are popping off
        double topVaule = stack[index-1];
        
        //decrementing index by 1 since we popped something off
        index--;

        // TODO: implement this
        //returning topValue (value that we are popping off)
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
        //setting variables to hold values we pop off
        //due to popping thing off multiple times
        double a;
        double b;
        //creating a scanner to read our String s
        Scanner mathInput = new Scanner(s);

        //loops through the string until it reaches the end
        while(mathInput.hasNext()){
            //when it reads in a double in the string it will puch it on to the stack
            if(mathInput.hasNextDouble()){
                push(mathInput.nextDouble());
            }

            //if not a double it should be an operator
            //Using cases to determine what operator and do the correct
            //math accordingly
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
        //by the end there should be one value in the stack which is the answer
        //popping off the last number in the stack
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
