import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Binary_tree_implementation bt=new Binary_tree_implementation();
//        bt.insert(new Scanner(System.in));
        //      5
        //     / \
        //   12   13
        //   /  \    \
        //  7    14   2
        // / \  /  \  / \
        //17 23 27 3  8  11

//        bt.display();
//        System.out.println( bt.preorderTraversal(bt.root));

//        System.out.println(bt.postorderTraversal(bt.root));
        System.out.println(bt.inorderTraversal(bt.root));
        }
    }
