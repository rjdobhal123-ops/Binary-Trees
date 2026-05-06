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
}
