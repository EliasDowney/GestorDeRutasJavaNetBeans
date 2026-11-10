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

    public int guardar(RutaModel ruta) throws SQLException {
        String sql = "INSERT INTO ruta (nombre, hora_salida, hora_final, estado, id_conductor, id_vehiculo, id_coordinador) VALUES (?,?,?,?,?,?,?)";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, ruta.getNombre());
            ps.setTime(2, Time.valueOf(ruta.getHoraSalida()));
            ps.setTime(3, Time.valueOf(ruta.getHoraFinal()));
            ps.setString(4, ruta.getEstado());
            ps.setInt(5, ruta.getIdConductor());
            ps.setInt(6, ruta.getIdVehiculo());
            ps.setInt(7, ruta.getIdCoordinador());
            ps.executeUpdate();
            try (ResultSet resultadoConsulta = ps.getGeneratedKeys()) {
                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<RutaModel> listar() throws SQLException {
        List<RutaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM ruta";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(fila(resultadoConsulta));
            }
        }
        return lista;
    }

    public RutaModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ruta WHERE id_ruta=?";
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

    public boolean actualizar(RutaModel ruta) throws SQLException {
        String sql = "UPDATE ruta SET nombre=?, hora_salida=?, hora_final=?, estado=?, id_conductor=?, id_vehiculo=?, id_coordinador=? WHERE id_ruta=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, ruta.getNombre());
            ps.setTime(2, Time.valueOf(ruta.getHoraSalida()));
            ps.setTime(3, Time.valueOf(ruta.getHoraFinal()));
            ps.setString(4, ruta.getEstado());
            ps.setInt(5, ruta.getIdConductor());
            ps.setInt(6, ruta.getIdVehiculo());
            ps.setInt(7, ruta.getIdCoordinador());
            ps.setInt(8, ruta.getIdRuta());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM ruta WHERE id_ruta=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private RutaModel fila(ResultSet resultadoConsulta) throws SQLException {
        return new RutaModel(resultadoConsulta.getInt("id_ruta"), resultadoConsulta.getString("nombre"),
                resultadoConsulta.getTime("hora_salida").toLocalTime(), resultadoConsulta.getTime("hora_final").toLocalTime(),
                resultadoConsulta.getString("estado"), resultadoConsulta.getInt("id_conductor"), resultadoConsulta.getInt("id_vehiculo"), resultadoConsulta.getInt("id_coordinador"));
    }

}