package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import models.Node;

public class MapGrid {
    private int[][] grid;
    private int width, height;
    public static double finalCost = 0;

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

    public int[][] getGridMatrix() {
        int rows = this.getWidth();
        int cols = this.getHeight();
        int[][] matrix = new int[rows][cols];
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = this.getHeight(i, j);
            }
        }
    
        return matrix;
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

    public static List<Node> reconstructPath(Node node, MapGrid map) {
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

    public static double getFinalCost() {
        return finalCost;
    }
}