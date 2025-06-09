package vista;

import controlador.Controlador;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

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
    
    @Override
    public void bienvenido() {
        System.out.println(""" 
                    Estás a punto de sumergirte en un
                    mundo lleno de aventuras de las
                    que vas a ser protagonista.

                    Te cruzarás con rivales y criaturas
                    salvajes que querrán luchar contigo,
                    pero ¡ánimo, tú puedes!""");
        limpiarConsola();
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
        limpiarConsola();
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
                
            } catch (NoSuchElementException e) {
                System.err.println("Error: No se encontró más entrada. Puede que la entrada haya sido interrumpida.");
                break; // O podrías decidir salir del programa aquí
            }
        }
        controlador.setListaPokemones(pokemon1, pokemon2, pokemon3);
        limpiarConsola();
        controlador.avanzarEscena();
    }

    @Override
    public void elegirPokemon(Entrenador entrenador1, Entrenador entrenador2) {
        controlador.setPokemonActivoEntrenador1(entrenador1.getEquipo().get(eleccion(entrenador1)-1));
        controlador.setPokemonActivoEntrenador2(entrenador2.getEquipo().get(eleccion(entrenador2)-1));
        controlador.ordenarContrincantes();
        System.out.println(controlador.getOrden().get(0).getNombre() + " vs " + controlador.getOrden().get(1).getNombre());
    }

    private byte eleccion(Entrenador entrenador) {
        ArrayList<Pokemon> pokemones = entrenador.getEquipo();
        int opcion = 0; 
        System.out.println("Elige un Pokémon:" + entrenador.getNombre());
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.println((i + 1) + ". " + pokemones.get(i).getNombre());
        }
        while(true) {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has elegido a " + pokemones.get(0).getNombre());
                        break; 
                    case 2:
                        System.out.println("Has elegido a " + pokemones.get(1).getNombre());
                        break;
                    case 3:
                        System.out.println("Has elegido a " + pokemones.get(2).getNombre());
                        break;
                    default:
                        System.out.println("Opción no válida. Elige un número entre 1 y 3.");
                }
                if (opcion >= 1 && opcion <= 3) {
                    break;
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
        limpiarConsola();
        return (byte) opcion;
    }

    @Override
    public void elegirAtaque(Pokemon pokemon) {
        ArrayList<Ataque> ataques = pokemon.getAtaques();
        int opcion = 0; 
        System.out.println("Elige un Ataque para : " + pokemon.getNombre());
        for (int i = 0; i < ataques.size(); i++) {
            System.out.println((i + 1) + ". " + ataques.get(i).getNombre() + " / " + ataques.get(i).getPoder());
        }
        while(true) {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has elegido a " + ataques.get(0).getNombre());
                        break;
                    case 2:
                        System.out.println("Has elegido a " + ataques.get(1).getNombre());
                        break;
                    case 3:
                        System.out.println("Has elegido a " + ataques.get(2).getNombre());
                        break;
                    case 4:
                        System.out.println("Has elegido a " + ataques.get(3).getNombre());
                        break;
                    default:
                        System.out.println("Opción no válida. Elige un número entre 1 y 4.");
                }
                if (opcion >= 1 && opcion <= 4) {
                    break;
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
        limpiarConsola();
        controlador.atacar(ataques.get(opcion-1));
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mostrarPokemon(ArrayList<Pokemon> pokemon) {
        for (int i = 0; i < pokemon.size(); i++) {  
            String stats = "Pokémon: " + pokemon.get(i).getNombre() + " | Tipo: " + pokemon.get(i).getTipo().toString() + "\nNivel: " + String.valueOf(pokemon.get(i).getNivel()) + " | HP: " + String.valueOf(pokemon.get(i).getHp()) + "\nAtaque: " + String.valueOf(pokemon.get(i).getAtk()) + " | Ataque Especial: " + String.valueOf(pokemon.get(i).getAtkEs()) + "\nDefensa: " + String.valueOf(pokemon.get(i).getDf()) + " | Defensa Especial: " + String.valueOf(pokemon.get(i).getDfEs()) + "\nVelocidad: " + String.valueOf(pokemon.get(i).getVelocidad());
            System.out.println(stats);
            System.out.println("\n");
            limpiarConsola();
        }
        controlador.avanzarEscena();
        elegirAtaque(controlador.getOrden().get(0));
    }
    
    @Override
    public void ganador(Entrenador entrenador) {
        System.out.println("\n¡El ganador es: " + entrenador.getNombre() + "!");
        System.exit(0);
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public void continuar() {
        elegirAtaque(controlador.getOrden().get(0));
    }

    public void limpiarConsola() {
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void mostrarLogro(String nombre, String descripcion, String entrenador) {
        System.out.println("\n¡Felicidades " + entrenador + "!");
        System.out.println("Logro Desbloqueado: " + nombre);
        System.out.println("Descripción: " + descripcion);
        limpiarConsola();
    }
}