import java.util.*;

public class UnweightedGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> map = new HashMap<>();

    public UnweightedGraph() {
        this(true);
    }

    public UnweightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V data) {
        map.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest) {
        addVertex(source);
        addVertex(dest);

        Vertex<V> sourceVertex = map.get(source);
        Vertex<V> destVertex = map.get(dest);

        sourceVertex.addAdjacentVertex(destVertex, 0D);

        if (undirected) {
            destVertex.addAdjacentVertex(sourceVertex, 0D);
        }
    }

    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;

        List<V> list = new LinkedList<>();
        for (Vertex<V> neighbor : map.get(v).getAdjacentVertices().keySet()) {
            list.add(neighbor.getData());
        }
        return list;
    }
}