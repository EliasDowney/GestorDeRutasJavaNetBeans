package Controller;

import Model.EstudianteModel;
import Service.EstudianteService;
import java.sql.SQLException;
import java.util.List;

public class EstudianteController {

    private final EstudianteService servicio = new EstudianteService();

    public int guardar(EstudianteModel estudiante) throws SQLException {
        return servicio.guardar(estudiante);
    }

    public List<EstudianteModel> listar() throws SQLException {
        return servicio.listar();
    }

    public EstudianteModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public boolean actualizar(EstudianteModel estudiante) throws SQLException {
        return servicio.actualizar(estudiante);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }
    public boolean guardarConValidacion(EstudianteModel estudiante) throws SQLException {
    if (estudiante.getDocumento() == null || !estudiante.getDocumento().matches("\\d{1,10}")) {
        javax.swing.JOptionPane.showMessageDialog(null, "El documento debe ser numérico y tener máximo 10 dígitos.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    if (estudiante.getNombre() == null || !estudiante.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
        javax.swing.JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    if (estudiante.getApellido() == null || !estudiante.getApellido().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
        javax.swing.JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    if (estudiante.getTelefono() == null || !estudiante.getTelefono().matches("\\d{1,10}")) {
        javax.swing.JOptionPane.showMessageDialog(null, "El teléfono debe ser numérico y tener máximo 10 dígitos.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    String valorGrado = estudiante.getGrado();
    if (valorGrado == null || !valorGrado.matches("\\d+") || Integer.parseInt(valorGrado) < 1 || Integer.parseInt(valorGrado) > 11) {
    javax.swing.JOptionPane.showMessageDialog(null, "El grado debe ser un número entre 1 y 11.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
    return false;
}
    
    if (estudiante.getDireccion() == null || !estudiante.getDireccion().matches("[a-zA-Z0-9#\\-\\°áéíóúÁÉÍÓÚñÑ ]+")) {
        javax.swing.JOptionPane.showMessageDialog(null, "La dirección contiene caracteres no válidos.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    if (estudiante.getNombreAcudiente() == null || !estudiante.getNombreAcudiente().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
    javax.swing.JOptionPane.showMessageDialog(null, "El nombre del acudiente solo debe contener letras.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
    return false;
}
    
    String correo = estudiante.getCorreoAcudiente() != null ? estudiante.getCorreoAcudiente().toLowerCase().trim() : "";
    if (!correo.matches("^[a-zA-Z0-9._%+-]+@(gmail|outlook|hotmail|yahoo)\\.com$")) {
        javax.swing.JOptionPane.showMessageDialog(null, "Correo no válido. Solo se permiten dominios: @gmail.com, @outlook.com, @hotmail.com o @yahoo.com", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
        return false;
    }
    servicio.guardar(estudiante);
    return true;
    }
    
    }
