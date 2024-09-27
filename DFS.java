import java.awt.Color;
import java.util.Stack;

/**
 * Classe DFS que implementa a busca em profundidade (Depth-First Search) em um grafo.
 * Esta classe realiza a busca em um grafo e atualiza a interface gráfica 
 * durante a execução, mostrando os nós processados.
 */
public class DFS {
    private Graph graph;
    private GraphPanel panel;

    /**
     * Construtor da classe DFS.
     *
     * @param graph O grafo em que a busca será realizada.
     * @param panel O painel onde a visualização dos nós será exibida.
     */
    public DFS(Graph graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
    }

    /**
     * Realiza a busca em profundidade (DFS) a partir de um nó inicial.
     * A busca percorre todos os nós de forma recursiva.
     *
     * @param startNode O nó inicial para começar a busca.
     */
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

        // Colore o nó inicial como vermelho para mostrar que foi processado
        panel.setNodeColor(startNode, Color.RED);
        panel.repaint();

        // Enquanto há nós na pilha, continue processando
        while (!stack.isEmpty()) {
            int currentNode = stack.pop();

            // Colore o nó processado completamente como verde
            panel.setNodeColor(currentNode, Color.GREEN);
            panel.repaint();

            // Pausa para visualização
            sleep(1000);

            // Itera sobre os vizinhos do nó atual
            for (Edge edge : graph.getAdjList()[currentNode]) {
                int neighbor = edge.dest;

                // Se o vizinho não foi visitado, marca como visitado
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[currentNode] + 1;
                    stack.push(neighbor);

                    // Colore o nó vizinho como vermelho (em processamento)
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
