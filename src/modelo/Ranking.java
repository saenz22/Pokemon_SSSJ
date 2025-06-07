package modelo;

import java.util.ArrayList;

public class Ranking {
    
    private ArrayList<Entrenador> ganadores = new ArrayList<>();
    private static Ranking instancia = null;

    // Constructor privado para implementar el patrón Singleton
    public static Ranking instanciar() {
        if (instancia == null) {
            instancia = new Ranking();
        }
        return instancia;
    }

    // Getter
    public ArrayList<Entrenador> getGanadores() {
        return ganadores;
    }

    public void generarRanking(ArrayList<Batalla> batallas) {
        for (Batalla batalla : batallas) {
            Entrenador entrenador1 = batalla.getEntrenador1();
            Entrenador entrenador2 = batalla.getEntrenador2();

            if (entrenador1.getVictorias() > entrenador2.getVictorias()) {
                ganadores.add(entrenador1);
            }
            else if (entrenador1.getVictorias() < entrenador2.getVictorias()) {
                ganadores.add(entrenador2);
            } 
            else {
                // En caso de empate, se pueden agregar ambos entrenadores
                ganadores.add(entrenador1);
                ganadores.add(entrenador2);
            }
        }
    }
}
