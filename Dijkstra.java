import java.awt.Color;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Dijkstra {
    private Graph graph;
    private GraphPanel panel;

    public Dijkstra(Graph graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
    }

    public void dijkstraSearch(int startNode) {
        int vertices = graph.getVertices();
        boolean[] visited = new boolean[vertices];
        int[] distances = new int[vertices];
        
        // Inicializa as distâncias como infinitas (exceto para o nó inicial)
        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startNode] = 0;

        // Fila de prioridade para selecionar o vértice com a menor distância
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));
        pq.add(startNode);

        panel.setNodeColor(startNode, Color.RED); // Nó inicial processado em vermelho
        panel.repaint();

        while (!pq.isEmpty()) {
            int currentNode = pq.poll(); // Remove o nó com a menor distância

            // Se o nó já foi visitado, continue
            if (visited[currentNode]) {
                continue;
            }

            visited[currentNode] = true;
            panel.setNodeColor(currentNode, Color.GREEN); // Nó processado completamente
            panel.repaint();

            // Pausa para visualizar o estado atual
            sleep(1000);

            for (Edge edge : graph.getAdjList()[currentNode]) {
                int neighbor = edge.dest;
                int weight = edge.weight;

                // Se o vizinho não foi visitado e a nova distância é menor, atualize
                if (!visited[neighbor] && distances[currentNode] + weight < distances[neighbor]) {
                    distances[neighbor] = distances[currentNode] + weight;
                    pq.add(neighbor); // Adiciona o vizinho à fila de prioridade

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
            System.out.println("Vértice " + i + ": " + (distances[i] == Integer.MAX_VALUE ? "Infinito" : distances[i]));
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
