package controlador;

import vista.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import modelo.Batalla;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Ataque;

public class Controlador {
    private VistaPokemon vista;
    private Batalla batalla;
    private Entrenador entrenador1, entrenador2;
    public boolean esGui;
    public byte escena;
    ArrayList<String> listaPokemones1, listaPokemones2, listaEntrenadores;

    Pokemon pokemon1, pokemon2;
    ArrayList<Pokemon> orden;

    public void setPokemonActivoEntrenador1(Pokemon pokemonActivoEntrenador1) {
        this.pokemon1 = pokemonActivoEntrenador1;
    }

    public void setPokemonActivoEntrenador2(Pokemon pokemonActivoEntrenador2) {
        this.pokemon2 = pokemonActivoEntrenador2;
    }

    public void setListaPokemones1(String nombre1, String nombre2, String nombre3) {
        this.listaPokemones1 = new ArrayList<>(Arrays.asList(nombre1, nombre2, nombre3));
    }

    public void setListaPokemones2(String nombre1, String nombre2, String nombre3) {
        this.listaPokemones2 = new ArrayList<>(Arrays.asList(nombre1, nombre2, nombre3));
    }

    public void setListaEntrenadores(String nombre1, String nombre2) {
        this.listaEntrenadores = new ArrayList<>(Arrays.asList(nombre1, nombre2));
    }

    public Controlador(VistaPokemon vista, boolean esGui) {
       this.vista = vista;
       this.esGui = esGui;
       vista.setControlador(this);
       this.listaPokemones1 = new ArrayList<>();
       this.listaPokemones2 = new ArrayList<>();
       this.listaEntrenadores = new ArrayList<>();
       this.escena = 0;
    }

    public void actualizarEscena() {
        System.out.println("Flujo de control iniciado." + escena);
        switch(escena) {
            case 0:
                vista.bienvenido();
                // Listener para cambiar de escena
                break;
            case 1:
                vista.entrenadores();
                // Aquí tiene que estar el listener que ademas de cambiar de escena, guarda las variables entrenador1 y entrenador2
                break;
            case 2:
                /*  Listener para guardar los 3 pokemones (Se llama 2 veces, una para cada entrenador)
                 * Te hice unos setters al principio del controlador, me tienes que mandar los strings que pide según la ventana 
                 * Por ejemplo, con setListaPokemones1(pokemon1, pokemon2, pokemon3), me pasas los 3 nombres de los textFields en tu listener
                 * */
                vista.pokemones(listaEntrenadores.get(0));
                // Si alguno de los pokemones o de los nombres de los entrenadores están vacíos, entonces vuelvo a llamar los métodos
                entrenador1 = Entrenador.capturarEntrenador(listaEntrenadores.get(0), listaPokemones1.get(0), listaPokemones1.get(1), listaPokemones1.get(2));
                /* Aquí estaría la segunda llamada al listener, hacer condicion:
                 * si está por 2da vez mandando pokemones, cambiar de escena */
                vista.pokemones(listaEntrenadores.get(1));
                entrenador2 = Entrenador.capturarEntrenador(listaEntrenadores.get(1), listaPokemones2.get(0), listaPokemones2.get(1), listaPokemones2.get(2));
                break;
            case 3:
                // Método para mostrar pokemones
                vista.mostrarPokemon(entrenador1.getEquipo().get(0));
                vista.mostrarPokemon(entrenador1.getEquipo().get(1));
                vista.mostrarPokemon(entrenador1.getEquipo().get(2));
                vista.mostrarPokemon(entrenador2.getEquipo().get(0));
                vista.mostrarPokemon(entrenador2.getEquipo().get(1));
                vista.mostrarPokemon(entrenador2.getEquipo().get(2));
                // Listener para cambiar de escena
                break;
            case 4:
                batalla = Batalla.instanciarBatalla(entrenador1, entrenador2);
                orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
                break;
        }        
    }

    public void avanzarEscena() {
        // Este método será el que usará la vista para avanzar de escena, en el listener del Enter puede ser
        escena++;
        actualizarEscena();
    }

    public void iniciarCombate(byte estadoCombate) {
        switch(estadoCombate) {
        case -2:
        // El entrenador 1 tiene que elegir un nuevo pokemon
        // vista.elegirPokemon(entrenador1) sería donde eliges el pokemon y usas el setter para cambiar pokemon1
        // El setter para cambiar pokemon1 se llama setPokemonActivoEntrenador1(pokemon)
            vista.elegirPokemon(entrenador1);
            orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
            break;
        case -1:
        // El entrenador 2 tiene que elegir un nuevo pokemon
        // vista.elegirPokemon(entrenador2) sería donde eliges el pokemon y usas el setter para cambiar pokemon2
        // El setter para cambiar pokemon2 se llama setPokemonActivoEntrenador2(pokemon)
            vista.elegirPokemon(entrenador2);
            orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
            break;
        case 0:
        // Si pokemon2 sigue vivo, es turno de pokemon2
            Collections.reverse(orden);
            break;
        case 1:
            vista.ganador(entrenador1);
            break;
        case 2:
            vista.ganador(entrenador2);
            break;
        }
    }

    public void atacar(Ataque ataqueElegido) {
        // Entonces el actionListener del botón de cada ataque tiene que llamar a este método y pasarle el ataque
        byte estadoCombate = (byte) batalla.turno(orden.get(0), ataqueElegido, orden.get(1));
        iniciarCombate(estadoCombate);
    }

    public ArrayList<Pokemon> getOrden() {
        return orden;
        // Esto es un regalo pa vos, aclaro que SIEMPRE orden.get(0) es el atacante y orden.get(1) es el atacado: 
        // Como al atacante le pones la imagen de espalda y al atacado la imagen de frente, con el orden puedes darle a cada quien
        // su imagen.
        // Además, así sabes la vida de cada quien, y por tanto qué barra de vida usar.
        // Ya vos relacionas "nombredelpokemon".getHp() con "nombredelpokemon".getHPMAX() para ver qué barra de vida usar
        // Con esto puedes sacar los ataques específicos del atacante "orden.get(0).getAtaques()";
        // Ya te hice HPMAX, puedes acceder al HPMAX de cada pokemon con "nombredelpokemon".getHPMAX()
    }

    public void cambiarVista(){
        if(esGui){
            vista = new VistaPokemonConsola();
        }else{
            vista = new VistaPokemonGUI();
        }
        esGui = !esGui;
        vista.setControlador(this);
        vista.bienvenido();
    }
}