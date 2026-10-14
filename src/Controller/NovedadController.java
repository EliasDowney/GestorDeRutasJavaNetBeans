package Controller;

import Model.ConductorModel;
import Model.EstudianteModel;
import Model.NovedadModel;
import Model.RutaModel;
import Model.VehiculoModel;
import Service.ConductorService;
import Service.EstudianteService;
import Service.NovedadService;
import Service.RutaService;
import Service.VehiculoService;
import java.sql.SQLException;
import java.util.List;

public abstract class NovedadController {

    private final NovedadService novedadServicio = new NovedadService();
    private final EstudianteService estudiantes = new EstudianteService();
    private final VehiculoService vehiculos = new VehiculoService();
    private final ConductorService conductores = new ConductorService();
    private final RutaService rutas = new RutaService();

    // CRUD generico de Novedad (aportado en el PR #6 por el companero)
    public int guardar(NovedadModel n) throws SQLException {
        return novedadServicio.insertarBase(n);
    }

    public List<NovedadModel> listar() throws SQLException {
        return novedadServicio.listar();
    }

    public NovedadModel buscarPorId(int idNovedad) throws SQLException {
        return novedadServicio.buscarPorId(idNovedad);
    }

    public boolean actualizar(NovedadModel n) throws SQLException {
        return novedadServicio.actualizar(n);
    }

    public boolean eliminar(int idNovedad) throws SQLException {
        return novedadServicio.eliminar(idNovedad);
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
