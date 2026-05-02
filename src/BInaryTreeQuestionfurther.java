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

}
