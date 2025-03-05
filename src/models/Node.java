package models;

public class Node implements Comparable<Node> {
    Cell cell;
    Node parent;
    double gCost; // Cost acumulat
    double hCost; // Heurística
    double fCost; // f = g + h

    public Node(Cell cell, Node parent, double gCost, double hCost) {
        this.cell = cell;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.fCost, other.fCost);
    }
}
