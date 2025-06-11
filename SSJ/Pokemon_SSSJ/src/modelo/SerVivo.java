package modelo;

import java.io.Serializable;

public abstract class SerVivo implements Serializable {
    
    // Esta es la clase Padre de Entrenador y Pokemon
    private static final long serialVersionUID = 1L; // Serialización para guardar seres vivos}
    protected String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Constructor
    public SerVivo(String nombre){
        this.nombre = nombre;
    }
}