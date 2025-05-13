package controlador;

import java.awt.Desktop.Action;
import vista.VistaPokemonGUI;

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