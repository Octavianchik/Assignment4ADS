import java.util.*;

public class Search<V> {
    protected Set<V> marked;
    protected Map<V, V> edgeTo;
    protected final V source;

    public Search(V source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(V v) {
        return marked.contains(v);
    }

    public Iterable<V> pathTo(V v) {
        if (!hasPathTo(v)) return null;

        LinkedList<V> ls = new LinkedList<>();
        for (V i = v; !i.equals(source); i = edgeTo.get(i)) {
            ls.push(i); // inverted adding
        }

        ls.push(source);
        return ls;
    }
}