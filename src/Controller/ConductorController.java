package Controller;

import Model.ConductorModel;
import Service.ConductorService;
import java.sql.SQLException;
import java.util.List;

public class ConductorController {

    private final ConductorService servicio = new ConductorService();

    public int guardar(ConductorModel conductor) throws SQLException {
        return servicio.guardar(conductor);
    }

    public List<ConductorModel> listar() throws SQLException {
        return servicio.listar();
    }

    public ConductorModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public boolean actualizar(ConductorModel conductor) throws SQLException {
        return servicio.actualizar(conductor);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }

    public List<ConductorModel> listarConductoresDisponibles() throws SQLException {
        List<ConductorModel> todos = servicio.listar();
        List<ConductorModel> disponibles = new java.util.ArrayList<>();
        
        for (ConductorModel conductor : todos) {
            if ("ACTIVO".equalsIgnoreCase(conductor.getEstado())) {
                disponibles.add(conductor);
            }
        }
        return disponibles;
    }

    public boolean guardarConValidacion(ConductorModel conductor) throws SQLException {
        if (conductor.getDocumento() == null || !conductor.getDocumento().matches("\\d{1,10}")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El documento debe ser numérico y tener máximo 10 dígitos.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (conductor.getNombre() == null || !conductor.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (conductor.getApellido() == null || !conductor.getApellido().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (conductor.getTelefono() == null || !conductor.getTelefono().matches("\\d{1,10}")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El teléfono debe ser numérico y tener máximo 10 dígitos.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        String categoria = conductor.getLicencia(); 
        if (categoria == null || !categoria.matches("^(A1|A2|B1|B2|B3|C1|C2|C3)$")) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Debe seleccionar una categoría de licencia válida en Colombia (Ej: A2, B1, C1).", 
                "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String estado = conductor.getEstado();
        if (estado == null || estado.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe indicar un estado válido.", "Error de Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        int resultado = servicio.guardar(conductor);
        if (resultado == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo registrar: El documento ya existe en el sistema.", "Error de Duplicidad", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}