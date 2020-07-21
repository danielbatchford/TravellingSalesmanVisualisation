package danielbatchford;

// Program Constants
public interface Constants {

    int WIDTH = 1000;
    // Screen graph width
    int SIDE_WIDTH = 500;
    int HEIGHT = 1000;
    // Padding between window edge and gui elements (all sides)
    int PADDING = 50;

    int[] TEXT_POS = new int[]{50, 50};
    // Spacing between rows of text on the info box
    int TEXT_SPACING = 25;
    int FONT_SIZE = 15;

    int NUMBER_OF_NODES = 50;
    // Radius of each circle representing a node
    int NODE_DRAW_RADIUS = 10;
    // Whether to render a unique node ID above each node
    boolean SHOW_NODE_ID = true;

    int FRAME_RATE = 60;

}
