package models;

public class Node implements Comparable<Node> {
    int x, y;
    double g, h;
    Node parent;
    private static boolean useAStar = false;

    public Node(int x, int y, double g, double h, Node parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    public static void setUseAStar(boolean value) {
        useAStar = value;
    }

    @Override
    public int compareTo(Node other) {
        if (useAStar) {
            return Double.compare(this.g + this.h, other.g + other.h); // A* (f = g + h)
        } else {
            return Double.compare(this.h, other.h); // Best-First (h only)
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getF() {
        return g + h;
    }
}