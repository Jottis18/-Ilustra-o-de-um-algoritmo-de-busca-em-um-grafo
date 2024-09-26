import java.awt.Color;
import java.util.Stack;

public class DFS {
    private Graph graph;
    private GraphPanel panel;

    public DFS(Graph graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
    }

    public void dfsSearch(int startNode) {
        boolean[] visited = new boolean[graph.getVertices()];
        int[] distances = new int[graph.getVertices()];
        Stack<Integer> stack = new Stack<>();

        // Inicializa todas as distâncias como -1 (não visitado)
        for (int i = 0; i < distances.length; i++) {
            distances[i] = -1;
        }

        // Começa pelo nó inicial
        visited[startNode] = true;
        distances[startNode] = 0;
        stack.push(startNode);

        panel.setNodeColor(startNode, Color.RED); // Nó inicial processado em vermelho
        panel.repaint();

        while (!stack.isEmpty()) {
            int currentNode = stack.pop();

            panel.setNodeColor(currentNode, Color.GREEN); // Nó processado completamente
            panel.repaint();

            // Pausa para visualizar o estado atual
            sleep(1000);

            for (Edge edge : graph.getAdjList()[currentNode]) {
                int neighbor = edge.dest; // Acessa o destino da aresta
            
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[currentNode] + 1; // Atualiza a distância
                    stack.push(neighbor); // Empilha o próximo nó a ser processado
            
                    panel.setNodeColor(neighbor, Color.RED); // Nó sendo processado
                    panel.repaint();
            
                    try {
                        Thread.sleep(1000); // Pausa a execução por 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
