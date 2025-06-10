package modelo;

import java.util.ArrayList;
import java.util.List;
public class Entrenador extends SerVivo {

    // Atributos
    private static final long serialVersionUID = 1L; // Serialización para guardar entrenadores
    private byte victorias, derrotas, victoriasSeguidas, derrotasSeguidas;
    private ArrayList<Pokemon> equipo = new ArrayList<>();

    // Constructor
    public Entrenador(String nombre) {
        // Heredando nombre
        super(nombre);
        this.victorias = 0;
        this.derrotas = 0;
        this.victoriasSeguidas = 0;
        this.derrotasSeguidas = 0;
    }

    // Getters
    public ArrayList<Pokemon> getEquipo() {
        return equipo;
    }

    public int getVictorias() {
        return victorias;
    }
    
    public int getDerrotas() {
        return derrotas;
    }

    public int getVictoriasSeguidas() {
        return victoriasSeguidas;
    }

    public int getDerrotasSeguidas() {
        return derrotasSeguidas;
    }

    // Setters
    public void aumentarVictorias() {
        this.victorias +=1;
        this.victoriasSeguidas += 1; // Aumenta las victorias seguidas
        this.derrotasSeguidas = 0; // Reinicia las derrotas seguidas
    }

    public void aumentarDerrotas() {
        this.derrotas +=1;
        this.derrotasSeguidas += 1; // Aumenta las derrotas seguidas
        this.victoriasSeguidas = 0; // Reinicia las victorias seguidas
    }

    //Factory method para capturar un entrenador por consola sin necesidad de instanciar un objeto con new
    public static Entrenador capturarEntrenador(String nombre, String primero, String segundo, String tercero) {
        
        Entrenador inicial = new Entrenador(nombre);
        inicial.capturarPokemon(primero, segundo, tercero);
        return inicial; // Retorna nuevo entrenador
    }

    // Método para crear el equipo de 3 Pokemones
    public void capturarPokemon(String primero, String segundo, String tercero) {

        List<String> lista = List.of(primero, segundo, tercero); // inmutable
        
        for (int i = 0; i < 3; i++) {
            equipo.add(Pokemon.instanciarPokemon(lista.get(i))); // Se instancian automáticamente
        }
    }

    public boolean desbloquearLogro(Logros logro, ManejadorLogros manejador) {
        if (logro.cumple(this) && manejador.getLogrosDesbloqueados().get(logro) == null) {
            manejador.getLogrosDesbloqueados().put(logro, this); // Agrega el logro al mapa de logros desbloqueados
            if (logro == Logros.ROCKET) {
                this.nombre = this.nombre + " Rocket"; // Cambia el nombre del entrenador si desbloquea el logro Rocket
            }
            return true; // Devuelve true si se desbloquea el logro
        }
        return false; // Devuelve false si no se cumple el logro
    }
}