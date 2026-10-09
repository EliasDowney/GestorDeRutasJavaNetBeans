package Repository;

import Model.NovedadVehiculoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovedadVehiculoRepository {

    public boolean guardar(NovedadVehiculoModel n) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try {
                int idNovedad = new NovedadRepository().insertarBase(con, n);
                if (idNovedad < 0) {
                    con.rollback();
                    return false;
                }
                String sql = "INSERT INTO novedad_vehiculo (id_novedad, id_vehiculo, id_ruta, id_conductor) VALUES (?,?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idNovedad);
                    ps.setInt(2, n.getIdVehiculo());
                    ps.setInt(3, n.getIdRuta());
                    ps.setInt(4, n.getIdConductor());
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

    public List<NovedadVehiculoModel> listar() throws SQLException {
        List<NovedadVehiculoModel> lista = new ArrayList<>();
        String sql = "SELECT n.*, nv.id_vehiculo, nv.id_ruta, nv.id_conductor "
                + "FROM novedad_vehiculo nv JOIN novedad n ON n.id_novedad = nv.id_novedad";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new NovedadVehiculoModel(rs.getInt("id_novedad"), rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(), rs.getString("estado"), rs.getString("categoria"),
                        rs.getInt("id_vehiculo"), rs.getInt("id_ruta"), rs.getInt("id_conductor")));
            }
        }
        return lista;
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try {
                String sql = "DELETE FROM novedad_vehiculo WHERE id_novedad=?";
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