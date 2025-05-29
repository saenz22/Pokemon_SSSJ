package vista.vistaGUI;

import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import modelo.Entrenador;
import modelo.Pokemon;
import controlador.Controlador;

// Botón personalizado para mostrar el Pokémon
class BotonPokemon extends JButton {
    Pokemon pokemon;
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

        if (pokemon.getImagen().getImage() != null) {
        g.drawImage(pokemon.getImagen().getImage(), 10, 10, 50, 50, this); // (x, y, ancho, alto)
        }

        // Texto nombre y nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(pokemon.getNombre().toUpperCase(), 70, 20);
        g.drawString("Nv" + pokemon.getNivel(), 70, 40);

        // PS
        g.setColor(Color.ORANGE);
        g.drawString("PS", 200, 20);

        // Barra gris de fondo
        g.setColor(Color.GRAY);
        g.fillRect(230, 12, 80, 10);

        // Barra verde
        int barra = (int)((double)pokemon.getHp() / pokemon.getHPMAX() * 80);
        g.setColor(Color.GREEN);
        g.fillRect(230, 12, barra, 10);

        // PS numérico
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(pokemon.getHp() + "/" + pokemon.getHPMAX(), 230, 35);

        super.paintComponent(g); // Para eventos del botón
    }
}

public class TablaPokemon {
    private Controlador controlador;
    private boolean seleccionado1 = false, seleccionado2 = false;

    public TablaPokemon(Controlador controlador) {
        this.controlador = controlador;
    }
    public void mostrarTabla(Entrenador e1, Entrenador e2) {
        JFrame frame = new JFrame("Tabla de Pokémon");
        frame.setSize(605, 327);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel fondo = new JPanel(new GridLayout(4, 2, 5, 5));
        fondo.setBackground(new Color(25, 95, 95));
        fondo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Hacer foco en el panel para detectar la tecla
        fondo.setFocusable(true);
        fondo.requestFocusInWindow(); // Requiere que la ventana esté visible

        fondo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (seleccionado1 && seleccionado2) {   
                        frame.dispose(); // Cierra la ventana
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Por favor, seleccione un pokemon para cada entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Fila de nombres de entrenadores
        JLabel entrenador1 = new JLabel("Entrenador 1: " + e1.getNombre(), SwingConstants.CENTER);
        JLabel entrenador2 = new JLabel("Entrenador 2: " + e2.getNombre(), SwingConstants.CENTER);
        entrenador1.setForeground(Color.WHITE);
        entrenador2.setForeground(Color.WHITE);
        entrenador1.setFont(new Font("Arial", Font.BOLD, 16));
        entrenador2.setFont(new Font("Arial", Font.BOLD, 16));
        fondo.add(entrenador1);
        fondo.add(entrenador2);

        // Equipos
        List<Pokemon> equipoE1 = e1.getEquipo();
        List<Pokemon> equipoE2 = e2.getEquipo();

        // Listas de botones por equipo
        BotonPokemon[] botonesE1 = new BotonPokemon[3];
        BotonPokemon[] botonesE2 = new BotonPokemon[3];

        // Crear y agregar botones al panel
        for (int i = 0; i < 3; i++) {
            BotonPokemon botonE1 = new BotonPokemon(equipoE1.get(i));
            BotonPokemon botonE2 = new BotonPokemon(equipoE2.get(i));

            botonesE1[i] = botonE1;
            botonesE2[i] = botonE2;

            fondo.add(botonE1);
            fondo.add(botonE2);
        }

        // Añadir listeners
        for (int i = 0; i < 3; i++) {
            int finalI = i;

            botonesE1[i].addActionListener(_ -> {
                System.out.println(botonesE1[finalI].getPokemon().getNombre());
                for (BotonPokemon b : botonesE1) {
                    b.setEnabled(false);
                }
                botonesE1[finalI].seleccionar();
                controlador.setPokemonActivoEntrenador1(botonesE1[finalI].getPokemon());
                seleccionado1 = true;
            });

            botonesE2[i].addActionListener(_ -> {
                System.out.println(botonesE2[finalI].getPokemon().getNombre());
                for (BotonPokemon b : botonesE2) {
                    b.setEnabled(false);
                }
                botonesE2[finalI].seleccionar();
                controlador.setPokemonActivoEntrenador2(botonesE2[finalI].getPokemon());
                seleccionado2 = true;
            });
        }

        frame.add(fondo);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fondo.requestFocusInWindow();
    }
}