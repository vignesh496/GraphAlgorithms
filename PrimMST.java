import java.util.*;

class PrimMST {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 3, 6);
        graph.addEdge(3, 4, 8);
        graph.addEdge(1, 3, 10); // Additional edge to make it disconnected

        int cost = 0; 
        System.out.println("u\tv\tw");  
        for (Edge e : Prim.findMST(graph, 4)) {
            System.out.println(e.u + "\t" + e.v + "\t" + e.w);  
            cost += e.w;
        }
        System.out.println("Minimum cost of edges : " + cost);
    }
}

class Edge {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class Graph {
    int V;
    List<Edge> adj;
    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
    }
    void addEdge(int u, int v, int w) {
        adj.add(new Edge(u, v, w));
        adj.add(new Edge(v, u, w));
    }
}

class Prim {
    public static List<Edge> findMST(Graph graph, int source) {
        List<Edge> mst = new ArrayList<>();
        boolean[] visited = new boolean[graph.V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        
        // Start from vertex 0
        for (Edge e : graph.adj) {
            if (e.u == source) {
                pq.offer(e);
            }
        }
        visited[source] = true;
        
        while (!pq.isEmpty()) {
            Edge min = pq.poll();
            int v = min.v;
            int w = min.w;
            
            if (visited[v]) 
                continue;

            mst.add(min);
            visited[v] = true;
            
            for (Edge e : graph.adj) {
                if (e.u == v && !visited[e.v]) {
                    pq.offer(e);
                }
            }
        }
        return mst;
    }
}
