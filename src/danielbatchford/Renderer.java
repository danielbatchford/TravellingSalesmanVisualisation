package danielbatchford;

import danielbatchford.nodecontrol.Edge;
import danielbatchford.nodecontrol.Node;
import danielbatchford.nodecontrol.GraphController;
import processing.core.PApplet;

import java.util.List;

public class Renderer extends PApplet implements Constants {

    GraphController nodeController;


    public static void main(String[] args) {
        PApplet.main("danielbatchford.Renderer");
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);

    }

    @Override
    public void setup() {
        nodeController = new GraphController(NUMBER_OF_NODES);
        strokeWeight(5);
        surface.setTitle("Travelling Salesman Visualisation");
        frameRate(FRAME_RATE);
        textFont(createFont("Arial", FONT_SIZE), FONT_SIZE);

    }

    @Override
    public void draw() {


        background(BG_COL[0], BG_COL[1], BG_COL[2]);


        stroke(LINE_COL[0], LINE_COL[1], LINE_COL[2]);

        List<Edge> edges = nodeController.getEdges();

        for (Edge edge : edges) {
            int[] pos1 = edge.getN1().getPos();
            int[] pos2 = edge.getN2().getPos();

            line(pos1[0], pos1[1], pos2[0], pos2[1]);
        }


        noStroke();
        fill(NODE_COL[0], NODE_COL[1], NODE_COL[2]);

        List<Node> nodes = nodeController.getNodes();
        for (Node node : nodes) {
            int[] pos = node.getPos();
            circle(pos[0], pos[1], CIRCLE_RADIUS);
        }

        text("Cost: " + nodeController.getCost(), TEXT_POS[0], TEXT_POS[1]);

        nodeController.update();
    }

    @Override
    public void keyPressed() {

        if (key == 'r') {
            nodeController = new GraphController(NUMBER_OF_NODES);
        }

    }
}
