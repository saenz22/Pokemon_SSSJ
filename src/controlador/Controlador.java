package controlador;

import vista.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import modelo.Batalla;
import modelo.Entrenador;

public class Controlador {
    private VistaPokemon vista;
    private Batalla batalla;
    private Entrenador entrenador1, entrenador2;
    public boolean esGui;
    int provisional;
    ArrayList<String> listaPokemones, listaEntrenadores = new ArrayList<>();

    public Controlador(VistaPokemon vista, boolean esGui) {
       this.vista = vista;
       this.esGui = esGui;
       vista.setControlador(this);
    }

    // getEscena corresponde a CurrentPanel en vistaGUI
    // Aunque no sé si realmente sea necesario, porque en la consola no lo necesitamos al hacerle el Fall Through
    // cambiarEscena() corresponde a switchToNextPanel() en vistaGUI

    public void flujo() {
        System.out.println("Flujo de control iniciado." + vista.getEscena());
        switch(vista.getEscena()) {
            case 0:
                vista.bienvenido();
            case 1:
                listaEntrenadores = vista.entrenadores();
            case 2:
                listaPokemones = vista.pokemones(listaEntrenadores.get(0));
                entrenador1 = Entrenador.capturarEntrenador(listaEntrenadores.get(0), listaPokemones.get(0), listaPokemones.get(1), listaPokemones.get(2));
                listaPokemones = vista.pokemones(listaEntrenadores.get(1));
                entrenador2 = Entrenador.capturarEntrenador(listaEntrenadores.get(1), listaPokemones.get(0), listaPokemones.get(1), listaPokemones.get(2));
            case 3:
                // Método para mostrar pokemones
                vista.mostrarPokemon(entrenador1.getEquipo().get(0));
                vista.mostrarPokemon(entrenador1.getEquipo().get(1));
                vista.mostrarPokemon(entrenador1.getEquipo().get(2));
                vista.mostrarPokemon(entrenador2.getEquipo().get(0));
                vista.mostrarPokemon(entrenador2.getEquipo().get(1));
                vista.mostrarPokemon(entrenador2.getEquipo().get(2));
                // Hay que instanciar aquí a los entrenadores
            case 4:
                batalla = Batalla.instanciarBatalla(entrenador1, entrenador2);
                provisional = batalla.turno(vista.elegirPokemon(entrenador1), vista.elegirAtaque(vista.elegirPokemon(entrenador1)), vista.elegirPokemon(entrenador2), false);
                switch(provisional) {
                    case -2:
                        vista.elegirPokemon(batalla.getEntrenador1());
                        provisional = batalla.turno(vista.elegirPokemon(entrenador1), vista.elegirAtaque(vista.elegirPokemon(entrenador1)), vista.elegirPokemon(entrenador2), false);
                        break;
                    case -1:
                        vista.elegirPokemon(batalla.getEntrenador2());
                        provisional = batalla.turno(vista.elegirPokemon(entrenador1), vista.elegirAtaque(vista.elegirPokemon(entrenador1)), vista.elegirPokemon(entrenador2), false);
                        break;
                    case 0:
                        provisional = batalla.turno(vista.elegirPokemon(entrenador1), vista.elegirAtaque(vista.elegirPokemon(entrenador1)), vista.elegirPokemon(entrenador2), true);
                        break;
                    case 1:
                        vista.ganador(batalla.getEntrenador1());
                        break;
                    case 2:
                        vista.ganador(batalla.getEntrenador2());
                        break;
                }
            break;
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