package Service;

import Model.ConductorModel;
import Repository.ConductorRepository;
import java.sql.SQLException;
import java.util.List;

public class ConductorService {

    private final ConductorRepository repositorio = new ConductorRepository();

    public int guardar(ConductorModel c) throws SQLException {
        return repositorio.guardar(c);
    }

    public List<ConductorModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public ConductorModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(ConductorModel c) throws SQLException {
        return repositorio.actualizar(c);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }

}