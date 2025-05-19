package controlador;

import vista.*;

import java.util.ArrayList;
import java.util.Collections;

import modelo.Batalla;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Ataque;

public class Controlador {
    private VistaPokemon vista;
    private Batalla batalla;
    private Entrenador entrenador1, entrenador2;
    private byte estadoCombate;
    public boolean esGui;
    public byte escena;
    ArrayList<String> listaPokemones, listaEntrenadores;
    Pokemon pokemon1, pokemon2;
    ArrayList<Pokemon> orden;

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }


    public Controlador(VistaPokemon vista, boolean esGui) {
       this.vista = vista;
       this.esGui = esGui;
       vista.setControlador(this);
       this.listaPokemones = new ArrayList<>();
       this.listaEntrenadores = new ArrayList<>();
       this.escena = 0;
    }

    /*
     * Entonces, el objetivo es que la vista maneje el flujo(). Para eso:
     * 1. Quitar el while(true) y hacer que sea la vista la que llame flujo()
     * 
     * 2. Quitar las funciones como tal, que son las escenas literalmente, y 
     *    reemplazarlas por los getters de la vista (getter de textField y tal)
     * 
     * 3. Modificar también las variables que estoy usando en el controlador, de acuerdo a lo que me dice la vista
     * 
     * 4. Hacer que la vistaGUI tenga los listeners ella misma, y que esos listeners llamen los métodos
     *    del controlador, que son los que llaman a los del modelo
     * 
     * 5. Crear en vistaGUI un método registrar eventos que tenga todos los eventos de enter, botones, etc.
     * 
     * Explico para no perderme luego que lo vaya a hacer:
     * Básicamente, la idea es que la vista me retorne las variables metiéndolas de una vez
     * en los parámetros de los métodos del controlador, de acuerdo a la escena en la que se encuentra la vista.
     * 
     * 6. Tienes que separar los eventos que cambian escenas de los eventos que hacen cosas, o ambos
     * 
     * 7. Propuesta para vistaGUI para que logre manejar los eventos según el panel:
     * 
     * public void registrarEventos() {
            campoTexto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (panelActual.equals("Panel1")) {
                        controlador.accionPanel1(campoTexto.getText());
                    } else if (panelActual.equals("Panel2")) {
                        controlador.accionPanel2(campoTexto.getText());
                    }
                }
            });
        }
     *
     * 8. Eso sí, sin importar el panel, se cambiará el panel y se llamará a flujo()   
     * 
     * 9. Está en debate ver elegirPokemon(), si cambio el nombre a pokemonElegido porque como tal el proceso de elección lo hace
     *    la vista, es decir, lo que activa pokemon elegido es el listener de la vista, algo como setPokemonElegido(pokemonElegido)
     * 
     * 10. Pensar en cómo la vista va a manejar el orden de batalla, que le pasa el controlador
     */

    public void flujo() {
        System.out.println("Flujo de control iniciado." + escena);
        switch(escena) {
            case 0:
                vista.bienvenido();
                // Listener para cambiar de escena
                break;
            case 1:
                listaEntrenadores = vista.entrenadores();
                // Aquí tiene que estar el listener que ademas de cambiar de escena, guarda las variables entrenador1 y entrenador2
                break;
            case 2:
                // Listener para guardar los 3 pokemones (Se llama 2 veces, una para cada entrenador)   
                // Cambiar variables por getters de las variables, para poder instanciar los entrenadores
                listaPokemones.addAll(vista.pokemones(listaEntrenadores.get(0)));
                entrenador1 = Entrenador.capturarEntrenador(listaEntrenadores.get(0), listaPokemones.get(0), listaPokemones.get(1), listaPokemones.get(2));
                // Aquí estaría la segunda llamada al listener, hacer condicion:
                //si está por 2da vez mandando pokemones, cambiar de escena
                listaPokemones.addAll(vista.pokemones(listaEntrenadores.get(1)));
                entrenador2 = Entrenador.capturarEntrenador(listaEntrenadores.get(1), listaPokemones.get(3), listaPokemones.get(4), listaPokemones.get(5));
                break;
            case 3:
                // Método para mostrar pokemones
                vista.mostrarPokemon(entrenador1.getEquipo().get(0));
                vista.mostrarPokemon(entrenador1.getEquipo().get(1));
                vista.mostrarPokemon(entrenador1.getEquipo().get(2));
                vista.mostrarPokemon(entrenador2.getEquipo().get(0));
                vista.mostrarPokemon(entrenador2.getEquipo().get(1));
                vista.mostrarPokemon(entrenador2.getEquipo().get(2));
                // Listener para cambiar de escena
                break;
            case 4:
                batalla = Batalla.instanciarBatalla(entrenador1, entrenador2);
                pokemon1 = vista.elegirPokemon(entrenador1);
                pokemon2 = vista.elegirPokemon(entrenador2);
                orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
                iniciarCombate();
                break;
        }        
    }

    public void avanzarEscena() {
        // Este método será el que usará la vista para avanzar de escena, en el listener del Enter puede ser
        escena++;
        flujo();
    }

    public void iniciarCombate() {
        switch(estadoCombate) {
        case -2:
        // El entrenador 1 tiene que elegir un nuevo pokemon, ese nuevo pokemon se definiría con el setter
            orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
            break;
        case -1:
        // El entrenador 2 tiene que elegir un nuevo pokemon, ese nuevo pokemon se definiría con el setter
            orden = batalla.ordenBatalla(pokemon1, pokemon2, false);
            break;
        case 0:
        // Si pokemon2 sigue vivo, es turno de pokemon2
            Collections.reverse(orden);
            break;
        case 1:
            vista.ganador(entrenador1);
            break;
        case 2:
            vista.ganador(entrenador2);
            break;
        }
    }

    public byte atacar(Ataque ataqueElegido) {
        // Entonces el actionListener del botón del ataque, tiene que llamar a este método y pasarle el ataque
        // Después de la llamada de esta función, se llama a iniciarCombate()
        return (byte) batalla.turno(orden.get(0), ataqueElegido, orden.get(1));
    }

    public ArrayList<Pokemon> getOrden() {
        // Este método es quizás el más polémico para mí, porque ahí si que no sé cómo se maneje en la vista la pelea a nivel visual,
        // igual y lo dejo aquí para discutirlo, porque sería útil: al atacante le pones la imagen de espalda y al atacado la imagen
        // de frente, además, así sabes cuál es la vida de quien (así sabes qué barra de vida usar)
        // y cuáles son los ataques específicos del atacante  HpMax.
        return orden;
    }

    public void cambiarVista(){
        if(esGui){
            vista = new VistaPokemonConsola();
        }else{
            vista = new VistaPokemonGUI();
        }
        esGui = !esGui;
        vista.setControlador(this);
        vista.bienvenido();
    }
}