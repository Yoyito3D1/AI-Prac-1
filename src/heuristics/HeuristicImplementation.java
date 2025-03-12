package heuristics;

import models.Node;
import services.Heuristic;
import utils.MapGrid;

public class HeuristicImplementation implements Heuristic {
    @Override
    public double calculateManhattanHeight(int x, int y, int goalX, int goalY, int height, int goalHeight) {
        return Math.abs(goalX - x) + Math.abs(goalY - y) + Math.abs(goalHeight - height);
    }

    @Override
    public double calculateHeightDifference(int x, int y, int goalX, int goalY, int height, int goalHeight) {
        return Math.abs(goalHeight - height);
    }

    @Override
    public double calculateMovementTime(int x, int y, int goalX, int goalY, int height, int goalHeight) {
        return Math.max(1, goalHeight - height) + Math.abs(goalX - x) + Math.abs(goalY - y);
    }

    public static double computeHeuristic(int x, int y, int xf, int yf, MapGrid map, int heuristic) {
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
}