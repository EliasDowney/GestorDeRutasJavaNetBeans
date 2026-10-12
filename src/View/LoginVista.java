package View;

import Controller.AdministradorController;
import Model.AdministradorModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginVista extends JDialog {

    private final AdministradorController controlador = new AdministradorController();
    private final JTextField campoDocumento = new JTextField(18);
    private final JPasswordField campoClave = new JPasswordField(18);
    private final JLabel estado = new JLabel(" ");

    public LoginVista(Frame padre) {
        super(padre, "Inicio de sesion", true);
        setLayout(new BorderLayout());
        setSize(420, 320);
        setResizable(false);

        JPanel header = new JPanel();
        header.setBackground(Tema.NAVY);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("ACCESO DE ADMINISTRADOR", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(Tema.fuente(Font.BOLD, 18));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(22));
        header.add(titulo);
        header.add(Box.createVerticalStrut(22));
        add(header, BorderLayout.NORTH);

        JPanel cuerpo = new JPanel(new GridBagLayout());
        cuerpo.setBackground(Color.WHITE);
        cuerpo.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setFont(Tema.fuente(Font.PLAIN, 14));
        cuerpo.add(lblDocumento, gbc);
        gbc.gridx = 1;
        campoDocumento.setFont(Tema.fuente(Font.PLAIN, 14));
        cuerpo.add(campoDocumento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblClave = new JLabel("Clave:");
        lblClave.setFont(Tema.fuente(Font.PLAIN, 14));
        cuerpo.add(lblClave, gbc);
        gbc.gridx = 1;
        campoClave.setFont(Tema.fuente(Font.PLAIN, 14));
        cuerpo.add(campoClave, gbc);

        estado.setForeground(Color.RED);
        estado.setFont(Tema.fuente(Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        cuerpo.add(estado, gbc);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(Tema.GOLD);
        btnIngresar.setForeground(Color.BLACK);
        btnIngresar.setFont(Tema.fuente(Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setPreferredSize(new Dimension(130, 36));
        btnIngresar.addActionListener(e -> ingresar());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Tema.LIGHT);
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFont(Tema.fuente(Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(130, 36));
        btnCancelar.addActionListener(e -> dispose());

        JPanel botones = new JPanel();
        botones.setBackground(Color.WHITE);
        botones.add(btnIngresar);
        botones.add(Box.createHorizontalStrut(12));
        botones.add(btnCancelar);
        gbc.gridy = 3;
        cuerpo.add(botones, gbc);

        add(cuerpo, BorderLayout.CENTER);
        getRootPane().setDefaultButton(btnIngresar);
        setLocationRelativeTo(padre);
    }

    private void ingresar() {
        String documento = campoDocumento.getText().trim();
        String clave = new String(campoClave.getPassword());
        if (documento.isEmpty() || clave.isEmpty()) {
            estado.setText("Digite documento y clave");
            return;
        }
        try {
            if (controlador.iniciarSesion(documento, clave)) {
                AdministradorModel admin = controlador.obtenerAdministrador(documento);
                dispose();
                new RutasVista(admin).setVisible(true);
            } else {
                estado.setText("Documento o clave incorrectos");
            }
        } catch (SQLException e) {
            estado.setText("Error de conexion con la BD (XAMPP encendido?)");
        }
    }

}
