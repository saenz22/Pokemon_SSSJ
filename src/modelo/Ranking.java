package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Ranking {
    
    private TreeSet<Entrenador> ganadores = new TreeSet<>(Comparator.comparingInt(Entrenador::getVictorias).reversed());
    private static Ranking instancia = null;

    // Constructor privado para implementar el patrón Singleton
    public static Ranking instanciar() {
        if (instancia == null) {
            instancia = new Ranking();
        }
        return instancia;
    }

    public TreeSet<Entrenador> generarRanking(ArrayList<Batalla> batallas) {
        // Limpiar el ranking actual
        if (batallas == null || batallas.isEmpty()) {
            ganadores.clear();
            return ganadores;
        }
        ganadores.clear();
        for (Batalla batalla : batallas) {
            Entrenador entrenador1 = batalla.getEntrenador1();
            Entrenador entrenador2 = batalla.getEntrenador2();
            ganadores.add(entrenador1);
            ganadores.add(entrenador2);
        }
        return ganadores;
    }
}