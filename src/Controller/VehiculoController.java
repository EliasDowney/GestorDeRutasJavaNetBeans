package Controller;

import Exepcion.Exepcionv;
import Model.VehiculoModel;
import Service.VehiculoService;
import java.sql.SQLException;
import java.util.List;


public class VehiculoController {

    private final VehiculoService servicio = new VehiculoService();

    public int guardar(VehiculoModel vehiculo) throws SQLException,Exepcionv {
        return servicio.guardar(vehiculo);
    }

    public List<VehiculoModel> listar() throws SQLException {
        return servicio.listar();
    }

    public VehiculoModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public boolean actualizar(VehiculoModel vehiculo) throws SQLException, Exepcionv {
        return servicio.actualizar(vehiculo);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }
    
    public boolean guardarConValidacion(VehiculoModel vehiculo) throws SQLException, Exepcionv {
        if (vehiculo == null) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "No se pudieron capturar los datos del vehículo.", 
                "Error de Validación", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String placa = vehiculo.getPlaca() != null ? vehiculo.getPlaca().trim().toUpperCase() : "";
        if (!placa.matches("^[A-Z]{3}\\d{3}$")) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Placa no válida. En Colombia los vehículos escolares usan 3 letras y 3 números sin guion (Ej: SOT123).", 
                "Error de Validación", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        this.guardar(vehiculo);
        return true;
    }

}
