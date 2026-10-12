package Repository;

import Model.NovedadModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NovedadRepository {

    public int insertarBase(Connection con, NovedadModel n) throws SQLException {
        String sql = "INSERT INTO novedad (descripcion, fecha, estado, categoria) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, n.getDescripcion());
            ps.setDate(2, java.sql.Date.valueOf(n.getFecha()));
            ps.setString(3, n.getEstado());
            ps.setString(4, n.getCategoria());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public int insertarBase(NovedadModel n) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            return insertarBase(con, n);
        }
    }

    public boolean eliminar(Connection con, int idNovedad) throws SQLException {
        String sql = "DELETE FROM novedad WHERE id_novedad=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idNovedad);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            return eliminar(con, idNovedad);
        }
    }

}