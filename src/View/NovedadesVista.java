package View;

import Controller.NovedadEstudianteController;
import Controller.NovedadVehiculoController;
import Model.ConductorModel;
import Model.EstudianteModel;
import Model.NovedadEstudianteModel;
import Model.NovedadVehiculoModel;
import Model.RutaModel;
import Model.VehiculoModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class NovedadesVista extends JFrame {

    private final NovedadEstudianteController controladorEstudiante = new NovedadEstudianteController();
    private final NovedadVehiculoController controladorVehiculo = new NovedadVehiculoController();

    private final JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Estudiante", "Vehiculo"});
    private final JTextField txtDescripcion = new JTextField();
    private final JTextField txtFecha = new JTextField();
    private final JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"PENDIENTE", "EN PROCESO", "RESUELTA"});

    private final JComboBox<EstudianteModel> cmbEstudiante = new JComboBox<>();
    private final JComboBox<RutaModel> cmbRutaEst = new JComboBox<>();
    private final JComboBox<VehiculoModel> cmbVehiculo = new JComboBox<>();
    private final JComboBox<RutaModel> cmbRutaVeh = new JComboBox<>();
    private final JComboBox<ConductorModel> cmbConductor = new JComboBox<>();

    private JPanel formulario;
    private JPanel panelEstudiante;
    private JPanel panelVehiculo;

    private final Map<Integer, String> estudiantesMap = new HashMap<>();
    private final Map<Integer, String> vehiculosMap = new HashMap<>();
    private final Map<Integer, String> conductoresMap = new HashMap<>();
    private final Map<Integer, String> rutasMap = new HashMap<>();

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"Id", "Tipo", "Descripcion", "Fecha", "Estado", "Asociado"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);
    private final List<Fila> filas = new ArrayList<>();

    private static class Fila {

        final int id;
        final String categoria;

        Fila(int id, String categoria) {
            this.id = id;
            this.categoria = categoria;
        }
    }

    public NovedadesVista() {
        setTitle("Colegio La Divina Ruta - Novedades");
        setSize(1020, 640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("NOVEDADES", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 24));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel subtitulo = new JLabel("Registro de novedades de estudiantes y vehiculos", SwingConstants.CENTER);
        subtitulo.setForeground(Tema.GOLD);
        subtitulo.setFont(Tema.fuente(Font.PLAIN, 14));
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(20));
        header.add(titulo);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitulo);
        header.add(Box.createVerticalStrut(20));
        add(header, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(15, 0));
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(centro, BorderLayout.CENTER);

        formulario = new JPanel();
        formulario.setBackground(Color.WHITE);
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setPreferredSize(new Dimension(420, 100));
        formulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Tema.GRAY), " Datos de la novedad "));

        formulario.add(crearCampo("Tipo:", cmbTipo));
        formulario.add(crearCampo("Descripcion:", txtDescripcion));
        formulario.add(crearCampo("Fecha (AAAA-MM-DD):", txtFecha));
        formulario.add(crearCampo("Estado:", cmbEstado));

        panelEstudiante = new JPanel();
        panelEstudiante.setBackground(Color.WHITE);
        panelEstudiante.setLayout(new BoxLayout(panelEstudiante, BoxLayout.Y_AXIS));
        panelEstudiante.add(crearCampo("Estudiante:", cmbEstudiante));
        panelEstudiante.add(crearCampo("Ruta:", cmbRutaEst));
        formulario.add(panelEstudiante);

        panelVehiculo = new JPanel();
        panelVehiculo.setBackground(Color.WHITE);
        panelVehiculo.setLayout(new BoxLayout(panelVehiculo, BoxLayout.Y_AXIS));
        panelVehiculo.add(crearCampo("Vehiculo:", cmbVehiculo));
        panelVehiculo.add(crearCampo("Ruta:", cmbRutaVeh));
        panelVehiculo.add(crearCampo("Conductor:", cmbConductor));
        formulario.add(panelVehiculo);

        JPanel botones = new JPanel();
        botones.setBackground(Color.WHITE);
        botones.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        estiloBoton(btnGuardar, Tema.GOLD, Color.BLACK);
        estiloBoton(btnEliminar, new Color(200, 70, 70), Color.WHITE);
        estiloBoton(btnLimpiar, Tema.LIGHT, Color.BLACK);
        botones.add(btnGuardar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        botones.setAlignmentX(LEFT_ALIGNMENT);
        formulario.add(botones);
        formulario.add(Box.createVerticalGlue());

        centro.add(formulario, BorderLayout.WEST);

        tabla.setFont(Tema.fuente(Font.PLAIN, 13));
        tabla.setRowHeight(24);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setFont(Tema.fuente(Font.BOLD, 13));
        centro.add(new JScrollPane(tabla), BorderLayout.CENTER);

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

        btnGuardar.addActionListener(e -> accionGuardar());
        btnEliminar.addActionListener(e -> accionEliminar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnVolver.addActionListener(e -> dispose());
        cmbTipo.addActionListener(e -> actualizarPaneles());

        cargarCombos();
        actualizarPaneles();
        cargarTabla();
    }

    private void cargarCombos() {
        try {
            cmbEstudiante.removeAllItems();
            estudiantesMap.clear();
            for (EstudianteModel e : controladorEstudiante.listarEstudiantes()) {
                estudiantesMap.put(e.getIdEstudiante(), e.getNombre() + " " + e.getApellido());
                if ("ACTIVO".equalsIgnoreCase(e.getEstado())) {
                    cmbEstudiante.addItem(e);
                }
            }
            cmbVehiculo.removeAllItems();
            vehiculosMap.clear();
            for (VehiculoModel v : controladorVehiculo.listarVehiculos()) {
                vehiculosMap.put(v.getIdVehiculo(), v.getPlaca());
                if ("ACTIVO".equalsIgnoreCase(v.getEstado())) {
                    cmbVehiculo.addItem(v);
                }
            }
            cmbConductor.removeAllItems();
            conductoresMap.clear();
            for (ConductorModel c : controladorVehiculo.listarConductores()) {
                conductoresMap.put(c.getIdConductor(), c.getNombre() + " " + c.getApellido());
                if ("ACTIVO".equalsIgnoreCase(c.getEstado())) {
                    cmbConductor.addItem(c);
                }
            }
            cmbRutaEst.removeAllItems();
            cmbRutaVeh.removeAllItems();
            rutasMap.clear();
            for (RutaModel r : controladorEstudiante.listarRutas()) {
                rutasMap.put(r.getIdRuta(), r.getNombre());
                if ("ACTIVO".equalsIgnoreCase(r.getEstado())) {
                    cmbRutaEst.addItem(r);
                    cmbRutaVeh.addItem(r);
                }
            }
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void actualizarPaneles() {
        boolean esEstudiante = "Estudiante".equals(cmbTipo.getSelectedItem());
        panelEstudiante.setVisible(esEstudiante);
        panelVehiculo.setVisible(!esEstudiante);
        formulario.revalidate();
        formulario.repaint();
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);
            filas.clear();
            for (NovedadEstudianteModel n : controladorEstudiante.listarNovedadesEstudiante()) {
                filas.add(new Fila(n.getIdNovedad(), "ESTUDIANTE"));
                modeloTabla.addRow(new Object[]{n.getIdNovedad(), "Estudiante", n.getDescripcion(),
                    n.getFecha(), n.getEstado(),
                    estudiantesMap.getOrDefault(n.getIdEstudiante(), "?") + " / "
                    + rutasMap.getOrDefault(n.getIdRuta(), "-")});
            }
            for (NovedadVehiculoModel n : controladorVehiculo.listarNovedadesVehiculo()) {
                filas.add(new Fila(n.getIdNovedad(), "VEHICULO"));
                modeloTabla.addRow(new Object[]{n.getIdNovedad(), "Vehiculo", n.getDescripcion(),
                    n.getFecha(), n.getEstado(),
                    vehiculosMap.getOrDefault(n.getIdVehiculo(), "?") + " / "
                    + conductoresMap.getOrDefault(n.getIdConductor(), "-")});
            }
            limpiarFormulario();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionGuardar() {
        String descripcion = txtDescripcion.getText().trim();
        if (descripcion.isEmpty()) {
            aviso("La descripcion es obligatoria.");
            return;
        }
        LocalDate fecha;
        String f = txtFecha.getText().trim();
        if (f.isEmpty()) {
            fecha = LocalDate.now();
        } else {
            try {
                fecha = LocalDate.parse(f);
            } catch (Exception ex) {
                aviso("La fecha debe tener el formato AAAA-MM-DD.");
                return;
            }
        }
        String estado = (String) cmbEstado.getSelectedItem();
        try {
            if ("Estudiante".equals(cmbTipo.getSelectedItem())) {
                EstudianteModel e = (EstudianteModel) cmbEstudiante.getSelectedItem();
                RutaModel r = (RutaModel) cmbRutaEst.getSelectedItem();
                if (e == null || r == null) {
                    aviso("Seleccione el estudiante y la ruta.");
                    return;
                }
                controladorEstudiante.guardarEstudiante(new NovedadEstudianteModel(0, descripcion, fecha, estado,
                        "ESTUDIANTE", e.getIdEstudiante(), r.getIdRuta()));
            } else {
                VehiculoModel v = (VehiculoModel) cmbVehiculo.getSelectedItem();
                RutaModel r = (RutaModel) cmbRutaVeh.getSelectedItem();
                ConductorModel c = (ConductorModel) cmbConductor.getSelectedItem();
                if (v == null || r == null || c == null) {
                    aviso("Seleccione el vehiculo, la ruta y el conductor.");
                    return;
                }
                controladorVehiculo.guardarVehiculo(new NovedadVehiculoModel(0, descripcion, fecha, estado,
                        "VEHICULO", v.getIdVehiculo(), r.getIdRuta(), c.getIdConductor()));
            }
            JOptionPane.showMessageDialog(this, "Novedad registrada correctamente.",
                    "Novedades", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionEliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            aviso("Seleccione una novedad de la tabla para eliminarla.");
            return;
        }
        Fila seleccion = filas.get(fila);
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la novedad seleccionada?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            if ("ESTUDIANTE".equals(seleccion.categoria)) {
                controladorEstudiante.eliminarEstudiante(seleccion.id);
            } else {
                controladorVehiculo.eliminarVehiculo(seleccion.id);
            }
            cargarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void limpiarFormulario() {
        txtDescripcion.setText("");
        txtFecha.setText("");
        cmbEstado.setSelectedIndex(0);
        cmbTipo.setSelectedIndex(0);
        tabla.clearSelection();
        actualizarPaneles();
        txtDescripcion.requestFocus();
    }

    private JPanel crearCampo(String etiqueta, Component campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        fila.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(150, 24));
        lbl.setFont(Tema.fuente(Font.PLAIN, 13));
        fila.add(lbl);
        campo.setPreferredSize(new Dimension(230, 26));
        fila.add(campo);
        fila.setMaximumSize(new Dimension(420, 36));
        fila.setAlignmentX(LEFT_ALIGNMENT);
        return fila;
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Novedades", JOptionPane.WARNING_MESSAGE);
    }

    private void fallo(SQLException ex) {
        JOptionPane.showMessageDialog(this,
                "Error de conexion con la BD (XAMPP encendido?)",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void estiloBoton(JButton boton, Color fondo, Color texto) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(Tema.fuente(Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

}
