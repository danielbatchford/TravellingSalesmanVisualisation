package danielbatchford.map;

public class Edge implements MapEdge{

    // The start and end nodes on the edge. (Order not important)
    private Node n1;
    private Node n2;

    Edge(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public Node getN1() {
        return n1;
    }

    public Node getN2() {
        return n2;
    }

    // Returns the Euclidean distance between both nodes. (Represented in pixels).
    public double getDistance() {
        int[] pos1 = n1.getPos();
        int[] pos2 = n2.getPos();

        return Math.hypot(Math.abs(pos1[0] - pos2[0]), Math.abs(pos1[1] - pos2[1]));
    }

}
