package Service;

import Model.NovedadModel;
import Repository.NovedadRepository;
import java.sql.SQLException;

public class NovedadService {

    private final NovedadRepository repositorio = new NovedadRepository();

    public int insertarBase(NovedadModel n) throws SQLException {
        return repositorio.insertarBase(n);
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return repositorio.eliminar(idNovedad);
    }

}