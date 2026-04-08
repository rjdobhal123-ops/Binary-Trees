import java.security.KeyPair;
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


    //Pre,Post and In in one traversal
    public void traversal3in1(Node root){
         if (root==null)
             return;

        class Pair {
            Node node;
            int num;

            public Pair(Node node, int num) {
                this.node = node;
                this.num = num;
            }
        }

         Stack<Pair> st=new Stack<>();
         List<Integer> pre=new ArrayList<>();
        List<Integer> in=new ArrayList<>();
        List<Integer> post=new ArrayList<>();

        st.push(new Pair(root,1));

        while(!st.isEmpty()){
            Pair it=st.pop();

            if (it.num==1){
                pre.add(it.node.value);
                it.num++;
                st.push(it);

                if (it.node.left!=null){
                    st.push(new Pair(it.node.left,1));
                }
            } else if (it.num==2) {
                in.add(it.node.value);
                it.num++;
                st.push(it);

                if (it.node.right!=null){
                    st.push(new Pair(it.node.right,1));
                }
            }else{
                post.add(it.node.value);
            }
        }
    }


    public int maxdepth(Node root){
        if (root==null)
            return 0;

        int lh=maxdepth(root.left);
        int rh=maxdepth(root.right);

        return 1+Math.max(lh,rh);
     }



     public boolean heightbalanced(Node root){
         return balancedtree(root)!=-1;
     }
     public int balancedtree(Node root){
         if (root==null)
             return 0;

         int lh=balancedtree(root.left);
         if (lh==-1) return -1;
         int rh=balancedtree(root.right);
        if (rh==-1) return -1;

        if (Math.abs(lh-rh)>1) return -1;

        return Math.max(lh,rh)+1;
     }


     //Diameter of the binary tree

     public int diameterOfBinaryTree(Node root) {
         int[] diameter=new int[1];
         height(root,diameter);
         return diameter[0];
     }

    public int height(Node root,int [] diameter){
        if(root==null)
            return 0;

        int lh=height(root.left,diameter);
        int rh=height(root.right,diameter);

        diameter[0]=Math.max(diameter[0],lh+rh);

        return Math.max(lh,rh)+1;
    }


    //Identical Tree
    public boolean isSameTree(Node p, Node q) {
        if(p==null || q==null)
            return p==q;

        return (p.value==q.value) && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }


    //Max path sum

     public int maxPathSum(Node root) {
        int[] maxvalue=new int[1];
        maxvalue[0]=Integer.MIN_VALUE;
        maxPathheight(root,maxvalue);
        return maxvalue[0];
    }

    public int maxPathheight(Node root,int[] maxvalue){
         if (root==null)
             return 0;

         int lsum=Math.max(0,maxPathheight(root.left,maxvalue));
         int rsum=Math.max(0,maxPathheight(root.right,maxvalue));

         maxvalue[0]=Math.max(maxvalue[0],lsum+rsum+root.value);

         return Math.max(lsum,rsum)+root.value;
    }


    //Zig-Zag level order traversal

    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> ans=new ArrayList<>();
        if(root==null)
            return ans;

        Queue<Node> q=new ArrayDeque<>();
        q.offer(root);

        boolean lefttoRight=true;

        while(!q.isEmpty()){
            int size=q.size();
            Integer[] level=new Integer[size];

            for(int i=0;i<size;i++){
                Node node=q.poll();
                int index=lefttoRight?i:size-1-i;
                level[index]=node.value;

                if(node.left!=null)
                    q.add(node.left);

                if(node.right!=null)
                    q.add(node.right);
            }
            lefttoRight=!lefttoRight;
            ans.add(Arrays.asList(level));
        }
        return ans;
    }


    //Boundary traversal

    boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }
    // left boundary of the tree
    void addLeftBoundary(Node root, List<Integer> res) {
        Node curr = root.left;
        while (curr != null) {
            if (!isLeaf(curr)) {
                res.add(curr.value);
            }

            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }
    // right boundary of the tree
    void addRightBoundary(Node root, List<Integer> res) {
        Node curr = root.right;
        List<Integer> temp = new ArrayList<>();
        while (curr != null) {
            if (!isLeaf(curr)) {
                temp.add(curr.value);
            }
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        // Reverse and add the values
        for (int i = temp.size() - 1; i >= 0; --i) {
            res.add(temp.get(i));
        }
    }

    // leaves of the tree
    void addLeaves(Node root, List<Integer> res) {
        if (isLeaf(root)) {
            res.add(root.value);
            return;
        }
        if (root.left != null) {
            addLeaves(root.left, res);
        }
        if (root.right != null) {
            addLeaves(root.right, res);
        }
    }
    List<Integer> printBoundary(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        if (!isLeaf(root)) {
            res.add(root.value);
        }

        // Add the left boundary, leaves,
        // and right boundary in order
        addLeftBoundary(root, res);
        addLeaves(root, res);
        addRightBoundary(root, res);

        return res;
    }
}
