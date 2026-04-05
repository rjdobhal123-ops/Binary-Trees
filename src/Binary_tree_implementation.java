import java.util.ArrayList;
import java.util.List;
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

     Node root;

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


     //Preorder traversal
     public List<Integer> preorderTraversal(Node root) {
         List<Integer> ans =new ArrayList<>();
         preorder(root,ans);
         return ans;
     }

    public void preorder(Node root,List<Integer> list){
        if(root==null)
            return;

        list.add(root.value);
        preorder(root.left,list);
        preorder(root.right,list);
    }

    //PostOrder traversal
    public List<Integer> postorderTraversal(Node root) {
        List<Integer> ans =new ArrayList<>();
        postorder(root,ans);
        return ans;
    }

    public void postorder(Node root,List<Integer> list){
        if(root==null)
            return;

        postorder(root.left,list);
        postorder(root.right,list);
        list.add(root.value);
    }

    //In-Order traversal
    public List<Integer> inorderTraversal(Node root) {
        List<Integer> ans =new ArrayList<>();
        inorder(root,ans);
        return ans;
    }

    public void inorder(Node root,List<Integer> list){
        if(root==null)
            return;

        postorder(root.left,list);
        list.add(root.value);
        postorder(root.right,list);

    }
}
