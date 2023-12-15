import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class ObjectNode extends Node implements Iterable<String> {

    private Map<String, Node> map;

    public ObjectNode() {
        map = new HashMap<>();
    }

    public Node get(String key) {
        return map.get(key);
    }

    public void set(String key, Node node) {
        map.put(key, node);
    }

    public int size() {
        return map.size();
    }

    @Override
    public Iterator<String> iterator() {
        return new TreeSet<>(map.keySet()).iterator();
    }
}

