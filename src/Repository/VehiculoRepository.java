package Repository;

import Model.VehiculoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoRepository {

    public int guardar(VehiculoModel v) throws SQLException {
        String sql = "INSERT INTO vehiculo (placa, capacidad, estado) VALUES (?,?,?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getPlaca());
            ps.setInt(2, v.getCapacidad());
            ps.setString(3, v.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<VehiculoModel> listar() throws SQLException {
        List<VehiculoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(fila(rs));
            }
        }
        return lista;
    }

    public VehiculoModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM vehiculo WHERE id_vehiculo=?";
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

    public boolean actualizar(VehiculoModel v) throws SQLException {
        String sql = "UPDATE vehiculo SET placa=?, capacidad=?, estado=? WHERE id_vehiculo=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getPlaca());
            ps.setInt(2, v.getCapacidad());
            ps.setString(3, v.getEstado());
            ps.setInt(4, v.getIdVehiculo());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM vehiculo WHERE id_vehiculo=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private VehiculoModel fila(ResultSet rs) throws SQLException {
        return new VehiculoModel(rs.getInt("id_vehiculo"), rs.getString("placa"),
                rs.getInt("capacidad"), rs.getString("estado"));
    }

}