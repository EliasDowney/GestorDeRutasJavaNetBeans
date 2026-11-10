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

    public int insertarBase(Connection conexion, NovedadModel novedad) throws SQLException {
        String sql = "INSERT INTO novedad (descripcion, fecha, estado, categoria) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, novedad.getDescripcion());
            ps.setDate(2, java.sql.Date.valueOf(novedad.getFecha()));
            ps.setString(3, novedad.getEstado());
            ps.setString(4, novedad.getCategoria());
            ps.executeUpdate();
            try (ResultSet resultadoConsulta = ps.getGeneratedKeys()) {
                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt(1);
                }
            }
        }
        return -1;
    }

    public int insertarBase(NovedadModel novedad) throws SQLException {
        try (Connection conexion = Conexion.conectar()) {
            return insertarBase(conexion, novedad);
        }
    }

    public boolean eliminar(Connection conexion, int idNovedad) throws SQLException {
        String sql = "DELETE FROM novedad WHERE id_novedad=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idNovedad);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        try (Connection conexion = Conexion.conectar()) {
            return eliminar(conexion, idNovedad);
        }
    }

    public List<NovedadModel> listar() throws SQLException {
        List<NovedadModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM novedad";
        try (Connection conexion = Conexion.conectar(); Statement st = conexion.createStatement(); ResultSet resultadoConsulta = st.executeQuery(sql)) {
            while (resultadoConsulta.next()) {
                lista.add(fila(resultadoConsulta));
            }
        }
        return lista;
    }

    public NovedadModel buscarPorId(int idNovedad) throws SQLException {
        String sql = "SELECT * FROM novedad WHERE id_novedad=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idNovedad);
            try (ResultSet resultadoConsulta = ps.executeQuery()) {
                if (resultadoConsulta.next()) {
                    return fila(resultadoConsulta);
                }
            }
        }
        return null;
    }

    public boolean actualizar(NovedadModel novedad) throws SQLException {
        String sql = "UPDATE novedad SET descripcion=?, fecha=?, estado=?, categoria=? WHERE id_novedad=?";
        try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, novedad.getDescripcion());
            ps.setDate(2, java.sql.Date.valueOf(novedad.getFecha()));
            ps.setString(3, novedad.getEstado());
            ps.setString(4, novedad.getCategoria());
            ps.setInt(5, novedad.getIdNovedad());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizarEstado(int idNovedad, String estado) throws SQLException{ 
            String sql = "UPDATE novedad SET estado=? WHERE id_novedad=?";
            try (Connection conexion = Conexion.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)){
                ps.setString(1, estado);
                   ps.setInt(2, idNovedad);
                return ps.executeUpdate() > 0;
            }

    }  
    
    private NovedadModel fila(ResultSet resultadoConsulta) throws SQLException {
        return new NovedadModel(resultadoConsulta.getInt("id_novedad"), resultadoConsulta.getString("descripcion"),
                resultadoConsulta.getDate("fecha").toLocalDate(), resultadoConsulta.getString("estado"), resultadoConsulta.getString("categoria"));
    }

}