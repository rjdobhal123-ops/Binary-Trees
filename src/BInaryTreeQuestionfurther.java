import java.util.*;

public class BInaryTreeQuestionfurther extends Binary_tree_implementation {
    public int timeToBurnTree(Node root, int start) {
        return mintime(root,start);
    }

    private int mintime(Node root,int target){
        Map<Integer, List<Integer>> graph=new HashMap<>();
        buildgraph(root,null,graph);

        Set<Integer> visited=new HashSet<>();

        Queue<Integer> queue=new LinkedList<>();
        queue.offer(target);
        visited.add(target);

        int time=0;

        while(!queue.isEmpty()){
            int size=queue.size();
            boolean burned=false;

            for (int i = 0; i < size; i++) {
                int node=queue.poll();

                for (int neighbour:graph.getOrDefault(node,new ArrayList<>())) {
                   if(!visited.contains(neighbour)){
                       visited.add(neighbour);
                       queue.offer(neighbour);
                       burned=true;
                   }
                }
            }
            if(burned)
                time++;
        }
        return time;
    }

    private void buildgraph(Node node,Node parent,Map<Integer, List<Integer>> graph){
        if(node==null)
            return;

        if(parent!=null){
            graph.computeIfAbsent(node.value, k -> new ArrayList<>()).add(parent.value);
            graph.computeIfAbsent(parent.value, k -> new ArrayList<>()).add(node.value);
        }

        buildgraph(node.left, node, graph);
        buildgraph(node.right, node, graph);
    }



    //Question 222
    public int countNodes(Node root) {
        if(root==null)
            return 0;

        int left=getHeightLeft(root);
        int right=getHeightRight(root);

        if(left==right){
            return (1<<left)-1;
        }

        return 1+countNodes(root.left)+countNodes(root.right);

    }

    private int getHeightLeft(Node root){
        int count=0;
        while(root!=null){
            count++;
            root=root.left;
        }
        return count;
    }

    private int getHeightRight(Node root){
        int count=0;
        while(root!=null){
            count++;
            root=root.right;
        }
        return count;
    }


    //Question-->106
    public Node buildTree(int[] inorder, int[] postorder) {
        if(inorder.length!=postorder.length)
            return null;

        Map<Integer,Integer> inMap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            inMap.put(inorder[i],i);
        }

        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1,inMap);
    }

    private Node build(int[] inorder,int inStart,int inEnd,int[] postorder,int postStart,int postEnd, Map<Integer,Integer> inMap){
        if(postStart>postEnd || inStart>inEnd)
            return null;

        Node root=new Node(postorder[postEnd]);
        int inRoot=inMap.get(postorder[postEnd]);
        int numsLeft=inRoot-inStart;

        root.left=build(inorder,inStart,inRoot-1,postorder,postStart,postStart+numsLeft-1,inMap);

        root.right=build(inorder,inRoot+1,inEnd,postorder,postStart+numsLeft,postEnd-1,inMap);

        return root;
    }



    //Question-->297

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root==null)
            return "";

        Queue<Node> q=new LinkedList<>();
        StringBuilder res=new StringBuilder();

        q.add(root);

        while(!q.isEmpty()){
            Node node=q.poll();
            if (node==null){
                res.append("# ");
                continue;
            }
            res.append(node.value+" ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data=="")
            return null;

        Queue<Node> q=new LinkedList<>();
        String[] values=data.split(" ");
        Node root=new Node(Integer.parseInt(values[0]));
        q.add(root);

        for (int i = 1; i < values.length; i++) {
            Node parent=q.poll();

            if (!values[i].equals("#")){
                Node left=new Node(Integer.parseInt(values[i]));
                parent.left=left;
                q.add(left);
            }

            if (!values[++i].equals("#")){
                Node right=new Node(Integer.parseInt(values[i]));
                parent.right=right;
                q.add(right);
            }
        }
        return root;
    }


    //Morris Traversal algorithm-->S.C.=O(1)

    //Morris Inorder traversal
    public List<Integer> morrisinorder(Node root){
        List<Integer> inorder=new ArrayList<>();
        if (root==null)
            return inorder;

        Node curr=root;

        while(curr!=null){
            if (curr.left==null){
                inorder.add(curr.value);
                curr=curr.right;
            }else{
                Node pred=curr.left;
                while(pred.right!=null && pred.right!=curr)
                    pred=pred.right;

                if (pred.right==null){
                    pred.right=curr;
                    curr=curr.left;
                }else{
                    pred.right=null;
                    inorder.add(curr.value);
                    curr=curr.right;
                }
            }
        }
        return inorder;
    }

    //Morris preorder traversal
    public List<Integer> morrispreorder(Node root){
        List<Integer> preorder=new ArrayList<>();
        if (root==null)
            return preorder;

        Node curr=root;

        while(curr!=null){
            if (curr.left==null){
                preorder.add(curr.value);
                curr=curr.right;
            }else{
                Node pred=curr.left;
                while(pred.right!=null && pred.right!=curr)
                    pred=pred.right;

                if (pred.right==null){
                    preorder.add(curr.value);
                    pred.right=curr;
                    curr=curr.left;
                }else{
                    pred.right=null;
                    curr=curr.right;
                }
            }
        }
        return preorder;
    }


    //Flatten the binary tree into linkedlist

    public void flatten(Node root) {
        if(root==null)
            return;

        Node curr=root;
        Node prev=null;

        while(curr!=null){
            if (curr.left!=null){
               prev=curr.left;
               while(prev.right!=null)
                   prev=prev.right;

               prev.right=curr.right;
               curr.right=curr.left;
               curr.left=null;
            }
            curr=curr.right;
        }
    }

}
