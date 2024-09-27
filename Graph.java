import java.util.*;

/**
 * Classe para representar uma aresta com destino e peso.
 * Esta classe é utilizada para armazenar a aresta e o peso no grafo.
 */
class Edge {
    int dest;
    int weight;

    /**
     * Construtor da classe Edge.
     *
     * @param dest O vértice de destino da aresta.
     * @param weight O peso da aresta.
     */
    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

/**
 * Classe Graph que representa um grafo com vértices e arestas.
 * As arestas podem ser ponderadas e o grafo é não-direcionado.
 */
public class Graph {
    private int vertices;
    private LinkedList<Edge> adjList[];

    /**
     * Método para adicionar vértices ao grafo.
     *
     * @param vertices O número de vértices a serem adicionados ao grafo.
     */
    public void addVertices(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * Verifica se uma aresta já existe entre dois vértices.
     *
     * @param src O vértice de origem.
     * @param dest O vértice de destino.
     * @return true se a aresta já existir, caso contrário false.
     */
    public boolean edgeExists(int src, int dest) {
        for (Edge edge : adjList[src]) {
            if (edge.dest == dest) {
                return true; // Aresta já existe
            }
        }
        return false;
    }

    /**
     * Adiciona uma aresta ao grafo.
     *
     * @param src O vértice de origem.
     * @param dest O vértice de destino.
     * @param weight O peso da aresta.
     * @return true se a aresta for adicionada com sucesso, caso contrário false.
     */
    public boolean addEdge(int src, int dest, int weight) {
        if (src >= 0 && src < vertices && dest >= 0 && dest < vertices) {
            for (Edge edge : adjList[src]) {
                if (edge.dest == dest) {
                    System.out.println("Erro: Aresta já existe entre " + src + " e " + dest);
                    return false;
                }
            }
            adjList[src].add(new Edge(dest, weight));
            adjList[dest].add(new Edge(src, weight)); // Grafo não-direcionado
            return true;
        } else {
            System.out.println("Erro: vértices fora dos limites (" + src + ", " + dest + ")");
            return false;
        }
    }

    /**
     * Retorna a lista de adjacência do grafo.
     *
     * @return O array de listas de adjacência.
     */
    public LinkedList<Edge>[] getAdjList() {
        return adjList;
    }

    /**
     * Retorna o número de vértices no grafo.
     *
     * @return O número de vértices.
     */
    public int getVertices() {
        return vertices;
    }
}
