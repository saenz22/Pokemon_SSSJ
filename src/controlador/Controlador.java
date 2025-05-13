package controlador;

import vista.*;
import modelo.Batalla;
import modelo.Entrenador;
import modelo.Pokemon;

public class Controlador {
    private VistaPokemon vista;
    private Batalla batalla;
    public boolean esGui;

    public Controlador(VistaPokemon vista, boolean esGui) {
       this.vista = vista;
       this.esGui = esGui;
       vista.setControlador(this);
    }

    // getEscena corresponde a CurrentPanel en vistaGUI
    // cambiarEscena() corresponde a switchToNextPanel() en vistaGUI

    public void flujo() {
        System.out.println("Flujo de control iniciado." + vista.getEscena());
        switch(vista.getEscena()) {
            case 0:
                vista.bienvenido();
                break;
            case 2:
                vista.cambiarEscena(vista.escena3());
                break;
            case 3:
                vista.entrenadores();
                break;
            case 4:
                vista.cambiarEscena(vista.escena5(vista.getNombre1()));
                break;
            case 5:
                vista.pokemones();
                
                //vista.cambiarEscena(vista.showFifthPanel(vista.getNombre2()));

                break;
            case 6:
                Entrenador entrenador1 = Entrenador.capturarEntrenador(vista.getNombre1(), vista.getPokemon1(), vista.getPokemon2(), vista.getPokemon3());
                System.out.println("Entrenador 1: " + entrenador1.getNombre());
                System.out.println("Pokémon 1: " + entrenador1.getEquipo().get(0).getNombre());
                System.out.println("Pokémon 2: " + entrenador1.getEquipo().get(1).getNombre());
                System.out.println("Pokémon 3: " + entrenador1.getEquipo().get(2).getNombre());
                for(int i = 0; i < entrenador1.getEquipo().size(); i++) {
                   vista.cambiarEscena(vista.escena6(entrenador1.getEquipo().get(i)));
                }
                break;
            case 7:
                // Combate
                int number = 0;
                batalla = Batalla.instanciarBatalla(entrenador1, Entrenador2);
                while(number != 0) {
                    number = batalla.turno(vista.elegirPokemon1(), vista.elegirAtaque(), vista.elegirPokemon2());
                    if (number == 1) {
                        vista.elegirPokemon2();
                    } else if (number == 2) {
                        batalla.intercambiarActivos();
                    }
                }
                break;
           break;
        }
        
    }
    // Métodos para manejar las vistas

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

    // public static void main(String[] args) {
    //     VistaPokemonGUI vista = new VistaPokemonGUI();
    //     Controlador control = new Controlador(vista);
    //     control.flujo();
    // }
}