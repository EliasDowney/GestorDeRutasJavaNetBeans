package Repository;

import Model.EstudianteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository {

    public int guardar(EstudianteModel estudiante) throws SQLException {
        String sql = "INSERT INTO estudiante (documento, nombre, apellido, telefono, grado, direccion, nombre_acudiente, correo_acudiente, estado) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, estudiante.getDocumento());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setString(4, estudiante.getTelefono());
            ps.setString(5, estudiante.getGrado());
            ps.setString(6, estudiante.getDireccion());
            ps.setString(7, estudiante.getNombreAcudiente());
            ps.setString(8, estudiante.getCorreoAcudiente());
            ps.setString(9, estudiante.getEstado());
            ps.executeUpdate();
            try (ResultSet resultadoConsulta = ps.getGeneratedKeys()) {
                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<EstudianteModel> listar() throws SQLException {
        List<EstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(fila(resultadoConsulta));
            }
        }
        return lista;
    }

    public EstudianteModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM estudiante WHERE id_estudiante=?";
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

    public boolean actualizar(EstudianteModel estudiante) throws SQLException {
        String sql = "UPDATE estudiante SET documento=?, nombre=?, apellido=?, telefono=?, grado=?, direccion=?, nombre_acudiente=?, correo_acudiente=?, estado=? WHERE id_estudiante=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, estudiante.getDocumento());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setString(4, estudiante.getTelefono());
            ps.setString(5, estudiante.getGrado());
            ps.setString(6, estudiante.getDireccion());
            ps.setString(7, estudiante.getNombreAcudiente());
            ps.setString(8, estudiante.getCorreoAcudiente());
            ps.setString(9, estudiante.getEstado());
            ps.setInt(10, estudiante.getIdEstudiante());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM estudiante WHERE id_estudiante=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<EstudianteModel> listarPorRuta(int idRuta) throws SQLException {
        List<EstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante WHERE id_ruta=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idRuta);
            try (ResultSet resultadoConsulta = ps.executeQuery()) {
                while (resultadoConsulta.next()) {
                    lista.add(fila(resultadoConsulta));
                }
            }
        }
        return lista;
    }

    public List<EstudianteModel> listarSinRuta() throws SQLException {
        List<EstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante WHERE id_ruta IS NULL";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(fila(resultadoConsulta));
            }
        }
        return lista;
    }

    public boolean asignarRuta(int idEstudiante, int idRuta) throws SQLException {
        String sql = "UPDATE estudiante SET id_ruta=? WHERE id_estudiante=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idRuta);
            ps.setInt(2, idEstudiante);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean quitarRuta(int idEstudiante) throws SQLException {
        String sql = "UPDATE estudiante SET id_ruta=NULL WHERE id_estudiante=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            return ps.executeUpdate() > 0;
        }
    }

    private EstudianteModel fila(ResultSet resultadoConsulta) throws SQLException {
        EstudianteModel estudiante = new EstudianteModel(resultadoConsulta.getInt("id_estudiante"), resultadoConsulta.getString("grado"),
                resultadoConsulta.getString("direccion"), resultadoConsulta.getString("nombre_acudiente"), resultadoConsulta.getString("correo_acudiente"),
                resultadoConsulta.getString("documento"), resultadoConsulta.getString("nombre"), resultadoConsulta.getString("apellido"),
                resultadoConsulta.getString("telefono"), resultadoConsulta.getString("estado"));
        Object idRuta = resultadoConsulta.getObject("id_ruta");
        if (idRuta != null) {
            estudiante.setIdRuta((Integer) idRuta);
        }
        return estudiante;
    }

}