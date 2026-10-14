package Controller;

import Model.ConductorModel;
import Model.EstudianteModel;
import Model.NovedadEstudianteModel;
import Model.NovedadModel;
import Model.NovedadVehiculoModel;
import Model.RutaModel;
import Model.VehiculoModel;
import Service.ConductorService;
import Service.EstudianteService;
import Service.NovedadEstudianteService;
import Service.NovedadService;
import Service.NovedadVehiculoService;
import Service.RutaService;
import Service.VehiculoService;
import java.sql.SQLException;
import java.util.List;

public class NovedadController {

    private final NovedadService servicio = new NovedadService();
    private final NovedadEstudianteService estudianteServicio = new NovedadEstudianteService();
    private final NovedadVehiculoService vehiculoServicio = new NovedadVehiculoService();
    private final EstudianteService estudiantes = new EstudianteService();
    private final VehiculoService vehiculos = new VehiculoService();
    private final ConductorService conductores = new ConductorService();
    private final RutaService rutas = new RutaService();

    // CRUD de Novedad (aportado en el PR #6)
    public int guardar(NovedadModel n) throws SQLException {
        return servicio.insertarBase(n);
    }

    public List<NovedadModel> listar() throws SQLException {
        return servicio.listar();
    }

    public NovedadModel buscarPorId(int idNovedad) throws SQLException {
        return servicio.buscarPorId(idNovedad);
    }

    public boolean actualizar(NovedadModel n) throws SQLException {
        return servicio.actualizar(n);
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return servicio.eliminar(idNovedad);
    }

    // Novedades de estudiante y vehiculo
    public boolean guardarEstudiante(NovedadEstudianteModel n) throws SQLException {
        return estudianteServicio.guardar(n);
    }

    public boolean guardarVehiculo(NovedadVehiculoModel n) throws SQLException {
        return vehiculoServicio.guardar(n);
    }

    public List<NovedadEstudianteModel> listarNovedadesEstudiante() throws SQLException {
        return estudianteServicio.listar();
    }

    public List<NovedadVehiculoModel> listarNovedadesVehiculo() throws SQLException {
        return vehiculoServicio.listar();
    }

    public boolean eliminarEstudiante(int idNovedad) throws SQLException {
        return estudianteServicio.eliminar(idNovedad);
    }

    public boolean eliminarVehiculo(int idNovedad) throws SQLException {
        return vehiculoServicio.eliminar(idNovedad);
    }

    // Listas auxiliares
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

}
