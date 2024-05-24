import java.util.*;

class Main {
    public static void main(String[] args)  {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, -4); // Negative weight edge
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 1, 1); // Edge that completes the negative weight cycle
        graph.addEdge(1, 4, 3);
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
        int[] pred = new int[graph.V];
        Arrays.fill(pred, -1);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        for (int i = 1; i < graph.V; i++) {
            for (Edge e: graph.adj) {
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.v] > dist[e.u] + e.w) {
                    dist[e.v] = dist[e.u] + e.w;
                    pred[e.v] = e.u;
                }
            }
        }

        for (Edge e : graph.adj) {
            if (dist[e.u] != Integer.MAX_VALUE && dist[e.v] > dist[e.u] + e.w) {
                System.out.println("Graph contains negative weight cycle");
                printCycle(pred, e.v, graph.V);
                return;
            }
        }

        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < graph.V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }

    public static void printCycle(int[] pred, int start, int V) {
        boolean[] visited = new boolean[V];
        int current = start;
        
        for (int i = 0; i < V; i++) 
            current = pred[current];
        
        int cycleStart = current;
        List<Integer> cycle = new ArrayList<>();
        
        do {
            cycle.add(current);
            current = pred[current];
        } while (current != cycleStart);
        
        cycle.add(cycleStart);  
        Collections.reverse(cycle);
        System.out.println("Negative weight cycle: " + cycle);
    }
}
