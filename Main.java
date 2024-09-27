import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner sc = new Scanner(System.in);
        GraphControler.GraphInput(sc, graph);

        // Cria o JFrame e o painel do grafo
        GraphPanel graphPanel = new GraphPanel(graph);
        GraphPanel.showGraph(graphPanel);
        System.out.println("Obrigado, Uma janela com a visualização do grafo foi aberta em seu sistema operacional, confira.");
        
        GraphControler.GraphSearchInput(sc, graph, graphPanel);
        sc.close();
    }
}
