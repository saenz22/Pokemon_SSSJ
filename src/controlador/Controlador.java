package controlador;

import vista.*;

import java.util.ArrayList;
import java.util.Collections;

import modelo.Batalla;
import modelo.Entrenador;
import modelo.Pokemon;

public class Controlador {
    private VistaPokemon vista;
    private Batalla batalla;
    private Entrenador entrenador1, entrenador2;
    public boolean esGui;
    int estadoCombate;
    ArrayList<String> listaPokemones, listaEntrenadores;

    public Controlador(VistaPokemon vista, boolean esGui) {
       this.vista = vista;
       this.esGui = esGui;
       vista.setControlador(this);
       this.listaPokemones = new ArrayList<>();
       this.listaEntrenadores = new ArrayList<>();
    }

    // getEscena corresponde a CurrentPanel en vistaGUI
    // Aunque no sé si realmente sea necesario, porque en la consola no lo necesitamos al hacerle el Fall Through
    // cambiarEscena() corresponde a switchToNextPanel() en vistaGUI

    public void flujo() {
        System.out.println("Flujo de control iniciado." + vista.getEscena());
        while(true) {
            switch(vista.getEscena()) {
            case 0:
                vista.bienvenido();
                vista.setEscena((byte) 1);
                break;
            case 1:
                listaEntrenadores = vista.entrenadores();
                vista.setEscena((byte) 2);
                break;
            case 2:
                listaPokemones.addAll(vista.pokemones(listaEntrenadores.get(0)));
                entrenador1 = Entrenador.capturarEntrenador(listaEntrenadores.get(0), listaPokemones.get(0), listaPokemones.get(1), listaPokemones.get(2));
                listaPokemones.addAll(vista.pokemones(listaEntrenadores.get(1)));
                entrenador2 = Entrenador.capturarEntrenador(listaEntrenadores.get(1), listaPokemones.get(3), listaPokemones.get(4), listaPokemones.get(5));
                vista.setEscena((byte) 3);
                break;
            case 3:
                // Método para mostrar pokemones
                vista.mostrarPokemon(entrenador1.getEquipo().get(0));
                vista.mostrarPokemon(entrenador1.getEquipo().get(1));
                vista.mostrarPokemon(entrenador1.getEquipo().get(2));
                vista.mostrarPokemon(entrenador2.getEquipo().get(0));
                vista.mostrarPokemon(entrenador2.getEquipo().get(1));
                vista.mostrarPokemon(entrenador2.getEquipo().get(2));
                vista.setEscena((byte) 4);
                break;
            case 4:
                iniciarCombate();
                return;
            }
        }        
    }

    public void iniciarCombate() {
        batalla = Batalla.instanciarBatalla(entrenador1, entrenador2);
        Pokemon pokemon1, pokemon2;
        ArrayList<Pokemon> orden;
        pokemon1 = vista.elegirPokemon(entrenador1);
        pokemon2 = vista.elegirPokemon(entrenador2);
        orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
        estadoCombate = batalla.turno(orden.get(0), vista.elegirAtaque(orden.get(0)), orden.get(1));
        while(true) {
            switch(estadoCombate) {
                case -2:
                // El entrenador 1 tiene que elegir un nuevo pokemon
                    pokemon1 = vista.elegirPokemon(entrenador1);
                    orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
                    estadoCombate = batalla.turno(orden.get(0), vista.elegirAtaque(orden.get(0)), orden.get(1));
                    break;
                case -1:
                // El entrenador 2 tiene que elegir un nuevo pokemon
                    pokemon2 = vista.elegirPokemon(entrenador2);
                    orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
                    estadoCombate = batalla.turno(orden.get(0), vista.elegirAtaque(orden.get(0)), orden.get(1));
                    break;
                case 0:
                // Si pokemon2 sigue vivo, es turno de pokemon2
                    Collections.reverse(orden);
                    estadoCombate = batalla.turno(orden.get(0), vista.elegirAtaque(orden.get(0)), orden.get(1));
                    break;
                case 1:
                    vista.ganador(entrenador1);
                    return;
                case 2:
                    vista.ganador(entrenador2);
                    return;
                }
        }
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