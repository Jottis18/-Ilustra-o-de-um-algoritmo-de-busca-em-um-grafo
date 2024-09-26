import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class GraphPanel extends JPanel {
    private Graph graph;
    private HashMap<Integer, Color> nodeColors;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        this.nodeColors = new HashMap<>();

        // Inicializa todos os nós como azul (não visitado)
        for (int i = 0; i < graph.getVertices(); i++) {
            nodeColors.put(i, Color.BLUE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int radius = 30; // Raio dos nós
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int n = graph.getVertices();

        // Calcula as posições dos nós em um círculo
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = (int) (centerX + 150 * Math.cos(2 * Math.PI * i / n));
            int y = (int) (centerY + 150 * Math.sin(2 * Math.PI * i / n));
            points[i] = new Point(x, y);
        }

        // Desenha as arestas
        g.setColor(Color.BLACK);
        for (int i = 0; i < n; i++) {
            for (Edge edge : graph.getAdjList()[i]) { 
                int neighbor = edge.dest;  
                g.drawLine(points[i].x, points[i].y, points[neighbor].x, points[neighbor].y);

                int midX = (((points[i].x + points[neighbor].x))/ 2) + 25;
                int midY = (points[i].y + points[neighbor].y) / 2;

                g.drawString(String.valueOf(edge.weight), midX, midY);
            }
        }

        // Desenha os nós com as cores dinâmicas
        for (int i = 0; i < n; i++) {
            g.setColor(nodeColors.get(i)); // Pega a cor atual do nó
            g.fillOval(points[i].x - radius / 2, points[i].y - radius / 2, radius, radius);
            g.setColor(Color.BLACK);
            g.drawString("V " + i, points[i].x - radius / 4, points[i].y + radius / 4);
        }
    }

    // Método para alterar a cor de um nó específico
    public void setNodeColor(int node, Color color) {
        nodeColors.put(node, color);
    }
}
