package algorithms;

import models.Node;
import services.Heuristic;


import java.util.*;

public class BestFirstSearch {
    private static final int[][] MOVES = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static List<Node> bestFirstSearch(int[][] map, int startX, int startY, int endX, int endY, Heuristic heuristic, int heuristicType) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Node> openList = new PriorityQueue<>();

        Node startNode = new Node(startX, startY, map[startX][startY],
                selectHeuristic(startX, startY, endX, endY, map[startX][startY], map[endX][endY], heuristic, heuristicType), null);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current.getX() == endX && current.getY() == endY) {
                return reconstructPath(current);
            }

            visited[current.getX()][current.getY()] = true;

            for (int[] move : MOVES) {
                int newX = current.getX() + move[0];
                int newY = current.getY() + move[1];

                if (isValid(newX, newY, rows, cols, visited)) {
                    int newAltura = map[newX][newY];
                    double h = selectHeuristic(newX, newY, endX, endY, newAltura, map[endX][endY], heuristic, heuristicType);
                    Node neighbor = new Node(newX, newY, newAltura, h, current);
                    openList.add(neighbor);
                }
            }
        }
        return null;
    }

    private static double selectHeuristic(int x, int y, int goalX, int goalY, int height, int goalHeight, Heuristic heuristic, int heuristicType) {
        switch (heuristicType) {
            case 1:
                return heuristic.calculateManhattanHeight(x, y, goalX, goalY, height, goalHeight);
            case 2:
                return heuristic.calculateHeightDifference(x, y, goalX, goalY, height, goalHeight);
            case 3:
                return heuristic.calculateMovementTime(x, y, goalX, goalY, height, goalHeight);
            default:
                throw new IllegalArgumentException("Invalid heuristic type");
        }
    }

    private static boolean isValid(int x, int y, int rows, int cols, boolean[][] visited) {
        return x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y];
    }

    private static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}