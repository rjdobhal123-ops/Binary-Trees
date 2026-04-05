import java.util.Scanner;

class Binary_tree_implementation {
    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    //Inserting nodes
     public void insert(Scanner sc){
         System.out.println("Enter the root node:");
         int value=sc.nextInt();
         root=new Node(value);
         insert(sc,root);
    }

    public void insert(Scanner sc,Node node){
        System.out.println("Do you want to enter left of" +node.value);
        boolean left=sc.nextBoolean();
        if (left){
            System.out.println("Enter the value of left node of" +node.value);
            int value=sc.nextInt();
            node.left=new Node(value);
            insert(sc,node.left);
        }

        System.out.println("Do you want to enter right of" +node.value);
        boolean right=sc.nextBoolean();
        if (right){
            System.out.println("Enter the value of left node of" +node.value);
            int value=sc.nextInt();
            node.right=new Node(value);
            insert(sc,node.right);
        }
    }

    public void display(){
         display(this.root);
    }

    public void display(Node node){
         if (node==null)
             return;

        System.out.println(node.value);
        display(node.left);
        display(node.right);
     }
}
