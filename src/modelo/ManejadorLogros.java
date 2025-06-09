package modelo;

import controlador.Controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ManejadorLogros {

    // Atributos
    private Controlador controlador;
    private static final ArrayList<Logros> listaLogros = new ArrayList<>(Arrays.asList(Logros.values())); // Lista de logros disponibles
    private static Map<Logros, Entrenador> logrosDesbloqueados = new HashMap<>(); // Lista de logros registrados
    private static ManejadorLogros instancia = null;

    public ManejadorLogros(Controlador controlador) {
        this.controlador = controlador;
    }

    // Aplicando patrón Singleton para asegurar que solo haya una instancia de ManejadorLogros
    public static ManejadorLogros instanciar(Controlador controlador) {
        if (instancia == null) {
            instancia = new ManejadorLogros(controlador);
        }
        return instancia;
    }

    static {
        logrosDesbloqueados.put(listaLogros.get(0), null); // IMBATIDO
        logrosDesbloqueados.put(listaLogros.get(1), null); // POKEMON_LEGENDARIO
        logrosDesbloqueados.put(listaLogros.get(2), null); // PELOS
        logrosDesbloqueados.put(listaLogros.get(3), null); // MAESTRO_POKEMON
        logrosDesbloqueados.put(listaLogros.get(4), null); // YO_TE_ELIJO
        logrosDesbloqueados.put(listaLogros.get(5), null); // ROCKET
    }

    public Map<Logros, Entrenador> getLogrosDesbloqueados() {
        return logrosDesbloqueados;
    }

    public void agregarLogro(Entrenador entrenador) {
        for (Logros logro : listaLogros) {
            entrenador.desbloquearLogro(logro, this);
            controlador.notificarLogro(logro.getNombre(), logro.getDescripcion(), entrenador.getNombre());
        }
    }

    public void logroSecreto() {
        // Método para desbloquear el logro secreto
        for (Entrenador entrenadorLogro : logrosDesbloqueados.values()) {
            if (entrenadorLogro != null) {
                controlador.notificarLogro(Logros.SECRETO.getNombre(), Logros.SECRETO.getDescripcion(), "Ash");
            }
        }
    }
}