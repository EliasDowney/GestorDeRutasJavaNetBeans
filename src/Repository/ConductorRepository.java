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

    public int guardar(ConductorModel conductor) throws SQLException {
        String sql = "INSERT INTO conductor (documento, nombre, apellido, telefono, licencia, estado) VALUES (?,?,?,?,?,?)";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, conductor.getDocumento());
            ps.setString(2, conductor.getNombre());
            ps.setString(3, conductor.getApellido());
            ps.setString(4, conductor.getTelefono());
            ps.setString(5, conductor.getLicencia());
            ps.setString(6, conductor.getEstado());
            ps.executeUpdate();
            try (ResultSet resultadoConsulta = ps.getGeneratedKeys()) {
                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<ConductorModel> listar() throws SQLException {
        List<ConductorModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM conductor";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(fila(resultadoConsulta));
            }
        }
        return lista;
    }

    public ConductorModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM conductor WHERE id_conductor=?";
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

    // ⭐ MÉTODO CLAVE: Valida si el documento ya existe para cumplir la regla "No CRUD"
    public ConductorModel buscarPorDocumento(String documento) throws SQLException {
        String sql = "SELECT * FROM conductor WHERE documento=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, documento);
            try (ResultSet resultadoConsulta = ps.executeQuery()) {
                if (resultadoConsulta.next()) {
                    return fila(resultadoConsulta);
                }
            }
        }
        return null;
    }

    public boolean actualizar(ConductorModel conductor) throws SQLException {
        String sql = "UPDATE conductor SET documento=?, nombre=?, apellido=?, telefono=?, licencia=?, estado=? WHERE id_conductor=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, conductor.getDocumento());
            ps.setString(2, conductor.getNombre());
            ps.setString(3, conductor.getApellido());
            ps.setString(4, conductor.getTelefono());
            ps.setString(5, conductor.getLicencia());
            ps.setString(6, conductor.getEstado());
            ps.setInt(7, conductor.getIdConductor());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM conductor WHERE id_conductor=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private ConductorModel fila(ResultSet resultadoConsulta) throws SQLException {
        return new ConductorModel(
            resultadoConsulta.getString("documento"), 
            resultadoConsulta.getString("nombre"), 
            resultadoConsulta.getString("apellido"),
            resultadoConsulta.getString("telefono"), 
            resultadoConsulta.getString("estado"),
            resultadoConsulta.getInt("id_conductor"), 
            resultadoConsulta.getString("licencia")   
        );   }
    }