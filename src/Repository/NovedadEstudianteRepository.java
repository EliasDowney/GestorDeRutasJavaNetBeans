package Repository;

import Model.NovedadEstudianteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovedadEstudianteRepository {

    public boolean guardar(NovedadEstudianteModel novedadEstudiante) throws SQLException {
        try (Connection conexion = Conexion.conectar()) {
            conexion.setAutoCommit(false);
            try {
                int idNovedad = new NovedadRepository().insertarBase(conexion, novedadEstudiante);
                if (idNovedad < 0) {
                    conexion.rollback();
                    return false;
                }
                String sql = "INSERT INTO novedad_estudiante (id_novedad, id_estudiante, id_ruta) VALUES (?,?,?)";
                try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                    ps.setInt(1, idNovedad);
                    ps.setInt(2, novedadEstudiante.getIdEstudiante());
                    ps.setInt(3, novedadEstudiante.getIdRuta());
                    ps.executeUpdate();
                }
                conexion.commit();
                return true;
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
    }

    public List<NovedadEstudianteModel> listar() throws SQLException {
        List<NovedadEstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT novedadEstudiante.*, ne.id_estudiante, ne.id_ruta, ne.id_novedad AS n_id "
                + "FROM novedad_estudiante ne JOIN novedad novedadEstudiante ON novedadEstudiante.id_novedad = ne.id_novedad";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(new NovedadEstudianteModel(resultadoConsulta.getInt("id_novedad"), resultadoConsulta.getString("descripcion"),
                        resultadoConsulta.getDate("fecha").toLocalDate(), resultadoConsulta.getString("estado"), resultadoConsulta.getString("categoria"),
                        resultadoConsulta.getInt("id_estudiante"), resultadoConsulta.getInt("id_ruta")));
            }
        }
        return lista;
    }

    public NovedadEstudianteModel buscarPorId(int idNovedad) throws SQLException {
        String sql = "SELECT novedadEstudiante.*, ne.id_estudiante, ne.id_ruta FROM novedad_estudiante ne "
                + "JOIN novedad novedadEstudiante ON novedadEstudiante.id_novedad = ne.id_novedad WHERE novedadEstudiante.id_novedad=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idNovedad);
            try (ResultSet resultadoConsulta = ps.executeQuery()) {
                if (resultadoConsulta.next()) {
                    return new NovedadEstudianteModel(resultadoConsulta.getInt("id_novedad"), resultadoConsulta.getString("descripcion"),
                            resultadoConsulta.getDate("fecha").toLocalDate(), resultadoConsulta.getString("estado"), resultadoConsulta.getString("categoria"),
                            resultadoConsulta.getInt("id_estudiante"), resultadoConsulta.getInt("id_ruta"));
                }
            }
        }
        return null;
    }

    public boolean actualizar(NovedadEstudianteModel novedadEstudiante) throws SQLException {
        try (Connection conexion = Conexion.conectar()) {
            conexion.setAutoCommit(false);
            try {
                String sql1 = "UPDATE novedad SET descripcion=?, fecha=?, estado=?, categoria=? WHERE id_novedad=?";
                try (PreparedStatement ps = conexion.prepareStatement(sql1)) {
                    ps.setString(1, novedadEstudiante.getDescripcion());
                    ps.setDate(2, java.sql.Date.valueOf(novedadEstudiante.getFecha()));
                    ps.setString(3, novedadEstudiante.getEstado());
                    ps.setString(4, novedadEstudiante.getCategoria());
                    ps.setInt(5, novedadEstudiante.getIdNovedad());
                    ps.executeUpdate();
                }
                String sql2 = "UPDATE novedad_estudiante SET id_estudiante=?, id_ruta=? WHERE id_novedad=?";
                try (PreparedStatement ps = conexion.prepareStatement(sql2)) {
                    ps.setInt(1, novedadEstudiante.getIdEstudiante());
                    ps.setInt(2, novedadEstudiante.getIdRuta());
                    ps.setInt(3, novedadEstudiante.getIdNovedad());
                    ps.executeUpdate();
                }
                conexion.commit();
                return true;
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        try (Connection conexion = Conexion.conectar()) {
            conexion.setAutoCommit(false);
            try {
                String sql = "DELETE FROM novedad_estudiante WHERE id_novedad=?";
                try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                    ps.setInt(1, idNovedad);
                    if (ps.executeUpdate() <= 0) {
                        conexion.rollback();
                        return false;
                    }
                }
                boolean ok = new NovedadRepository().eliminar(conexion, idNovedad);
                conexion.commit();
                return ok;
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
    }

}