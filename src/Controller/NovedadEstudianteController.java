package Controller;

import Model.NovedadEstudianteModel;
import Service.NovedadEstudianteService;
import java.sql.SQLException;
import java.util.List;

public class NovedadEstudianteController {

    private final NovedadEstudianteService servicio = new NovedadEstudianteService();

    public boolean guardar(NovedadEstudianteModel n) throws SQLException {
        return servicio.guardar(n);
    }

    public List<NovedadEstudianteModel> listar() throws SQLException {
        return servicio.listar();
    }

    public NovedadEstudianteModel buscarPorId(int idNovedad) throws SQLException {
        return servicio.buscarPorId(idNovedad);
    }

    public boolean actualizar(NovedadEstudianteModel n) throws SQLException {
        return servicio.actualizar(n);
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return servicio.eliminar(idNovedad);
    }

}
