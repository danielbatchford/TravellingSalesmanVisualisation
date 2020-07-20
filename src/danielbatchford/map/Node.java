package danielbatchford.map;

import danielbatchford.Constants;

import java.util.Arrays;
import java.util.Random;

public class Node implements Constants {

    private Random random;
    private int[] pos;
    int id;

    Node(int id) {
        this.random = new Random();
        this.pos = new int[]{random.nextInt(WIDTH - 2 * PADDING) + PADDING, random.nextInt(HEIGHT - 2 * PADDING) + PADDING};
        this.id = id;
    }

    Node(Node node) {
        this.random = new Random();
        this.id = node.id;
        int[] oldPos = node.getPos();
        this.pos = new int[]{oldPos[0], oldPos[1]};
    }

    public int[] getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " " + Arrays.toString(pos);
    }

}
