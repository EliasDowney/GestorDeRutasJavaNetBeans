package Controller;

import Model.NovedadModel;
import Service.NovedadService;
import java.sql.SQLException;
import java.util.List;

public class NovedadController {

    private final NovedadService servicio = new NovedadService();

    public int guardar(NovedadModel n) throws SQLException {
        return servicio.insertarBase(n);
    }

    public List<NovedadModel> listar() throws SQLException {
        return servicio.listar();
    }

    public NovedadModel buscarPorId(int idNovedad) throws SQLException {
        return servicio.buscarPorId(idNovedad);
    }

    public boolean actualizar(NovedadModel n) throws SQLException {
        return servicio.actualizar(n);
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return servicio.eliminar(idNovedad);
    }

}
