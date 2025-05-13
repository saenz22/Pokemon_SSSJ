package controlador;

import vista.*;
import modelo.Batalla;

public class Controlador {
    private VistaPokemon vista;
    private Batalla batalla;
    public boolean esGui;

    public Controlador(VistaPokemon vista, Batalla batalla, boolean esGui) {
       this.vista = vista;
       this.batalla = batalla;
       this.esGui = esGui;
       //vista.setControlador(this);
    }

    // Métodos para manejar las vistas

    public void cambiarVista(){
        if(esGui){
            vista = new VistaPokemonConsola();
        }else{
            vista = new VistaPokemonGUI();
        }
        esGui = !esGui;
        //vista.setControlador(this);
        //vista.bienvenido();
    }
}