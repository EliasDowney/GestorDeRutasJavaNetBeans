package Repository;

import Model.CoordinadorModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordinadorRepository {

    public int guardar(CoordinadorModel c) throws SQLException {
        String sql = "INSERT INTO coordinador (documento, nombre, apellido, telefono, correo, estado) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getDocumento());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getCorreo());
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

    public List<CoordinadorModel> listar() throws SQLException {
        List<CoordinadorModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM coordinador";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(fila(rs));
            }
        }
        return lista;
    }

    public CoordinadorModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM coordinador WHERE id_coordinador=?";
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

    public boolean actualizar(CoordinadorModel c) throws SQLException {
        String sql = "UPDATE coordinador SET documento=?, nombre=?, apellido=?, telefono=?, correo=?, estado=? WHERE id_coordinador=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getDocumento());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getCorreo());
            ps.setString(6, c.getEstado());
            ps.setInt(7, c.getIdCoordinador());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM coordinador WHERE id_coordinador=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private CoordinadorModel fila(ResultSet rs) throws SQLException {
        return new CoordinadorModel(rs.getInt("id_coordinador"), rs.getString("correo"),
                rs.getString("documento"), rs.getString("nombre"), rs.getString("apellido"),
                rs.getString("telefono"), rs.getString("estado"));
    }

}