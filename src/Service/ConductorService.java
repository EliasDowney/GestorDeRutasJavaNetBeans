package Service;

import Model.ConductorModel;
import Repository.ConductorRepository;
import java.sql.SQLException;
import java.util.List;

public class ConductorService {

    private final ConductorRepository repositorio = new ConductorRepository();

    public int guardar(ConductorModel conductor) throws SQLException {
        ConductorModel conductorExistente = repositorio.buscarPorDocumento(conductor.getDocumento());
        if (conductorExistente != null) {
            return -1;
        }
        
        return repositorio.guardar(conductor);
    }

    public List<ConductorModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public ConductorModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(ConductorModel conductor) throws SQLException {
        if (conductor.getDocumento() == null || conductor.getDocumento().trim().isEmpty()) {
            return false;
        }
        return repositorio.actualizar(conductor);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }

}