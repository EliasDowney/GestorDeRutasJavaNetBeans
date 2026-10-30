package View;

import Model.AdministradorModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AdminVista extends JFrame {

    public AdminVista(AdministradorModel admin) {
        setTitle("Colegio La Divina Ruta - Panel de Administracion");
        setSize(720, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("PANEL DE ADMINISTRACION", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 24));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        String nombre = (admin != null) ? admin.getNombre() : "Administrador";
        JLabel sesion = new JLabel("Sesion: " + nombre, SwingConstants.CENTER);
        sesion.setForeground(Tema.GOLD);
        sesion.setFont(Tema.fuente(Font.PLAIN, 14));
        sesion.setAlignmentX(CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(20));
        header.add(titulo);
        header.add(Box.createVerticalStrut(4));
        header.add(sesion);
        header.add(Box.createVerticalStrut(20));
        add(header, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(3, 2, 18, 18));
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        centro.add(modulo("Matriculas", () -> new MatriculasVista().setVisible(true)));
        centro.add(modulo("Rutas", () -> new RutasVista(admin).setVisible(true)));
        centro.add(modulo("Conductores", () -> new ConductoresVista().setVisible(true)));
        centro.add(modulo("Vehiculos", () -> new VehiculosVista().setVisible(true)));
        centro.add(modulo("Coordinadores", () -> new CoordinadoresVista().setVisible(true)));
        centro.add(modulo("Novedades", () -> new NovedadesVista().setVisible(true)));
        add(centro, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        footer.setBackground(Tema.LIGHT);
        JButton btnSalirApp = new JButton("Salir de la aplicacion");
        estiloBoton(btnSalirApp, Tema.NAVY, Color.WHITE);
        btnSalirApp.addActionListener(e -> salir());
        JLabel copyright = new JLabel("© 2026 Colegio La Divina Ruta");
        copyright.setForeground(Tema.GRAY);
        copyright.setFont(Tema.fuente(Font.PLAIN, 12));
        footer.add(btnSalirApp);
        footer.add(copyright);
        add(footer, BorderLayout.SOUTH);
    }

    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea salir de la aplicacion?", "Salir", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private JButton modulo(String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setBackground(Tema.NAVY);
        boton.setForeground(Color.WHITE);
        boton.setFont(Tema.fuente(Font.BOLD, 18));
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(280, 90));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(e -> accion.run());
        return boton;
    }

    private static void estiloBoton(JButton boton, Color fondo, Color texto) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(Tema.fuente(Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

}
