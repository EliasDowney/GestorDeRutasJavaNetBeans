package Repository;

import Model.NovedadVehiculoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovedadVehiculoRepository {

    public boolean guardar(NovedadVehiculoModel novedadVehiculo) throws SQLException {
        try (Connection conexion = Conexion.conectar()) {
            conexion.setAutoCommit(false);
            try {
                int idNovedad = new NovedadRepository().insertarBase(conexion, novedadVehiculo);
                if (idNovedad < 0) {
                    conexion.rollback();
                    return false;
                }
                String sql = "INSERT INTO novedad_vehiculo (id_novedad, id_vehiculo, id_ruta, id_conductor) VALUES (?,?,?,?)";
                try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                    ps.setInt(1, idNovedad);
                    ps.setInt(2, novedadVehiculo.getIdVehiculo());
                    ps.setInt(3, novedadVehiculo.getIdRuta());
                    ps.setInt(4, novedadVehiculo.getIdConductor());
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

    public List<NovedadVehiculoModel> listar() throws SQLException {
        List<NovedadVehiculoModel> lista = new ArrayList<>();
        String sql = "SELECT novedadVehiculo.*, nv.id_vehiculo, nv.id_ruta, nv.id_conductor "
                + "FROM novedad_vehiculo nv JOIN novedad novedadVehiculo ON novedadVehiculo.id_novedad = nv.id_novedad";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet resultadoConsulta = ps.executeQuery()) {
            while (resultadoConsulta.next()) {
                lista.add(new NovedadVehiculoModel(resultadoConsulta.getInt("id_novedad"), resultadoConsulta.getString("descripcion"),
                        resultadoConsulta.getDate("fecha").toLocalDate(), resultadoConsulta.getString("estado"), resultadoConsulta.getString("categoria"),
                        resultadoConsulta.getInt("id_vehiculo"), resultadoConsulta.getInt("id_ruta"), resultadoConsulta.getInt("id_conductor")));
            }
        }
        return lista;
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        try (Connection conexion = Conexion.conectar()) {
            conexion.setAutoCommit(false);
            try {
                String sql = "DELETE FROM novedad_vehiculo WHERE id_novedad=?";
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