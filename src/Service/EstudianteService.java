package Service;

import Model.EstudianteModel;
import Repository.EstudianteRepository;
import java.sql.SQLException;
import java.util.List;

public class EstudianteService {

    private final EstudianteRepository repositorio = new EstudianteRepository();

    public int guardar(EstudianteModel e) throws SQLException {
        return repositorio.guardar(e);
    }

    public List<EstudianteModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public EstudianteModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(EstudianteModel e) throws SQLException {
        return repositorio.actualizar(e);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }

    public List<EstudianteModel> listarPorRuta(int idRuta) throws SQLException {
        return repositorio.listarPorRuta(idRuta);
    }

    public List<EstudianteModel> listarSinRuta() throws SQLException {
        return repositorio.listarSinRuta();
    }

    public boolean asignarRuta(int idEstudiante, int idRuta) throws SQLException {
        return repositorio.asignarRuta(idEstudiante, idRuta);
    }

    public boolean quitarRuta(int idEstudiante) throws SQLException {
        return repositorio.quitarRuta(idEstudiante);
    }

}