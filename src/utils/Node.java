package utils;

public class Node implements Comparable<Node> {
    int x, y;
    double g, h;
    Node parent;

    public Node(int x, int y, double g, double h, Node parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    public double getF() {
        return g + h;
    }

    public int getx() {
        return x;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public Node getParent() {
        return parent;
    }

    public int gety() {
        return y;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.getF(), other.getF());
    }
}
