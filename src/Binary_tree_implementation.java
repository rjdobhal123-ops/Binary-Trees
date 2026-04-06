import java.util.*;

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
            System.out.println("Enter the value of right node of" +node.value);
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

        inorder(root.left,list);
        list.add(root.value);
        inorder(root.right,list);

    }

    //Level-order traversal
    public List<List<Integer>> levelorderTraversal(Node root){
        List<List<Integer>> ans=new ArrayList<>();
        if(root==null)
            return ans;

        Queue<Node> q=new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()){
            int size=q.size();
            List<Integer> level=new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                level.add(node.value);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            ans.add(level);
        }
        return ans;
    }


    //Iterative methods

    //Iterative preorder
    public List<Integer> itpreorder(Node root){
         List<Integer> preorder=new ArrayList<>();
         if (root==null)
             return preorder;

         Stack<Node> st=new Stack<>();
         st.push(root);

         while (!st.isEmpty()){
             root=st.pop();
             preorder.add(root.value);

             if (root.right!=null)
                 st.push(root.right);

             if (root.left!=null)
                 st.push(root.left);

         }
         return preorder;
    }

    //Iterative inorder
    public List<Integer> itinorder(Node root){
        List<Integer> inorder=new ArrayList<>();
        if (root==null)
            return inorder;
        Stack<Node> st=new Stack<>();
        Node node=root;

        while(true){
            if (node!=null){
                st.push(node);
                node=node.left;
            }else{
                if (st.isEmpty())
                    break;

                node=st.pop();
                inorder.add(node.value);
                node=node.right;
            }
        }
        return inorder;
    }

    //Postorder traversal using 2 stacks
    public List<Integer> postorder2stack(Node root){
         List<Integer> postorder=new ArrayList<>();
         if (root==null)
             return postorder;

         Stack<Node> st1=new Stack<>();
         Stack<Node> st2=new Stack<>();

         st1.push(root);

         while(!st1.isEmpty()){
             root=st1.pop();
             st2.push(root);
             if (root.left!=null)
                 st1.push(root.left);
             if (root.right!=null)
                 st1.push(root.right);
         }

         while(!st2.isEmpty()){
             postorder.add(st2.pop().value);
         }
         return postorder;
    }


    //post order traversal using 1 stack
    public List<Integer> postorder1stack(Node root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<Node> st = new Stack<>();
        Node curr = root;

        while (curr != null || !st.isEmpty()) {
            // go as left as possible
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            // peek at top node
            Node temp = st.peek().right;

            // if no right child, pop and add to answer
            if (temp == null) {
                temp = st.pop();
                postorder.add(temp.value);

                // check if popped node was right child of new top
                while (!st.isEmpty() && st.peek().right == temp) {
                    temp = st.pop();
                    postorder.add(temp.value);
                }
            } else {
                // move to right subtree
                curr = temp;
            }
        }
        return postorder;
    }
}
