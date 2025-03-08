package heuristics;

import services.Heuristic;

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
}