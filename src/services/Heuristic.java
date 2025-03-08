package services;

public interface Heuristic {
    double calculateManhattanHeight(int x, int y, int goalX, int goalY, int height, int goalHeight);
    double calculateHeightDifference(int x, int y, int goalX, int goalY, int height, int goalHeight);
    double calculateMovementTime(int x, int y, int goalX, int goalY, int height, int goalHeight);
}
