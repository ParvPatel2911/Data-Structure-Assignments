import java.util.Scanner;
// 3137681Parv Patel
// Driver class for assignment part 2

public class Assign1PartB_Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedStack<CalculatorState> undoStack = new LinkedStack<>();
        LinkedStack<CalculatorState> redoStack = new LinkedStack<>();

        System.out.println("Simple Calculator: type z to undo, y to redo, q to quit");
        System.out.print("Enter the first number: ");

        CalculatorState currentState = new CalculatorState(scanner.nextDouble());
        undoStack.push(currentState);

        
       

        while (true) {
            System.out.print("Enter the next operation on " + currentState.getResult() + ": ");
            
           
         

          
            String input = scanner.next();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("z")) {
                // Handle undo
                if (!undoStack.isEmpty()) {
                    redoStack.push(currentState);
                    currentState = undoStack.pop();
                    System.out.println("UNDO: " + currentState.getResult());
                } else {
                    System.out.println("Nothing to undo.");
                }
            } else if (input.equalsIgnoreCase("y")) {
                // Handle redo
                if (!redoStack.isEmpty()) {
                    undoStack.push(currentState);
                    currentState = redoStack.pop();
                    System.out.println("REDO: " + currentState.getResult());
                } else {
                    System.out.println("Nothing to redo.");
                }
            } else {
                // Handle arithmetic operation
                double secondOperand = scanner.nextDouble();
                char operator = input.charAt(0);

                // Update currentState based on the operation
                currentState = performOperation(currentState, operator, secondOperand);

                // Push the current state to undoStack
                undoStack.push(currentState);

                System.out.println("= " + currentState.getResult());
            }
           
        }

        scanner.close();
    }

    private static CalculatorState performOperation(CalculatorState currentState, char operator, double secondOperand) {
        double currentResult = currentState.getResult();
    double newResult;
    // method for arithmetic operations

    switch (operator) {
        case '+':
            newResult = currentResult + secondOperand;
            break;
        case '-':
            newResult = currentResult - secondOperand;
            break;
        case '*':
        case 'x':
            newResult = currentResult * secondOperand;
            break;
        case '/':
            if (secondOperand != 0) {
                newResult = currentResult / secondOperand;
            } else {
                System.out.println("Error: Cannot divide by zero.");

                return currentState;
            }
            break;
        default:
            System.out.println("Error: Invalid operator");
            return currentState;
    }

        return new CalculatorState(newResult);  // Replace with the actual updated state
    }
}
