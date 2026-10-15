package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public class PrincipalVista extends JFrame {

    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenedor = new JPanel(tarjetas);
    private final JButton[] navBotones = new JButton[4];
    private final String[] navTarjetas = {"inicio", "quienes", "servicios", "contacto"};

    public PrincipalVista() {
        setTitle("Colegio La Divina Ruta - Sistema de Rutas Escolares");
        setSize(900, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(construirBanner(), BorderLayout.NORTH);

        contenedor.add(tarjetaInicio(), "inicio");
        contenedor.add(tarjetaQuienes(), "quienes");
        contenedor.add(tarjetaServicios(), "servicios");
        contenedor.add(tarjetaContacto(), "contacto");
        add(contenedor, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 8));
        footer.setBackground(Tema.LIGHT);
        JButton btnSalir = new JButton("Salir de la aplicacion");
        btnSalir.setBackground(Tema.NAVY);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(Tema.fuente(Font.BOLD, 13));
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(e -> salir());
        JLabel copyright = new JLabel("© 2026 Colegio La Divina Ruta - Javier Cicuamia");
        copyright.setForeground(Tema.GRAY);
        copyright.setFont(Tema.fuente(Font.PLAIN, 12));
        footer.add(btnSalir);
        footer.add(copyright);
        add(footer, BorderLayout.SOUTH);

        mostrarTarjeta("inicio");
    }

    private JPanel construirBanner() {
        JPanel banner = new JPanel(new BorderLayout());
        banner.setBackground(Tema.NAVY);

        JPanel logotipo = new JPanel();
        logotipo.setBackground(Tema.NAVY);
        logotipo.setLayout(new BoxLayout(logotipo, BoxLayout.Y_AXIS));
        logotipo.setBorder(BorderFactory.createEmptyBorder(16, 24, 10, 24));
        JLabel titulo = new JLabel("COLEGIO LA DIVINA RUTA");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 24));
        titulo.setAlignmentX(LEFT_ALIGNMENT);
        JLabel subtitulo = new JLabel("Sistema de Gestion de Rutas Escolares");
        subtitulo.setForeground(Tema.GOLD);
        subtitulo.setFont(Tema.fuente(Font.PLAIN, 14));
        subtitulo.setAlignmentX(LEFT_ALIGNMENT);
        logotipo.add(titulo);
        logotipo.add(Box.createVerticalStrut(2));
        logotipo.add(subtitulo);
        banner.add(logotipo, BorderLayout.NORTH);

        JPanel navegacion = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
        navegacion.setBackground(new Color(6, 32, 58));
        JButton btnInicio = crearNav("Inicio");
        JButton btnRutas = crearNav("Rutas");
        JButton btnQuienes = crearNav("Quienes somos");
        JButton btnServicios = crearNav("Servicios");
        JButton btnContacto = crearNav("Contactenos");
        navBotones[0] = btnInicio;
        navBotones[1] = btnQuienes;
        navBotones[2] = btnServicios;
        navBotones[3] = btnContacto;

        btnInicio.addActionListener(e -> mostrarTarjeta("inicio"));
        btnQuienes.addActionListener(e -> mostrarTarjeta("quienes"));
        btnServicios.addActionListener(e -> mostrarTarjeta("servicios"));
        btnContacto.addActionListener(e -> mostrarTarjeta("contacto"));
        btnRutas.addActionListener(e -> abrirRutas());

        navegacion.add(btnInicio);
        navegacion.add(btnRutas);
        navegacion.add(btnQuienes);
        navegacion.add(btnServicios);
        navegacion.add(btnContacto);
        banner.add(navegacion, BorderLayout.SOUTH);
        return banner;
    }

    private void abrirRutas() {
        LoginVista login = new LoginVista(this);
        login.setVisible(true);
    }

    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea salir de la aplicacion?", "Salir", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void mostrarTarjeta(String nombre) {
        tarjetas.show(contenedor, nombre);
        for (int i = 0; i < navBotones.length; i++) {
            navBotones[i].setForeground(navTarjetas[i].equals(nombre) ? Tema.GOLD : Color.WHITE);
        }
    }

    private JPanel tarjetaInicio() {
        JPanel panel = tarjeta();
        panel.add(Box.createVerticalGlue());
        JLabel saludo = new JLabel("Bienvenido", SwingConstants.CENTER);
        saludo.setFont(Tema.fuente(Font.BOLD, 34));
        saludo.setForeground(Tema.NAVY);
        saludo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel descripcion = new JLabel("Administre matriculas, rutas, conductores y novedades de forma sencilla.",
                SwingConstants.CENTER);
        descripcion.setFont(Tema.fuente(Font.PLAIN, 16));
        descripcion.setForeground(Tema.GRAY);
        descripcion.setAlignmentX(CENTER_ALIGNMENT);
        JButton btnEntrar = new JButton("Ingresar al sistema");
        btnEntrar.setAlignmentX(CENTER_ALIGNMENT);
        btnEntrar.setBackground(Tema.GOLD);
        btnEntrar.setForeground(Color.BLACK);
        btnEntrar.setFont(Tema.fuente(Font.BOLD, 15));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setPreferredSize(new Dimension(220, 44));
        btnEntrar.setMaximumSize(new Dimension(220, 44));
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(e -> abrirRutas());
        panel.add(saludo);
        panel.add(Box.createVerticalStrut(12));
        panel.add(descripcion);
        panel.add(Box.createVerticalStrut(28));
        panel.add(btnEntrar);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel tarjetaQuienes() {
        JPanel panel = tarjeta();
        panel.add(titulo("Quienes somos"));
        panel.add(parrafo("El Colegio La Divina Ruta es una institucion educativa comprometida con la formacion "
                + "integral de sus estudiantes y con la seguridad de cada uno de ellos."));
        panel.add(parrafo("Contamos con un servicio de transporte escolar organizado por rutas, en el que "
                + "participan conductores, vehiculos y coordinadores, con el objetivo de brindar un "
                + "desplazamiento seguro, puntual y confiable para las familias."));
        panel.add(parrafo("Este sistema de gestion permite administrar de manera eficiente la matricula de "
                + "estudiantes, la asignacion de rutas y el registro de novedades del dia a dia."));
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel tarjetaServicios() {
        JPanel panel = tarjeta();
        panel.add(titulo("Servicios"));
        panel.add(parrafo("Ofrecemos herramientas para la administracion del transporte escolar:"));
        panel.add(salto(10));
        panel.add(servicio("Matriculas", "Registro y gestion de estudiantes."));
        panel.add(servicio("Rutas", "Asignacion automatica de estudiantes segun la ruta y su cupo."));
        panel.add(servicio("Conductores", "Registro y control de los conductores."));
        panel.add(servicio("Vehiculos", "Administracion de la flota y su capacidad."));
        panel.add(servicio("Coordinadores", "Gestion de los coordinadores de ruta."));
        panel.add(servicio("Novedades", "Reporte de novedades de estudiantes y vehiculos."));
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel tarjetaContacto() {
        JPanel panel = tarjeta();
        panel.add(titulo("Contactenos"));
        panel.add(parrafo("Estamos disponibles para atender a las familias de nuestra institucion."));
        panel.add(salto(10));
        panel.add(servicio("Direccion", "Colegio La Divina Ruta - Sede principal"));
        panel.add(servicio("Telefono", "(601) 000 0000"));
        panel.add(servicio("Correo", "contacto@ladivinaruta.edu.co"));
        panel.add(servicio("Horario", "Lunes a viernes, 6:00 a.m. a 5:00 p.m."));
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel tarjeta() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        return panel;
    }

    private JLabel titulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(Tema.fuente(Font.BOLD, 26));
        lbl.setForeground(Tema.NAVY);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Tema.GOLD));
        return lbl;
    }

    private JLabel parrafo(String texto) {
        JLabel lbl = new JLabel("<html><body style='width:640px'>" + texto + "</body></html>");
        lbl.setFont(Tema.fuente(Font.PLAIN, 14));
        lbl.setForeground(Color.DARK_GRAY);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        return lbl;
    }

    private JPanel servicio(String nombre, String descripcion) {
        JPanel fila = new JPanel(new BorderLayout(12, 0));
        fila.setBackground(Color.WHITE);
        fila.setAlignmentX(LEFT_ALIGNMENT);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        JLabel lbl = new JLabel(nombre + ":");
        lbl.setFont(Tema.fuente(Font.BOLD, 14));
        lbl.setForeground(Tema.NAVY);
        lbl.setPreferredSize(new Dimension(130, 24));
        JLabel desc = new JLabel(descripcion);
        desc.setFont(Tema.fuente(Font.PLAIN, 13));
        desc.setForeground(Color.DARK_GRAY);
        fila.add(lbl, BorderLayout.WEST);
        fila.add(desc, BorderLayout.CENTER);
        return fila;
    }

    private java.awt.Component salto(int alto) {
        return Box.createVerticalStrut(alto);
    }

    private JButton crearNav(String texto) {
        JButton boton = new JButton(texto);
        boton.setForeground(Color.WHITE);
        boton.setFont(Tema.fuente(Font.BOLD, 15));
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 22));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

}
