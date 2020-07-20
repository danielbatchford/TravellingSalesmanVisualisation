package danielbatchford.nodecontrol;

public class Edge {

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

    double getDistance() {
        int[] pos1 = n1.getPos();
        int[] pos2 = n2.getPos();
        return Math.sqrt(Math.pow(pos1[0] - pos2[0], 2) + Math.pow(pos1[1] - pos2[1], 2));
    }

    @Override
    public String toString() {
        return n1.toString() + " " + n2.toString();
    }

}