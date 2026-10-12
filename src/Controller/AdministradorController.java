package Controller;

import Model.AdministradorModel;
import Service.AdministradorService;
import java.sql.SQLException;

public class AdministradorController {

    private final AdministradorService servicio = new AdministradorService();

    public boolean iniciarSesion(String documento, String clave) throws SQLException {
        return servicio.validarCredenciales(documento, clave);
    }

    public AdministradorModel obtenerAdministrador(String documento) throws SQLException {
        return servicio.buscarPorDocumento(documento);
    }

}
