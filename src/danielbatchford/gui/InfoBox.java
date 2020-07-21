package danielbatchford.gui;

import danielbatchford.Constants;
import processing.core.PApplet;

import java.text.DecimalFormat;

public class InfoBox implements Constants {

    private DecimalFormat decimalFormat;

    PApplet parent;
    double learningRate;

    InfoBox(PApplet parent, double learningRate) {
        parent.textFont(parent.createFont("Arial", FONT_SIZE), FONT_SIZE);
        this.parent = parent;
        this.learningRate = learningRate;
        decimalFormat = new DecimalFormat("######.##");
    }

    void draw(double currCost, double temperature) {

        parent.fill(255, 255, 255, 150);
        parent.rect(TEXT_POS[0] - 20, TEXT_POS[1] - FONT_SIZE - 20, 170, 150);
        parent.fill(0, 0, 0);
        parent.text("Avg Distance: " + decimalFormat.format(currCost), TEXT_POS[0], TEXT_POS[1]);
        parent.text("Temperature: " + decimalFormat.format(temperature), TEXT_POS[0], TEXT_POS[1] + TEXT_SPACING);
        parent.text("Learning Rate: " + learningRate, TEXT_POS[0], TEXT_POS[1] + 2 * TEXT_SPACING);
        parent.text("Pause with \"P\"", TEXT_POS[0], TEXT_POS[1] + 3 * TEXT_SPACING);
        parent.text("Reset with \"R\"", TEXT_POS[0], TEXT_POS[1] + 4 * TEXT_SPACING);
    }
}
