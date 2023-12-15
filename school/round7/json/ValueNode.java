public class ValueNode extends Node {
    private Object value;

    public ValueNode() {
        this.value = null;
    }

    public ValueNode(double value) {
        this.value = value;
    }

    public ValueNode(boolean value) {
        this.value = value;
    }

    public ValueNode(String value) {
        this.value = value;
    }

    public boolean isNumber() {
        return value instanceof Double;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isNull() {
        return value == null;
    }

    public double getNumber() {
        return (Double) value;
    }

    public boolean getBoolean() {
        return (Boolean) value;
    }

    public String getString() {
        return (String) value;
    }

    public Object getNull() {
        return value;
    }

    public void printJson() {
        if (isNull()) {
            System.out.print("null");
        } else if (isNumber()) {
            System.out.print(numberToString(getNumber()));
        } else if (isBoolean()) {
            System.out.print(getBoolean());
        } else if (isString()) {
            System.out.print("\"" + getString() + "\"");
        }
    }

    private static String numberToString(Double d) {
        String str = Double.toString(d);
        if (str.endsWith(".0")) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }
}

