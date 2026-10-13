package Service;

import Model.NovedadModel;
import Repository.NovedadRepository;
import java.sql.SQLException;
import java.util.List;

public class NovedadService {

    private final NovedadRepository repositorio = new NovedadRepository();

    public int insertarBase(NovedadModel n) throws SQLException {
        return repositorio.insertarBase(n);
    }

    public List<NovedadModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public NovedadModel buscarPorId(int idNovedad) throws SQLException {
        return repositorio.buscarPorId(idNovedad);
    }

    public boolean actualizar(NovedadModel n) throws SQLException {
        return repositorio.actualizar(n);
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return repositorio.eliminar(idNovedad);
    }

}