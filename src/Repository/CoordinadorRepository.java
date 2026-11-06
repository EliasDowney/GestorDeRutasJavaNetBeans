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

    public int guardar(CoordinadorModel coordinador) throws SQLException {
        String sql = "INSERT INTO coordinador (documento, nombre, apellido, telefono, correo, estado) VALUES (?,?,?,?,?,?)";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, coordinador.getDocumento());
            ps.setString(2, coordinador.getNombre());
            ps.setString(3, coordinador.getApellido());
            ps.setString(4, coordinador.getTelefono());
            ps.setString(5, coordinador.getCorreo());
            ps.setString(6, coordinador.getEstado());
            ps.executeUpdate();
            try (ResultSet resultadoConsulta = ps.getGeneratedKeys()) {
                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<CoordinadorModel> listar() throws SQLException {
        List<CoordinadorModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM coordinador";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(fila(resultadoConsulta));
            }
        }
        return lista;
    }

    public CoordinadorModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM coordinador WHERE id_coordinador=?";
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

    public boolean actualizar(CoordinadorModel coordinador) throws SQLException {
        String sql = "UPDATE coordinador SET documento=?, nombre=?, apellido=?, telefono=?, correo=?, estado=? WHERE id_coordinador=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, coordinador.getDocumento());
            ps.setString(2, coordinador.getNombre());
            ps.setString(3, coordinador.getApellido());
            ps.setString(4, coordinador.getTelefono());
            ps.setString(5, coordinador.getCorreo());
            ps.setString(6, coordinador.getEstado());
            ps.setInt(7, coordinador.getIdCoordinador());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM coordinador WHERE id_coordinador=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private CoordinadorModel fila(ResultSet resultadoConsulta) throws SQLException {
        return new CoordinadorModel(resultadoConsulta.getInt("id_coordinador"), resultadoConsulta.getString("correo"),
                resultadoConsulta.getString("documento"), resultadoConsulta.getString("nombre"), resultadoConsulta.getString("apellido"),
                resultadoConsulta.getString("telefono"), resultadoConsulta.getString("estado"));
    }

}