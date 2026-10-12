package Repository;

import Model.RutaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class RutaRepository {

    public int guardar(RutaModel r) throws SQLException {
        String sql = "INSERT INTO ruta (nombre, hora_salida, hora_final, estado, id_conductor, id_vehiculo, id_coordinador) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getNombre());
            ps.setTime(2, Time.valueOf(r.getHoraSalida()));
            ps.setTime(3, Time.valueOf(r.getHoraFinal()));
            ps.setString(4, r.getEstado());
            ps.setInt(5, r.getIdConductor());
            ps.setInt(6, r.getIdVehiculo());
            ps.setInt(7, r.getIdCoordinador());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<RutaModel> listar() throws SQLException {
        List<RutaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM ruta";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(fila(rs));
            }
        }
        return lista;
    }

    public RutaModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ruta WHERE id_ruta=?";
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

    public boolean actualizar(RutaModel r) throws SQLException {
        String sql = "UPDATE ruta SET nombre=?, hora_salida=?, hora_final=?, estado=?, id_conductor=?, id_vehiculo=?, id_coordinador=? WHERE id_ruta=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getNombre());
            ps.setTime(2, Time.valueOf(r.getHoraSalida()));
            ps.setTime(3, Time.valueOf(r.getHoraFinal()));
            ps.setString(4, r.getEstado());
            ps.setInt(5, r.getIdConductor());
            ps.setInt(6, r.getIdVehiculo());
            ps.setInt(7, r.getIdCoordinador());
            ps.setInt(8, r.getIdRuta());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM ruta WHERE id_ruta=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private RutaModel fila(ResultSet rs) throws SQLException {
        return new RutaModel(rs.getInt("id_ruta"), rs.getString("nombre"),
                rs.getTime("hora_salida").toLocalTime(), rs.getTime("hora_final").toLocalTime(),
                rs.getString("estado"), rs.getInt("id_conductor"), rs.getInt("id_vehiculo"), rs.getInt("id_coordinador"));
    }

}