package danielbatchford.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NodeMap implements MapController, LearningParameters {

    // Represents the number of nodes
    private final int n;

    private List<Node> nodes;
    private ArrayList<Edge> edges;

    private Random random;

    public double temperature;

    // Holds the current calculated cost of the graph
    private double currCost;

    public NodeMap(int n) {

        this.n = n;

        // Initialise n nodes
        nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i));
        }

        // Initialise edges
        edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(nodes.get(i), nodes.get((i + 1) % n)));
        }


        random = new Random();
        temperature = INITIAL_TEMPERATURE;
        currCost = getCost(edges);
    }

    /* Simulate one search and update of the graph. 2 indexes are chosen and the sublist between these indexes is reversed.
    If the cost decreases, update the graph. Else with probability controlled by simulated annealing, update the graph
    anyway. Else, continue searching.*/
    public void update() {

        // The index of the first chosen node.
        int sIndex = 0;

        // Whether a successful swap has been chosen.
        boolean swapped = false;

        while (!swapped) {

            // End index from range [sIndex, n)
            int eIndex = random.nextInt(n - sIndex) + sIndex;

            // Copy the current nodes and reverse the sublist between the start and end indexes.
            List<Node> newNodes = new ArrayList<>(nodes);
            Collections.reverse(newNodes.subList(sIndex, eIndex));

            // Populate a copy of the edge list with the new changes
            ArrayList<Edge> newEdges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                newEdges.add(new Edge(newNodes.get(i), newNodes.get((i + 1) % n)));
            }

            // Calculate a cost for this current permutation
            double newCost = getCost(newEdges);

            // If cost decreases or with probability p = e^(deltaCost/temperature), update the graph with the changes.
            if (newCost < currCost || random.nextDouble() < Math.exp((currCost - newCost) / temperature)) {
                currCost = newCost;
                nodes = newNodes;
                edges = newEdges;
                temperature *= ALPHA;
                swapped = true;
            }

            // Otherwise, iterate the start index and continue searching
            sIndex++;
        }
    }


    // Currently returns the average distance between nodes.
    private double getCost(List<Edge> edges) {
        double sum = 0;
        for (Edge edge : edges) {
            sum += edge.getDistance();
        }
        return sum / n;
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

    public double getLearningRate() {
        return ALPHA;
    }

}
