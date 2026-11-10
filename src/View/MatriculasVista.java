package View;

import Controller.EstudianteController;
import Model.EstudianteModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MatriculasVista extends JFrame {

    private final EstudianteController controlador = new EstudianteController();

    private final JTextField txtDocumento = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtTelefono = new JTextField();
    private final JTextField txtGrado = new JTextField();
    private final JComboBox<String> cmbTipoVia = new JComboBox<>(new String[]{"Calle", "Carrera", "Transversal", "Avenida", "Diagonal"});
    private final JTextField txtDireccion = new JTextField();
    private final JTextField txtNombreAcudiente = new JTextField();
    private final JTextField txtCorreoAcudiente = new JTextField();
    private final JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"ACTIVO", "INACTIVO"});

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"Documento", "Nombre", "Apellido", "Grado", "Direccion", "Acudiente", "Estado"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);
    private final List<EstudianteModel> filas = new ArrayList<>();
    private final JButton btnEstado = new JButton("Inactivar");
    private int idSeleccionado = -1;
    
    public String getDireccionCompleta() {
    String tipoVia = cmbTipoVia.getSelectedItem() != null ? cmbTipoVia.getSelectedItem().toString() : "";
    String Direccion = txtDireccion.getText().trim();
    return tipoVia + " " + Direccion;
}

    public MatriculasVista() {
        setTitle("Colegio La Divina Ruta - Matriculas");
        setSize(1050, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("MATRICULAS", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 24));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel subtitulo = new JLabel("Registro de estudiantes", SwingConstants.CENTER);
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
        formulario.setPreferredSize(new Dimension(380, 100));
        formulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Tema.GRAY), " Datos del estudiante "));

        formulario.add(crearCampo("Documento:", txtDocumento));
        formulario.add(crearCampo("Nombre:", txtNombre));
        formulario.add(crearCampo("Apellido:", txtApellido));
        formulario.add(crearCampo("Telefono:", txtTelefono));
        formulario.add(crearCampo("Grado:", txtGrado));
        formulario.add(crearCampo("Nom. acudiente:", txtNombreAcudiente));
        formulario.add(crearCampo("Correo acudiente:", txtCorreoAcudiente));
        formulario.add(crearCampo("Estado:", cmbEstado));

        JPanel panelDir = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelDir.setBackground(Color.WHITE);
        cmbTipoVia.setPreferredSize(new Dimension(95, 26));
        txtDireccion.setPreferredSize(new Dimension(110, 26));
        panelDir.add(cmbTipoVia);
        panelDir.add(Box.createHorizontalStrut(5));
        panelDir.add(txtDireccion);

    formulario.add(crearCampo("Direccion:", panelDir));
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
        JLabel copyright = new JLabel("© 2026 Colegio La Divina Ruta ");
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

    private void cargarTabla()
    {
        try {
            modeloTabla.setRowCount(0);
            filas.clear();
            for (EstudianteModel estudiante : controlador.listar()) {
                filas.add(estudiante);
                modeloTabla.addRow(new Object[]{estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(),
                    estudiante.getGrado(), estudiante.getDireccion(), estudiante.getNombreAcudiente(), estudiante.getEstado()});
            }
            limpiarFormulario();
        } catch (SQLException ex) {
            fallo(ex);
        }
        configurarPlaceholder(txtDocumento, "Ej: 10203040");
        configurarPlaceholder(txtNombre, "Ej: Juan");
        configurarPlaceholder(txtApellido, "Ej: Pérez");
        configurarPlaceholder(txtTelefono, "Ej: 3001234567");
        configurarPlaceholder(txtGrado, "Ej: 11");
        configurarPlaceholder(txtDireccion, "Ej: 45 # 20");
        configurarPlaceholder(txtNombreAcudiente, "Ej: María Pérez");
        configurarPlaceholder(txtCorreoAcudiente, "Ej: ejemp@gmail.com / outlook.com / hotmail.com / yahoo.com ");
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            return;
        }
        EstudianteModel estudiante = filas.get(fila);
        idSeleccionado = estudiante.getIdEstudiante();
        txtDocumento.setText(estudiante.getDocumento());
        txtNombre.setText(estudiante.getNombre());
        txtApellido.setText(estudiante.getApellido());
        txtTelefono.setText(estudiante.getTelefono());
        txtGrado.setText(estudiante.getGrado());
        txtDireccion.setText(estudiante.getDireccion());
        txtNombreAcudiente.setText(estudiante.getNombreAcudiente());
        txtCorreoAcudiente.setText(estudiante.getCorreoAcudiente());
        cmbEstado.setSelectedItem(estudiante.getEstado());
        btnEstado.setText("ACTIVO".equalsIgnoreCase(estudiante.getEstado()) ? "Inactivar" : "Activar");
    }

    private void accionGuardar() {
        if (txtDocumento.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()
                || txtApellido.getText().trim().isEmpty()) {
            aviso("Documento, nombre y apellido son obligatorios.");
            return;
        }
        
        try {
            EstudianteModel estudiante = recoger(false);
           
            boolean exito = controlador.guardarConValidacion(estudiante);
            
            if (exito) {
                JOptionPane.showMessageDialog(this, "Estudiante matriculado correctamente.",
                        "Matriculas", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            }
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionActualizar() {
        if (idSeleccionado < 0) {
            aviso("Seleccione un estudiante de la tabla para actualizarlo.");
            return;
        }
        if (txtDocumento.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()
                || txtApellido.getText().trim().isEmpty()) {
            aviso("Documento, nombre y apellido son obligatorios.");
            return;
        }
        try {
            controlador.actualizar(recoger(true));
            JOptionPane.showMessageDialog(this, "Estudiante actualizado.",
                    "Matriculas", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionCambiarEstado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            aviso("Seleccione un estudiante de la tabla.");
            return;
        }
        EstudianteModel estudiante = filas.get(fila);
        boolean activo = "ACTIVO".equalsIgnoreCase(estudiante.getEstado());
        String accion = activo ? "inactivar" : "activar";
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea " + accion + " al estudiante " + estudiante.getNombre() + " " + estudiante.getApellido() + "?",
                "Cambiar estado", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }
        estudiante.setEstado(activo ? "INACTIVO" : "ACTIVO");
        try {
            controlador.actualizar(estudiante);
            cargarTabla();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private EstudianteModel recoger(boolean conId) {
        Integer idRuta = null;
        String docTexto = txtDocumento.getText().trim();
        String  documento = docTexto.equals("Ej: 10203040") ? "" : docTexto;

        String nomTexto = txtNombre.getText().trim();
        String nombre = nomTexto.equals("Ej: Juan") ? "" : nomTexto;

        String apeTexto = txtApellido.getText().trim();
        String apellido = apeTexto.equals("Ej: Pérez") ? "" : apeTexto;

        String telTexto = txtTelefono.getText().trim();
        String telefono = telTexto.equals("Ej: 3001234567") ? "" : telTexto;

        String gradoTexto = txtGrado.getText().trim();
        String grado = gradoTexto.equals("Ej: 11-A") ? "" : gradoTexto;

        String acudTexto = txtNombreAcudiente.getText().trim();
        String nombreAcudiente = acudTexto.equals("Ej: María Pérez") ? "" : acudTexto;

        String correoTexto = txtCorreoAcudiente.getText().trim();
        String correoAcudiente = correoTexto.equals("Ej: correo@gmail.com") ? "" : correoTexto;
        
        return new EstudianteModel(
            txtDocumento.getText().trim(),        
            txtNombre.getText().trim(),           
            txtApellido.getText().trim(),         
            txtTelefono.getText().trim(),         
            (String) cmbEstado.getSelectedItem(), 
            conId ? idSeleccionado : 0,           
            txtGrado.getText().trim(),            
            getDireccionCompleta(),               
            txtNombreAcudiente.getText().trim(),  
            txtCorreoAcudiente.getText().trim(),  
            idRuta                                
        );
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        tabla.clearSelection();
        txtDocumento.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtGrado.setText("");
        txtDireccion.setText("");
        txtNombreAcudiente.setText("");
        txtCorreoAcudiente.setText("");
        cmbEstado.setSelectedIndex(0);
        btnEstado.setText("Inactivar");
        txtDocumento.requestFocus();
    }

    private JPanel crearCampo(String etiqueta, java.awt.Component campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 2));
        fila.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(120, 24));
        lbl.setFont(Tema.fuente(Font.PLAIN, 13));
        fila.add(lbl);
        if (campo instanceof JTextField) {
            campo.setPreferredSize(new Dimension(210, 26));
        } else {
            campo.setPreferredSize(new Dimension(210, 26));
        }
        fila.add(campo);
        fila.setMaximumSize(new Dimension(380, 32));
        fila.setAlignmentX(LEFT_ALIGNMENT);
        return fila;
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Matriculas", JOptionPane.WARNING_MESSAGE);
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
    
    private void configurarPlaceholder(javax.swing.JTextField textField, String textoGuia) {
        if (textField.getText().trim().isEmpty()) {
            textField.setText(textoGuia);
            textField.setForeground(java.awt.Color.GRAY);
        }
        
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(textoGuia)) {
                    textField.setText("");
                    textField.setForeground(java.awt.Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText(textoGuia);
                    textField.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }
}