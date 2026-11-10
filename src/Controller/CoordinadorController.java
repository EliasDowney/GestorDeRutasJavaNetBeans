package Controller;

import Model.CoordinadorModel;
import Service.CoordinadorService;
import java.sql.SQLException;
import java.util.List;

public class CoordinadorController {

    private final CoordinadorService servicio = new CoordinadorService();

    public int guardar(CoordinadorModel coordinador) throws SQLException {
        return servicio.guardar(coordinador);
    }

    public List<CoordinadorModel> listar() throws SQLException {
        return servicio.listar();
    }

    public CoordinadorModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public boolean actualizar(CoordinadorModel coordinador) throws SQLException {
        return servicio.actualizar(coordinador);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }
    public List<CoordinadorModel> listarConductoresDisponibles() throws SQLException {
        List<CoordinadorModel> todos = servicio.listar();
        List<CoordinadorModel> disponibles = new java.util.ArrayList<>();
        
        for (CoordinadorModel coordinador : todos) {
            if ("ACTIVO".equalsIgnoreCase(coordinador.getEstado())) {
                disponibles.add(coordinador);
            }
        }
        return disponibles;
    }
    public boolean guardarConValidacion(CoordinadorModel coordinador) throws SQLException {
        if (coordinador.getDocumento() == null || coordinador.getDocumento().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "El documento es obligatorio.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!coordinador.getDocumento().matches("\\d{1,15}")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El documento solo debe contener números.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (coordinador.getNombre() == null || !coordinador.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El nombre es obligatorio y solo debe contener letras.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (coordinador.getApellido() == null || !coordinador.getApellido().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El apellido es obligatorio y solo debe contener letras.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (coordinador.getTelefono() != null && !coordinador.getTelefono().trim().isEmpty() && !coordinador.getTelefono().matches("\\d{7,15}")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El teléfono debe contener solo números, maximo 10 digitos.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String correo = coordinador.getCorreo() != null ? coordinador.getCorreo().toLowerCase().trim() : "";
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@(gmail|outlook|hotmail|yahoo)\\.com$")) {
            javax.swing.JOptionPane.showMessageDialog(null, "Correo no válido. Solo se permiten dominios: @gmail.com, @outlook.com, @hotmail.com o @yahoo.com", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String estado = coordinador.getEstado();
        if (estado == null || estado.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe indicar un estado válido.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int resultado = servicio.guardar(coordinador);
        if (resultado == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo registrar: El documento ya existe o hubo un error.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

}
