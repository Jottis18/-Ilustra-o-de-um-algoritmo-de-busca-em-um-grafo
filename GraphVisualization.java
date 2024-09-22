import javax.swing.*;

public class GraphVisualization {
    public static void showGraph(Graph graph) {
        JFrame frame = new JFrame("Visualização do Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        GraphPanel graphPanel = new GraphPanel(graph);
        frame.add(graphPanel);
        
        frame.setVisible(true);
    }
}
