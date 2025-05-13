package controlador;

import java.awt.Desktop.Action;
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

    Controlador(VistaPokemonGUI vista) {
       this.vista = new VistaPokemonGUI();
       this.vista.setControlador(this);
    
    }

    

    public void flujo() {
        System.out.println("Flujo de control iniciado." + vista.currentPanel);
        switch(vista.currentPanel) {
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
                
                //vista.switchToNextPanel(vista.showFifthPanel(vista.getNombre2()));

                break;
                case 6:
                Entrenador entrenador1 = Entrenador.capturarEntrenador(vista.getNombre1(), vista.getPokemon1(), vista.getPokemon2(), vista.getPokemon3());
                System.out.println("Entrenador 1: " + entrenador1.getNombre());
                System.out.println("Pokémon 1: " + entrenador1.getEquipo().get(0).getNombre());
                System.out.println("Pokémon 2: " + entrenador1.getEquipo().get(1).getNombre());
                System.out.println("Pokémon 3: " + entrenador1.getEquipo().get(2).getNombre());
                for(int i = 0; i < entrenador1.getEquipo().size(); i++) {
                   vista.switchToNextPanel(vista.showSixthPanel(entrenador1.getEquipo().get(i)));
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