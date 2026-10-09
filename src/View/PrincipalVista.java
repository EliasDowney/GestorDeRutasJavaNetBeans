package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PrincipalVista extends JFrame {

    public PrincipalVista() {
        setTitle("Colegio La Divina Ruta - Sistema de Rutas Escolares");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("COLEGIO LA DIVINA RUTA", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 28));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel subtitulo = new JLabel("Sistema de Gestion de Rutas Escolares", SwingConstants.CENTER);
        subtitulo.setForeground(Tema.GOLD);
        subtitulo.setFont(Tema.fuente(Font.PLAIN, 15));
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(35));
        header.add(titulo);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitulo);
        header.add(Box.createVerticalStrut(35));
        add(header, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setBackground(Color.WHITE);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JButton btnInicio = crearBoton("Inicio", Tema.NAVY, Color.WHITE);
        JButton btnMatriculas = crearBoton("Matriculas", Tema.NAVY, Color.WHITE);
        JButton btnRutas = crearBoton("Rutas", Tema.GOLD, Color.BLACK);

        btnInicio.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Bienvenido al Sistema de Rutas Escolares\nColegio La Divina Ruta",
                "Inicio", JOptionPane.INFORMATION_MESSAGE));

        btnMatriculas.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Ventana de Matriculas: proximamente\n(Registro de estudiantes)",
                "Matriculas", JOptionPane.INFORMATION_MESSAGE));

        btnRutas.addActionListener(e -> new LoginVista(this).setVisible(true));

        centro.add(btnInicio);
        centro.add(Box.createVerticalStrut(18));
        centro.add(btnMatriculas);
        centro.add(Box.createVerticalStrut(18));
        centro.add(btnRutas);
        add(centro, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(Tema.LIGHT);
        JLabel copyright = new JLabel("© 2026 Colegio La Divina Ruta - Todos los derechos reservados Javier Cicuamia");
        copyright.setForeground(Tema.GRAY);
        copyright.setFont(Tema.fuente(Font.PLAIN, 12));
        footer.add(copyright);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(CENTER_ALIGNMENT);
        boton.setPreferredSize(new Dimension(300, 52));
        boton.setMaximumSize(new Dimension(300, 52));
        boton.setMinimumSize(new Dimension(300, 52));
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFont(Tema.fuente(Font.BOLD, 17));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return boton;
    }

}
