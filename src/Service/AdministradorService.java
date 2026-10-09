package Service;

import Model.AdministradorModel;
import Repository.AdministradorRepository;
import java.sql.SQLException;

public class AdministradorService {

    private final AdministradorRepository repositorio = new AdministradorRepository();

    public boolean validarCredenciales(String documento, String clave) throws SQLException {
        return repositorio.validarCredenciales(documento, clave);
    }

    public AdministradorModel buscarPorDocumento(String documento) throws SQLException {
        return repositorio.buscarPorDocumento(documento);
    }

}
