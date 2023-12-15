package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class for representing a JSON array.
*/
public class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> list;

    /**
     * Constructs an initially empty JSON array node.
    */
    public ArrayNode() {
        this.list = new ArrayList<>();
    }

     /**
     * Returns the number of JSON nodes stored in this JSON array
     * @return the number of JSON nodes in this JSON array.
    */ 
    public int size() {
        return list.size();
    }

    /**
     * Adds a new JSON node to the end of this JSON array.
     * @param node the new JSON node to be added.
    */
    public void add(Node node) {
        list.add(node);
    }

    /**
     * @hidden
     */
    public Node get(int index) {
        return list.get(index);
    }

    /**
     * Returns a Node iterator that iterates the JSON nodes stored in this JSON array.
     * @return a Node iterator that iterates the JSON nodes stored in this JSON array.
    */
    public Iterator<Node> iterator() {
        return list.iterator();
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

