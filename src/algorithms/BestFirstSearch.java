package algorithms;

import models.Node;
import services.Heuristic;

import java.util.*;

public class BestFirstSearch {
    private static final int[][] MOVES = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static double finalCost = 0;
    public static int numIterations = 0;


    public static List<Node> bestFirstSearch(int[][] map, int startX, int startY, int endX, int endY, Heuristic heuristic, int heuristicType) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Node> openList = new PriorityQueue<>();

        Node startNode = new Node(startX, startY, 0,
                selectHeuristic(startX, startY, endX, endY, 0, map[startX][startY], map[endX][endY], heuristic, heuristicType), null);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            numIterations++;
            Node current = openList.poll();

            if (current.getX() == endX && current.getY() == endY) {
                return reconstructPath(current, map);
            }

            visited[current.getX()][current.getY()] = true;

            for (int[] move : MOVES) {
                int newX = current.getX() + move[0];
                int newY = current.getY() + move[1];

                if (isValid(newX, newY, rows, cols, visited, openList)) {
                    int newAltura = map[newX][newY];
                    double g = current.getG() + computeCost(current, newX, newY, map);
                    double h = selectHeuristic(newX, newY, endX, endY, g, newAltura, map[endX][endY], heuristic, heuristicType);
                    Node neighbor = new Node(newX, newY, g, h, current);
                    openList.add(neighbor);
                }
            }
        }
        return null;
    }

    private static double selectHeuristic(int x, int y, int goalX, int goalY, double g, int height, int goalHeight, Heuristic heuristic, int heuristicType) {
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

    private static double computeCost(Node current, int newX, int newY, int[][] map) {
        int currentHeight = map[current.getX()][current.getY()];
        int newHeight = map[newX][newY];
        return (newHeight > currentHeight) ? 1 + (newHeight - currentHeight) : 1;
    }

    private static boolean isValid(int x, int y, int rows, int cols, boolean[][] visited, PriorityQueue<Node> openList) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y]) {
            return false;
        }

        for (Node node : openList) {
            if (node.getX() == x && node.getY() == y) {
                return false;
            }
        }
        return true;
    }

    public static void printPathOnGrid(int[][] grid, List<Node> path) {
        // Creem una còpia de la matriu per no modificar la original
        String[][] visualGrid = new String[grid.length][grid[0].length];

        // Omplim la matriu amb els valors originals, canviant -1 per "X"
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                visualGrid[i][j] = (grid[i][j] == -1) ? "X" : String.valueOf(grid[i][j]);
            }
        }

        // Marquem el camí amb "*"
        for (Node node : path) {
            visualGrid[node.getX()][node.getY()] = "*";
        }

        // Imprimim la matriu
        for (int i = 0; i < visualGrid.length; i++) {
            for (int j = 0; j < visualGrid[0].length; j++) {
                System.out.print(visualGrid[i][j] + "   ");
            }
            System.out.println();
        }
    }


    private static List<Node> reconstructPath(Node node, int[][] map) {
        List<Node> path = new ArrayList<>();
       finalCost = 0;
        while (node != null) {
            path.add(node);
            if (node.getParent() != null) {
                int actualHeight = map[node.getParent().getX()] [node.getParent().getY()];
                int newHeight = map[node.getX()] [node.getY()];
                if (newHeight >= actualHeight) {
                    finalCost += 1 + (newHeight - actualHeight);
                } else {
                    finalCost += 0.5;
                }
            }
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    public int getHeight(int [][] map, int x, int y) {
        return map[y][x];
    }



}