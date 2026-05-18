import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {
    public BreadthFirstSearch(UnweightedGraph<V> graph, V source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(UnweightedGraph<V> graph, V current) {
        marked.add(current);

        Queue<V> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            V v = queue.remove();

            for (V neighbor : graph.adjacencyList(v)) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, v);
                    queue.add(neighbor);
                }
            }
        }
    }
}