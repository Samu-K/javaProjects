import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> list;

    public ArrayNode() {
        this.list = new ArrayList<>();
    }

    public void add(Node node) {
        list.add(node);
    }

    public int size() {
        return list.size();
    }

    public Node get(int index) {
        return list.get(index);
    }

    public Iterator<Node> iterator() {
        return list.iterator();
    }

    public void printJson() {
        int size = size();
        System.out.print("[");
        if (size > 0) {
            System.out.println();
            for (int i = 0; i < size; i++) {
                Node node = get(i);
                indent();
                node.printJson();
                if (i < size - 1) {
                    System.out.println(",");
                } else {
                    System.out.println();
                }
            }
            indent(-1);
        }
        indent();
        System.out.print("]");
    }

    private void indent() {
        indent(1);
    }

    private void indent(int delta) {
        for (int i = 0; i < delta; i++) {
            System.out.print("  ");
        }
    }
}

