package danielbatchford.map;

import danielbatchford.Constants;
import java.util.Random;

public class Node implements MapNode, Constants {

    private Random random;
    private int[] pos;

    // A unique id for this node
    int id;

    Node(int id) {
        this.random = new Random();
        // Initialise a node in a random position.
        this.pos = new int[]{random.nextInt(WIDTH - 2 * PADDING) + PADDING, random.nextInt(HEIGHT - 2 * PADDING) + PADDING};
        this.id = id;
    }

    public int[] getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }
}
