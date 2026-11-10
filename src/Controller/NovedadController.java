package Controller;

import Model.ConductorModel;
import Model.EstudianteModel;
import Model.NovedadModel;
import Model.RutaModel;
import Model.VehiculoModel;
import Service.*;
import java.sql.SQLException;
import java.util.List;

public abstract class NovedadController {

    private final NovedadService novedadServicio = new NovedadService();
    private final EstudianteService estudiantes = new EstudianteService();
    private final VehiculoService vehiculos = new VehiculoService();
    private final ConductorService conductores = new ConductorService();
    private final RutaService rutas = new RutaService();
    
    public int guardar(NovedadModel novedad) throws SQLException {
        return novedadServicio.insertarBase(novedad);
    }

    public List<NovedadModel> listar() throws SQLException {
        return novedadServicio.listar();
    }

    public NovedadModel buscarPorId(int idNovedad) throws SQLException {
        return novedadServicio.buscarPorId(idNovedad);
    }

    public boolean actualizar(NovedadModel novedad) throws SQLException {
        return novedadServicio.actualizar(novedad);
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return novedadServicio.eliminar(idNovedad);
    }
    public List<EstudianteModel> listarEstudiantes() throws SQLException {
        return estudiantes.listar();
    }

    public List<VehiculoModel> listarVehiculos() throws SQLException {
        return vehiculos.listar();
    }

    public List<ConductorModel> listarConductores() throws SQLException {
        return conductores.listar();
    }

    public List<RutaModel> listarRutas() throws SQLException {
        return rutas.listar();
    }
    public boolean actualizarEstado(int idNovedad, String estado) throws SQLException {
    return novedadServicio.actualizarEstado(idNovedad, estado);
}

}
