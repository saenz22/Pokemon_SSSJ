package vista;

import java.util.ArrayList;
import controlador.Controlador;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.Pokemon;

public class VistaPokemonConsola implements VistaPokemon {
<<<<<<< HEAD
    private Scanner scanner;
    private String nombre1, nombre2, pokemon1, pokemon2, pokemon3;
    private Controlador controlador;
    ArrayList<String> listaPokemones = new ArrayList<>();
    ArrayList<String> listaEntrenadores = new ArrayList<>();
=======
>>>>>>> 2b69a2c4eca6237a16052e4eb374b5190d930e8d

    @Override
    public void bienvenido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bienvenido'");
    }

    @Override
    public void entrenadores() {
    }

    @Override
    public void pokemones() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pokemones'");
    }

    @Override
    public void mostrarPokemon(ArrayList<Pokemon> pokemon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarPokemon'");
    }

    @Override
    public void ganador(Entrenador entrenador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ganador'");
    }

    @Override
    public Pokemon elegirPokemon(Entrenador entrenador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elegirPokemon'");
    }

    @Override
    public Ataque elegirAtaque(Pokemon pokemon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elegirAtaque'");
    }

    @Override
    public void setControlador(Controlador controlador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setControlador'");
    }
    @Override
    public boolean isError() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isError'");
    }

<<<<<<< HEAD
    @Override
    public void mostrarPokemon(Pokemon pokemon) {
        System.out.println("Pokémon: " + pokemon.getNombre());
        System.out.println("Tipo: " + pokemon.getTipo());
        System.out.println("Nivel: " + String.valueOf(pokemon.getNivel()));
        System.out.println("HP: " + String.valueOf(pokemon.getHp()));
        System.out.println("ATAQUE: " + String.valueOf(pokemon.getAtk()));
        System.out.println("DEFENSA: " + String.valueOf(pokemon.getDf()));
        System.out.println("ATAQUE ESPECIAL: " + String.valueOf(pokemon.getAtkEs()));
        System.out.println("DEFENSA ESPECIAL: " + String.valueOf(pokemon.getDfEs()));
        System.out.println("VELOCIDAD: " +  String.valueOf(pokemon.getVelocidad()));
    }
    
    @Override
    public void ganador(Entrenador entrenador) {
        System.out.println("El ganador es: " + entrenador.getNombre());
    }
=======
 
>>>>>>> 2b69a2c4eca6237a16052e4eb374b5190d930e8d
}