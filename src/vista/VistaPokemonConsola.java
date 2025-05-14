package vista;

import controlador.Controlador;
import modelo.Entrenador;
import modelo.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.IllegalStateException;
import java.util.InputMismatchException;

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
       String nombre1 = "";
       String nombre2 = "";
        boolean NombresValidos = false;
        while (NombresValidos == false) {
            try {
                System.out.print("Ingrese el nombre del Entrenador 1: ");
                nombre1 = scanner.nextLine().trim(); 
                if (nombre1.isEmpty()) {
                    System.out.println("Error: El nombre del Entrenador 1 no puede estar vacío.");
                    continue; 
                }

                System.out.print("Ingrese el nombre del Entrenador 2: ");
                nombre2 = scanner.nextLine().trim();
                if (nombre2.isEmpty()) {
                    System.out.println("Error: El nombre del Entrenador 2 no puede estar vacío.");
                    continue; 
                }

                NombresValidos = true; 

            } catch (NoSuchElementException e) {
                System.err.println("Error: No se encontró más entrada. Saliendo...");
                return;
            } catch (IllegalStateException e) {
                System.err.println("Error: El scanner ha sido cerrado. Saliendo...");
                return;
            }
        }

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
        String pokemon1 = "";
        String pokemon2 = "";
        String pokemon3 = "";
        boolean PokemonValidos = false;

        while (!PokemonValidos) {
            try {
                System.out.print("Ingrese el nombre del primer Pokémon: ");
                pokemon1 = scanner.nextLine().trim();
                if (pokemon1.isEmpty()) {
                    System.out.println("Error: El nombre del primer Pokémon no puede estar vacío.");
                    continue;
                }

                System.out.print("Ingrese el nombre del segundo Pokémon: ");
                pokemon2 = scanner.nextLine().trim();
                if (pokemon2.isEmpty()) {
                    System.out.println("Error: El nombre del segundo Pokémon no puede estar vacío.");
                    continue;
                }

                System.out.print("Ingrese el nombre del tercer Pokémon: ");
                pokemon3 = scanner.nextLine().trim();
                if (pokemon3.isEmpty()) {
                    System.out.println("Error: El nombre del tercer Pokémon no puede estar vacío.");
                    continue;
                }

                PokemonValidos = true; // Si todos los nombres no están vacíos, salimos del bucle

                System.out.println("Los Pokémon ingresados son:");
                System.out.println("1. " + pokemon1);
                System.out.println("2. " + pokemon2);
                System.out.println("3. " + pokemon3);

            } catch (NoSuchElementException e) {
                System.err.println("Error: No se encontró más entrada. Puede que la entrada haya sido interrumpida.");
                break; // O podrías decidir salir del programa aquí
            } catch (IllegalStateException e) {
                System.err.println("Error: El scanner ha sido cerrado.");
                break; // O podrías decidir salir del programa aquí
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'pokemones'");
    }

    @Override
    public void batalla() {
        System.out.println("""
                ¡Es hora de la batalla!
                
                """);
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
        celebracion();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ganador'");
    }

    @Override
    public Pokemon elegirPokemon(Entrenador entrenador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elegirPokemon'");
    }


    
    // Implementar métodos
}
