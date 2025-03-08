package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapGrid {
    private int[][] grid;
    private int width, height;

    public MapGrid(String filename) throws IOException {
        loadMap(filename);
    }

    private void loadMap(String filename) throws IOException {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                int[] row = Arrays.stream(line.split(" "))
                        .mapToInt(s -> s.equals("X") ? -1 : Integer.parseInt(s))
                        .toArray();
                rows.add(row);
            }
        }
        height = rows.size();
        width = rows.get(0).length;
        grid = rows.toArray(new int[height][width]);
    }

    public int getHeight(int x, int y) {
        return grid[y][x];
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height && grid[y][x] != -1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
