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
    private Controlador controlador;

    public VistaPokemonConsola() {
        scanner = new Scanner(System.in);
        this.nombre1 = "";
        this.nombre2 = "";
        this.pokemon1 = "";
        this.pokemon2 = "";
        this.pokemon3 = "";
    }

    // Hacer souts mucho más descriptivos y estéticos, sería bueno agregar un await o algo parecido, métodos para que se sienta más ameno
    
    @Override
    public void bienvenido() {
        System.out.println(""" 
                    Estás a punto de sumergirte en un
                    mundo lleno de aventuras de las
                    que vas a ser protagonista.

                    Te cruzarás con rivales y criaturas
                    salvajes que querrán luchar contigo,
                    pero ¡ánimo, tú puedes!""");
        controlador.avanzarEscena();
    }

    @Override
    public void entrenadores() {
        boolean nombresValidos = false;
        while (!nombresValidos) {
            try {
                System.out.print("Ingrese el nombre del Entrenador 1: ");
                nombre1 = scanner.nextLine().trim(); 
                if (nombre1.isEmpty()) {
                    System.out.println("Error: El nombre del Entrenador 1 no puede estar vacío.");
                    scanner.nextLine();
                    continue; 
                }

                System.out.print("Ingrese el nombre del Entrenador 2: ");
                nombre2 = scanner.nextLine().trim();
                if (nombre2.isEmpty()) {
                    System.out.println("Error: El nombre del Entrenador 2 no puede estar vacío.");
                    scanner.nextLine();
                    continue; 
                }
                nombresValidos = true; 

            } catch (NoSuchElementException e) {
                System.err.println("Error: No se encontró más entrada. Saliendo...");
                break;
            } catch (IllegalStateException e) {
                System.err.println("Error: El scanner ha sido cerrado. Saliendo...");
                break;
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
        controlador.setListaEntrenadores(nombre1, nombre2);
        controlador.avanzarEscena();
    }

    @Override
    public void pokemones() {
        boolean PokemonValidos = false;
        while (!PokemonValidos) {
            try {
                System.out.print("Ingresa el nombre del primer Pokémon: ");
                this.pokemon1 = scanner.nextLine().trim();
                if (pokemon1.isEmpty()) {
                    System.out.println("Error: El nombre del primer Pokémon no puede estar vacío.");
                    scanner.nextLine();
                    continue;
                }

                System.out.print("Ingresa el nombre del segundo Pokémon: ");
                this.pokemon2 = scanner.nextLine().trim();
                if (pokemon2.isEmpty()) {
                    System.out.println("Error: El nombre del segundo Pokémon no puede estar vacío.");
                    scanner.nextLine();
                    continue;
                }

                System.out.print("Ingresa el nombre del tercer Pokémon: ");
                this.pokemon3 = scanner.nextLine().trim();
                if (pokemon3.isEmpty()) {
                    System.out.println("Error: El nombre del tercer Pokémon no puede estar vacío.");
                    scanner.nextLine();
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
        controlador.setListaPokemones(pokemon1, pokemon2, pokemon3);
        controlador.avanzarEscena();
    }

    @Override
    public void elegirPokemon(Entrenador entrenador1, Entrenador entrenador2) {
        controlador.setPokemonActivoEntrenador1(entrenador1.getEquipo().get(eleccion(entrenador1)-1));
        controlador.setPokemonActivoEntrenador2(entrenador2.getEquipo().get(eleccion(entrenador2)-1));
        controlador.avanzarEscena();
    }

    private byte eleccion(Entrenador entrenador) {
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
                scanner.nextLine();
                }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Por favor, ingresa un número.");
            scanner.nextLine();
        }
        return (byte) opcion;
    }

    @Override
    public void elegirAtaque(Pokemon pokemon) {
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
                scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Por favor, ingresa un número.");
            scanner.next();
        }
        controlador.atacar(ataques.get(opcion-1));
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mostrarPokemon(ArrayList<Pokemon> pokemon) {
        for (int i = 0; i < pokemon.size(); i++) {  
            System.out.println("Pokémon: " + pokemon.get(i).getNombre());
            System.out.println("Tipo: " + pokemon.get(i).getTipo());
            System.out.println("Nivel: " + String.valueOf(pokemon.get(i).getNivel()));
            System.out.println("HP: " + String.valueOf(pokemon.get(i).getHp()));
            System.out.println("ATAQUE: " + String.valueOf(pokemon.get(i).getAtk()));
            System.out.println("DEFENSA: " + String.valueOf(pokemon.get(i).getDf()));
            System.out.println("ATAQUE ESPECIAL: " + String.valueOf(pokemon.get(i).getAtkEs()));
            System.out.println("DEFENSA ESPECIAL: " + String.valueOf(pokemon.get(i).getDfEs()));
            System.out.println("VELOCIDAD: " +  String.valueOf(pokemon.get(i).getVelocidad()) + "\n");
        }
        controlador.avanzarEscena();
        elegirAtaque(controlador.getOrden().get(0));
    }
    
    @Override
    public void ganador(Entrenador entrenador) {
        System.out.println("\n¡El ganador es: " + entrenador.getNombre() + "!");
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public void continuar() {
        elegirAtaque(controlador.getOrden().get(0));
    }
}