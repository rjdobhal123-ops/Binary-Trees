import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Search_Tree extends Binary_tree_implementation {

    //Searching in a BST
    public Node search(Node root,int val){
        while(root!=null && root.value!=val){
            if(root.value>val)
                root=root.left;
            else
                root=root.right;
        }
        return root;
    }


    //Insertion in a BST
    public Node insertIntoBST(Node root, int val) {
        if(root==null)
            return new Node(val);

        Node curr=root;

        while(true){
            if(curr.value<=val){
                if(curr.right!=null)
                    curr=curr.right;
                else{
                    curr.right=new Node(val);
                    break;
                }
            }
            else{
                if(curr.left!=null)
                    curr=curr.left;
                else{
                    curr.left=new Node(val);
                    break;
                }
            }
        }
        return root;
    }


    //Deletion in a BST
    public Node delete(Node root,int key){
        if(root==null)
            return null;

        if(root.value==key)
            return helper(root);

        Node dummy=root;
        while(root!=null){
            if(root.value>key){
                if(root.left!=null && root.left.value==key){
                    root.left=helper(root.left);
                    break;
                }else
                    root=root.left;
            }
            else{
                if (root.right!=null && root.right.value==key){
                    root.right=helper(root.right);
                    break;
                }else
                    root=root.right;
            }
        }
        return dummy;
    }

    public Node helper(Node root){
        if (root.left==null)
            return root.right;
        else if(root.right==null)
            return root.left;
        else{
            Node rightChild=root.right;
            Node lastRight=findLastRight(root.left);
            lastRight.right=rightChild;
            return root.left;
        }
    }

    public Node findLastRight(Node root){
        if (root.right==null)
            return root;

        return findLastRight(root.right);
    }



    //Kth smallest element in BST
    public int kthSmallest(Node root, int k) {
        Node curr = root;

        while (curr != null) {
            if (curr.left == null) {
                // Visit node
                k--;
                if (k == 0) return curr.value;
                curr = curr.right;
            } else {
                // Find inorder predecessor
                Node pred = curr.left;
                while (pred.right != null && pred.right != curr)
                    pred = pred.right;

                if (pred.right == null) {
                    // Create thread
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // Remove thread and visit
                    pred.right = null;
                    k--;
                    if (k == 0) return curr.value;
                    curr = curr.right;
                }
            }
        }
        return -1;
    }

    //Validate BST or not
    public boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(Node root, long minVal, long maxVal){
        if(root==null)
            return true;

        if( root.value>=maxVal || root.value<=minVal)
            return false;

        return isValidBST(root.left, minVal, root.value) && isValidBST(root.right, root.value, maxVal);
    }


    //Lowest Common Ancestor
    public Node lowestCommonAncestor(Node root, Node p, Node q){

        Node curr=root;
        while(curr!=null){
            if(p.value<curr.value && q.value<curr.value)
                curr=curr.left;
            else if(p.value>curr.value && q.value>curr.value)
                curr=curr.right;
            else
                return curr;
        }
        return null;
    }


    //Constructing BST using preorder traversal
    public Node bstFromPreorder(int[] preorder) {
        return build(preorder, new int[]{0}, Integer.MAX_VALUE);
    }

    private Node build(int[] preorder, int[] index, int bound){
        if (index[0]==preorder.length || preorder[index[0]]>bound){
            return null;
        }

        root=new Node(preorder[index[0]++]);

        root.left=build(preorder, index, root.value);
        root.right=build(preorder, index, bound);

        return root;

    }


    //Inorder predecessor/Successor in BST
    public int[] presucc(Node root, int key) {
        int[] result = new int[]{-1, -1};

        Node curr = root;
        while (curr != null) {
            if (key < curr.value) {
                result[1] = curr.value;
                curr = curr.left;
            } else if (key > curr.value) {
                result[0] = curr.value;
                curr = curr.right;
            } else {
                if (curr.left != null) {
                    Node pred = curr.left;
                    while (pred.right != null)
                        pred = pred.right;

                    result[0] = pred.value;
                }

                if (curr.right != null) {
                    Node succ = curr.right;
                    while (succ.left != null)
                        succ = succ.left;

                    result[1] = succ.value;
                }
                break;
            }
        }
        return result;


        //BST iterator

        class BSTIterator1 {
            Stack<Node> st = new Stack<>();

             BSTIterator1(Node root) {
                pushAll(root);
            }

            public int next() {
                Node tempnode = st.pop();
                pushAll(tempnode.right);
                return tempnode.value;
            }

            public boolean hasNext() {
                if (st.isEmpty())
                    return false;

                return true;
            }

            public void pushAll(Node node) {
                for (; node != null; st.push(node), node = node.left) ;
            }
        }
    }


    //Two sum IV-->Input is a BST

    class Solution {
        public boolean findTarget(Node root, int k) {
            if(root==null)
                return false;

            BSTIterator left=new BSTIterator(root, false);
            BSTIterator right=new BSTIterator(root, true);

            int i=left.next();
            int j=right.next();

            while(i<j){
                if(i+j==k)
                    return true;
                else if(i+j<k)
                    i=left.next();
                else
                    j=right.next();
            }

            return false;
        }
    }

    class BSTIterator {
        Stack<Node> st;
        boolean reverse;

        public BSTIterator(Node root, boolean isReverse) {
            st=new Stack<>();
            reverse=isReverse;
            pushAll(root);
        }

        public int next() {
            Node tempnode=st.pop();

            if(!reverse)
                pushAll(tempnode.right);
            else
                pushAll(tempnode.left);

            return tempnode.value;
        }

        public boolean hasNext() {
            return !st.isEmpty();
        }

        public void pushAll(Node node){
            while(node!=null){
                st.push(node);

                if(reverse)
                    node=node.right;
                else
                    node=node.left;
            }
        }
    }


    // Recover BST

    public void recoverTree(Node root) {
        Node first  = null;
        Node second = null;
        Node prev   = null;
        Node curr   = root;

        while (curr != null) {
            if (curr.left == null) {
                // Visit current node
                if (prev != null && prev.value > curr.value) {
                    if (first == null) first = prev;  // first violation
                    second = curr;                     // always update second
                }
                prev = curr;
                curr = curr.right;

            } else {
                // Find inorder predecessor
                Node pred = curr.left;
                while (pred.right != null && pred.right != curr)
                    pred = pred.right;

                if (pred.right == null) {
                    pred.right = curr;       // create thread
                    curr = curr.left;
                } else {
                    pred.right = null;       // remove thread

                    // Visit current node
                    if (prev != null && prev.value > curr.value) {
                        if (first == null) first = prev;
                        second = curr;
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        // Swap the two wrongly placed nodes
        int temp   = first.value;
        first.value  = second.value;
        second.value = temp;
    }



    //Maxsum BST in BT

    class Solution2 {
        int maxsum=0;
        public int maxSumBST(Node root) {
            postOrder(root);
            return maxsum;
        }

        private int[] postOrder(Node root){
            if (root==null)
                return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

            int[] left=postOrder(root.left);
            int[] right=postOrder(root.right);

            //check if parent subtree is valid or not
            if (left[0]==1 && right[0]==1 && root.value>left[2] && root.value<right[1]){
                int sum=root.value+left[3]+right[3];
                maxsum=Math.max(maxsum, sum);

                //parent info
                int min=Math.min(root.value, left[1]);
                int max=Math.max(root.value, right[2]);
                return new int[]{1, min, max, sum};
            }
            return new int[]{0, 0, 0, 0};
        }
    }


    //MERGE TWO BST

    public Node mergeTrees(Node root1, Node root2){
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();

        inorder(root1,list1);
        inorder(root2,list2);

        List<Integer> merged=mergeSorted(list1, list2);

        return buildBST(merged, 0, merged.size());
    }


    private List<Integer> mergeSorted(List<Integer> list1, List<Integer> list2) {
        List<Integer> result=new ArrayList<>();
        int i=0,j=0;

        while(i<list1.size() && j<list2.size()){
            if (list1.get(i)<=list2.get(j))
                result.add(list1.get(i++));
            else
                result.add(list2.get(j++));
        }

        while(i<list1.size())
            result.add(list1.get(i++));

        while(j<list2.size())
            result.add(list2.get(j++));

        return result;
    }

    private Node buildBST(List<Integer> merged, int start, int end) {
        if (start>end)
            return null;

        int mid=start+(end-start)/2;
        Node root=new Node(merged.get(mid));

        root.left=buildBST(merged, start, mid-1);
        root.right=buildBST(merged, mid+1, end);

        return root;
    }
}
