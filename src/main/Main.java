package main;

import algorithms.AStar;
import utils.MapGrid;
import models.Node;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        MapGrid mapa = new MapGrid("src/data/matrix.txt");
        Node inici = new Node(0, 0, 0, 0, null);
        Node fi = new Node(9, 9, 0, 0, null);
        
        List<Node> cami = AStar.search(inici, fi, mapa, 1);
        if (cami != null) {
            System.out.println("La solució de l'A* és:");
            for (Node node : cami) {
                System.out.println("(" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("No s'ha trobat cap camí.");
        }
        MapGrid.printPathOnGrid(mapa.getGridMatrix(), cami);
        System.out.println("El cost total de l'A* per arribar al destí és de: " + MapGrid.getFinalCost());
        System.out.println("El nombre d'iteracions de l'A* és de: " + AStar.getNumIterations());
    }
}