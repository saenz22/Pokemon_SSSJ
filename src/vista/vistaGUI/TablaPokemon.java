import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Arrays;

class Pokemon {
    String nombre;
    int nivel, saludActual, saludMaxima;

    public Pokemon(String nombre, int nivel, int saludActual, int saludMaxima) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.saludActual = saludActual;
        this.saludMaxima = saludMaxima;
    }
}

// Botón personalizado para mostrar el Pokémon
class BotonPokemon extends JButton {
    private final Pokemon pokemon;
    private boolean seleccionado = false;

    public BotonPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        setPreferredSize(new Dimension(290, 75));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void seleccionar() {
        this.seleccionado = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Fondo según selección
        if (seleccionado) {
            g.setColor(new Color(30, 100, 180)); // Azul oscuro si está seleccionado
        } else {
            g.setColor(new Color(100, 180, 255)); // Azul claro por defecto
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        // Texto nombre y nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(pokemon.nombre.toUpperCase(), 10, 20);
        g.drawString("Nv" + pokemon.nivel, 10, 40);

        // PS
        g.setColor(Color.ORANGE);
        g.drawString("PS", 200, 20);

        // Barra gris de fondo
        g.setColor(Color.GRAY);
        g.fillRect(230, 12, 80, 10);

        // Barra verde
        int barra = (int)((double)pokemon.saludActual / pokemon.saludMaxima * 80);
        g.setColor(Color.GREEN);
        g.fillRect(230, 12, barra, 10);

        // PS numérico
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(pokemon.saludActual + "/" + pokemon.saludMaxima, 230, 35);

        super.paintComponent(g); // Para eventos del botón
    }
}

public class TablaPokemon {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tabla de Pokémon");
        frame.setSize(605, 327);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel fondo = new JPanel(new GridLayout(4, 2, 5, 5));
        fondo.setBackground(new Color(25, 95, 95));
        fondo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Fila de nombres de entrenadores
        JLabel entrenador1 = new JLabel("Entrenador 1: Ash", SwingConstants.CENTER);
        JLabel entrenador2 = new JLabel("Entrenador 2: Misty", SwingConstants.CENTER);
        entrenador1.setForeground(Color.WHITE);
        entrenador2.setForeground(Color.WHITE);
        entrenador1.setFont(new Font("Arial", Font.BOLD, 16));
        entrenador2.setFont(new Font("Arial", Font.BOLD, 16));
        fondo.add(entrenador1);
        fondo.add(entrenador2);

        // Equipos
        List<Pokemon> equipoAsh = Arrays.asList(
            new Pokemon("Sparky", 20, 9, 51),
            new Pokemon("Alastor", 26, 70, 70),
            new Pokemon("Solais", 22, 60, 60)
        );

        List<Pokemon> equipoMisty = Arrays.asList(
            new Pokemon("Hydroz", 23, 66, 66),
            new Pokemon("Dormilon", 12, 30, 30),
            new Pokemon("Starmie", 28, 80, 80)
        );

        // Listas de botones por equipo
        BotonPokemon[] botonesAsh = new BotonPokemon[3];
        BotonPokemon[] botonesMisty = new BotonPokemon[3];

        // Crear y agregar botones al panel
        for (int i = 0; i < 3; i++) {
            BotonPokemon botonAsh = new BotonPokemon(equipoAsh.get(i));
            BotonPokemon botonMisty = new BotonPokemon(equipoMisty.get(i));

            botonesAsh[i] = botonAsh;
            botonesMisty[i] = botonMisty;

            fondo.add(botonAsh);
            fondo.add(botonMisty);
        }

        // Añadir listeners
        for (int i = 0; i < 3; i++) {
            int finalI = i;

            botonesAsh[i].addActionListener(e -> {
                System.out.println(botonesAsh[finalI].getPokemon().nombre);
                for (BotonPokemon b : botonesAsh) {
                    b.setEnabled(false);
                }
                botonesAsh[finalI].seleccionar();
            });

            botonesMisty[i].addActionListener(e -> {
                System.out.println(botonesMisty[finalI].getPokemon().nombre);
                for (BotonPokemon b : botonesMisty) {
                    b.setEnabled(false);
                }
                botonesMisty[finalI].seleccionar();
            });
        }

        frame.add(fondo);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
