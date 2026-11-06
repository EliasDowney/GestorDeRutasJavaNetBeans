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

}
