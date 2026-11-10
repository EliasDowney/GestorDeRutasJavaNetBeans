package Controller;

import Model.ConductorModel;
import Model.CoordinadorModel;
import Model.EstudianteModel;
import Model.RutaModel;
import Model.VehiculoModel;
import Service.*;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalTime;

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
        ConductorModel conductor = conductorServicio.buscarPorId(idConductor);
        return (conductor == null) ? "Sin conductor" : conductor.getNombre() + " " + conductor.getApellido();
    }

    public String datosCoordinador(int idCoordinador) throws SQLException {
        CoordinadorModel conductor = coordinadorServicio.buscarPorId(idCoordinador);
        return (conductor == null) ? "Sin coordinador" : conductor.getNombre() + " " + conductor.getApellido();
    }

    public String datosVehiculo(int idVehiculo) throws SQLException {
        VehiculoModel vehiculo = vehiculoServicio.buscarPorId(idVehiculo);
        return (vehiculo == null) ? "Sin vehiculo" : vehiculo.getPlaca() + " (capacidad: " + vehiculo.getCapacidad() + ")";
    }

    public int capacidad(int idVehiculo) throws SQLException {
        VehiculoModel vehiculo = vehiculoServicio.buscarPorId(idVehiculo);
        return (vehiculo == null) ? 0 : vehiculo.getCapacidad();
    }
    public List<String> paradasRecorrido(List<EstudianteModel> asignados) {
        return servicio.paradasRecorrido(asignados);
    }

    public LocalTime horaSalida(RutaModel ruta) {
        return servicio.horaSalida(ruta);
    }

    public int minutosPorParada(RutaModel ruta, int totalParadas) {
        return servicio.minutosPorParada(ruta, totalParadas);
    }

    public String textoDiferencia(int realSegundos, int programadoMin) {
        return servicio.textoDiferencia(realSegundos, programadoMin);
    }
}
