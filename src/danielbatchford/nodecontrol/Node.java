package danielbatchford.nodecontrol;

import danielbatchford.Constants;

import java.util.Arrays;
import java.util.Random;

public class Node implements Constants {

    private Random random;
    private int[] pos;
    int id;

    Node(int id) {
        random = new Random();
        pos = new int[]{random.nextInt(WIDTH - 2 * PADDING) + PADDING, random.nextInt(HEIGHT - 2 * PADDING) + PADDING};
        this.id = id;
    }

    public int[] getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "ID: " + id + " " + Arrays.toString(pos);
    }

}
