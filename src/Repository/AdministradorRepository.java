package Repository;

import Model.AdministradorModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorRepository {

    public boolean validarCredenciales(String documento, String clave) throws SQLException {
        String sql = "SELECT id FROM administrador WHERE documento=? AND clave=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, documento);
            ps.setString(2, clave);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public AdministradorModel buscarPorDocumento(String documento) throws SQLException {
        String sql = "SELECT id, documento, clave, nombre FROM administrador WHERE documento=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, documento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AdministradorModel(rs.getInt("id"), rs.getString("documento"),
                            rs.getString("clave"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }

}
