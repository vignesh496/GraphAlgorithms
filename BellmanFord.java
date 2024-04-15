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

        BellmanFord.shortestPath(graph, 0);
        
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

class BellmanFord  {
        static void shortestPath(Graph graph, int s) {
        int[] dist = new int[graph.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        for (int i = 1; i < graph.V; i++) {
            for (Edge e: graph.adj) {
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.v] > dist[e.u] + e.w) 
                    dist[e.v] = dist[e.u] + e.w;
            }
        }

        for (Edge e : graph.adj) {
            if (dist[e.u] != Integer.MAX_VALUE && dist[e.v] > dist[e.u] + e.w) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < graph.V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }
}
