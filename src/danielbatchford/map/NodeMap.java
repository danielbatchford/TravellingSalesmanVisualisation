package danielbatchford.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NodeMap implements LEARNING_PARAMETERS {

    private final int numberOfNodes;
    private List<Node> nodes;
    private ArrayList<Edge> edges;

    private Random random;

    public double temperature;
    private double oldCost;

    public NodeMap(int numberOfNodes) {

        this.numberOfNodes = numberOfNodes;

        nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        edges = new ArrayList<>();
        for (int i = 0; i < numberOfNodes - 1; i++) {
            edges.add(new Edge(nodes.get(i), nodes.get(i + 1)));
        }
        edges.add(new Edge(nodes.get(numberOfNodes - 1), nodes.get(0)));

        random = new Random();
        temperature = INITIAL_TEMPERATURE;
        oldCost = getCost(edges);
    }

    public void update() {

        boolean swapped = false;
        int currentAttempt = 0;

        while(!swapped && currentAttempt < MAX_ATTEMPTS) {

            int firstIndex = random.nextInt(numberOfNodes);
            int secondIndex = random.nextInt(numberOfNodes);
            List<Node> newNodes = new ArrayList<>(nodes);
            Collections.swap(newNodes, firstIndex, secondIndex);

            ArrayList<Edge> newEdges = new ArrayList<>(edges);
            for (int i = Math.max(firstIndex-1,0), max = Math.min(firstIndex+1,numberOfNodes-2); i <= max; i++) {
                newEdges.set(i,new Edge(newNodes.get(i), newNodes.get(i + 1)));
            }
            for (int i = Math.max(secondIndex-1,0), max = Math.min(secondIndex+1,numberOfNodes-2); i <= max; i++) {
                newEdges.set(i,new Edge(newNodes.get(i), newNodes.get(i + 1)));
            }

            newEdges.set(numberOfNodes-1,new Edge(newNodes.get(numberOfNodes - 1), newNodes.get(0)));

            double newCost = getCost(newEdges);

            if (newCost < oldCost || random.nextDouble() < Math.exp((oldCost - newCost) / temperature)) {
                swapped = true;
                oldCost = newCost;
                nodes = newNodes;
                edges = newEdges;
            }
            currentAttempt++;
            temperature *= ALPHA;
        }
    }


    private double getCost(List<Edge> edges) {
        double sum = 0;
        for (Edge edge : edges) {
            sum += edge.getDistance();
        }
        return sum / numberOfNodes;
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
            sb.append(edge.toString()).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    public double getLearningRate(){
        return ALPHA;
    }

}
