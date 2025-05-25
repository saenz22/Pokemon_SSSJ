package vista;

import controlador.Controlador;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Ataque;
import java.util.ArrayList;

public interface VistaPokemon {

   void bienvenido();
   void entrenadores();
<<<<<<< HEAD
   void pokemones(String nombre);
   void mostrarPokemon(Pokemon pokemon);
=======
   void pokemones();
   void mostrarPokemon(ArrayList<Pokemon> pokemon);
>>>>>>> 2b69a2c4eca6237a16052e4eb374b5190d930e8d
   void ganador(Entrenador entrenador);
   Pokemon elegirPokemon(Entrenador entrenador);
   Ataque elegirAtaque(Pokemon pokemon);
   void setControlador(Controlador controlador);
<<<<<<< HEAD
}
=======
   boolean isError();
}
>>>>>>> 2b69a2c4eca6237a16052e4eb374b5190d930e8d
