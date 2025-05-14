package vista;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.Controlador;
import modelo.Entrenador;
import modelo.Pokemon;

public class VistaPokemonConsola implements VistaPokemon {
    private Scanner scanner;
    

    @Override
    public void bienvenido() {
        System.out.println(""" 
                    Estás a punto de sumergirte en un
                    mundo lleno de aventuras de las
                    que vas a ser protagonista.

                    Te cruzarás con rivales y criaturas
                    salvajes que querrán luchar contigo,
                    pero ¡ánimo, tú puedes!""");
        
        throw new UnsupportedOperationException("Unimplemented method 'bienvenido'");
    }

    @Override
    public void entrenadores() {
        scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del Entrenador 1: ");
        nombre1 = scanner.nextLine();
        System.out.print("Ingrese el nombre del Entrenador 2: ");
        nombre2 = scanner.nextLine();
        String nombre1 = "Jugador 1";
        String nombre2 = "Jugador 2";
        String texto = """
            Bienvenidos a Pokémon {jugador1} y 
                {jugador2} les espera un gran 
                desafío en su aventura.

                Sus pokemones serán asignados 
                aleatoriamente, y tendrán que 
                enfrentarse para demostrar 
                quién es el mejor entrenador.
            """;
        texto = texto.replace("{jugador1}", nombre1).replace("{jugador2}", nombre2);
        System.out.println(texto);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entrenadores'");
    }

    @Override
    public void pokemones() {
        scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del primer Pokémon: ");
        String pokemon1 = scanner.nextLine();
        System.out.print("Ingrese el nombre del segundo Pokémon: ");
        String pokemon2 = scanner.nextLine();
        System.out.print("Ingrese el nombre del tercer Pokémon: ");
        String pokemon3 = scanner.nextLine();

        System.out.println("Los Pokémon ingresados son:");
        System.out.println("1. " + pokemon1);
        System.out.println("2. " + pokemon2);
        System.out.println("3. " + pokemon3);
        throw new UnsupportedOperationException("Unimplemented method 'pokemones'");
    }

    @Override
    public void batalla() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'batalla'");
    }

    @Override
    public void getEscena() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEscena'");
    }

    @Override
    public void cambiarEscena() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarEscena'");
    }

    @Override
    public void elegirPokemon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elegirPokemon'");
    }

    @Override
    public void elegirAtaque() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elegirAtaque'");
    }

    @Override
    public void setControlador(Controlador controlador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setControlador'");
    }

    @Override
    public void mostrarPokemon(Pokemon pokemon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarPokemon'");
    }

    @Override
    public void ganador() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ganador'");
    }

    @Override
    public Pokemon elegirPokemon(Entrenador entrenador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elegirPokemon'");
    }

    @Override
    public String getNombre1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre1'");
    }

    @Override
    public String getNombre2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre2'");
    }

    @Override
    public String getPokemon1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPokemon1'");
    }

    @Override
    public String getPokemon2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPokemon2'");
    }

    @Override
    public String getPokemon3() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPokemon3'");
    }

    
    // Implementar métodos
}
