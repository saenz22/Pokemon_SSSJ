package vista;

import controlador.Controlador;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

public class VistaPokemonConsola implements VistaPokemon {
    private Scanner scanner;
    private String nombre1, nombre2, pokemon1, pokemon2, pokemon3;
    private byte contadorEscena;
    
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
        nombre1 = "";
        nombre2 = "";
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
        pokemon1 = "";
        pokemon2 = "";
        pokemon3 = "";
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
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'pokemones'");
    }

    @Override
    public void batalla() {
        System.out.println("""
                ¡Es hora de la batalla!
            
                """);
      //  controlador.batalla(nombre1, nombre2, pokemon1, pokemon2, pokemon3);

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'batalla'");
    }
    @Override
    public Pokemon elegirPokemon(Entrenador entrenador) {
        ArrayList<Pokemon> pokemones = entrenador.getEquipo();
        int opcion = 0; 
        System.out.println("Elige un Pokémon:" + entrenador.getNombre());
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.println((i + 1) + ". " + pokemones.get(i).getNombre());
        }
        try {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> System.out.println("Has elegido a " + pokemones.get(0).getNombre()); 
                    case 2 -> System.out.println("Has elegido a " + pokemones.get(1).getNombre());
                    case 3 -> System.out.println("Has elegido a " + pokemones.get(2).getNombre());
                    default -> System.out.println("Opción no válida. Elige un número entre 1 y 3.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                scanner.next(); 
            }
            return pokemones.get(opcion-1);
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Por favor, ingresa un número.");
            scanner.next();
        }

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elegirPokemon1'");
    }

    @Override
    public byte getEscena() {
        return contadorEscena;
    }

    @Override
    public void cambiarEscena() {
        switch (contadorEscena) {
            case 0 -> {
                bienvenido();
                contadorEscena++;
            }
            case 1 -> {
                entrenadores();
                contadorEscena++;
            }
            case 2 -> {
                pokemones();
                contadorEscena++;
            }
            case 3 -> {
                batalla();
                contadorEscena++;
            }
        }
        
    }
    @Override
    public Ataque elegirAtaque(Pokemon pokemon) {
        ArrayList<Ataque> ataques = pokemon.getAtaques();
        int opcion = 0; 
        System.out.println("Elige un Ataque para : " + pokemon.getNombre());
        for (int i = 0; i < ataques.size(); i++) {
            System.out.println((i + 1) + ". " + ataques.get(i).getNombre());
        }
        try {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> System.out.println("Has elegido a " + ataques.get(0).getNombre()); 
                    case 2 -> System.out.println("Has elegido a " + ataques.get(1).getNombre());
                    case 3 -> System.out.println("Has elegido a " + ataques.get(2).getNombre());
                    case 4 -> System.out.println("Has elegido a " + ataques.get(3).getNombre());
                    default -> System.out.println("Opción no válida. Elige un número entre 1 y 4.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                scanner.next(); 
            }
            return ataques.get(opcion-1);
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Por favor, ingresa un número.");
            scanner.next();
        }
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
    public void ganador() {
        System.out.println("""
                ¡Felicidades!
                Has ganado la batalla.
                """);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ganador'");
    }
    @Override
    public String getNombre1() {
        return nombre1;
    }
    @Override
    public String getNombre2() {
        return nombre2;
    }
    @Override
    public String getPokemon1() {
        return pokemon1;
    }
    @Override
    public String getPokemon2() {
        return pokemon2;
    }
    @Override
    public String getPokemon3() {
        return pokemon3;
    }


    
    // Implementar métodos
}
