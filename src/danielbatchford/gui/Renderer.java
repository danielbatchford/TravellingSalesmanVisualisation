package danielbatchford.gui;

import danielbatchford.Constants;
import danielbatchford.map.Edge;
import danielbatchford.map.Node;
import danielbatchford.map.NodeMap;
import processing.core.PApplet;

import java.util.List;

// Controls program loop and GUI rendering / updating
public class Renderer extends PApplet implements Constants {

    private NodeMap nodeMap;
    private Plot plot;
    private InfoBox infoBox;

    private boolean paused;
    private int framesSinceReset;

    public static void main(String[] args) {
        // Set the main processing class to this class
        PApplet.main("danielbatchford.gui.Renderer");
    }

    @Override
    public void settings() {
        size(WIDTH + SIDE_WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        nodeMap = new NodeMap(NUMBER_OF_NODES);
        plot = new Plot(this);

        // Initialises the info box with the nodeMap's learning rate
        infoBox = new InfoBox(this, nodeMap.getLearningRate());

        paused = false;
        framesSinceReset = 0;

        frameRate(FRAME_RATE);
        surface.setTitle("Travelling Salesman Visualisation");
        strokeWeight(3);
    }

    @Override
    public void draw() {

        background(color(255, 255, 255));
        stroke(50, 50, 50, 100);

        List<Edge> edges = nodeMap.getEdges();

        // Loops through the retrieved edges and draws lines between each edge's start and end positions.
        for (Edge edge : edges) {
            int[] pos1 = edge.getN1().getPos();
            int[] pos2 = edge.getN2().getPos();

            line(pos1[0], pos1[1], pos2[0], pos2[1]);
        }

        noStroke();
        fill(0, 0, 0);

        // Loops through the retrieved nodes and draws them to the screen
        List<Node> nodes = nodeMap.getNodes();
        for (Node node : nodes) {
            int[] pos = node.getPos();
            circle(pos[0], pos[1], NODE_DRAW_RADIUS);

            // Draw the Id text above each node
            if (SHOW_NODE_ID) {
                text(node.getId(), pos[0], pos[1] - 10);
            }
        }

        double currCost = nodeMap.getCost();

        // Draw the info box with the cost, temperature and learning rate
        infoBox.draw(currCost, nodeMap.temperature);
        plot.append(framesSinceReset, (float) currCost);
        plot.draw();
        framesSinceReset++;
        nodeMap.update();
    }

    // Called once each key press
    @Override
    public void keyPressed() {

        switch (key) {
            // Reset
            case 'r':
                nodeMap = new NodeMap(NUMBER_OF_NODES);
                plot = new Plot(this);
                framesSinceReset = 0;
                paused = false;
                loop();
                break;

            // Pause
            case 'p':
                if (paused) {
                    loop();
                } else {
                    noLoop();
                }
                paused = !paused;
                break;
        }
    }
}
