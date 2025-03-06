package utils;

public class Cell {
    int x, y;
    int height;
    boolean isCliff; // Indica si és un precipici

    public Cell(int x, int y, int height, boolean isCliff) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.isCliff = isCliff;
    }
}
