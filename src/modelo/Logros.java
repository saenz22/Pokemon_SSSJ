package modelo;

import java.util.ArrayList;

public enum Logros {

    IMBATIDO("Imbatido", "Vence a tu rival sin perder ningún Pokémon") {
        public boolean cumple(Entrenador entrenador) {
            return entrenador.getEquipo().size() == 3;
        }
    },
    POKEMON_LEGENDARIO("Pokémon legendario", "Obtén un Pokémon con nivel 90 o superior") {
        public boolean cumple(Entrenador entrenador) {
            for (Pokemon pokemon : entrenador.getEquipo()) {
                if (pokemon.getNivel() >= 90) {
                    return true;
                }
            }
            return false;
        }
    },
    PELOS("¡Por los pelos!", "Gana una batalla con tu último Pokémon a menos del 15% de salud") {
        public boolean cumple(Entrenador entrenador) {
            ArrayList<Pokemon> equipo = entrenador.getEquipo();
            return equipo.size() == 1 && equipo.get(0).getHp() < equipo.get(0).getHPMAX() * 0.15;
        }
    },
    MAESTRO_POKEMON("Maestro Pokémon", "Vence por tercera vez consecutiva") {
        public boolean cumple(Entrenador entrenador) {
            return entrenador.getVictoriasSeguidas() == 3;
        }
    },
    YO_TE_ELIJO("¡Yo te elijo!", "Gana tu primera batalla") {
        public boolean cumple(Entrenador entrenador) {
            return entrenador.getVictorias() == 1;
        }
    },
    ROCKET("Como en los viejos tiempos", "Únete al equipo Rocket siendo derrotado por tercera vez consecutiva") {
        public boolean cumple(Entrenador entrenador) {
            return entrenador.getDerrotasSeguidas() >= 3;
        }
    },
    SECRETO("¡Atrápalos ya!", "Completa el juego al 100%") {
        public boolean cumple(Entrenador entrenador) {
            return true;
        }
    };

    private final String nombre;
    private final String descripcion;
    public abstract boolean cumple(Entrenador entrenador);

    Logros(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombre + ": " + descripcion;
    }
}