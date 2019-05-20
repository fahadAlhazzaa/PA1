
/**
 * Fahad Alhazzaa
 * 05/19/2019
 * Binary Search Tree program / CSCI 232 PA1
 * This assignment should show how to print the BST with some functions like the traversal order (Inorder, Preorder, Postorder)
 * This assignment should show how th Delete a node or search for a node in the BST
 * This assignment has an input.txt file with values for the tree
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class BinarySearchTree { // setting the name of the code Binary Search tree to represnt BST

    private class Node {
        int data; // have the data to be in intger
        Node left, right; // the direction for the node would be left or right

        Node(int data) { // have the node to be in intger
            this.data = data;
        }
    }
    private Node root; // definding the root to be in the tree
    public void insert(int data) {
        root = insert(root, data); // Insert a node value to the tree
    }
    private Node insert(Node n, int data) { // a method for the node to be insert
        if (n == null) { 
            n = new Node(data); // n will represnt the node
        } else if (n.data > data) { // compare the node to the data
            n.left = insert(n.left, data); // if the node less than the data will go to the left
        } else if (n.data < data) { // compare the node to the data
            n.right = insert(n.right, data); // if the node bigger than the data will go to the right
        }
        return n;
    }
    public void delete(int data) {
        root = delete(root, data); // Delete a node value to the tree
    }
    private Node delete(Node n, int data) { // a method for the node to be insert
        if (n == null) {
            return null; // if the value not found return nothing
        }
        if (n.data > data) { // compare the node to the data
            n.left = delete(n.left, data); // if the node less than the value will delete the left
        } else if (n.data < data) { // compare the node to the data
            n.right = delete(n.right, data); // if the node bigger than the value will delete the right
        } else if (n.left != null && n.right != null) // if the root has two children 
        {
            int minKey = getMinData(n.right); //looking for the minimum value
            n.data = minKey; // set the minumim key to data
            n.right = delete(n.right, n.data); // delete the right child 
        } else {
            n = (n.left != null) ? n.left : n.right; // to evaluate if the left child not null 
        }
        return n;
    }
    private int getMinData(Node p) {
        while (p.left != null) { // when the left child not equal to null 
            p = p.left; // the left child will be the minimum value
        }
        return p.data;
    }
    public boolean find(int data) { 
        return find(root, data); // method the find the value the user will ask for
    }
    private boolean find(Node p, int data) {
        if (p == null) { // the valeu equal to null 
            return false;
        } else if (p.data > data) { // the value is less than the root 
            return find(p.left, data); // it will be to the left 
        } else if (p.data < data) { // the value is greater than the root 
            return find(p.right, data); // it will be to the right
        }
        return true;
    }
    public void print() { 
        int height = height(root); // seting up the hieght to the root 
        for (int level = 1; level <= height; level++) { // calculation when the input equal 1 to print the tree 
            System.out.println(" "); 
            printLevel(root, level); // print each root with the level for it 
        }
    } 
    private int height(Node p) { 
        if (p == null) {  // the valeu equal to null 
            return 0;
        }
        int lheight = height(p.left); // if the left is equal to the hieght print it to the left
        int rheight = height(p.right); // if the right is equal to the hieght print it to the right
        if (lheight > rheight) { // if the left is bigger than the right
            return lheight + 1; // print it the th left
        } else {
            return rheight + 1; // if the right is bigger than the left print it to the right
        }
    }
    private void printLevel(Node p, int level) { // the levels 
        if (p != null) {  // the valeu not equal to null 
            if (level == 1) { // the first level  
                System.out.print(p.data + "\n"); // print it in the first level 
            } else {
                printLevel(p.left, level - 1); // print the left child to the left with one more level
                printLevel(p.right, level - 1 ); // print the right child to the right with one more level
            }
        }
    }
    public void traversalOrder() { // the three order of the Binary Search Tree
        System.out.println("Tree traversal"); 
        System.out.print("  inorder:"); // first traversal of the tree
        inorder(root); // inorder traversal for the tree 
        System.out.println();
        System.out.print("  preorder:"); // second traversal of the tree
        preorder(root); // preorder traversal for the tree 
        System.out.println();
        System.out.print("  postorder:"); // third traversal of the tree
        postorder(root); // postorder traversal for the tree 
        System.out.println();
    }
    private void inorder(Node p) {
        if (p != null) { // the valeu not equal to null
            inorder(p.left); // print it from the left 
            System.out.print(" " + p.data); // taking the data from the left child 
            inorder(p.right); // finishing up the print to the right child 
        }
    }
    private void preorder(Node p) {
        if (p != null) { // the valeu not equal to null
            System.out.print(" " + p.data); // print the data left and right 
            preorder(p.left); // strat with left first
            preorder(p.right); // take from the right after each left 
        }
    }
    private void postorder(Node p) {
        if (p != null) { // the valeu not equal to null
            postorder(p.left); // take from the left 
            postorder(p.right); // then take from the right 
            System.out.print(" " + p.data); // print the order left then right 
        }
    }
    private static Scanner keyboard = new Scanner(System.in); // read the input and read it from the choises 
    private static BinarySearchTree tree;
    // ask the user choise and return user's choice
    static int UserChoise() {
        System.out.println("1- print the tree"); // the first choice to print the tree from the input.txt file 
        System.out.println("2- Tree traversal order"); // the second choice to show the three orders of the tree 
        System.out.println("3- delete a value"); // the third choice to delete a value from the tree 
        System.out.println("4- search for a value"); // the fourth choice to find a value from the tree 
        System.out.print("Enter your choice: ");
        int choice = keyboard.nextInt(); // make sure that the user insert an intger not string 
        return choice;
    }
    static void inputTree(String fileName) throws IOException { 
        tree = new BinarySearchTree(); // the roots of the tree elemnts 
        Scanner infile = new Scanner(new File(fileName)); // scan from the file 
        infile.useDelimiter(","); // to read from the file with ( , )
        while (infile.hasNextInt()) { // take it as integers after each other 
            tree.insert(infile.nextInt()); // insert them in the order one by one 
        }
        infile.close(); // close the file we read from 
    }
    static void delete() {
        System.out.print("Enter a key to delete: ");
        int data = keyboard.nextInt(); // whatever the user decide ti delete 
        tree.delete(data); // delete what the user have chosen from the data 
    }
    static void search() {
        System.out.print("Enter a key to search for: ");
        int data = keyboard.nextInt(); // whatever the user decide ti search for 
        if (tree.find(data)) { // look up on the data we have to match the value that the user did enterd 
            System.out.println(data + " is found"); // print the result that the integer is found 
        } else {
            System.out.println(data + " is not found"); // other than that show the intger has noot been found 
        }
    }
    static void printTree() { // print the tree when the user chose 1 
        tree.print();
    }
    static void traversal() { // print the order when the user chose 2 
        tree.traversalOrder();
    }
    public static void main(String[] args) throws IOException {
        int choice; // make sure the user have integer as an input 
        inputTree("input.txt"); // read from the input.txt file 
        do {
            choice = UserChoise();
            switch (choice) {
                case 1: // choice num 1 to print the tree 
                    printTree();
                    break;
                case 2: // choice num 2 to print the order 
                    traversal();
                    break;
                case 3: // choice num 3 to delete an integer 
                    delete();
                    break;
                case 4: // choise num 4 to find an integer 
                    search();
                    break; // after the search stop the run 
                default:
                    System.out.println("not a correct input!"); // if the user insert num not from the 4 nums 
                    break;
            }
            System.out.println();
        } while (choice != 4); // after the search stop the run 
    }
}
