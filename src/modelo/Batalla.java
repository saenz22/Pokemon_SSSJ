package modelo;

import java.util.ArrayList;  
import java.util.Random;
import vista.VistaPokemon;

public class Batalla {
    
    // Objetos globales
    VistaPokemon vista;
    static Random random = new Random(); // Generar aleatoriedad
    Entrenador e1, e2;

    ArrayList<Pokemon> disponibles1, disponibles2;

    public Entrenador getEntrenador1() {
        return e1;
    }

    public void setEntrenador1(Entrenador e1) {
        this.e1 = e1;
    }

    public Entrenador getEntrenador2() {
        return e2;
    }

    public void setEntrenador2(Entrenador e2) {
        this.e2 = e2;
    }

    public Batalla(Entrenador e1, Entrenador e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.disponibles1 = e1.getEquipo();
        this.disponibles2 = e2.getEquipo();
    }

    public int turno(Pokemon atacante, Ataque ataqueElegido, Pokemon atacado, boolean esRepetido) {
        
        ordenBatalla(atacante, atacado, esRepetido);
         // Si ambos tienen la misma velocidad, el primero que llege a la batalla, lo ataca
        atacante.atacar(ataqueElegido, atacado);
        if (atacado.getVivo() == false) {
            // Verificar el pokemon de quién perdió
            if (disponibles1.contains(atacado)) {
                disponibles1.remove(atacado);
                if (disponibles1.isEmpty()) {
                    return 2; // el ganador es el entrenador 2
                } else {
                    return -2; // el entrenador1 tiene que elegir un nuevo pokemon
                }
            }
            else if (disponibles2.contains(atacado)) {
                disponibles2.remove(atacado);
                if (disponibles2.isEmpty()) {
                    return 1; // El ganador es el entrenador 1
                } else {
                    return -1; // el entrenador2 tiene que elegir un nuevo pokemon
                }
            }
        }
        return 0;
    }
    
     // Método principal para iniciar una batalla entre dos entrenadores
     public static Batalla instanciarBatalla(Entrenador e1, Entrenador e2) {
        Batalla batalla = new Batalla(e1, e2);
        return batalla;
    }

    void ordenBatalla(Pokemon atacante, Pokemon atacado, boolean esRepetido) {
        Pokemon temporal;
        if (!esRepetido) {
            if (atacado.getVelocidad() > atacante.getVelocidad()) {
            temporal = atacante;
            atacante = atacado;
            atacado = temporal;
            }
        }
    }
}   