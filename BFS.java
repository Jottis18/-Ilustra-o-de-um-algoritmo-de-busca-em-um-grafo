import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private Graph graph;
    private GraphPanel panel;

    public BFS(Graph graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
    }

    public void bfsSearch(int startNode) {
        boolean[] visited = new boolean[graph.getVertices()];
        int[] distances = new int[graph.getVertices()];
        Queue<Integer> queue = new LinkedList<>();

        // Inicializa todas as distâncias como -1 (não visitado)
        for (int i = 0; i < distances.length; i++) {
            distances[i] = -1;
        }

        // Começa pelo nó inicial
        visited[startNode] = true;
        distances[startNode] = 0;
        queue.add(startNode);

        panel.setNodeColor(startNode, Color.RED); // Nó inicial processado em vermelho
        panel.repaint();

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            panel.setNodeColor(currentNode, Color.GREEN); // Nó processado completamente
            panel.repaint();
            
            // Pausa para visualizar o estado atual
            sleep(1000);

            for (int neighbor : graph.getAdjList()[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[currentNode] + 1; // Atualiza a distância
                    queue.add(neighbor);

                    panel.setNodeColor(neighbor, Color.RED); // Nó sendo processado
                    panel.repaint();
                    
                    sleep(1000);
                }
            }
        }

        System.out.println("Distâncias do nó " + startNode + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vértice " + i + ": " + distances[i]);
        }
    }

    // Função auxiliar para pausar entre iterações e permitir que a visualização seja atualizada
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
