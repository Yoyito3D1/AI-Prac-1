import java.io.*;

public class MapGrid {
    private int width, height;
    private int[][] grid;

    public MapGrid(String filename) throws IOException {
        loadFromFile(filename);
    }

    private void loadFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] dimensions = br.readLine().split(" ");
            this.width = Integer.parseInt(dimensions[0]);
            this.height = Integer.parseInt(dimensions[1]);
            this.grid = new int[width][height];

            for (int i = 0; i < height; i++) {
                String[] values = br.readLine().split(" ");
                for (int j = 0; j < width; j++) {
                    grid[i][j] = Integer.parseInt(values[j]);
                }
            }
        }
    }

    public void printMap() {
        System.out.println("Mapa carregat:");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}

