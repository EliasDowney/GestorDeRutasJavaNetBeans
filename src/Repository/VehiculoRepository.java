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

    public int guardar(VehiculoModel vehiculo) throws SQLException {
        String sql = "INSERT INTO vehiculo (placa, capacidad, estado) VALUES (?,?,?)";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vehiculo.getPlaca());
            ps.setInt(2, vehiculo.getCapacidad());
            ps.setString(3, vehiculo.getEstado());
            ps.executeUpdate();
            try (ResultSet resultadoConsulta = ps.getGeneratedKeys()) {
                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<VehiculoModel> listar() throws SQLException {
        List<VehiculoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(fila(resultadoConsulta));
            }
        }
        return lista;
    }

    public VehiculoModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM vehiculo WHERE id_vehiculo=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet resultadoConsulta = ps.executeQuery()) {
                if (resultadoConsulta.next()) {
                    return fila(resultadoConsulta);
                }
            }
        }
        return null;
    }
    
     

    public boolean actualizar(VehiculoModel vehiculo) throws SQLException {
        String sql = "UPDATE vehiculo SET placa=?, capacidad=?, estado=? WHERE id_vehiculo=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getPlaca());
            ps.setInt(2, vehiculo.getCapacidad());
            ps.setString(3, vehiculo.getEstado());
            ps.setInt(4, vehiculo.getIdVehiculo());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM vehiculo WHERE id_vehiculo=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private VehiculoModel fila(ResultSet resultadoConsulta) throws SQLException {
        return new VehiculoModel(resultadoConsulta.getInt("id_vehiculo"), resultadoConsulta.getString("placa"),
                resultadoConsulta.getInt("capacidad"), resultadoConsulta.getString("estado"));
    }

}