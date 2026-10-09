package Service;

import Model.NovedadEstudianteModel;
import Repository.NovedadEstudianteRepository;
import java.sql.SQLException;
import java.util.List;

public class NovedadEstudianteService {

    private final NovedadEstudianteRepository repositorio = new NovedadEstudianteRepository();

    public boolean guardar(NovedadEstudianteModel n) throws SQLException {
        return repositorio.guardar(n);
    }

    public List<NovedadEstudianteModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return repositorio.eliminar(idNovedad);
    }

}