package danielbatchford.gui;

import danielbatchford.Constants;
import org.gicentre.utils.stat.XYChart;
import processing.core.PApplet;

import java.util.LinkedList;
import java.util.List;

public class Plot implements Constants {

    PApplet parent;
    XYChart chart;
    int chartFrameCount;

    private List<Float> yData;
    private List<Float> xData;

    Plot(PApplet parent) {
        this.parent = parent;

        chartFrameCount = 0;
        chart = new XYChart(this.parent);
        chart.setMinX(0);
        chart.setMinY(0);
        chart.showXAxis(true);
        chart.showYAxis(true);
        chart.setPointSize(0);
        chart.setLineWidth(2);
        chart.setYAxisLabel("Avg Distance");
        chart.setXAxisLabel("Iteration");
        chart.setLineColour(parent.color(0, 0, 0));
        yData = new LinkedList<>();
        xData = new LinkedList<>();
    }

    void append(float x, float y) {
        xData.add(x);
        yData.add(y);
    }

    void draw() {

        float[] xDataPrim = new float[xData.size()];
        float[] yDataPrim = new float[yData.size()];

        for (int i = 0; i < xDataPrim.length; i++) {
            xDataPrim[i] = xData.get(i);
        }

        for (int i = 0; i < yDataPrim.length; i++) {
            yDataPrim[i] = yData.get(i);
        }

        chart.setData(xDataPrim, yDataPrim);
        chart.draw(WIDTH + PADDING, PADDING, SIDE_WIDTH - 2 * PADDING, HEIGHT - 2 * PADDING);
        chartFrameCount++;
    }

}
