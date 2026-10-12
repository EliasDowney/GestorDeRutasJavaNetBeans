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

    public int guardar(EstudianteModel e) throws SQLException {
        String sql = "INSERT INTO estudiante (documento, nombre, apellido, telefono, grado, direccion, nombre_acudiente, correo_acudiente, estado) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getDocumento());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getGrado());
            ps.setString(6, e.getDireccion());
            ps.setString(7, e.getNombreAcudiente());
            ps.setString(8, e.getCorreoAcudiente());
            ps.setString(9, e.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<EstudianteModel> listar() throws SQLException {
        List<EstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(fila(rs));
            }
        }
        return lista;
    }

    public EstudianteModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM estudiante WHERE id_estudiante=?";
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

    public boolean actualizar(EstudianteModel e) throws SQLException {
        String sql = "UPDATE estudiante SET documento=?, nombre=?, apellido=?, telefono=?, grado=?, direccion=?, nombre_acudiente=?, correo_acudiente=?, estado=? WHERE id_estudiante=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getDocumento());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getGrado());
            ps.setString(6, e.getDireccion());
            ps.setString(7, e.getNombreAcudiente());
            ps.setString(8, e.getCorreoAcudiente());
            ps.setString(9, e.getEstado());
            ps.setInt(10, e.getIdEstudiante());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM estudiante WHERE id_estudiante=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<EstudianteModel> listarPorRuta(int idRuta) throws SQLException {
        List<EstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante WHERE id_ruta=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRuta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(fila(rs));
                }
            }
        }
        return lista;
    }

    public List<EstudianteModel> listarSinRuta() throws SQLException {
        List<EstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante WHERE id_ruta IS NULL";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(fila(rs));
            }
        }
        return lista;
    }

    public boolean asignarRuta(int idEstudiante, int idRuta) throws SQLException {
        String sql = "UPDATE estudiante SET id_ruta=? WHERE id_estudiante=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRuta);
            ps.setInt(2, idEstudiante);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean quitarRuta(int idEstudiante) throws SQLException {
        String sql = "UPDATE estudiante SET id_ruta=NULL WHERE id_estudiante=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            return ps.executeUpdate() > 0;
        }
    }

    private EstudianteModel fila(ResultSet rs) throws SQLException {
        EstudianteModel e = new EstudianteModel(rs.getInt("id_estudiante"), rs.getString("grado"),
                rs.getString("direccion"), rs.getString("nombre_acudiente"), rs.getString("correo_acudiente"),
                rs.getString("documento"), rs.getString("nombre"), rs.getString("apellido"),
                rs.getString("telefono"), rs.getString("estado"));
        Object idRuta = rs.getObject("id_ruta");
        if (idRuta != null) {
            e.setIdRuta((Integer) idRuta);
        }
        return e;
    }

}