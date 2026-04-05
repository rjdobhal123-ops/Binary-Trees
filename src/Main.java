import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Binary_tree_implementation bt=new Binary_tree_implementation();
        bt.insert(new Scanner(System.in));
//        bt.display();
//        System.out.println( bt.preorderTraversal(bt.root));

//        System.out.println(bt.postorderTraversal(bt.root));
        System.out.println(bt.inorderTraversal(bt.root));
        }
    }
