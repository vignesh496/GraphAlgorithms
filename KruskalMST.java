import java.util.*;

class KruskalMST {
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
        graph.addEdge(1, 3, 10); // Additional edge to make it disconnected

        for (Edge e : Kruskal.findMST(graph))    {
                System.out.println(e.u + " "+ e.v + " "+e.w);  
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

class Kruskal   {
    static int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }
    static void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }
    public static List<Edge> findMST(Graph graph)  {
        List<Edge> mst = new ArrayList<>();
        
        int[] parent = new int[graph.V];
        Arrays.fill(parent, -1);
        
        Collections.sort(graph.adj, Comparator.comparingInt(e -> e.w));
        for (Edge e : graph.adj)  {
            int x = find(parent, e.u);
            int y = find(parent, e.v);
            if (x != y) {
                mst.add(e);
                union(parent, x, y);
            }
        }
        return mst;
    }
}
