package View;

import Exepcion.Exepcionv;
import Controller.VehiculoController;
import Model.VehiculoModel;
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

public class VehiculosVista extends JFrame {

    private final VehiculoController controlador = new VehiculoController();

    private final JTextField txtPlaca = new JTextField();
    private final JTextField txtCapacidad = new JTextField();
    private final JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"ACTIVO", "INACTIVO"});

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"Placa", "Capacidad", "Estado"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);
    private final List<VehiculoModel> filas = new ArrayList<>();
    private final JButton btnEstado = new JButton("Inactivar");
    private int idSeleccionado = -1;

    public VehiculosVista() {
        setTitle("Colegio La Divina Ruta - Vehiculos");
        setSize(900, 540);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("VEHICULOS", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 24));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel subtitulo = new JLabel("Registro de vehiculos", SwingConstants.CENTER);
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
        formulario.setPreferredSize(new Dimension(340, 100));
        formulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Tema.GRAY), " Datos del vehiculo "));

        formulario.add(crearCampo("Placa:", txtPlaca));
        formulario.add(crearCampo("Capacidad:", txtCapacidad));
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
            for (VehiculoModel v : controlador.listar()) {
                filas.add(v);
                modeloTabla.addRow(new Object[]{v.getPlaca(), v.getCapacidad(), v.getEstado()});
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
        VehiculoModel v = filas.get(fila);
        idSeleccionado = v.getIdVehiculo();
        txtPlaca.setText(v.getPlaca());
        txtCapacidad.setText(String.valueOf(v.getCapacidad()));
        cmbEstado.setSelectedItem(v.getEstado());
        btnEstado.setText("ACTIVO".equalsIgnoreCase(v.getEstado()) ? "Inactivar" : "Activar");
    }

    private void accionGuardar() {
        VehiculoModel v = recoger(false);
        if (v == null) {
            return;
        }
        try {
            controlador.guardar(v);
            JOptionPane.showMessageDialog(this, "Vehiculo registrado correctamente.",
                    "Vehiculos", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        }catch (Exepcionv Placarepetida){
            aviso(Placarepetida.getMessage());
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionActualizar() {
        if (idSeleccionado < 0) {
            aviso("Seleccione un vehiculo de la tabla para actualizarlo.");
            return;
        }
        VehiculoModel v = recoger(true);
        if (v == null) {
            return;
        }
        try {
            controlador.actualizar(v);
            JOptionPane.showMessageDialog(this, "Vehiculo actualizado.",
                    "Vehiculos", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } catch (Exepcionv Placarepetida)   {
            aviso(Placarepetida.getMessage());
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionCambiarEstado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            aviso("Seleccione un vehiculo de la tabla.");
            return;
        }
        VehiculoModel v = filas.get(fila);
        boolean activo = "ACTIVO".equalsIgnoreCase(v.getEstado());
        String accion = activo ? "inactivar" : "activar";
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea " + accion + " el vehiculo con placa " + v.getPlaca() + "?",
                "Cambiar estado", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }
        v.setEstado(activo ? "INACTIVO" : "ACTIVO");
        try {
            controlador.actualizar(v);
            cargarTabla();
            
        } catch (Exepcionv Placarepetida) {
            aviso(Placarepetida.getMessage());
           
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private VehiculoModel recoger(boolean conId) {
        String placa = txtPlaca.getText().trim();
        if (placa.isEmpty()) {
            aviso("La placa es obligatoria.");
            return null;
        }
        int capacidad;
        try {
            capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            if (capacidad <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            aviso("La capacidad debe ser un numero mayor a cero.");
            return null;
        }
        return new VehiculoModel(conId ? idSeleccionado : 0, placa, capacidad,
                (String) cmbEstado.getSelectedItem());
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        tabla.clearSelection();
        txtPlaca.setText("");
        txtCapacidad.setText("");
        cmbEstado.setSelectedIndex(0);
        btnEstado.setText("Inactivar");
        txtPlaca.requestFocus();
    }

    private JPanel crearCampo(String etiqueta, Component campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        fila.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(90, 24));
        lbl.setFont(Tema.fuente(Font.PLAIN, 13));
        fila.add(lbl);
        campo.setPreferredSize(new Dimension(210, 26));
        fila.add(campo);
        fila.setMaximumSize(new Dimension(330, 36));
        fila.setAlignmentX(LEFT_ALIGNMENT);
        return fila;
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Vehiculos", JOptionPane.WARNING_MESSAGE);
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
