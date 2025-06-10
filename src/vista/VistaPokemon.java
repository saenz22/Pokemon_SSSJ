package vista;

import controlador.Controlador;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.Pokemon;

import java.util.ArrayList;

public interface VistaPokemon {

   void bienvenido();
   void entrenadores();
   void pokemones();
   void mostrarPokemon(ArrayList<Pokemon> pokemon);
   void ganador(Entrenador entrenador);
   void elegirPokemon(Entrenador entrenador, Entrenador entrenador2);
   void elegirAtaque(Pokemon pokemon);
   void setControlador(Controlador controlador);
   void continuar();
   void mostrarLogro(String nombre, String descripcion, String nombreEntrenador);
   void mostrarHistorialAtaques(ArrayList<Ataque> ataques, Pokemon defensor);
   void mostrarLogros(ArrayList<String> logros);
   boolean isError();
}