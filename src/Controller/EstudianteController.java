package Controller;

import Model.EstudianteModel;
import Service.EstudianteService;
import java.sql.SQLException;
import java.util.List;

public class EstudianteController {

    private final EstudianteService servicio = new EstudianteService();

    public int guardar(EstudianteModel e) throws SQLException {
        return servicio.guardar(e);
    }

    public List<EstudianteModel> listar() throws SQLException {
        return servicio.listar();
    }

    public EstudianteModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public boolean actualizar(EstudianteModel e) throws SQLException {
        return servicio.actualizar(e);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }

}
