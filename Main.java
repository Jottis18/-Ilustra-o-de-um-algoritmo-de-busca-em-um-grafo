import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o número de vértices (5 <= n <= 25): ");
        int vertices = sc.nextInt();

        // Verifica se o número de vértices está no intervalo correto
        while (vertices < 5 || vertices > 25) {
            System.out.println("Número inválido! O número de vértices deve estar entre 5 e 25. Digite novamente: ");
            vertices = sc.nextInt();
        }

        Graph graph = new Graph(vertices);

        System.out.println("Digite o número de arestas: ");
        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.println("Digite a origem e o destino da aresta " + (i + 1) + ": ");
            int src = sc.nextInt();
            int dest = sc.nextInt();

            // Tenta adicionar a aresta e avisa se houver um erro
            graph.addEdge(src, dest);
        }

        // Cria o JFrame e o painel do grafo
        JFrame frame = new JFrame("Visualização do Grafo com BFS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        
        GraphPanel graphPanel = new GraphPanel(graph);
        frame.add(graphPanel);
        frame.setVisible(true);

        System.out.println("Digite o vértice inicial para a busca: ");
        int startNode = sc.nextInt();

        // Verifica se o vértice inicial está dentro dos limites
        if (startNode >= 0 && startNode < vertices) {
            BFS bfs = new BFS(graph, graphPanel);
            System.out.println("Resultado da busca em largura (BFS): ");
            bfs.bfsSearch(startNode);
        } else {
            System.out.println("Erro: vértice inicial fora dos limites!");
        }
    }
}
