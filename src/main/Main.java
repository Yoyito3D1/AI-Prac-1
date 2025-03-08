package main;

import models.AStar;
import utils.MapGrid;
import utils.Node;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        MapGrid mapa = new MapGrid("src/data/matrix.txt");
        Node inici = new Node(0, 0, 0, 0, null);
        Node fi = new Node(9, 9, 0, 0, null);
        
        List<Node> cami = AStar.cerca(inici, fi, mapa, 1);
        if (cami != null) {
            for (Node node : cami) {
                System.out.println("(" + node.getx() + ", " + node.gety() + ")");
            }
        } else {
            System.out.println("No s'ha trobat cap camí.");
        }
        System.out.println(AStar.getCostFinal());
    }
}