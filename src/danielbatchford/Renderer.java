package danielbatchford;

import danielbatchford.map.Edge;
import danielbatchford.map.NodeMap;
import danielbatchford.map.Node;
import processing.core.PApplet;
import org.gicentre.utils.stat.*;

import java.util.LinkedList;
import java.util.List;

public class Renderer extends PApplet implements Constants {

    private NodeMap nodeMap;
    private XYChart chart;

    private List<Float> yData;
    private List<Float> xData;

    private boolean paused;

    private int chartFrameCount;


    public static void main(String[] args) {
        PApplet.main("danielbatchford.Renderer");
    }

    @Override
    public void settings() {
        size(WIDTH + SIDE_WIDTH, HEIGHT);

    }

    @Override
    public void setup() {
        nodeMap = new NodeMap(NUMBER_OF_NODES);

        surface.setTitle("Travelling Salesman Visualisation");

        frameRate(FRAME_RATE);

        textFont(createFont("Arial", FONT_SIZE), FONT_SIZE);

        paused = false;

        strokeWeight(3);

        initialiseChart();
    }

    void initialiseChart(){

        chartFrameCount = 0;
        chart = new XYChart(this);
        chart.setMinX(0);
        chart.setMinY(0);
        chart.showXAxis(true);
        chart.showYAxis(true);
        chart.setPointSize(0);
        chart.setLineWidth(2);
        chart.setYAxisLabel("Distance");
        chart.setXAxisLabel("Iteration");
        chart.setLineColour(color(0,0,0));
        yData = new LinkedList<>();
        xData = new LinkedList<>();


    }

    @Override
    public void draw() {


        background(color(255,255,255));
        stroke(0,0,0);

        List<Edge> edges = nodeMap.getEdges();

        for (Edge edge : edges) {
            int[] pos1 = edge.getN1().getPos();
            int[] pos2 = edge.getN2().getPos();

            line(pos1[0], pos1[1], pos2[0], pos2[1]);
        }

        noStroke();
        fill(0,0,0);

        List<Node> nodes = nodeMap.getNodes();
        for (Node node : nodes) {
            int[] pos = node.getPos();
            circle(pos[0], pos[1], NODE_DRAW_RADIUS);
            text(node.getId(), pos[0]+10,pos[1]);
        }

        double currCost = nodeMap.getCost();
        text("Distance: " + currCost, TEXT_POS[0], TEXT_POS[1]);
        text("Temperature: " + nodeMap.getTemperature(), TEXT_POS[0], TEXT_POS[1] + TEXT_SPACING);
        text("Learning Rate: " + nodeMap.getLearningRate(), TEXT_POS[0], TEXT_POS[1] + 2*TEXT_SPACING);

        xData.add((float) chartFrameCount);
        yData.add((float) currCost);

        float[] xDataPrim = new float[xData.size()];
        float[] yDataPrim = new float[yData.size()];

        for(int i = 0; i < xDataPrim.length; i++){
            xDataPrim[i] = xData.get(i);
        }

        for(int i = 0; i < yDataPrim.length; i++){
            yDataPrim[i] = yData.get(i);
        }

        chart.setData(xDataPrim, yDataPrim);
        chart.draw(WIDTH + PADDING,PADDING,SIDE_WIDTH - 2*PADDING,HEIGHT-2*PADDING);
        chartFrameCount++;

        nodeMap.update();
    }

    @Override
    public void keyPressed() {

        switch (key) {
            case 'r':
                nodeMap = new NodeMap(NUMBER_OF_NODES);
                initialiseChart();
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
