package Controller;

import Model.ConductorModel;
import Model.CoordinadorModel;
import Model.EstudianteModel;
import Model.RutaModel;
import Model.VehiculoModel;
import Service.ConductorService;
import Service.CoordinadorService;
import Service.RutaService;
import Service.VehiculoService;
import java.sql.SQLException;
import java.util.List;

public class RutaController {

    private final RutaService servicio = new RutaService();
    private final ConductorService conductorServicio = new ConductorService();
    private final CoordinadorService coordinadorServicio = new CoordinadorService();
    private final VehiculoService vehiculoServicio = new VehiculoService();

    public List<RutaModel> listar() throws SQLException {
        return servicio.listar();
    }

    public RutaModel buscarPorId(int id) throws SQLException {
        return servicio.buscarPorId(id);
    }

    public int guardar(RutaModel ruta) throws SQLException {
        return servicio.guardar(ruta);
    }

    public boolean actualizar(RutaModel ruta) throws SQLException {
        return servicio.actualizar(ruta);
    }

    public boolean eliminar(int id) throws SQLException {
        return servicio.eliminar(id);
    }

    public List<ConductorModel> listarConductores() throws SQLException {
        return conductorServicio.listar();
    }

    public List<CoordinadorModel> listarCoordinadores() throws SQLException {
        return coordinadorServicio.listar();
    }

    public List<VehiculoModel> listarVehiculos() throws SQLException {
        return vehiculoServicio.listar();
    }

    public List<EstudianteModel> listarAsignados(int idRuta) throws SQLException {
        return servicio.listarAsignados(idRuta);
    }

    public List<EstudianteModel> listarPendientes() throws SQLException {
        return servicio.listarPendientes();
    }

    public int asignarAutomatica(RutaModel ruta) throws SQLException {
        return servicio.asignarAutomatica(ruta);
    }

    public boolean quitarEstudiante(int idEstudiante) throws SQLException {
        return servicio.quitarEstudiante(idEstudiante);
    }

    public String datosConductor(int idConductor) throws SQLException {
        ConductorModel c = conductorServicio.buscarPorId(idConductor);
        return (c == null) ? "Sin conductor" : c.getNombre() + " " + c.getApellido();
    }

    public String datosCoordinador(int idCoordinador) throws SQLException {
        CoordinadorModel c = coordinadorServicio.buscarPorId(idCoordinador);
        return (c == null) ? "Sin coordinador" : c.getNombre() + " " + c.getApellido();
    }

    public String datosVehiculo(int idVehiculo) throws SQLException {
        VehiculoModel v = vehiculoServicio.buscarPorId(idVehiculo);
        return (v == null) ? "Sin vehiculo" : v.getPlaca() + " (capacidad: " + v.getCapacidad() + ")";
    }

    public int capacidad(int idVehiculo) throws SQLException {
        VehiculoModel v = vehiculoServicio.buscarPorId(idVehiculo);
        return (v == null) ? 0 : v.getCapacidad();
    }

   

    public int cuposDisponibles(RutaModel ruta) throws SQLException {
        return servicio.cuposDisponibles(ruta);
    }

    public String resumenOcupacion(RutaModel ruta) throws SQLException {
        int ocupados = servicio.cuposOcupados(ruta);
        int libres = servicio.cuposDisponibles(ruta);
        double porcentaje = servicio.porcentajeOcupacion(ruta);
        long minutos = servicio.duracionMinutos(ruta);
        return ocupados + " estudiantes, " + libres + " cupos libres ("
                + String.format("%.1f", porcentaje) + "% ocupada). Duración: " + minutos + " min";
    }
    
    public String mensajeEstadoRuta(RutaModel ruta) throws SQLException {
    String estado = "ACTIVO".equalsIgnoreCase(ruta.getEstado()) ? "ACTIVA" : "INACTIVA";
     if (!"ACTIVO".equalsIgnoreCase(ruta.getEstado())) {
        return "INACTIVA";
    }
    return "Ruta: " + ruta.getNombre() + "\n"
            + "Estado: " + estado + "\n"
            + "Conductor: " + datosConductor(ruta.getIdConductor()) + "\n"
            + "Vehiculo: " + datosVehiculo(ruta.getIdVehiculo()) + "\n"
            + resumenOcupacion(ruta);
}
}
