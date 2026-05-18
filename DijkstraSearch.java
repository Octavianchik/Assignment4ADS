import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Set<V> unsettledNodes;
    private final Map<V, Double> distances;
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;

        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            V currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            Map<Vertex<V>, Double> neighbors = graph.getAdjacentVerticesWithWeights(currentNode);
            if (neighbors == null) continue;

            for (Map.Entry<Vertex<V>, Double> entry : neighbors.entrySet()) {
                V neighborData = entry.getKey().getData();
                double weight = entry.getValue();

                // Skip processing if we've already settled this node
                if (marked.contains(neighborData)) continue;

                double newDistance = getShortestDistance(currentNode) + weight;

                if (getShortestDistance(neighborData) > newDistance) {
                    distances.put(neighborData, newDistance);
                    edgeTo.put(neighborData, currentNode);
                    unsettledNodes.add(neighborData);
                }
            }
        }
    }

    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
                continue;
            }

            if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(V destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}