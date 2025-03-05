package main;

import models.MapGrid;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String mapFile = "data/matrix.txt";
        int width = 10;
        int height = 10;

        try {
            MapGrid map = new MapGrid(mapFile, width, height);
            System.out.println("Loaded Map:");
            map.printMap();



        } catch (IOException e) {
            System.err.println("Error loading the map: " + e.getMessage());
        }
    }
}
