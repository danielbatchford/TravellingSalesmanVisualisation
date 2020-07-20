package danielbatchford.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NodeMap {

    private final int numberOfNodes;
    private final List<Node> nodes;
    private ArrayList edges;
    private Random random;
    private double alpha;
    private double temperature;

    public NodeMap(int numberOfNodes) {
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

        alpha = 0.99;
        temperature = 1000;
    }

    public void update() {
        int firstIndex = random.nextInt(numberOfNodes - 1);
        int secondIndex = firstIndex + 1;


        List<Node> newNodeList = new ArrayList<>(nodes);
        System.out.println(newNodeList);
        Collections.swap(newNodeList, firstIndex, secondIndex);
        System.out.println(newNodeList);


        List<Edge> newEdgeList = new ArrayList<>();
        for (int i = 0; i < numberOfNodes - 1; i++) {
            newEdgeList.add(new Edge(newNodeList.get(i), newNodeList.get(i + 1)));
        }
        System.out.println(edges);

        double oldCost = getCost(edges);
        double newCost = getCost(newEdgeList);

        System.out.println(oldCost);
        System.out.println(newCost);
        System.out.println();

        if (newCost < oldCost) {
            edges = new ArrayList<Edge>(newEdgeList);
        } else if (random.nextDouble() < Math.exp((oldCost - newCost) / temperature) && false) {
            edges = new ArrayList<Edge>(newEdgeList);
        }

        temperature *= alpha;
    }


    private double getCost(List<Edge> edges) {
        double sum = 0;
        for (Edge edge : edges) {
            sum += edge.getDistance();
        }
        return sum/numberOfNodes;
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

    public double getTemperature() {
        return temperature;
    }

    public double getLearningRate() {
        return alpha;
    }
}
