package controlador;

import vista.*;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import modelo.Batalla;
import modelo.Entrenador;
import modelo.HistorialAtaques;
import modelo.Ranking;
import modelo.Pokemon;
import modelo.Ataque;
import modelo.PersistenciaBatallas;
import modelo.ManejadorLogros;

// Hacer funcionar desde la vista historialAtaques, ranking y logros

// NOTA: la idea es que en la vista cuando se quiera comprobar si un logro se ha desbloqueado, se llame:
// agregarLogro(Logros logro, Entrenador entrenador);
// Y para mostrar los logros, desbloqueados o no según si tiene null o el entrenador que lo desbloqueó, se llame:
// getLogrosDesbloqueados();

public class Controlador implements PersistenciaBatallas {

    // Atributos
    private static Controlador controlador = null;
    private VistaPokemon vista;
    private ManejadorLogros manejadorLogros;
    private HistorialAtaques historialAtaques;
    private Ranking ranking;
    private Batalla batalla;
    private Entrenador entrenador1, entrenador2;
    private Pokemon pokemon1, pokemon2;
    private LinkedList<Pokemon> orden;
    private ArrayList<String> listaPokemones, listaEntrenadores;
    public boolean esGui;
    public byte escena;

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

    public ArrayList<Entrenador> getListaEntrenadores() {
        return new ArrayList<>(Arrays.asList(entrenador1, entrenador2)); 
    }

    public ArrayList<Pokemon> getListaPokemones1() {
        return entrenador1.getEquipo();
    }

    public ArrayList<Pokemon> getListaPokemones2() {
        return entrenador2.getEquipo();
    }

    public LinkedList<Pokemon> getOrden() {
        return orden;
    }

    public void setPokemonActivoEntrenador1(Pokemon pokemonActivoEntrenador1) {
        this.pokemon1 = pokemonActivoEntrenador1;
    }

    public void setPokemonActivoEntrenador2(Pokemon pokemonActivoEntrenador2) {
        this.pokemon2 = pokemonActivoEntrenador2;
    }

    public void setListaPokemones(String nombre1, String nombre2, String nombre3) {
        this.listaPokemones = new ArrayList<>(Arrays.asList(nombre1, nombre2, nombre3));
    }

    public void setListaEntrenadores(String nombre1, String nombre2) {
        this.listaEntrenadores = new ArrayList<>(Arrays.asList(nombre1, nombre2));
    }

    public void setBatalla(Batalla batalla) {
        this.entrenador1 = batalla.getEntrenador1();
        this.entrenador2 = batalla.getEntrenador2();
        this.escena = 6; // Cambiamos la escena a la de elegir pokemon
        actualizarEscena();
    }

    public void actualizarEscena() {
        switch(escena) {
            case 0:
                vista.bienvenido();
                break;
            case 1:
                vista.entrenadores();
                break;
            case 2:
                vista.pokemones();
                break;
            case 3:
              if (vista.isError() == false){
                    entrenador1 = Entrenador.capturarEntrenador(listaEntrenadores.get(0), listaPokemones.get(0), listaPokemones.get(1), listaPokemones.get(2));
                    System.out.println("Entrenador 1: " + entrenador1.getNombre());      
                    vista.mostrarPokemon(entrenador1.getEquipo());  
                }
                break;
            case 4:
                vista.pokemones();
                break;
            case 5:
               if (vista.isError() == false){
                    entrenador2 = Entrenador.capturarEntrenador(listaEntrenadores.get(1), listaPokemones.get(0), listaPokemones.get(1), listaPokemones.get(2));
                    System.out.println("Entrenador 2: " + entrenador2.getNombre());      
                    vista.mostrarPokemon(entrenador2.getEquipo());
                }
                break;
            case 6:
                batalla = Batalla.instanciarBatalla(entrenador1, entrenador2);
                vista.elegirPokemon(entrenador1, entrenador2);
                break;
        }        
    }

    public void avanzarEscena() {
        // Este método será el que usará la vista para avanzar de escena
        escena++;
        actualizarEscena();
    }

    public void atacar(Ataque ataqueElegido) {
        // Entonces cada vez que seleccione un ataque tiene que llamar a este método y pasarle el ataque
        byte estadoCombate = (byte) batalla.turno(orden.get(0), ataqueElegido, orden.get(1));
        iniciarCombate(estadoCombate);
    }

    public void ordenarContrincantes() {
        // Este método será el que usaremos para avanzar de escena entre la elección de pokemones y la pelea
        orden = batalla.ordenBatalla(pokemon1, pokemon2);
    }

    public void guardarBatalla() {
        ArrayList<Batalla> batallas = PersistenciaBatallas.cargar();
        batallas.add(batalla);
        PersistenciaBatallas.guardar(batallas);
    }

    public ArrayList<Batalla> cargarBatalla() {
        ArrayList<Batalla> batallas = PersistenciaBatallas.cargar();
        PersistenciaBatallas.guardar(batallas); // Aseguramos que el archivo exista
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

    // Métodos del historial de ataques obtener

    public void agregarAtaqueHistorial(String descripcionAtaque) {
        historialAtaques.guardarAtaque(descripcionAtaque);
    }

    // Método para ranking
    public void generarRanking(ArrayList<Batalla> batallas) {
        ranking.generarRanking(batallas);
    }
    

    public void iniciarCombate(byte estadoCombate) {
        switch(estadoCombate) {
        case -2:
        // El entrenador 1 tiene que elegir un nuevo pokemon
            vista.elegirPokemon(entrenador1, entrenador2);
            vista.continuar();
            break;
        case -1:
        // El entrenador 2 tiene que elegir un nuevo pokemon       
            vista.elegirPokemon(entrenador1, entrenador2);
            vista.continuar();
            break;
        case 0:
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
            break;
        case 2:
            vista.ganador(entrenador2);
            entrenador2.aumentarVictorias();
            entrenador1.aumentarDerrotas();
            // Restaurar el equipo de ambos entrenadores
            break;
        }
    }

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