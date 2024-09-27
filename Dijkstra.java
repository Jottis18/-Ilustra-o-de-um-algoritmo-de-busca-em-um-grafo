import java.awt.Color;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Classe Dijkstra que implementa o algoritmo de Dijkstra para encontrar 
 * o caminho mais curto em um grafo ponderado.
 * A classe também atualiza a interface gráfica, exibindo a visualização 
 * do processo de busca.
 */
public class Dijkstra {
    private Graph graph;
    private GraphPanel panel;

    /**
     * Construtor da classe Dijkstra.
     *
     * @param graph O grafo em que o algoritmo de Dijkstra será executado.
     * @param panel O painel onde a visualização dos nós será exibida.
     */
    public Dijkstra(Graph graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
    }

    /**
     * Executa o algoritmo de Dijkstra a partir de um nó inicial para calcular
     * o caminho mais curto até os outros nós do grafo.
     *
     * @param startNode O nó inicial para começar o algoritmo.
     */
    public void dijkstraSearch(int startNode) {
        int vertices = graph.getVertices();
        boolean[] visited = new boolean[vertices];
        int[] distances = new int[vertices];
        
        // Inicializa as distâncias como infinitas, exceto para o nó inicial
        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startNode] = 0;

        // Fila de prioridade para selecionar o vértice com a menor distância
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));
        pq.add(startNode);

        // Colore o nó inicial como vermelho para indicar que foi processado
        panel.setNodeColor(startNode, Color.RED);
        panel.repaint();

        // Enquanto houver nós na fila de prioridade
        while (!pq.isEmpty()) {
            int currentNode = pq.poll(); // Remove o nó com a menor distância

            // Se o nó já foi visitado, pula para o próximo
            if (visited[currentNode]) {
                continue;
            }

            visited[currentNode] = true;
            panel.setNodeColor(currentNode, Color.GREEN); // Nó processado completamente
            panel.repaint();

            // Pausa para visualização
            sleep(1000);

            // Itera sobre os vizinhos do nó atual
            for (Edge edge : graph.getAdjList()[currentNode]) {
                int neighbor = edge.dest;
                int newDist = distances[currentNode] + edge.weight;

                // Atualiza a distância se for menor
                if (newDist < distances[neighbor]) {
                    distances[neighbor] = newDist;
                    pq.add(neighbor);

                    // Colore o vizinho como vermelho (em processamento)
                    panel.setNodeColor(neighbor, Color.RED);
                    panel.repaint();
                }
            }
        }
    }

    /**
     * Função auxiliar para pausar a execução por um tempo.
     *
     * @param millis O tempo de pausa em milissegundos.
     */
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
