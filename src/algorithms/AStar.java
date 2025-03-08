package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import models.Heuristic;
import models.Node;
import utils.MapGrid;

public class AStar {
    public static int costFinal = 0;

    public static List<Node> cerca(Node inici, Node fi, MapGrid mapa, int heuristica) {
        PriorityQueue<Node> frontera = new PriorityQueue<>();
        Set<String> visitats = new HashSet<>();
        frontera.add(inici);

        while (!frontera.isEmpty()) {
            Node actual = frontera.poll();
            if (actual.getX() == fi.getX() && actual.getY() == fi.getY()) {
                return reconstruirCami(actual, mapa);
            }

            visitats.add(actual.getX() + "," + actual.getY());

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int nouX = actual.getX() + dir[0];
                int nouY = actual.getY() + dir[1];
                if (mapa.isValid(nouX, nouY) && !visitats.contains(nouX + "," + nouY)) {
                    System.out.println(mapa.getHeight(nouX, nouY));
                    int nouG = actual.getAltura() + calcularCost(actual, nouX, nouY, mapa);
                    int nouH = calcularHeuristica(nouX, nouY, fi.getX(), fi.getY(), mapa, heuristica);
                    frontera.add(new Node(nouX, nouY, nouG, nouH, actual));
                }
            }
        }
        return null; // No s'ha trobat cap camí
    }

    private static int calcularCost(Node actual, int nouX, int nouY, MapGrid mapa) {
        int altActual = mapa.getHeight(actual.getX(), actual.getY());
        int altNova = mapa.getHeight(nouX, nouY);
        costFinal = altNova + costFinal;
        return altNova > altActual ? 1 + (altNova - altActual) : 1;
    }

    private static int calcularHeuristica(int x, int y, int xf, int yf, MapGrid mapa, int heuristica) {
        Node actual = new Node(x, y, 0, 0, null);
        Node desti = new Node(xf, yf, 0, 0, null);
        switch (heuristica) {
            case 1: return Heuristic.desnivellAcumulat(actual, desti, mapa);
            case 2: return Heuristic.costMinim(actual, desti);
            case 3: return Heuristic.bifurcacioCamiOptim(actual, desti, mapa);
            default: return 0;
        }
    }

    public static int getCostFinal() {
        return costFinal;
    }

    private static List<Node> reconstruirCami(Node node, MapGrid mapa) {
        List<Node> cami = new ArrayList<>();
        costFinal = 0;  // Reinicialitzem abans de calcular el cost final
    
        while (node != null) {
            cami.add(node);
            if (node.getParent() != null) {
                int altActual = mapa.getHeight(node.getParent().getX(), node.getParent().getY());
                int altNova = mapa.getHeight(node.getX(), node.getY());
    
                if (altNova >= altActual) {
                    costFinal += 1 + (altNova - altActual);
                } else {
                    costFinal += 0.5;
                }
            }
            node = node.getParent();
        }
        Collections.reverse(cami);
        return cami;
    }
    
}
