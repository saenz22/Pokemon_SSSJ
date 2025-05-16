import vista.*;
import controlador.Controlador;

public class App {
    public static void main(String[] args) throws Exception {
        Controlador controlador;
        VistaPokemon vista = new VistaPokemonConsola();
        controlador = new Controlador(vista, false);
        controlador.flujo();
    }
}