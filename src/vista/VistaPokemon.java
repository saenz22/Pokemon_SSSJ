package vista;

import controlador.Controlador;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Ataque;

public interface VistaPokemon {

   void bienvenido();
   void entrenadores();
   void pokemones();
   // Aquí se llama capturarEntrenador(String nombre, String primero, String segundo, String tercero);
   void mostrarPokemon(Pokemon pokemon);
   void batalla();
   void ganador();
   int getEscena();
   void cambiarEscena();
   Pokemon elegirPokemon(Entrenador entrenador);
   Ataque elegirAtaque();
   String getNombre1();
   String getNombre2();
   String getPokemon1();
   String getPokemon2();
   String getPokemon3();
   void setControlador(Controlador controlador);
}
