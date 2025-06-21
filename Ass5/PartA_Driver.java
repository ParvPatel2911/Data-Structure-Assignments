import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// 3137681 Parv Patel
public class PartA_Driver {
    public static void main(String[] args) {
        
        LinkedBinaryTree<String> decisionTree = buildDecisionTree();

        //  to check Display the tree (uncomment next two lines)
       // System.out.println("Tree");
        //System.out.println(decisionTree);

        // Tree Interaction
        System.out.println("Tree Interaction");

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Start traversal from the root
        LinkedBinaryTree.Node<String> currentNode = (LinkedBinaryTree.Node<String>) decisionTree.root();

        // Traverse the tree until a decision is reached
        while (currentNode != null) {
            System.out.println(currentNode.getElement());

            // If the current node is a leaf node, break the loop
            if (decisionTree.isExternal(currentNode))
                break;

            // Prompt the user for input
            System.out.print("(yes/no) ");
            String input = scanner.nextLine().toLowerCase();

            // Update current node based on user input
            if (input.equals("yes")) {
                currentNode = (LinkedBinaryTree.Node<String>) decisionTree.left(currentNode);
            } else if (input.equals("no")) {
                currentNode = (LinkedBinaryTree.Node<String>) decisionTree.right(currentNode);
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        // Display the final decision
        if (currentNode != null && decisionTree.isExternal(currentNode)) {
            System.out.println("Final Decision:");
            System.out.println(currentNode.getElement());
        }

        // Close the scanner
        scanner.close();

        // Build the arithmetic expression tree
        LinkedBinaryTree<String> expressionTree = buildArithmeticExpressionTree();

        System.out.println("Tree");
       System.out.println(expressionTree);
        // Display the inorder representation of the tree
        System.out.println("Inorder Representation of the Arithmetic Expression Tree:");
        List<String> inorderList = new ArrayList<>();
        inorderTraversal(expressionTree.root(), inorderList);
        System.out.println(inorderList);
        System.out.println();

        // Display the postorder representation of the tree
        System.out.println("Postorder Representation of the Arithmetic Expression Tree:");
        List<String> postorderList = new ArrayList<>();
        postorderTraversal(expressionTree.root(), postorderList);
       System.out.println(postorderList);
        System.out.println();

        // Evaluate the expression tree
        int result = evaluateExpressionTree(expressionTree);
        System.out.println("Result of the Arithmetic Expression: " + result);
    }

    //  method to build the decision tree
    private static LinkedBinaryTree<String> buildDecisionTree() {
        // Construct the decision tree nodes
        LinkedBinaryTree<String> decisionTree = new LinkedBinaryTree<>();
        LinkedBinaryTree.Node<String> root = (LinkedBinaryTree.Node<String>) decisionTree.addRoot("Are you solo traveller? (yes/no)");
        LinkedBinaryTree.Node<String> a = (LinkedBinaryTree.Node<String>) decisionTree.addLeft(root, "Solo travel can be a great way to explore new destinations and have unique experiences.");
        LinkedBinaryTree.Node<String> b = (LinkedBinaryTree.Node<String>) decisionTree.addRight(root, "Do you prefer beach vacations?(yes/no)");
        LinkedBinaryTree.Node<String> c = (LinkedBinaryTree.Node<String>) decisionTree.addLeft(b, "Do you prefer warm weather?(warm/cool)");
        LinkedBinaryTree.Node<String> d = (LinkedBinaryTree.Node<String>) decisionTree.addRight(b, "Do you prefer mountain vacations?(yes/no)");
        LinkedBinaryTree.Node<String> e = (LinkedBinaryTree.Node<String>) decisionTree.addLeft(c, "you can visit thailand this summer");
        LinkedBinaryTree.Node<String> f = (LinkedBinaryTree.Node<String>) decisionTree.addRight(c, "you can visit Canada ");
        LinkedBinaryTree.Node<String> g = (LinkedBinaryTree.Node<String>) decisionTree.addLeft(d, "Do you prefer snow mountain?(yes/no)");
        LinkedBinaryTree.Node<String> h = (LinkedBinaryTree.Node<String>) decisionTree.addRight(d, "plan your next trip");
        LinkedBinaryTree.Node<String> i = (LinkedBinaryTree.Node<String>) decisionTree.addLeft(g, "Enjoy winter activities with family in alps .");
        LinkedBinaryTree.Node<String> j = (LinkedBinaryTree.Node<String>) decisionTree.addRight(g, "you can explore summer trails with friends in Banff.");
        

        return decisionTree;
    }

    // Helper method to build the arithmetic expression tree
    private static LinkedBinaryTree<String> buildArithmeticExpressionTree() {
        // Construct the arithmetic expression tree nodes
        LinkedBinaryTree<String> expressionTree = new LinkedBinaryTree<>();
        LinkedBinaryTree.Node<String> root = (LinkedBinaryTree.Node<String>) expressionTree.addRoot("*");
        LinkedBinaryTree.Node<String> a = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(root, "+");
        LinkedBinaryTree.Node<String> b = (LinkedBinaryTree.Node<String>) expressionTree.addRight(root, "10");
        LinkedBinaryTree.Node<String> c = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(a, "+");
        LinkedBinaryTree.Node<String> d = (LinkedBinaryTree.Node<String>) expressionTree.addRight(a, "*");
        LinkedBinaryTree.Node<String> e = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(c, "-");
        LinkedBinaryTree.Node<String> f = (LinkedBinaryTree.Node<String>) expressionTree.addRight(c, "*");
        LinkedBinaryTree.Node<String> g = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(d, "9");
        LinkedBinaryTree.Node<String> h = (LinkedBinaryTree.Node<String>) expressionTree.addRight(d, "-");
        LinkedBinaryTree.Node<String> i = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(e, "5");
        LinkedBinaryTree.Node<String> j = (LinkedBinaryTree.Node<String>) expressionTree.addRight(e, "2");
        LinkedBinaryTree.Node<String> k = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(f, "4");
        LinkedBinaryTree.Node<String> l = (LinkedBinaryTree.Node<String>) expressionTree.addRight(f, "-");
        LinkedBinaryTree.Node<String> m = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(h, "*");
        LinkedBinaryTree.Node<String> n = (LinkedBinaryTree.Node<String>) expressionTree.addRight(h, "+");
        LinkedBinaryTree.Node<String> o = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(l, "8");
        LinkedBinaryTree.Node<String> p = (LinkedBinaryTree.Node<String>) expressionTree.addRight(l, "+");
        LinkedBinaryTree.Node<String> q = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(m, "3");
        LinkedBinaryTree.Node<String> r = (LinkedBinaryTree.Node<String>) expressionTree.addRight(m, "6");
        LinkedBinaryTree.Node<String> s = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(n, "7");
        LinkedBinaryTree.Node<String> t = (LinkedBinaryTree.Node<String>) expressionTree.addRight(n, "2");
        LinkedBinaryTree.Node<String> u = (LinkedBinaryTree.Node<String>) expressionTree.addLeft(p, "3");
        LinkedBinaryTree.Node<String> v = (LinkedBinaryTree.Node<String>) expressionTree.addRight(p, "1");
        // Return the constructed expression tree
        return expressionTree;
    }
    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    //  inorder traversal
    private static void inorderTraversal(Position<String> node, List<String> list) {
        if (node != null) {
            LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
            LinkedBinaryTree.Node<String> treeNode = (LinkedBinaryTree.Node<String>) node;
            inorderTraversal(tree.left(treeNode), list);
            list.add(treeNode.getElement());
            inorderTraversal(tree.right(treeNode), list);
        }
    }

    //  postorder traversal
    private static void postorderTraversal(Position<String> node, List<String> list) {
        if (node != null) {
            LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
            LinkedBinaryTree.Node<String> treeNode = (LinkedBinaryTree.Node<String>) node;
            postorderTraversal(tree.left(treeNode), list);
            postorderTraversal(tree.right(treeNode), list);
            list.add(treeNode.getElement());
        }
    }

    // Method to evaluate the expression tree using a stack
    private static int evaluateExpressionTree(LinkedBinaryTree<String> expressionTree) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        postorderTraversal(expressionTree.root(), expressionTree, stack);
        return stack.pop();
    }

    // postorder traversal
    private static void postorderTraversal(Position<String> node, LinkedBinaryTree<String> expressionTree, LinkedStack<Integer> stack) {
        if (node != null) {
            postorderTraversal(expressionTree.left(node), expressionTree, stack);
            postorderTraversal(expressionTree.right(node), expressionTree, stack);
            String element = node.getElement();
            if (element != null) { // Check if the element is null
                if (isNumeric(element)) {
                    stack.push(Integer.parseInt(element));
                } else {
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    switch (element) {
                        case "+":
                            stack.push(operand1 + operand2);
                            break;
                        case "-":
                            stack.push(operand1 - operand2);
                            break;
                        case "*":
                        case "x":
                            stack.push(operand1 * operand2);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid operator: " + element);
                    }
                }
            }
        }
    }

    
}

