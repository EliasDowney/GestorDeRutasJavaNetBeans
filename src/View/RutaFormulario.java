package View;

import Controller.RutaController;
import Model.ConductorModel;
import Model.CoordinadorModel;
import Model.RutaModel;
import Model.VehiculoModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.function.ToIntFunction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RutaFormulario extends JDialog {

    private final RutaController controlador;
    private final RutaModel ruta;
    private boolean guardado;

    private final JTextField txtNombre = new JTextField();
    private final JTextField txtHoraSalida = new JTextField();
    private final JTextField txtHoraFinal = new JTextField();
    private final JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"ACTIVO", "INACTIVO"});
    private final JComboBox<ConductorModel> cmbConductor = new JComboBox<>();
    private final JComboBox<VehiculoModel> cmbVehiculo = new JComboBox<>();
    private final JComboBox<CoordinadorModel> cmbCoordinador = new JComboBox<>();

    public RutaFormulario(JFrame padre, RutaController controlador, RutaModel ruta) {
        super(padre, ruta == null ? "Nueva ruta" : "Editar ruta", true);
        this.controlador = controlador;
        this.ruta = ruta;
        construir();
        cargarCombos();
        if (ruta != null) {
            precargar();
        }
        setLocationRelativeTo(padre);
    }

    public boolean isGuardado() {
        return guardado;
    }

    private void construir() {
        setLayout(new BorderLayout());
        setSize(440, 470);
        setResizable(false);

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        JLabel titulo = new JLabel(ruta == null ? "NUEVA RUTA" : "EDITAR RUTA", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 18));
        header.add(Box.createHorizontalStrut(0));
        header.add(titulo);
        header.setPreferredSize(new Dimension(440, 60));
        add(header, BorderLayout.NORTH);

        JPanel cuerpo = new JPanel();
        cuerpo.setBackground(Color.WHITE);
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setBorder(BorderFactory.createEmptyBorder(15, 25, 10, 25));
        cuerpo.add(crearCampo("Nombre:", txtNombre));
        cuerpo.add(crearCampo("Hora salida:", txtHoraSalida));
        cuerpo.add(crearCampo("Hora final:", txtHoraFinal));
        cuerpo.add(crearCampo("Estado:", cmbEstado));
        cuerpo.add(crearCampo("Conductor:", cmbConductor));
        cuerpo.add(crearCampo("Vehiculo:", cmbVehiculo));
        cuerpo.add(crearCampo("Coordinador:", cmbCoordinador));
        JLabel ayuda = new JLabel("Hora en formato HH:mm (ej. 06:00)");
        ayuda.setForeground(Tema.GRAY);
        ayuda.setFont(Tema.fuente(Font.PLAIN, 12));
        ayuda.setAlignmentX(LEFT_ALIGNMENT);
        cuerpo.add(Box.createVerticalStrut(4));
        cuerpo.add(ayuda);
        add(cuerpo, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        botones.setBackground(Tema.LIGHT);
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        estiloBoton(btnGuardar, Tema.GOLD, Color.BLACK);
        estiloBoton(btnCancelar, Tema.LIGHT, Color.BLACK);
        botones.add(btnGuardar);
        botones.add(btnCancelar);
        add(botones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());
        getRootPane().setDefaultButton(btnGuardar);
    }

    private void cargarCombos() {
        try {
            for (ConductorModel c : controlador.listarConductores()) {
                if ("ACTIVO".equalsIgnoreCase(c.getEstado())) {
                    cmbConductor.addItem(c);
                }
            }
            for (VehiculoModel v : controlador.listarVehiculos()) {
                if ("ACTIVO".equalsIgnoreCase(v.getEstado())) {
                    cmbVehiculo.addItem(v);
                }
            }
            for (CoordinadorModel c : controlador.listarCoordinadores()) {
                if ("ACTIVO".equalsIgnoreCase(c.getEstado())) {
                    cmbCoordinador.addItem(c);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de conexion con la BD (XAMPP encendido?)",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void precargar() {
        txtNombre.setText(ruta.getNombre());
        txtHoraSalida.setText(String.valueOf(ruta.getHoraSalida()));
        txtHoraFinal.setText(String.valueOf(ruta.getHoraFinal()));
        cmbEstado.setSelectedItem(ruta.getEstado());
        seleccionar(cmbConductor, ConductorModel::getIdConductor, ruta.getIdConductor());
        seleccionar(cmbVehiculo, VehiculoModel::getIdVehiculo, ruta.getIdVehiculo());
        seleccionar(cmbCoordinador, CoordinadorModel::getIdCoordinador, ruta.getIdCoordinador());
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            aviso("El nombre de la ruta es obligatorio.");
            return;
        }
        LocalTime salida;
        LocalTime finalHora;
        try {
            salida = LocalTime.parse(txtHoraSalida.getText().trim());
            finalHora = LocalTime.parse(txtHoraFinal.getText().trim());
        } catch (DateTimeParseException ex) {
            aviso("Hora invalida. Use el formato HH:mm (ej. 06:00).");
            return;
        }
        if (cmbConductor.getSelectedItem() == null || cmbVehiculo.getSelectedItem() == null
                || cmbCoordinador.getSelectedItem() == null) {
            aviso("Debe seleccionar conductor, vehiculo y coordinador.");
            return;
        }
        RutaModel r = new RutaModel(
                ruta == null ? 0 : ruta.getIdRuta(),
                nombre, salida, finalHora, (String) cmbEstado.getSelectedItem(),
                ((ConductorModel) cmbConductor.getSelectedItem()).getIdConductor(),
                ((VehiculoModel) cmbVehiculo.getSelectedItem()).getIdVehiculo(),
                ((CoordinadorModel) cmbCoordinador.getSelectedItem()).getIdCoordinador());
        try {
            if (ruta == null) {
                controlador.guardar(r);
            } else {
                controlador.actualizar(r);
            }
            guardado = true;
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la ruta (revise la BD).",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static <T> void seleccionar(JComboBox<T> combo, ToIntFunction<T> id, int valor) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (id.applyAsInt(combo.getItemAt(i)) == valor) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    private JPanel crearCampo(String etiqueta, Component campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 3));
        fila.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(110, 24));
        lbl.setFont(Tema.fuente(Font.PLAIN, 13));
        fila.add(lbl);
        campo.setPreferredSize(new Dimension(240, 28));
        fila.add(campo);
        fila.setMaximumSize(new Dimension(400, 36));
        fila.setAlignmentX(LEFT_ALIGNMENT);
        return fila;
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Ruta", JOptionPane.WARNING_MESSAGE);
    }

    private static void estiloBoton(JButton boton, Color fondo, Color texto) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(Tema.fuente(Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(120, 34));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

}
