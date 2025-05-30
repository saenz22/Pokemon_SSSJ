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
    private Pokemon pokemon1, pokemon2;
    private ArrayList<Pokemon> orden;
    private ArrayList<String> listaPokemones, listaEntrenadores;
    public boolean esGui;
    public byte escena;

    public ArrayList<Entrenador> getListaEntrenadores() {
        return new ArrayList<>(Arrays.asList(entrenador1, entrenador2)); 
    }

    public ArrayList<Pokemon> getListaPokemones1() {
        return entrenador1.getEquipo();
    }

    public ArrayList<Pokemon> getListaPokemones2() {
        return entrenador2.getEquipo();
    }


    public ArrayList<Pokemon> getOrden() {
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

    public Controlador(boolean esGui) {
       this.esGui = esGui;
       this.crearVista();
       vista.setControlador(this);
       this.listaPokemones = new ArrayList<>();
       this.listaEntrenadores = new ArrayList<>();
       this.escena = 0;
       this.pokemon1 = null;
       this.pokemon2 = null;
    }

    public void actualizarEscena() {
        System.out.println("Flujo de control iniciado." + escena);
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
        // Este método será el que usará la vista para avanzar de escena, en el listener del Enter puede ser
        escena++;
        actualizarEscena();
    }
    public void atacar(Ataque ataqueElegido) {
        // Entonces el actionListener del botón de cada ataque tiene que llamar a este método y pasarle el ataque
        byte estadoCombate = (byte) batalla.turno(orden.get(0), ataqueElegido, orden.get(1));
        iniciarCombate(estadoCombate);
    }
    public void ordenarContrincantes() {
        // Este método sera el que usaremos para avanzar de escena en el listener del Enter entre la tabla de pokemones y la pelea
        orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
        System.out.println(getOrden().get(0).getNombre() + " vs " + getOrden().get(1).getNombre());
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
        // Si pokemon2 sigue vivo, es turno de pokemon2
            Collections.reverse(orden);
            vista.continuar();
            break;
        case 1:
            vista.ganador(entrenador1);
            return;
        case 2:
            vista.ganador(entrenador2);
            return;
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