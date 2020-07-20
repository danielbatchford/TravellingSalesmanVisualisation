package danielbatchford.nodecontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GraphController {

    private final int numberOfNodes;
    private final List<Node> nodes;
    private List<Edge> edges;
    private Random random;

    public GraphController(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        nodes = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }
        edges = new ArrayList<>();

        for (int i = 0; i < numberOfNodes - 1; i++) {
            edges.add(new Edge(nodes.get(i), nodes.get(i + 1)));
        }

    }

    public void update() {
        int startIndex = random.nextInt(nodes.size());
        int endIndex = random.nextInt(nodes.size() - startIndex) + startIndex;

        List<Node> newNodeList = new ArrayList<>(nodes);
        List<Node> subList = nodes.subList(startIndex, endIndex);
        Collections.reverse(subList);

        for(int i = startIndex; i < endIndex; i++){
            newNodeList.set(i,subList.get(i-startIndex));
        }

        List<Edge> newEdgeList = new ArrayList<>();

        for (int i = 0; i < newEdgeList.size() - 1; i++) {
            newEdgeList.add(new Edge(newNodeList.get(i), newNodeList.get(i + 1)));
        }

        if (getCost(newEdgeList) < getCost(edges)) {
            edges = new ArrayList(newEdgeList);
        }

        for (Node n : nodes) {
            System.out.println(n.toString());
        }
        System.out.println();
    }


    private double getCost(List<Edge> edges) {
        double sum = 0;
        for (Edge edge : edges) {
            sum += edge.getDistance();
        }
        return sum;
    }

    public double getCost() {
        return getCost(this.edges);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public String edgesToString(List<Edge> edges) {
        StringBuilder sb = new StringBuilder();

        for (Edge edge : edges) {
            sb.append(edge.toString() + "\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
