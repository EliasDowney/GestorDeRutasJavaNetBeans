package Repository;

import Model.AdministradorModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorRepository {

    public boolean validarCredenciales(String documento, String clave) throws SQLException {
        String sql = "SELECT id FROM administrador WHERE documento=? AND clave=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, documento);
            ps.setString(2, clave);
            try (ResultSet resultadoConsulta = ps.executeQuery()) {
                return resultadoConsulta.next();
            }
        }
    }

    public AdministradorModel buscarPorDocumento(String documento) throws SQLException {
        String sql = "SELECT id, documento, clave, nombre FROM administrador WHERE documento=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, documento);
            try (ResultSet resultadoConsulta = ps.executeQuery()) {
                if (resultadoConsulta.next()) {
                    return new AdministradorModel(resultadoConsulta.getInt("id"), resultadoConsulta.getString("documento"),
                            resultadoConsulta.getString("clave"), resultadoConsulta.getString("nombre"));
                }
            }
        }
        return null;
    }

}
