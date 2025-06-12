package controlador;

import vista.*;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;

import modelo.Batalla;
import modelo.Entrenador;
import modelo.HistorialAtaques;
import modelo.Ranking;
import modelo.Pokemon;
import modelo.Ataque;
import modelo.PersistenciaBatallas;
import modelo.ManejadorLogros;

<<<<<<< HEAD
// ME FALTAN LOS LOGROS Y EVITAR QUE SE GUARDEN MÁS DE 4 BATALLAS, PODER BORRAR BATALLAS

// NOTA: la idea es que en la vista cuando se quiera comprobar si un logro se ha desbloqueado, se llame:
// agregarLogro(Logros logro, Entrenador entrenador);
// Y para mostrar los logros, desbloqueados o no según si tiene null o el entrenador que lo desbloqueó, se llame:
// getLogrosDesbloqueados();

public class Controlador implements PersistenciaBatallas {

    // Atributos
    private static Controlador controlador = null;
    private VistaPokemon vista;
    private ManejadorLogros manejadorLogros;
    private static HistorialAtaques historialAtaques;
    private static Ranking ranking;
=======
/**
 * Clase Controlador que gestiona la lógica principal del flujo del juego,
 * coordinando la interacción entre la Vista y el Modelo bajo el patrón MVC.
 */
public class Controlador {
    // Referencia a la vista activa (GUI o Consola)
    private VistaPokemon vista;
    // Instancia de la batalla actual
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    private Batalla batalla;
    // Entrenadores participantes
    private Entrenador entrenador1, entrenador2;
    // Pokémon activos de cada entrenador
    private Pokemon pokemon1, pokemon2;
<<<<<<< HEAD
    private LinkedList<Pokemon> orden;
=======
    // Orden de los Pokémon en combate según velocidad
    private ArrayList<Pokemon> orden;
    // Listas temporales para nombres de Pokémon y entrenadores
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    private ArrayList<String> listaPokemones, listaEntrenadores;
    // Indica si la vista actual es GUI (true) o consola (false)
    public boolean esGui;
    // Escena actual del flujo del juego
    public byte escena;

<<<<<<< HEAD
    public Controlador(boolean esGui) {
       this.esGui = esGui;
       this.crearVista();
       this.listaPokemones = new ArrayList<>();
       this.listaEntrenadores = new ArrayList<>();
       this.escena = 0;
       this.pokemon1 = null;
       this.pokemon2 = null;
       this.manejadorLogros = ManejadorLogros.instanciar(this);
       this.historialAtaques = HistorialAtaques.instanciar();
       this.ranking = Ranking.instanciar();
       this.orden = new LinkedList<>();
       File archivoBatallas = new File("batallas.ser");
        if(!archivoBatallas.exists() || archivoBatallas.length() == 0) {
            // Si el archivo no existe o está vacío, inicializamos la persistencia con una lista vacía
            PersistenciaBatallas.guardar(new ArrayList<Batalla>());
        }
    }

    // Método para instanciar el controlador (Singleton)
    // Este método asegura que solo haya una instancia del controlador en toda la aplicación   ordenBatalla
    public static Controlador instanciar(boolean esGui) {
        if (controlador == null) {
            controlador = new Controlador(esGui);
        }
        return controlador;
    }

=======

     /**
     * Devuelve una lista con ambos entrenadores.
     * @return ArrayList con los entrenadores participantes.
     */
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    public ArrayList<Entrenador> getListaEntrenadores() {
        return new ArrayList<>(Arrays.asList(entrenador1, entrenador2)); 
    }

    /**
     * Obtiene el equipo de Pokémon del primer entrenador.
     * @return Lista de Pokémon del entrenador 1.
     */
    public ArrayList<Pokemon> getListaPokemones1() {
        return entrenador1.getEquipo();
    }

    /**
     * Obtiene el equipo de Pokémon del segundo entrenador.
     * @return Lista de Pokémon del entrenador 2.
     */
    public ArrayList<Pokemon> getListaPokemones2() {
        return entrenador2.getEquipo();
    }

<<<<<<< HEAD
    public LinkedList<Pokemon> getOrden() {
=======
    /**
     * Devuelve el orden actual de los Pokémon en combate.
     * @return Lista ordenada de Pokémon.
     */
    public ArrayList<Pokemon> getOrden() {
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
        return orden;
    }

    /**
     * Establece el Pokémon activo del primer entrenador.
     * @param pokemonActivoEntrenador1 Pokémon seleccionado.
     */
    public void setPokemonActivoEntrenador1(Pokemon pokemonActivoEntrenador1) {
        this.pokemon1 = pokemonActivoEntrenador1;
    }

    /**
     * Establece el Pokémon activo del segundo entrenador.
     * @param pokemonActivoEntrenador2 Pokémon seleccionado.
     */
    public void setPokemonActivoEntrenador2(Pokemon pokemonActivoEntrenador2) {
        this.pokemon2 = pokemonActivoEntrenador2;
    }

    /**
     * Define la lista temporal de nombres de Pokémon seleccionados.
     * @param nombre1 Nombre del primer Pokémon.
     * @param nombre2 Nombre del segundo Pokémon.
     * @param nombre3 Nombre del tercer Pokémon.
     */
    public void setListaPokemones(String nombre1, String nombre2, String nombre3) {
        this.listaPokemones = new ArrayList<>(Arrays.asList(nombre1, nombre2, nombre3));
    }

    /**
     * Define la lista temporal de nombres de entrenadores.
     * @param nombre1 Nombre del primer entrenador.
     * @param nombre2 Nombre del segundo entrenador.
     */
    public void setListaEntrenadores(String nombre1, String nombre2) {
        this.listaEntrenadores = new ArrayList<>(Arrays.asList(nombre1, nombre2));
    }

<<<<<<< HEAD
    public void setBatalla(Batalla batalla) {
        this.entrenador1 = batalla.getEntrenador1();
        this.entrenador2 = batalla.getEntrenador2();
        this.escena = 6; // Cambiamos la escena a la de elegir pokemon
        actualizarEscena();
=======
    /**
     * Constructor del controlador. Inicializa la vista y variables principales.
     * @param esGui Indica si se usará la interfaz gráfica (true) o consola (false).
     */
    public Controlador(boolean esGui) {
       this.esGui = esGui;
       this.crearVista();
       vista.setControlador(this);
       this.listaPokemones = new ArrayList<>();
       this.listaEntrenadores = new ArrayList<>();
       this.escena = 0;
       this.pokemon1 = null;
       this.pokemon2 = null;
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    }

    /**
     * Actualiza la escena actual del flujo del juego, mostrando la vista correspondiente.
     * Controla el avance entre pantallas y la creación de entrenadores y batallas.
     */
    public void actualizarEscena() {
        switch(escena) {
            case 0:
                // Pantalla de bienvenida
                vista.bienvenido();
                ranking.generarRanking(PersistenciaBatallas.cargar());
                break;
            case 1:
                // Solicita nombres de entrenadores
                vista.entrenadores();
                break;
            case 2:
                // Solicita nombres de Pokémon para el primer entrenador
                vista.pokemones();
                break;
            case 3:
                // Crea el primer entrenador y muestra su equipo
                if (!vista.isError()) {
                    entrenador1 = Entrenador.capturarEntrenador(
                        listaEntrenadores.get(0),
                        listaPokemones.get(0),
                        listaPokemones.get(1),
                        listaPokemones.get(2)
                    );
                    System.out.println("Entrenador 1: " + entrenador1.getNombre());      
                    vista.mostrarPokemon(entrenador1.getEquipo());  
                }
                break;
            case 4:
                // Solicita nombres de Pokémon para el segundo entrenador
                vista.pokemones();
                break;
            case 5:
                // Crea el segundo entrenador y muestra su equipo
                if (!vista.isError()) {
                    entrenador2 = Entrenador.capturarEntrenador(
                        listaEntrenadores.get(1),
                        listaPokemones.get(0),
                        listaPokemones.get(1),
                        listaPokemones.get(2)
                    );
                    System.out.println("Entrenador 2: " + entrenador2.getNombre());      
                    vista.mostrarPokemon(entrenador2.getEquipo());
                }
                break;
            case 6:
                // Instancia la batalla y permite elegir Pokémon activos
                batalla = Batalla.instanciarBatalla(entrenador1, entrenador2);
                vista.elegirPokemon(entrenador1, entrenador2);
                break;
        }        
    }

    /**
     * Avanza a la siguiente escena del flujo del juego.
     * Llama a actualizarEscena() para mostrar la vista correspondiente.
     */
    public void avanzarEscena() {
        escena++;
        actualizarEscena();
    }

<<<<<<< HEAD
=======
    /**
     * Ejecuta el ataque seleccionado y gestiona el flujo del combate.
     * @param ataqueElegido Ataque seleccionado por el usuario.
     */
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    public void atacar(Ataque ataqueElegido) {
        // Ejecuta el turno y obtiene el estado resultante del combate
        byte estadoCombate = (byte) batalla.turno(orden.get(0), ataqueElegido, orden.get(1));
        iniciarCombate(estadoCombate);
    }

<<<<<<< HEAD
=======
    /**
     * Ordena los Pokémon activos según su velocidad para determinar el orden de ataque.
     */
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    public void ordenarContrincantes() {
        orden = batalla.ordenBatalla(pokemon1, pokemon2);
    }

<<<<<<< HEAD
    public void guardarBatalla() {
        ArrayList<Batalla> batallas = PersistenciaBatallas.cargar();
        batallas.add(batalla);
        PersistenciaBatallas.guardar(batallas);
    }

    public ArrayList<Batalla> cargarBatalla() {
        ArrayList<Batalla> batallas = PersistenciaBatallas.cargar();
        //PersistenciaBatallas.guardar(batallas); Aseguramos que el archivo exista
        return batallas;
    }

    public void reiniciarPartida() {
        // Reinicia la partida, reseteando los entrenadores y pokemones
        entrenador1.restaurarEquipo();
        entrenador2.restaurarEquipo();
        pokemon1 = null;
        pokemon2 = null;
        orden.clear();
        escena = 0; // Volvemos a la escena de bienvenida
        actualizarEscena();
    }

    // Métodos para ManejadorLogros

    // Método para vistaPokemon
    public void notificarLogro(String nombre, String descripcion, String entrenador) {
        vista.mostrarLogro(nombre, descripcion, entrenador);
    }

    // Método para ManejadorLogros
    public void llamadaAgregarLogro(Entrenador entrenador) {
        manejadorLogros.agregarLogro(entrenador);
    }

    public void logroSecreto() {
        manejadorLogros.logroSecreto();
    }

    // Métodos del historial de ataques

    public static  ArrayList<Ataque> getHistorialAtaques() {
        return (ArrayList<Ataque>)historialAtaques.obtenerHistorial();
    }
    public void agregarAtaqueHistorial(Ataque descripcionAtaque) {
        historialAtaques.guardarAtaque(descripcionAtaque);
    }

    // Métodos del ranking
    
    public static TreeSet<Entrenador> generarRanking() {
        TreeSet<Entrenador> listaGanadores = ranking.getGanadores();
        return listaGanadores;
    }

=======
    /**
     * Gestiona el flujo del combate según el estado devuelto por la batalla.
     * @param estadoCombate Estado actual del combate (-2: cambia Pokémon 1, -1: cambia Pokémon 2, 0: sigue, 1/2: ganador).
     */
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
    public void iniciarCombate(byte estadoCombate) {
        switch(estadoCombate) {
        case -2:
            // El entrenador 1 debe elegir un nuevo Pokémon
            vista.elegirPokemon(entrenador1, entrenador2);
            vista.continuar();
            break;
        case -1:
            // El entrenador 2 debe elegir un nuevo Pokémon
            vista.elegirPokemon(entrenador1, entrenador2);
            vista.continuar();
            break;
        case 0:
<<<<<<< HEAD
        // Si pokemon atacado sigue vivo, es su turno
            Pokemon atacante = orden.poll();
            orden.addLast(atacante);
            vista.continuar();
            break;
        case 1:
            vista.ganador(entrenador1);  
            entrenador1.aumentarVictorias();
            entrenador2.aumentarDerrotas();
            // Restaurar el equipo de ambos entrenadores
            ranking.generarRanking(PersistenciaBatallas.cargar());
=======
            // El combate continúa, cambia el turno
            Collections.reverse(orden);
            vista.continuar();
            break;
        case 1:
            // El entrenador 1 gana
            vista.ganador(entrenador1);
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
            break;
        case 2:
            // El entrenador 2 gana
            vista.ganador(entrenador2);
<<<<<<< HEAD
            entrenador2.aumentarVictorias();
            entrenador1.aumentarDerrotas();
            // Restaurar el equipo de ambos entrenadores
            ranking.generarRanking(PersistenciaBatallas.cargar());
=======
>>>>>>> a2ff1df139af7afe44e29da50e759658d8c33b2e
            break;
        }
    }

    /**
     * Crea la vista correspondiente (GUI o consola) y la inicializa.
     * Alterna entre vistas si es necesario.
     */
    public void crearVista(){
        if(!esGui){
            this.vista = new VistaPokemonConsola();
        }else{
            this.vista = new VistaPokemonGUI();
        }
        esGui = !esGui;
        vista.setControlador(this);
        vista.bienvenido();
    }
}