package View;

import Controller.RutaController;
import Model.AdministradorModel;
import Model.EstudianteModel;
import Model.RutaModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class RutasVista extends JFrame {

    private final RutaController controlador = new RutaController();
    private final JComboBox<RutaModel> comboRutas = new JComboBox<>();
    private final JLabel lblConductor = valorInfo();
    private final JLabel lblVehiculo = valorInfo();
    private final JLabel lblCoordinador = valorInfo();
    private final JLabel lblHorario = valorInfo();
    private final JLabel lblEstado = valorInfo();
    private final JLabel lblCupo = new JLabel(" ");
    private final JLabel lblPendientes = new JLabel(" ");
    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"#", "Estudiante", "Documento", "Grado", "Direccion"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);
    private final JButton btnAsignar = new JButton("Asignar automaticamente");
    private final JButton btnQuitar = new JButton("Quitar seleccionado");
    private final JButton btnRefrescar = new JButton("Refrescar");
    private List<EstudianteModel> asignados = new ArrayList<>();
    private boolean cargando;

    public RutasVista(AdministradorModel admin) {
        setTitle("Colegio La Divina Ruta - Asignacion de Rutas");
        setSize(950, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("ASIGNACION DE RUTAS", SwingConstants.CENTER);
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

        JPanel centro = new JPanel(new BorderLayout(15, 0));
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(centro, BorderLayout.CENTER);

        JPanel izquierda = new JPanel();
        izquierda.setBackground(Color.WHITE);
        izquierda.setLayout(new BoxLayout(izquierda, BoxLayout.Y_AXIS));
        izquierda.setPreferredSize(new Dimension(300, 100));

        JLabel lblRuta = new JLabel("Seleccione la ruta:");
        lblRuta.setFont(Tema.fuente(Font.BOLD, 14));
        lblRuta.setForeground(Tema.NAVY);
        lblRuta.setAlignmentX(LEFT_ALIGNMENT);
        comboRutas.setMaximumSize(new Dimension(300, 34));
        comboRutas.setAlignmentX(LEFT_ALIGNMENT);
        comboRutas.setFont(Tema.fuente(Font.PLAIN, 13));

        JPanel info = new JPanel(new GridBagLayout());
        info.setBackground(Tema.LIGHT);
        info.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Tema.GRAY), " Informacion de la ruta "));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 6, 3, 6);
        gbc.anchor = GridBagConstraints.WEST;
        agregarInfo(info, gbc, 0, "Conductor:", lblConductor);
        agregarInfo(info, gbc, 1, "Vehiculo:", lblVehiculo);
        agregarInfo(info, gbc, 2, "Coordinador:", lblCoordinador);
        agregarInfo(info, gbc, 3, "Horario:", lblHorario);
        agregarInfo(info, gbc, 4, "Estado:", lblEstado);
        info.setMaximumSize(new Dimension(300, 150));
        info.setAlignmentX(LEFT_ALIGNMENT);

        JPanel botones = new JPanel();
        botones.setBackground(Color.WHITE);
        botones.setLayout(new BoxLayout(botones, BoxLayout.Y_AXIS));
        estiloBoton(btnAsignar, Tema.GOLD, Color.BLACK);
        estiloBoton(btnQuitar, Tema.LIGHT, Color.BLACK);
        estiloBoton(btnRefrescar, Tema.LIGHT, Color.BLACK);
        botones.add(btnAsignar);
        botones.add(Box.createVerticalStrut(8));
        botones.add(btnQuitar);
        botones.add(Box.createVerticalStrut(8));
        botones.add(btnRefrescar);
        botones.setAlignmentX(LEFT_ALIGNMENT);

        izquierda.add(lblRuta);
        izquierda.add(Box.createVerticalStrut(6));
        izquierda.add(comboRutas);
        izquierda.add(Box.createVerticalStrut(12));
        izquierda.add(info);
        izquierda.add(Box.createVerticalStrut(12));
        izquierda.add(botones);
        centro.add(izquierda, BorderLayout.WEST);

        JPanel derecha = new JPanel(new BorderLayout(0, 8));
        derecha.setBackground(Color.WHITE);
        lblCupo.setFont(Tema.fuente(Font.BOLD, 14));
        lblCupo.setForeground(Tema.NAVY);
        derecha.add(lblCupo, BorderLayout.NORTH);
        tabla.setFont(Tema.fuente(Font.PLAIN, 13));
        tabla.setRowHeight(24);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setFont(Tema.fuente(Font.BOLD, 13));
        JScrollPane scroll = new JScrollPane(tabla);
        derecha.add(scroll, BorderLayout.CENTER);
        lblPendientes.setFont(Tema.fuente(Font.PLAIN, 13));
        lblPendientes.setForeground(Tema.GRAY);
        derecha.add(lblPendientes, BorderLayout.SOUTH);
        centro.add(derecha, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        footer.setBackground(Tema.LIGHT);
        JButton btnVolver = new JButton("Volver al menu");
        estiloBoton(btnVolver, Tema.NAVY, Color.WHITE);
        JLabel copyright = new JLabel("© 2026 Colegio La Divina Ruta - Javier Cicuamia");
        copyright.setForeground(Tema.GRAY);
        copyright.setFont(Tema.fuente(Font.PLAIN, 12));
        footer.add(btnVolver);
        footer.add(copyright);
        add(footer, BorderLayout.SOUTH);

        comboRutas.addActionListener(e -> {
            if (!cargando) {
                refrescarTabla();
            }
        });
        btnAsignar.addActionListener(e -> accionAsignar());
        btnQuitar.addActionListener(e -> accionQuitar());
        btnRefrescar.addActionListener(e -> cargarRutas());
        btnVolver.addActionListener(e -> dispose());

        cargarRutas();
    }

    private void cargarRutas() {
        try {
            cargando = true;
            comboRutas.removeAllItems();
            for (RutaModel r : controlador.listar()) {
                comboRutas.addItem(r);
            }
            cargando = false;
            refrescarTabla();
        } catch (SQLException ex) {
            cargando = false;
            fallo(ex);
        }
    }

    private void refrescarTabla() {
        RutaModel ruta = (RutaModel) comboRutas.getSelectedItem();
        try {
            modeloTabla.setRowCount(0);
            if (ruta == null) {
                lblConductor.setText("-");
                lblVehiculo.setText("-");
                lblCoordinador.setText("-");
                lblHorario.setText("-");
                lblEstado.setText("-");
                lblCupo.setText("No hay rutas creadas. Cree una ruta primero.");
                lblPendientes.setText(" ");
                btnAsignar.setEnabled(false);
                btnQuitar.setEnabled(false);
                asignados = new ArrayList<>();
                return;
            }
            btnAsignar.setEnabled(true);
            btnQuitar.setEnabled(true);

            lblConductor.setText(controlador.datosConductor(ruta.getIdConductor()));
            lblVehiculo.setText(controlador.datosVehiculo(ruta.getIdVehiculo()));
            lblCoordinador.setText(controlador.datosCoordinador(ruta.getIdCoordinador()));
            lblHorario.setText(ruta.getHoraSalida() + " - " + ruta.getHoraFinal());
            lblEstado.setText(ruta.getEstado());

            asignados = controlador.listarAsignados(ruta.getIdRuta());
            int cupo = controlador.capacidad(ruta.getIdVehiculo());
            int n = 1;
            for (EstudianteModel e : asignados) {
                modeloTabla.addRow(new Object[]{n++, e.getNombre() + " " + e.getApellido(),
                    e.getDocumento(), e.getGrado(), e.getDireccion()});
            }
            lblCupo.setText("Asignados: " + asignados.size() + " de " + cupo + " lugares");
            int pendientes = controlador.listarPendientes().size();
            lblPendientes.setText("Estudiantes sin ruta: " + pendientes);
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionAsignar() {
        RutaModel ruta = (RutaModel) comboRutas.getSelectedItem();
        if (ruta == null) {
            return;
        }
        try {
            int n = controlador.asignarAutomatica(ruta);
            if (n > 0) {
                JOptionPane.showMessageDialog(this,
                        "Se asignaron " + n + " estudiantes a la ruta\n" + ruta.getNombre(),
                        "Asignacion automatica", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se asigno nadie: la ruta ya esta llena\no no hay estudiantes sin ruta.",
                        "Asignacion automatica", JOptionPane.WARNING_MESSAGE);
            }
            refrescarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionQuitar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un estudiante de la tabla para quitarlo.",
                    "Quitar estudiante", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            controlador.quitarEstudiante(asignados.get(fila).getIdEstudiante());
            refrescarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void fallo(SQLException ex) {
        JOptionPane.showMessageDialog(this,
                "Error de conexion con la BD (XAMPP encendido?)",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static JLabel valorInfo() {
        JLabel l = new JLabel("-");
        l.setFont(Tema.fuente(Font.PLAIN, 13));
        l.setForeground(Color.BLACK);
        return l;
    }

    private static void agregarInfo(JPanel panel, GridBagConstraints gbc, int fila, String texto, JLabel valor) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel lbl = new JLabel(texto);
        lbl.setFont(Tema.fuente(Font.BOLD, 13));
        lbl.setForeground(Tema.NAVY);
        panel.add(lbl, gbc);
        gbc.gridx = 1;
        panel.add(valor, gbc);
    }

    private static void estiloBoton(JButton boton, Color fondo, Color texto) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(Tema.fuente(Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setMaximumSize(new Dimension(300, 34));
        boton.setAlignmentX(LEFT_ALIGNMENT);
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

}
