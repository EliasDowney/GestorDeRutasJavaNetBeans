package Service;

import Model.VehiculoModel;
import Repository.VehiculoRepository;
import java.sql.SQLException;
import java.util.List;

public class VehiculoService {

    private final VehiculoRepository repositorio = new VehiculoRepository();

    public int guardar(VehiculoModel v) throws SQLException {
        return repositorio.guardar(v);
    }

    public List<VehiculoModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public VehiculoModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(VehiculoModel v) throws SQLException {
        return repositorio.actualizar(v);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }

}