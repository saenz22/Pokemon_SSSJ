package vista;

import controlador.Controlador;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Ataque;

public interface VistaPokemon {

   void bienvenido();
   void entrenadores();
   void pokemones(String nombre);
   void mostrarPokemon(Pokemon pokemon);
   void ganador(Entrenador entrenador);
   Pokemon elegirPokemon(Entrenador entrenador);
   Ataque elegirAtaque(Pokemon pokemon);
   void setControlador(Controlador controlador);
}
