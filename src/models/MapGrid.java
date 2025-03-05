package models;

import utils.MapLoader;
import java.io.IOException;

public class MapGrid {
    private int width, height;
    private int[][] grid;

    public MapGrid(String filename, int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.grid = MapLoader.loadMapFromFile(filename, width, height);
    }

    public void printMap() {
        System.out.println("PrintGrid:");
        System.out.println("==============");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
