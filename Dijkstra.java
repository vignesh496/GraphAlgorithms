import java.util.*;

class Dijkstra {
    
    static List<Edge> paths = null;
    
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

        int[] dist = shortestPath(graph, 1);
        for (int i = 0; i < dist.length; i++)  {
            System.out.println(i + " -> " + dist[i]);
        }
        System.out.println("\nIntermediary paths\nu --- v\t\tw");
        for (Edge e : paths)    {
            System.out.println(e.u + " --- " + e.v + "\t\t" + e.w);
        }
    }
    
    public static int[] shortestPath(Graph graph, int s) {
        int[] dist = new int[graph.V];
        boolean[] visited = new boolean[graph.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        pq.offer(new Edge(s, s, 0));

        paths = new ArrayList<>();
        while (!pq.isEmpty()) {
            Edge min = pq.poll();
            int u = min.v;

            if (visited[u]) 
                continue;
                
            visited[u] = true;

            for (Edge e : graph.adj) {
                if (e.u == u && dist[e.v] > dist[u] + e.w) {
                    dist[e.v] = dist[u] + e.w;
                    pq.offer(new Edge(u, e.v, dist[e.v]));
                    paths.add(e);
                }
            }
        }

        return dist;
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

