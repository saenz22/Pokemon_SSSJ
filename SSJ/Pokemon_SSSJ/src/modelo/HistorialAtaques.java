package modelo;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class HistorialAtaques {
    
    private final byte LIMITE = 5; // Limite de ataques a guardar
    private Deque<Ataque> historial = new LinkedList<>();
    private static HistorialAtaques instancia = null;

    public static HistorialAtaques instanciar() {
        if (instancia == null) {
            instancia = new HistorialAtaques();
        }
        return instancia;
    }

    public void guardarAtaque(Ataque descripcion) {
        if (historial.size() >= LIMITE) {
            historial.pop();
        }
        historial.push(descripcion);
    }

    public List<Ataque> obtenerHistorial() {
        return new ArrayList<>(historial);
    }
}