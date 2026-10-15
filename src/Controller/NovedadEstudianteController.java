package Controller;

import Model.NovedadEstudianteModel;
import Service.NovedadEstudianteService;
import java.sql.SQLException;
import java.util.List;

public class NovedadEstudianteController extends NovedadController {

    private final NovedadEstudianteService servicio = new NovedadEstudianteService();

    public boolean guardarEstudiante(NovedadEstudianteModel n) throws SQLException {
        return servicio.guardar(n);
    }

    public List<NovedadEstudianteModel> listarNovedadesEstudiante() throws SQLException {
        return servicio.listar();
    }

    // Aportado en el PR #6 por el companero
    public NovedadEstudianteModel buscarNovedadEstudiantePorId(int idNovedad) throws SQLException {
        return servicio.buscarPorId(idNovedad);
    }

    public boolean actualizarNovedadEstudiante(NovedadEstudianteModel n) throws SQLException {
        return servicio.actualizar(n);
    }

    public boolean eliminarEstudiante(int idNovedad) throws SQLException {
        return servicio.eliminar(idNovedad);
    }

}
