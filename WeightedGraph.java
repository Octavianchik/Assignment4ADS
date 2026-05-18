import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V data) {
        map.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        addVertex(source);
        addVertex(dest);

        Vertex<V> sourceVertex = map.get(source);
        Vertex<V> destVertex = map.get(dest);

        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (undirected) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
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

    // New helper specific for Dijkstra to get neighbors AND their weights
    public Map<Vertex<V>, Double> getAdjacentVerticesWithWeights(V v) {
        if (!hasVertex(v)) return null;
        return map.get(v).getAdjacentVertices();
    }
}