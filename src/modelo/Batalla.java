package modelo;

import java.util.ArrayList;  
import java.util.Random;

public class Batalla {
    
    // Objetos globales
    static Random random = new Random(); // Generar aleatoriedad
    Entrenador e1, e2;
    ArrayList<Pokemon> disponibles1, disponibles2;
    Pokemon activo1, activo2;

    public Batalla(Entrenador e1, Entrenador e2, Pokemon activo1, Pokemon activo2) {
        this.e1 = e1;
        this.e2 = e2;
        this.activo1 = activo1;
        this.activo2 = activo2;
        this.disponibles1 = e1.getEquipo();
        this.disponibles2 = e2.getEquipo();
    }

    public int combate(Ataque ataqueElegido) {
        // getAtaqueElegido() --> Getter proveniente de método de WindowBatalla
        activo1.atacar(ataqueElegido, activo2);
        if (activo2.getVivo() == false) {
            if (disponibles2.isEmpty()) {
                return 0; // 0 --> Ganó entrenador 1 porque no quedan Pokemones a entrenador 2
            } else {
                return 1; // 1 --> Entrenador 2 sigue porque tiene más pokemones
            }
        } else {
            intercambiarActivos();
            return 2; // 2 --> Turno de quien fue atacado porque no murió
        }
    }
     // Método principal para iniciar una batalla entre dos entrenadores
     public static Batalla instanciarBatalla(Entrenador e1, Entrenador e2, Pokemon a1, Pokemon a2, Ataque ataqueElegido) {
        Batalla batalla = new Batalla(e1, e2, a1, a2);
        batalla.ordenBatalla();
        batalla.combate(ataqueElegido);
        return batalla;
    }

    public void intercambiarActivos() {
        Pokemon temp1 = activo1;
        Entrenador temp2 = e1;
        this.activo1 = activo2;
        this.activo2 = temp1;
        this.e1 = e2;
        this.e2 = temp2;
    }

    public void ordenBatalla() {
        if (activo2.getVelocidad() > activo1.getVelocidad()) {
            this.intercambiarActivos();
        } else if (activo2.getVelocidad() == activo1.getVelocidad()) {
            if (!random.nextBoolean()) {
                this.intercambiarActivos();
            }
        }
    }
}   