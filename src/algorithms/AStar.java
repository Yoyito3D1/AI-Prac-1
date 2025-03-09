package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import utils.MapGrid;
import heuristics.HeuristicImplementation;
import models.Node;

public class AStar {
    public static double finalCost = 0;
    public static int numIterations = 0;

    public static List<Node> search(Node start, Node end, MapGrid map, int heuristic) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        frontier.add(start);

        while (!frontier.isEmpty()) {
            numIterations++;

            Node actual = frontier.poll();
            if (actual.getX() == end.getX() && actual.getY() == end.getY()) {
                return reconstructPath(actual, map);
            }

            visited.add(actual.getX() + "," + actual.getY());

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = actual.getX() + dir[0];
                int newY = actual.getY() + dir[1];
                if (map.isValid(newX, newY) && !visited.contains(newX + "," + newY)) {
                    double nouG = actual.getG() + computeCost(actual, newX, newY, map);
                    double nouH = computeHeuristic(newX, newY, end.getX(), end.getY(), map, heuristic);
                    frontier.add(new Node(newX, newY, nouG, nouH, actual));
                }
            }
        }
        return null; // No s'ha trobat cap camí
    }

    private static double computeCost(Node actual, int newX, int newY, MapGrid map) {
        int actualHeight = map.getHeight(actual.getX(), actual.getY());
        int newHeight = map.getHeight(newX, newY);
        finalCost = newHeight + finalCost
;
        return newHeight > actualHeight ? 1 + (newHeight - actualHeight) : 1;
    }

    private static double computeHeuristic(int x, int y, int xf, int yf, MapGrid map, int heuristic) {
        Node actual = new Node(x, y, 0, 0, null);
        Node desti = new Node(xf, yf, 0, 0, null);
        HeuristicImplementation heuristicImpl = new HeuristicImplementation();
        switch (heuristic) {
            case 1: return heuristicImpl.calculateManhattanHeight(actual.getX(), actual.getY(), desti.getX(), desti.getY(), map.getHeight(x, y), map.getHeight(xf, yf));
            case 2: return heuristicImpl.calculateHeightDifference(actual.getX(), actual.getY(), desti.getX(), desti.getY(), map.getHeight(x, y), map.getHeight(xf, yf));
            case 3: return heuristicImpl.calculateMovementTime(actual.getX(), actual.getY(), desti.getX(), desti.getY(), map.getHeight(x, y), map.getHeight(xf, yf));
            default: return 0;
        }
    }

    public static double getFinalCost() {
        return finalCost;
    }

    public static int getNumIterations() {
        return numIterations;
    }

    private static List<Node> reconstructPath(Node node, MapGrid map) {
        List<Node> cami = new ArrayList<>();
        finalCost = 0;  // Restartalitzem abans de calcular el cost endnal
    
        while (node != null) {
            cami.add(node);
            if (node.getParent() != null) {
                int actualHeight = map.getHeight(node.getParent().getX(), node.getParent().getY());
                int newHeight = map.getHeight(node.getX(), node.getY());
    
                if (newHeight >= actualHeight) {
                    finalCost += 1 + (newHeight - actualHeight);
                } else {
                    finalCost += 0.5;
                }
            }
            node = node.getParent();
        }
        Collections.reverse(cami);
        return cami;
    }
    
}