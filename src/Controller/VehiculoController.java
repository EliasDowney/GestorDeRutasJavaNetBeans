package Controller;

import Model.VehiculoModel;
import Service.VehiculoService;
import java.sql.SQLException;
import java.util.List;

public class VehiculoController {

    private final VehiculoService servicio = new VehiculoService();

    public int guardar(VehiculoModel v) throws SQLException {
        return servicio.guardar(v);
    }

    public List<VehiculoModel> listar() throws SQLException {
        return servicio.listar();
    }

    public VehiculoModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public boolean actualizar(VehiculoModel v) throws SQLException {
        return servicio.actualizar(v);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }

}
