import javax.swing.*;


public class GraphVisualization {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    public static void showGraph(Graph graph) {
        JFrame frame = new JFrame("Visualização do Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        GraphPanel graphPanel = new GraphPanel(graph);
        frame.add(graphPanel);
        
        frame.setVisible(true);
    }
}
