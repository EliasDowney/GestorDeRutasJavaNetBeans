package View;

import Controller.RutaController;
import Model.AdministradorModel;
import Model.EstudianteModel;
import Model.RutaModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
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
    private final JButton btnNueva = new JButton("Nueva ruta");
    private final JButton btnEditar = new JButton("Editar ruta");
    private final JButton btnEstadoRuta = new JButton("Inactivar ruta");
    private final JButton btnVerEstado = new JButton("Ver estado de la ruta");
    private final JButton btnRecorrido = new JButton("Simular recorrido");
    private List<EstudianteModel> asignados = new ArrayList<>();

    private static final int ESCALA_SEGUNDOS = 1;   
    private static final Color VERDE = new Color(0x2E7D32);
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("hh:mm:ss a");
    private int indiceParada;
    private int segundosSimulados;
    private int segundosUltimaParada;
    private boolean cargando;

    public RutasVista(AdministradorModel admin) {
        setTitle("Colegio La Divina Ruta - Asignacion de Rutas");
        setSize(950, 800);
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
        GridBagConstraints restricionesDistribucion = new GridBagConstraints();
        restricionesDistribucion.insets = new Insets(3, 6, 3, 6);
        restricionesDistribucion.anchor = GridBagConstraints.WEST;
        agregarInfo(info, restricionesDistribucion, 0, "Conductor:", lblConductor);
        agregarInfo(info, restricionesDistribucion, 1, "Vehiculo:", lblVehiculo);
        agregarInfo(info, restricionesDistribucion, 2, "Coordinador:", lblCoordinador);
        agregarInfo(info, restricionesDistribucion, 3, "Horario:", lblHorario);
        agregarInfo(info, restricionesDistribucion, 4, "Estado:", lblEstado);
        info.setMaximumSize(new Dimension(300, 150));
        info.setAlignmentX(LEFT_ALIGNMENT);

        JPanel botones = new JPanel();
        botones.setBackground(Color.WHITE);
        botones.setLayout(new BoxLayout(botones, BoxLayout.Y_AXIS));
        estiloBoton(btnAsignar, Tema.GOLD, Color.BLACK);
        estiloBoton(btnQuitar, Tema.LIGHT, Color.BLACK);
        estiloBoton(btnRefrescar, Tema.LIGHT, Color.BLACK);
        estiloBoton(btnRecorrido, Tema.NAVY, Color.WHITE);
        botones.add(btnAsignar);
        botones.add(Box.createVerticalStrut(8));
        botones.add(btnQuitar);
        botones.add(Box.createVerticalStrut(8));
        botones.add(btnRefrescar);
        botones.add(Box.createVerticalStrut(8));
        botones.add(btnRecorrido);
        botones.setAlignmentX(LEFT_ALIGNMENT);

        JPanel gestion = new JPanel();
        gestion.setBackground(Color.WHITE);
        gestion.setLayout(new BoxLayout(gestion, BoxLayout.Y_AXIS));
        gestion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Tema.GRAY), " Administrar rutas "));
        estiloBoton(btnNueva, Tema.NAVY, Color.WHITE);
        estiloBoton(btnEditar, Tema.LIGHT, Color.BLACK);
        estiloBoton(btnEstadoRuta, Tema.NAVY, Color.WHITE);
        estiloBoton(btnVerEstado, Tema.GOLD, Color.BLACK);
        gestion.add(btnNueva);
        gestion.add(Box.createVerticalStrut(8));
        gestion.add(btnEditar);
        gestion.add(Box.createVerticalStrut(8));
        gestion.add(btnEstadoRuta);
        gestion.add(Box.createVerticalStrut(8));
        gestion.add(btnVerEstado);
        gestion.setAlignmentX(LEFT_ALIGNMENT);
        gestion.setMaximumSize(new Dimension(310, Integer.MAX_VALUE));

        izquierda.add(lblRuta);
        izquierda.add(Box.createVerticalStrut(6));
        izquierda.add(comboRutas);
        izquierda.add(Box.createVerticalStrut(12));
        izquierda.add(info);
        izquierda.add(Box.createVerticalStrut(12));
        izquierda.add(botones);
        izquierda.add(Box.createVerticalStrut(12));
        izquierda.add(gestion);

        JScrollPane scrollIzquierda = new JScrollPane(izquierda,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollIzquierda.setBorder(null);
        scrollIzquierda.getViewport().setBackground(Color.WHITE);
        scrollIzquierda.getVerticalScrollBar().setUnitIncrement(16);
        scrollIzquierda.setPreferredSize(new Dimension(325, 100));
        centro.add(scrollIzquierda, BorderLayout.WEST);

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
        JLabel copyright = new JLabel("© 2026 Colegio La Divina Ruta");
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
        btnNueva.addActionListener(e -> accionNuevaRuta());
        btnEditar.addActionListener(e -> accionEditarRuta());
        btnEstadoRuta.addActionListener(e -> accionCambiarEstadoRuta());
        btnVerEstado.addActionListener(e -> accionVerEstadoRuta());
        btnVolver.addActionListener(e -> dispose());
        btnRecorrido.addActionListener(e -> accionRecorrido());

        cargarRutas();
    }

    private void cargarRutas() {
        try {
            cargando = true;
            comboRutas.removeAllItems();
            for (RutaModel ruta : controlador.listar()) {
                comboRutas.addItem(ruta);
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
            boolean activa = "ACTIVO".equalsIgnoreCase(ruta.getEstado());
            btnAsignar.setEnabled(activa);
            btnQuitar.setEnabled(true);

            lblConductor.setText(controlador.datosConductor(ruta.getIdConductor()));
            lblVehiculo.setText(controlador.datosVehiculo(ruta.getIdVehiculo()));
            lblCoordinador.setText(controlador.datosCoordinador(ruta.getIdCoordinador()));
            lblHorario.setText(ruta.getHoraSalida() + " - " + ruta.getHoraFinal());
            lblEstado.setText(ruta.getEstado());
            btnEstadoRuta.setText("ACTIVO".equalsIgnoreCase(ruta.getEstado()) ? "Inactivar ruta" : "Activar ruta");

            asignados = controlador.listarAsignados(ruta.getIdRuta());
            int cupo = controlador.capacidad(ruta.getIdVehiculo());
            int n = 1;
            for (EstudianteModel estudiante : asignados) {
                modeloTabla.addRow(new Object[]{n++, estudiante.getNombre() + " " + estudiante.getApellido(),
                    estudiante.getDocumento(), estudiante.getGrado(), estudiante.getDireccion()});
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
            if (n < 0) {
                JOptionPane.showMessageDialog(this,
                        "La ruta esta inactiva. Actívela para poder asignar estudiantes.",
                        "Asignacion automatica", JOptionPane.WARNING_MESSAGE);
            } else if (n > 0) {
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

    private void accionNuevaRuta() {
        RutaFormulario formulario = new RutaFormulario(this, controlador, null);
        formulario.setVisible(true);
        if (formulario.isGuardado()) {
            cargarRutas();
        }
    }

    private void accionEditarRuta() {
        RutaModel ruta = (RutaModel) comboRutas.getSelectedItem();
        if (ruta == null) {
            aviso("Seleccione una ruta para editarla.");
            return;
        }
        RutaFormulario formulario = new RutaFormulario(this, controlador, ruta);
        formulario.setVisible(true);
        if (formulario.isGuardado()) {
            cargarRutas();
        }
    }

    private void accionCambiarEstadoRuta() {
        RutaModel ruta = (RutaModel) comboRutas.getSelectedItem();
        if (ruta == null) {
            aviso("Seleccione una ruta.");
            return;
        }
        boolean activo = "ACTIVO".equalsIgnoreCase(ruta.getEstado());
        String accion = activo ? "inactivar" : "activar";
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea " + accion + " la ruta " + ruta.getNombre() + "?",
                "Cambiar estado", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }
        ruta.setEstado(activo ? "INACTIVO" : "ACTIVO");
        try {
            controlador.actualizar(ruta);
            cargarRutas();
        } catch (SQLException ex) {
            fallo(ex);
        }
    }

    private void accionVerEstadoRuta() {
        RutaModel rutaSeleccionada = (RutaModel) comboRutas.getSelectedItem();
        if (rutaSeleccionada == null) {
            aviso("Seleccione una ruta.");
            return;
        }
        try {
            String mensaje = controlador.mensajeEstadoRuta(rutaSeleccionada);
            JOptionPane.showMessageDialog(this, mensaje, "Estado de la ruta", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de conexion con la BD (XAMPP encendido?)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Rutas", JOptionPane.WARNING_MESSAGE);
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

    private static void agregarInfo(JPanel panel, GridBagConstraints restricionesDistribucion, int fila, String texto, JLabel valor) {
        restricionesDistribucion.gridx = 0;
        restricionesDistribucion.gridy = fila;
        JLabel lbl = new JLabel(texto);
        lbl.setFont(Tema.fuente(Font.BOLD, 13));
        lbl.setForeground(Tema.NAVY);
        panel.add(lbl, restricionesDistribucion);
        restricionesDistribucion.gridx = 1;
        panel.add(valor, restricionesDistribucion);
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

    private void accionRecorrido() {
        RutaModel ruta = (RutaModel) comboRutas.getSelectedItem();
        if (ruta == null || asignados.isEmpty()) {
            aviso(ruta == null ? "Seleccione una ruta para simular el recorrido."
                    : "La ruta no tiene estudiantes asignados.");
            return;
        }
        List<String> paradas = controlador.paradasRecorrido(asignados);
        int total = paradas.size();
        LocalTime salida = controlador.horaSalida(ruta);
        int minPorParada = controlador.minutosPorParada(ruta, total);
        indiceParada = segundosSimulados = segundosUltimaParada = 0;

        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (int i = 0; i < total; i++) {
            modelo.addElement("");
        }
        JList<String> lista = new JList<>(modelo);
        lista.setFont(Tema.fuente(Font.PLAIN, 16));
        lista.setFocusable(false);
        JTextArea registro = new JTextArea();
        registro.setEditable(false);
        JLabel lblParada = new JLabel("", SwingConstants.CENTER);
        lblParada.setOpaque(true);
        lblParada.setForeground(Color.WHITE);
        lblParada.setFont(Tema.fuente(Font.BOLD, 22));
        lblParada.setPreferredSize(new Dimension(100, 90));
        JLabel lblTiempo = new JLabel("", SwingConstants.CENTER);
        JButton btnSiguiente = new JButton();
        estiloBoton(btnSiguiente, Tema.GOLD, Color.BLACK);

        Runnable notaSalida = () -> registro.setText(
                "Salida: Colegio La Divina Ruta | " + salida.format(FORMATO_HORA) + "\n");

        Runnable pintar = () -> {
            for (int i = 0; i < total; i++) {
                modelo.set(i, "Parada " + (i + 1) + ": " + paradas.get(i)
                        + (i == indiceParada ? "   -   en trayecto" : ""));
            }
            lista.setSelectedIndex(Math.min(indiceParada, total - 1));
            lblParada.setBackground(indiceParada == 0 ? Tema.NAVY : VERDE);
            lblParada.setText(indiceParada == 0 ? "Salida: Colegio La Divina Ruta"
                    : indiceParada >= total ? "Recorrido finalizado"
                    : "Parada " + indiceParada + ": " + paradas.get(indiceParada - 1) + " - Completada");
            btnSiguiente.setText(indiceParada >= total ? "Reiniciar recorrido" : "Siguiente Parada");
            lblTiempo.setText(String.format("Hora: %s  |  Transcurrido: %02d:%02d  |  Estimado: +%d de %d min",
                    salida.plusSeconds(segundosSimulados).format(FORMATO_HORA),
                    segundosSimulados / 60, segundosSimulados % 60,
                    indiceParada * minPorParada, total * minPorParada));
        };
        Timer reloj = new Timer(1000, ev -> {
            segundosSimulados += ESCALA_SEGUNDOS;
            pintar.run();
        });
        btnSiguiente.addActionListener(ev -> {
            if (indiceParada >= total) {
                indiceParada = segundosSimulados = segundosUltimaParada = 0;
                notaSalida.run();
                reloj.start();
            } else {
                int real = segundosSimulados - segundosUltimaParada;
                registro.append(String.format("Parada %d: llegada %s | real %02d:%02d | programado %d min | %s%n",
                        indiceParada + 1, salida.plusSeconds(segundosSimulados).format(FORMATO_HORA),
                        real / 60, real % 60, minPorParada, controlador.textoDiferencia(real, minPorParada)));
                segundosUltimaParada = segundosSimulados;
                if (++indiceParada >= total) {
                    reloj.stop();
                }
            }
            pintar.run();
        });

        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 8));
        centro.add(new JScrollPane(lista));
        centro.add(new JScrollPane(registro));
        JPanel sur = new JPanel(new BorderLayout(0, 8));
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnSiguiente);
        sur.add(panelBoton, BorderLayout.NORTH);
        sur.add(lblTiempo, BorderLayout.SOUTH);

        JDialog dialogo = new JDialog(this, "Recorrido - " + ruta.getNombre(), true);
        dialogo.add(lblParada, BorderLayout.NORTH);
        dialogo.add(centro, BorderLayout.CENTER);
        dialogo.add(sur, BorderLayout.SOUTH);
        dialogo.setSize(700, 640);
        dialogo.setLocationRelativeTo(this);
        notaSalida.run();
        pintar.run();
        reloj.start();
        dialogo.setVisible(true);
        reloj.stop();
    }
}