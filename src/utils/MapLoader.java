package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {
    public static int[][] loadMapFromFile(String filename, int width, int height) throws IOException {
        int[][] grid = new int[height][width];

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < height; i++) {
                String[] values = br.readLine().split(" ");
                for (int j = 0; j < width; j++) {
                    if (values[j].equals("X")) {
                        grid[i][j] = -1; // Representa un acantilado
                    } else {
                        grid[i][j] = Integer.parseInt(values[j]);
                    }
                }
            }
        }
        return grid;
    }
}
