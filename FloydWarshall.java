import java.util.*;

class Main {
    public static void main(String[] args)  {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 3, 6);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 8);

        int[][] matrix = FloydWarshall.shortestPath(FloydWarshall.list_to_matrix(graph));
        
        for (int i = 0; i < FloydWarshall.V; i++)   {
            for (int j = 0; j < FloydWarshall.V; j++)   {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        
    }
}

class Edge {
    int u, v, w;
    Edge(int u, int v, int w)   {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class Graph {
    int V;
    List<Edge> adj;
    Graph(int V)    {
        this.V = V;
        adj = new ArrayList<>();
    }
    void addEdge(int u, int v, int w)   {
        adj.add(new Edge(u, v, w));
        adj.add(new Edge(v, u, w));
    }
}

class FloydWarshall {
    static int V = 0;
    public static int[][] list_to_matrix(Graph graph)   {
        V = graph.V;
        int[][] adj = new int[graph.V][graph.V];
        for(Edge e : graph.adj) {
            adj[e.u][e.v] = e.w;
            adj[e.v][e.u] = e.w;
        }
        return adj;
    }
    public static int[][] shortestPath(int[][] adj)   {
        int[][] dist = new int[V][V];
        for (int u = 0; u < V; u++) {
            for(int v = 0; v < V; v++)  
                dist[u][v] = adj[u][v];
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }
}
