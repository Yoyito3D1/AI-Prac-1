import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            MapGrid map = new MapGrid("src/map.txt");
            map.printMap();
        } catch (IOException e) {
            System.err.println("Error carregant el mapa: " + e.getMessage());
        }
    }
}
