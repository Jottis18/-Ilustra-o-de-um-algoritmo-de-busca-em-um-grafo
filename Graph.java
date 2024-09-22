import java.util.*;

public class Graph {
    private int vertices;
    private LinkedList<Integer> adjList[];

    // Construtor
    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Adiciona uma aresta ao grafo
    public void addEdge(int src, int dest) {
        if (src >= 0 && src < vertices && dest >= 0 && dest < vertices) {
            adjList[src].add(dest);
            adjList[dest].add(src); // Grafo não direcionado
        } else {
            System.out.println("Erro: vértices fora dos limites (" + src + ", " + dest + ")");
        }
    }

    public LinkedList<Integer>[] getAdjList() {
        return adjList;
    }

    public int getVertices() {
        return vertices;
    }
}
