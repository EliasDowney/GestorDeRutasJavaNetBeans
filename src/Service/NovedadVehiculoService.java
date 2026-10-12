package Service;

import Model.NovedadVehiculoModel;
import Repository.NovedadVehiculoRepository;
import java.sql.SQLException;
import java.util.List;

public class NovedadVehiculoService {

    private final NovedadVehiculoRepository repositorio = new NovedadVehiculoRepository();

    public boolean guardar(NovedadVehiculoModel n) throws SQLException {
        return repositorio.guardar(n);
    }

    public List<NovedadVehiculoModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return repositorio.eliminar(idNovedad);
    }

}