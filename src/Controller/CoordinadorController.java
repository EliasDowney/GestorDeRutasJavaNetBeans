package Controller;

import Model.CoordinadorModel;
import Service.CoordinadorService;
import java.sql.SQLException;
import java.util.List;

public class CoordinadorController {

    private final CoordinadorService servicio = new CoordinadorService();

    public int guardar(CoordinadorModel c) throws SQLException {
        return servicio.guardar(c);
    }

    public List<CoordinadorModel> listar() throws SQLException {
        return servicio.listar();
    }

    public CoordinadorModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public boolean actualizar(CoordinadorModel c) throws SQLException {
        return servicio.actualizar(c);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }

}
