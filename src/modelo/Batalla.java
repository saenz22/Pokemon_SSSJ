package modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Clase que gestiona la lógica de una batalla Pokémon entre dos entrenadores.
 * Controla los turnos, el estado de los equipos y determina el ganador.
 */
public class Batalla {
    
    // Generador de aleatoriedad para la batalla (puede usarse para eventos aleatorios)
    static Random random = new Random();
    // Entrenadores participantes en la batalla
    Entrenador e1, e2;
    // Listas de Pokémon disponibles para cada entrenador
    ArrayList<Pokemon> disponibles1, disponibles2;

<<<<<<< HEAD
    public Entrenador getEntrenador1() {
        return e1;
    }

    public Entrenador getEntrenador2() {
        return e2;
    }

=======
    /**
     * Constructor de la batalla. Inicializa los entrenadores y sus equipos disponibles.
     * @param e1 Primer entrenador.
     * @param e2 Segundo entrenador.
     */
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    public Batalla(Entrenador e1, Entrenador e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.disponibles1 = e1.getEquipo();
        this.disponibles2 = e2.getEquipo();
    }

    /**
     * Ejecuta un turno de combate entre dos Pokémon.
     * Aplica el ataque, verifica si hay un Pokémon derrotado y determina el estado de la batalla.
     * @param atacante Pokémon que realiza el ataque.
     * @param ataqueElegido Ataque seleccionado.
     * @param atacado Pokémon objetivo.
     * @return Código de estado: 1 (gana e1), 2 (gana e2), -1/-2 (cambio de Pokémon), 0 (continúa).
     */
    public int turno(Pokemon atacante, Ataque ataqueElegido, Pokemon atacado) {
<<<<<<< HEAD
         // Si ambos tienen la misma velocidad, el primero que llege a la batalla, lo ataca
=======

        // El atacante realiza el ataque sobre el Pokémon objetivo
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
        atacante.atacar(ataqueElegido, atacado);

        // Si el Pokémon atacado ha sido derrotado
        if (!atacado.getVivo()) {
            System.out.println("El pokemon " + atacado.getNombre() + " ha sido derrotado");
            // Verifica a qué entrenador pertenece el Pokémon derrotado
            if (disponibles1.contains(atacado)) {
                disponibles1.remove(atacado);
                e1.agregarDerrotado(atacado); // Agregar a la lista de derrotados del entrenador 1
                if (disponibles1.isEmpty()) {
                    System.out.println("El entrenador " + e2.getNombre() + " ha ganado la batalla");
                    return 2; // El ganador es el entrenador 2
                } else {
                    System.out.println("El entrenador " + e1.getNombre() + " tiene que elegir un nuevo pokemon");
                    return -2; // El entrenador 1 debe elegir un nuevo Pokémon
                }
            } else if (disponibles2.contains(atacado)) {
                disponibles2.remove(atacado);
                e2.agregarDerrotado(atacado); // Agregar a la lista de derrotados del entrenador 2
                System.out.println("El pokemon " + atacado.getNombre() + " ha sido derrotado");
                if (disponibles2.isEmpty()) {
                    return 1; // El ganador es el entrenador 1
                } else {
                    return -1; // El entrenador 2 debe elegir un nuevo Pokémon
                }
            }
        }
        // Si ambos Pokémon siguen en combate, muestra el estado actual
        System.out.println("El pokemon " + atacante.getNombre() + " ha atacado a " + atacado.getNombre());
        System.out.println("A " + atacado.getNombre() + " le quedan " + atacado.getHp() + "/" + atacado.getHPMAX() + " puntos de vida");
        return 0; // El combate continúa
    }
    
    /**
     * Método de fábrica para crear una nueva batalla entre dos entrenadores.
     * @param e1 Primer entrenador.
     * @param e2 Segundo entrenador.
     * @return Instancia de Batalla.
     */
    public static Batalla instanciarBatalla(Entrenador e1, Entrenador e2) {
        return new Batalla(e1, e2);
    }

<<<<<<< HEAD
    public LinkedList<Pokemon> ordenBatalla(Pokemon atacante, Pokemon atacado) {
    LinkedList<Pokemon> orden = new LinkedList<>();
    if (atacante.getVelocidad() >= atacado.getVelocidad()) {
        orden.add(atacante);
        orden.add(atacado);
    } else {
        orden.add(atacado);
        orden.add(atacante);
    }
    return orden;
}
=======
    /**
     * Determina el orden de ataque entre dos Pokémon según su velocidad.
     * @param atacante Primer Pokémon.
     * @param atacado Segundo Pokémon.
     * @return Lista ordenada de Pokémon (el más rápido primero).
     */
    public ArrayList<Pokemon> ordenBatalla(Pokemon atacante, Pokemon atacado) {
        if (atacado.getVelocidad() > atacante.getVelocidad()) {
            return new ArrayList<Pokemon>(Arrays.asList(atacado, atacante));
        }
        return new ArrayList<Pokemon>(Arrays.asList(atacante, atacado));
    }
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
}