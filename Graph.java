import java.util.*;

// Classe para representar uma aresta com destino e peso
class Edge {
    int dest;
    int weight;

    // Construtor
    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

public class Graph {
    private int vertices;
    private LinkedList<Edge> adjList[];

    // Construtor
    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public boolean edgeExists(int src, int dest) {
        for (Edge edge : adjList[src]) {
            if (edge.dest == dest) {
                return true; // A aresta já existe
            }
        }
        return false;
    }

    // Adiciona uma aresta ao grafo
    public boolean addEdge(int src, int dest, int weight) {
        if (src >= 0 && src < vertices && dest >= 0 && dest < vertices) {
            for (Edge edge : adjList[src]) {
                if (edge.dest == dest) {
                    System.out.println("Erro: Aresta já existe entre " + src + " e " + dest);
                    return false;
                }
            }
            adjList[src].add(new Edge(dest, weight));
            adjList[dest].add(new Edge(src, weight)); // Grafo não direcionado
            return true;
        } else {
            System.out.println("Erro: vértices fora dos limites (" + src + ", " + dest + ")");
            return false;
        }
    }

    public LinkedList<Edge>[] getAdjList() {
        return adjList;
    }

    public int getVertices() {
        return vertices;
    }
}
