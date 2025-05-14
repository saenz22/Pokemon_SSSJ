package controlador;

import java.awt.Desktop.Action;

import javax.swing.JPanel;

import vista.VistaPokemonGUI;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Ataque;
import modelo.Batalla;
import modelo.SerVivo;
import modelo.TipoAtaque;
import modelo.TipoAtaquePokemon;

public class Controlador {
    private VistaPokemonGUI vista;
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private int indicePokemonActual = 0; // Para llevar el control del Pokémon actual
    private boolean mostrandoJugador1 = true; // Para alternar entre los dos entrenadores

    Controlador(VistaPokemonGUI vista) {
       this.vista = vista;
       this.vista.setControlador(this);
    
    }



    public void flujo() {
        System.out.println("Flujo de control iniciado." + vista.getEscena());
        switch(vista.getEscena()) {
            case 0:
                vista.bienvenido();
                break;
        
            case 2:
                vista.switchToNextPanel(vista.showThirdPanel());
                break;
            case 3:
                vista.entrenadores();
                break;
            case 4:
                vista.switchToNextPanel(vista.showFifthPanel(vista.getNombre1()));
                break;
            case 5:
                 vista.pokemones();
                if (!(vista.getPokemon1().isEmpty()) && !(vista.getPokemon2().isEmpty()) && !(vista.getPokemon3().isEmpty())) {
                     flujo();
                }

                break;
                case 6:
                        if (mostrandoJugador1) {
                            if (entrenador1 == null) {

                                entrenador1 = Entrenador.capturarEntrenador(vista.getNombre1(), vista.getPokemon1(), vista.getPokemon2(),vista.getPokemon3());
                                indicePokemonActual = 0;
                               
                            }

                            if (indicePokemonActual < entrenador1.getEquipo().size()) {
                                vista.switchToNextPanel(vista.showSixthPanel(entrenador1.getEquipo().get(indicePokemonActual)));
                                indicePokemonActual++;
                            } else {
                                // Ya mostró los 3 Pokémon del jugador 1
                                mostrandoJugador1 = false;
                                indicePokemonActual = 0;

                                // Limpia los campos y muestra el panel de ingreso de pokémon para el jugador 2
                                vista.switchToNextPanel(vista.showFifthPanel(vista.getNombre2()));
                            }
                        } else {
                            if (entrenador2 == null) {
                                entrenador2 = Entrenador.capturarEntrenador(vista.getNombre2(),vista.getPokemon1(),vista.getPokemon2(),vista.getPokemon3());
                                indicePokemonActual = 0;
                            
                            }

                            if (indicePokemonActual < entrenador2.getEquipo().size()) {
                                vista.switchToNextPanel(vista.showSixthPanel(entrenador2.getEquipo().get(indicePokemonActual)));   // Solucion error de índice 
                                indicePokemonActual++;
                            } else {
                                System.out.println("¡Listos los dos entrenadores!");
                                // Aquí podrías avanzar a la batalla o a otro panel
                          
                            }
                        }
                break;

            default:
                System.out.println("No hay más Pokémon para mostrar.");
           break;
        }
        
    }

    public static void main(String[] args) {
        VistaPokemonGUI vista = new VistaPokemonGUI();
        Controlador control = new Controlador(vista);
        control.flujo();
    }
}