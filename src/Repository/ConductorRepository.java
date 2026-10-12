package Repository;

import Model.ConductorModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConductorRepository {

    public int guardar(ConductorModel c) throws SQLException {
        String sql = "INSERT INTO conductor (documento, nombre, apellido, telefono, licencia, estado) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getDocumento());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getLicencia());
            ps.setString(6, c.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<ConductorModel> listar() throws SQLException {
        List<ConductorModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM conductor";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(fila(rs));
            }
        }
        return lista;
    }

    public ConductorModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM conductor WHERE id_conductor=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return fila(rs);
                }
            }
        }
        return null;
    }

    public boolean actualizar(ConductorModel c) throws SQLException {
        String sql = "UPDATE conductor SET documento=?, nombre=?, apellido=?, telefono=?, licencia=?, estado=? WHERE id_conductor=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getDocumento());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getLicencia());
            ps.setString(6, c.getEstado());
            ps.setInt(7, c.getIdConductor());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM conductor WHERE id_conductor=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private ConductorModel fila(ResultSet rs) throws SQLException {
        return new ConductorModel(rs.getInt("id_conductor"), rs.getString("licencia"),
                rs.getString("documento"), rs.getString("nombre"), rs.getString("apellido"),
                rs.getString("telefono"), rs.getString("estado"));
    }

}