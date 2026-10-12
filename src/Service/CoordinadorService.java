package Service;

import Model.CoordinadorModel;
import Repository.CoordinadorRepository;
import java.sql.SQLException;
import java.util.List;

public class CoordinadorService {

    private final CoordinadorRepository repositorio = new CoordinadorRepository();

    public int guardar(CoordinadorModel c) throws SQLException {
        return repositorio.guardar(c);
    }

    public List<CoordinadorModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public CoordinadorModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(CoordinadorModel c) throws SQLException {
        return repositorio.actualizar(c);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }

}