package Repository;

import Model.NovedadEstudianteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovedadEstudianteRepository {

    public boolean guardar(NovedadEstudianteModel n) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try {
                int idNovedad = new NovedadRepository().insertarBase(con, n);
                if (idNovedad < 0) {
                    con.rollback();
                    return false;
                }
                String sql = "INSERT INTO novedad_estudiante (id_novedad, id_estudiante, id_ruta) VALUES (?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idNovedad);
                    ps.setInt(2, n.getIdEstudiante());
                    ps.setInt(3, n.getIdRuta());
                    ps.executeUpdate();
                }
                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    public List<NovedadEstudianteModel> listar() throws SQLException {
        List<NovedadEstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT n.*, ne.id_estudiante, ne.id_ruta, ne.id_novedad AS n_id "
                + "FROM novedad_estudiante ne JOIN novedad n ON n.id_novedad = ne.id_novedad";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new NovedadEstudianteModel(rs.getInt("id_novedad"), rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(), rs.getString("estado"), rs.getString("categoria"),
                        rs.getInt("id_estudiante"), rs.getInt("id_ruta")));
            }
        }
        return lista;
    }

    public NovedadEstudianteModel buscarPorId(int idNovedad) throws SQLException {
        String sql = "SELECT n.*, ne.id_estudiante, ne.id_ruta FROM novedad_estudiante ne "
                + "JOIN novedad n ON n.id_novedad = ne.id_novedad WHERE n.id_novedad=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idNovedad);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NovedadEstudianteModel(rs.getInt("id_novedad"), rs.getString("descripcion"),
                            rs.getDate("fecha").toLocalDate(), rs.getString("estado"), rs.getString("categoria"),
                            rs.getInt("id_estudiante"), rs.getInt("id_ruta"));
                }
            }
        }
        return null;
    }

    public boolean actualizar(NovedadEstudianteModel n) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try {
                String sql1 = "UPDATE novedad SET descripcion=?, fecha=?, estado=?, categoria=? WHERE id_novedad=?";
                try (PreparedStatement ps = con.prepareStatement(sql1)) {
                    ps.setString(1, n.getDescripcion());
                    ps.setDate(2, java.sql.Date.valueOf(n.getFecha()));
                    ps.setString(3, n.getEstado());
                    ps.setString(4, n.getCategoria());
                    ps.setInt(5, n.getIdNovedad());
                    ps.executeUpdate();
                }
                String sql2 = "UPDATE novedad_estudiante SET id_estudiante=?, id_ruta=? WHERE id_novedad=?";
                try (PreparedStatement ps = con.prepareStatement(sql2)) {
                    ps.setInt(1, n.getIdEstudiante());
                    ps.setInt(2, n.getIdRuta());
                    ps.setInt(3, n.getIdNovedad());
                    ps.executeUpdate();
                }
                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try {
                String sql = "DELETE FROM novedad_estudiante WHERE id_novedad=?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idNovedad);
                    if (ps.executeUpdate() <= 0) {
                        con.rollback();
                        return false;
                    }
                }
                boolean ok = new NovedadRepository().eliminar(con, idNovedad);
                con.commit();
                return ok;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

}