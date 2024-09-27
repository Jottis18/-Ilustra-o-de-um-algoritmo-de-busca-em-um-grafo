import java.util.Scanner;
import java.util.Random;

public class GraphControler {
    public static void GraphInput(Scanner sc, Graph graph){
        System.out.println("Olá! Bem vindo(a)");
        System.out.println("Você deseja:");

        System.out.println("[0] Gerar um grafo aleatório");
        System.out.println("[1] Cadastrar meu próprio grafo");

        int choice = sc.nextInt();

        System.out.println("Seu grafo será:");
        System.out.println("[0] Não Ponderado");
        System.out.println("[1] Ponderado");
        int ponderado = sc.nextInt();

        while (ponderado != 1 && ponderado != 0) {
            System.out.println("Digite um valor válido:");
            ponderado = sc.nextInt();
        }

        System.out.println("Digite o número de vértices (5 <= n <= 25): ");
        int vertices = sc.nextInt();

        // Verifica se o número de vértices está no intervalo correto
        while (vertices < 5 || vertices > 25) {
            System.out.println("Número inválido! O número de vértices deve estar entre 5 e 25. Digite novamente: ");
            vertices = sc.nextInt();
        }

        graph.addVertices(vertices);

        // Gerar grafo aleatório ou manualmente
        if (choice == 0) {
            System.out.println("Digite o número de arestas a serem geradas aleatoriamente: ");
            int edges = sc.nextInt();
            generateRandomGraph(graph, vertices, edges, ponderado);
        } else {
            System.out.println("Digite o número de arestas: ");
            int edges = sc.nextInt();

            for (int i = 0; i < edges; i++) {
                if (i == 0) {
                    if (ponderado == 1) {
                        System.out.println("Exemplo: 0 1 3 (Para conectar a aresta do vértice 0 ao 1 com peso 3)");
                    } else {
                        System.out.println("Exemplo: 0 1 (Para conectar a aresta do vértice 0 ao 1)");
                    }
                    System.out.println("Deixar o peso em branco fará com que seja 1");
                }
                System.out.println("\n");
                if (ponderado == 1) {
                    System.out.println("Digite a origem, destino e peso, nesta ordem, para a aresta " + (i + 1) + ": ");
                } else {
                    System.out.println("Digite a origem e destino para a aresta " + (i + 1) + ": ");
                }

                int src = sc.nextInt();
                int dest = sc.nextInt();
                int weight;

                if (ponderado == 1) {
                    weight = sc.nextInt();
                } else {
                    weight = 1;
                }

                // Tenta adicionar a aresta e avisa se houver um erro
                boolean added = graph.addEdge(src, dest, weight);

                if (!added) {
                    i -= 1;
                }
            }
        }    
    }

    public static void GraphSearchInput(Scanner sc, Graph graph, GraphPanel graphPanel){
        System.out.println("Digite o vértice inicial para a busca: ");
        int startNode = sc.nextInt();

        int vertices = graph.getVertices();
        // Verifica se o vértice inicial está dentro dos limites
        if (startNode >= 0 && startNode < vertices) {
            System.out.println("Digite o algoritmo que quer executar");
            System.out.println("[0] BFS (Busca em Largura)");
            System.out.println("[1] DFS (Busca em Profundidade)");
            System.out.println("[2] Algoritmo de Dijkstra");
            int algorithm = sc.nextInt();
            switch (algorithm) {
                case 0:
                    BFS bfs = new BFS(graph, graphPanel);
                    System.out.println("Resultado da busca em largura (BFS): ");
                    bfs.bfsSearch(startNode);
                    break;
                case 1:
                    DFS dfs = new DFS(graph, graphPanel);
                    System.out.println("Resultado da busca em profundidade (DFS): ");
                    dfs.dfsSearch(startNode);
                    break;
                case 2:
                    Dijkstra dij = new Dijkstra(graph, graphPanel);
                    System.out.println("Resultado da busca Dijkstra: ");
                    dij.dijkstraSearch(startNode);
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Erro: vértice inicial fora dos limites!");
        }
    }

    // Método para gerar um grafo aleatório
    private static void generateRandomGraph(Graph graph, int vertices, int edges, int ponderado) {
        Random rand = new Random();
        for (int i = 0; i < edges; i++) {
            int src = rand.nextInt(vertices);
            int dest = rand.nextInt(vertices);

            // Certifica-se de que não está criando uma aresta entre o mesmo vértice
            while (src == dest || graph.edgeExists(src, dest)) { // Verifica se a aresta já existe
                src = rand.nextInt(vertices);
                dest = rand.nextInt(vertices);
            }

            int weight = 1; // Peso padrão para grafos não ponderados
            if (ponderado == 1) {
                weight = rand.nextInt(10) + 1; // Peso aleatório entre 1 e 10
            }

            graph.addEdge(src, dest, weight); // Adiciona a aresta sem a necessidade de verificação extra
        }
    }
}
