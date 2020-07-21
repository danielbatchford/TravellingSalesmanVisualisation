package danielbatchford.gui;

import danielbatchford.Constants;
import danielbatchford.map.Edge;
import danielbatchford.map.Node;
import danielbatchford.map.NodeMap;
import processing.core.PApplet;

import java.util.List;

public class Renderer extends PApplet implements Constants {

    private NodeMap nodeMap;
    private Plot plot;
    private InfoBox infoBox;

    private boolean paused;
    private int framesSinceReset;

    public static void main(String[] args) {
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
            stroke(0, 0, 0);

            List<Edge> edges = nodeMap.getEdges();

            for (Edge edge : edges) {
                int[] pos1 = edge.getN1().getPos();
                int[] pos2 = edge.getN2().getPos();

                line(pos1[0], pos1[1], pos2[0], pos2[1]);
            }

            noStroke();
            fill(0, 0, 0);
            List<Node> nodes = nodeMap.getNodes();
            for (Node node : nodes) {
                int[] pos = node.getPos();
                circle(pos[0], pos[1], NODE_DRAW_RADIUS);
                text(node.getId(), pos[0], pos[1] - 10);
            }

            double currCost = nodeMap.getCost();

            infoBox.draw(currCost, nodeMap.temperature);
            plot.append(framesSinceReset, (float) currCost);
            plot.draw();
            framesSinceReset++;
            nodeMap.update();

    }




    @Override
    public void keyPressed() {

        switch (key) {
            case 'r':
                nodeMap = new NodeMap(NUMBER_OF_NODES);
                plot = new Plot(this);
                framesSinceReset = 0;
                paused = false;
                loop();
                break;
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
