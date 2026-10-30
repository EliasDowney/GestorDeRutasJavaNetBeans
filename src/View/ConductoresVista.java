package View;

import Controller.ConductorController;
import Model.ConductorModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ConductoresVista extends JFrame {

    private final ConductorController controlador = new ConductorController();

    private final JTextField txtDocumento = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtTelefono = new JTextField();
    private final JTextField txtLicencia = new JTextField();
    private final JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"ACTIVO", "INACTIVO"});

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"Documento", "Nombre", "Apellido", "Telefono", "Licencia", "Estado"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);
    private final List<ConductorModel> filas = new ArrayList<>();
    private final JButton btnEstado = new JButton("Inactivar");
    private int idSeleccionado = -1;

    public ConductoresVista() {
        setTitle("Colegio La Divina Ruta - Conductores");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("CONDUCTORES", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 24));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel subtitulo = new JLabel("Registro de conductores", SwingConstants.CENTER);
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

        JPanel formulario = new JPanel();
        formulario.setBackground(Color.WHITE);
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setPreferredSize(new Dimension(370, 100));
        formulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Tema.GRAY), " Datos del conductor "));

        formulario.add(crearCampo("Documento:", txtDocumento));
        formulario.add(crearCampo("Nombre:", txtNombre));
        formulario.add(crearCampo("Apellido:", txtApellido));
        formulario.add(crearCampo("Telefono:", txtTelefono));
        formulario.add(crearCampo("Licencia:", txtLicencia));
        formulario.add(crearCampo("Estado:", cmbEstado));

        JPanel botones = new JPanel();
        botones.setBackground(Color.WHITE);
        botones.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnLimpiar = new JButton("Limpiar");
        estiloBoton(btnGuardar, Tema.GOLD, Color.BLACK);
        estiloBoton(btnActualizar, Tema.LIGHT, Color.BLACK);
        estiloBoton(btnEstado, Tema.NAVY, Color.WHITE);
        estiloBoton(btnLimpiar, Tema.LIGHT, Color.BLACK);
        botones.add(btnGuardar);
        botones.add(btnActualizar);
        botones.add(btnEstado);
        botones.add(btnLimpiar);
        botones.setAlignmentX(LEFT_ALIGNMENT);
        formulario.add(botones);

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
        JLabel copyright = new JLabel("© 2026 Colegio La Divina Ruta");
        copyright.setForeground(Tema.GRAY);
        copyright.setFont(Tema.fuente(Font.PLAIN, 12));
        footer.add(btnVolver);
        footer.add(copyright);
        add(footer, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> accionGuardar());
        btnActualizar.addActionListener(e -> accionActualizar());
        btnEstado.addActionListener(e -> accionCambiarEstado());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnVolver.addActionListener(e -> dispose());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSeleccion();
            }
        });

        cargarTabla();
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);
            filas.clear();
            for (ConductorModel c : controlador.listar()) {
                filas.add(c);
                modeloTabla.addRow(new Object[]{c.getDocumento(), c.getNombre(), c.getApellido(),
                    c.getTelefono(), c.getLicencia(), c.getEstado()});
            }
            limpiarFormulario();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            return;
        }
        ConductorModel c = filas.get(fila);
        idSeleccionado = c.getIdConductor();
        txtDocumento.setText(c.getDocumento());
        txtNombre.setText(c.getNombre());
        txtApellido.setText(c.getApellido());
        txtTelefono.setText(c.getTelefono());
        txtLicencia.setText(c.getLicencia());
        cmbEstado.setSelectedItem(c.getEstado());
        btnEstado.setText("ACTIVO".equalsIgnoreCase(c.getEstado()) ? "Inactivar" : "Activar");
    }

    private void accionGuardar() {
        if (txtDocumento.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()
                || txtApellido.getText().trim().isEmpty()) {
            aviso("Documento, nombre y apellido son obligatorios.");
            return;
        }
        try {
            controlador.guardar(recoger(false));
            JOptionPane.showMessageDialog(this, "Conductor registrado correctamente.",
                    "Conductores", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionActualizar() {
        if (idSeleccionado < 0) {
            aviso("Seleccione un conductor de la tabla para actualizarlo.");
            return;
        }
        if (txtDocumento.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()
                || txtApellido.getText().trim().isEmpty()) {
            aviso("Documento, nombre y apellido son obligatorios.");
            return;
        }
        try {
            controlador.actualizar(recoger(true));
            JOptionPane.showMessageDialog(this, "Conductor actualizado.",
                    "Conductores", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionCambiarEstado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            aviso("Seleccione un conductor de la tabla.");
            return;
        }
        ConductorModel c = filas.get(fila);
        boolean activo = "ACTIVO".equalsIgnoreCase(c.getEstado());
        String accion = activo ? "inactivar" : "activar";
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea " + accion + " al conductor " + c.getNombre() + " " + c.getApellido() + "?",
                "Cambiar estado", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }
        c.setEstado(activo ? "INACTIVO" : "ACTIVO");
        try {
            controlador.actualizar(c);
            cargarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private ConductorModel recoger(boolean conId) {
        return new ConductorModel(
                conId ? idSeleccionado : 0,
                txtLicencia.getText().trim(),
                txtDocumento.getText().trim(),
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtTelefono.getText().trim(),
                (String) cmbEstado.getSelectedItem());
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        tabla.clearSelection();
        txtDocumento.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtLicencia.setText("");
        cmbEstado.setSelectedIndex(0);
        btnEstado.setText("Inactivar");
        txtDocumento.requestFocus();
    }

    private JPanel crearCampo(String etiqueta, Component campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        fila.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(110, 24));
        lbl.setFont(Tema.fuente(Font.PLAIN, 13));
        fila.add(lbl);
        campo.setPreferredSize(new Dimension(210, 26));
        fila.add(campo);
        fila.setMaximumSize(new Dimension(360, 34));
        fila.setAlignmentX(LEFT_ALIGNMENT);
        return fila;
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Conductores", JOptionPane.WARNING_MESSAGE);
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
