package Repository;

import Model.NovedadModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<NovedadModel> listar() throws SQLException {
        List<NovedadModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM novedad";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(fila(rs));
            }
        }
        return lista;
    }

    public NovedadModel buscarPorId(int idNovedad) throws SQLException {
        String sql = "SELECT * FROM novedad WHERE id_novedad=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idNovedad);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return fila(rs);
                }
            }
        }
        return null;
    }

    public boolean actualizar(NovedadModel n) throws SQLException {
        String sql = "UPDATE novedad SET descripcion=?, fecha=?, estado=?, categoria=? WHERE id_novedad=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, n.getDescripcion());
            ps.setDate(2, java.sql.Date.valueOf(n.getFecha()));
            ps.setString(3, n.getEstado());
            ps.setString(4, n.getCategoria());
            ps.setInt(5, n.getIdNovedad());
            return ps.executeUpdate() > 0;
        }
    }

    private NovedadModel fila(ResultSet rs) throws SQLException {
        return new NovedadModel(rs.getInt("id_novedad"), rs.getString("descripcion"),
                rs.getDate("fecha").toLocalDate(), rs.getString("estado"), rs.getString("categoria"));
    }

}