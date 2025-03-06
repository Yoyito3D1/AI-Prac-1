package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import utils.MapGrid;
import utils.Node;

public class AStar {
    public static List<Node> cerca(Node inici, Node fi, MapGrid mapa, int heuristica) {
        PriorityQueue<Node> frontera = new PriorityQueue<>();
        Set<String> visitats = new HashSet<>();
        frontera.add(inici);

        while (!frontera.isEmpty()) {
            Node actual = frontera.poll();
            if (actual.getx() == fi.getx() && actual.gety() == fi.gety()) {
                return reconstruirCami(actual);
            }

            visitats.add(actual.getx() + "," + actual.gety());

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int nouX = actual.getx() + dir[0];
                int nouY = actual.gety() + dir[1];
                if (mapa.isValid(nouX, nouY) && !visitats.contains(nouX + "," + nouY)) {
                    double nouG = actual.getG() + calcularCost(actual, nouX, nouY, mapa);
                    double nouH = calcularHeuristica(nouX, nouY, fi.getx(), fi.gety(), mapa, heuristica);
                    frontera.add(new Node(nouX, nouY, nouG, nouH, actual));
                }
            }
        }
        return null; // No s'ha trobat cap camí
    }

    private static double calcularCost(Node actual, int nouX, int nouY, MapGrid mapa) {
        int altActual = mapa.getHeight(actual.getx(), actual.gety());
        int altNova = mapa.getHeight(nouX, nouY);
        return altNova > altActual ? 1 + (altNova - altActual) : 1;
    }

    private static double calcularHeuristica(int x, int y, int xf, int yf, MapGrid mapa, int heuristica) {
        Node actual = new Node(x, y, 0, 0, null);
        Node desti = new Node(xf, yf, 0, 0, null);
        switch (heuristica) {
            case 1: return Heuristic.desnivellAcumulat(actual, desti, mapa);
            case 2: return Heuristic.costMinim(actual, desti);
            case 3: return Heuristic.bifurcacioCamiOptim(actual, desti, mapa);
            default: return 0;
        }
    }

    private static List<Node> reconstruirCami(Node node) {
        List<Node> cami = new ArrayList<>();
        while (node != null) {
            cami.add(node);
            node = node.getParent();
        }
        Collections.reverse(cami);
        return cami;
    }
}
