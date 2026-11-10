package Service;

import Model.EstudianteModel;
import Model.RutaModel;
import Model.VehiculoModel;
import Repository.EstudianteRepository;
import Repository.RutaRepository;
import Repository.VehiculoRepository;
import java.sql.SQLException;
import java.util.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.Duration;
import java.time.LocalTime;


public class RutaService {

    private final RutaRepository repositorio = new RutaRepository();
    private final EstudianteRepository estudianteRepositorio = new EstudianteRepository();

    public int guardar(RutaModel ruta) throws SQLException {
        return repositorio.guardar(ruta);
    }

    public List<RutaModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public RutaModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(RutaModel ruta) throws SQLException {
        return repositorio.actualizar(ruta);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }

    private static final Pattern PRIMER_NUMERO = Pattern.compile("\\d+");

    private static final Comparator<EstudianteModel> POR_LEJANIA =
            Comparator.comparingInt((EstudianteModel estudiante) -> numeroDeDireccion(estudiante.getDireccion())).reversed();

    private static int numeroDeDireccion(String direccion) {
        if (direccion == null) {
            return 0;
        }
        Matcher m = PRIMER_NUMERO.matcher(direccion);
        return m.find() ? Integer.parseInt(m.group()) : 0;
    }

    public List<EstudianteModel> asignarEstudiantes(List<EstudianteModel> pendientes, int cupo) {
        List<EstudianteModel> ordenados = new ArrayList<>(pendientes);
        ordenados.sort(POR_LEJANIA);
        if (ordenados.size() > cupo) {
            return new ArrayList<>(ordenados.subList(0, cupo));
        }
        return ordenados;
    }

    public List<EstudianteModel> listarAsignados(int idRuta) throws SQLException {
        List<EstudianteModel> lista = estudianteRepositorio.listarPorRuta(idRuta);
        lista.sort(POR_LEJANIA);
        return lista;
    }

    public List<EstudianteModel> listarPendientes() throws SQLException {
        List<EstudianteModel> pendientes = new ArrayList<>();
        for (EstudianteModel estudiante : estudianteRepositorio.listarSinRuta()) {
            if ("ACTIVO".equalsIgnoreCase(estudiante.getEstado())) {
                pendientes.add(estudiante);
            }
        }
        return pendientes;
    }

    public int asignarAutomatica(RutaModel ruta) throws SQLException {
        if (!"ACTIVO".equalsIgnoreCase(ruta.getEstado())) {
            return -1;
        }
        VehiculoModel vehiculo = new VehiculoRepository().buscarPorId(ruta.getIdVehiculo());
        if (vehiculo == null) {
            return 0;
        }
        int libres = vehiculo.getCapacidad() - estudianteRepositorio.listarPorRuta(ruta.getIdRuta()).size();
        if (libres <= 0) {
            return 0;
        }
        List<EstudianteModel> nuevos = asignarEstudiantes(listarPendientes(), libres);
        for (EstudianteModel estudiante : nuevos) {
            estudianteRepositorio.asignarRuta(estudiante.getIdEstudiante(), ruta.getIdRuta());
        }
        return nuevos.size();
    }

    public boolean quitarEstudiante(int idEstudiante) throws SQLException {
        return estudianteRepositorio.quitarRuta(idEstudiante);
    }
    private static final int MINUTOS_POR_PARADA = 10;
    public List<String> paradasRecorrido(List<EstudianteModel> asignados) {
        List<EstudianteModel> ordenados = new ArrayList<>(asignados);
        ordenados.sort(Comparator.comparingInt(
                (EstudianteModel estudiante) -> numeroDeDireccion(estudiante.getDireccion())));
        List<String> paradas = new ArrayList<>();
        for (EstudianteModel estudiante : ordenados) {
            String direccion = estudiante.getDireccion();
            paradas.add(direccion == null || direccion.isBlank() ? "Sin direccion" : direccion);
        }
        return paradas;
    }

    public LocalTime horaSalida(RutaModel ruta) {
        return leerHora(ruta.getHoraSalida(), LocalTime.of(6, 0));
    }
    public int minutosPorParada(RutaModel ruta, int totalParadas) {
        LocalTime salida = horaSalida(ruta);
        long duracion = Duration.between(salida, leerHora(ruta.getHoraFinal(), salida)).toMinutes();
        return (duracion > 0 && totalParadas > 0)
                ? (int) Math.max(1, duracion / totalParadas) : MINUTOS_POR_PARADA;
    }
    public String textoDiferencia(int realSegundos, int programadoMin) {
        int dif = realSegundos - programadoMin * 60;
        if (dif == 0) {
            return "justo a tiempo";
        }
        int abs = Math.abs(dif);
        return (dif < 0 ? "adelantados " : "retrasados ") + (abs / 60) + " min " + (abs % 60) + " s";
    }

    private LocalTime leerHora(Object valor, LocalTime porDefecto) {
        try {
            return LocalTime.parse(String.valueOf(valor));
        } catch (Exception ex) {
            return porDefecto;
        }
    }
    
    public int cuposOcupados(RutaModel ruta) throws SQLException {
    return estudianteRepositorio.listarPorRuta(ruta.getIdRuta()).size();
}

    public int cuposDisponibles(RutaModel ruta) throws SQLException {
        VehiculoModel vehiculo = new VehiculoRepository().buscarPorId(ruta.getIdVehiculo());
        if (vehiculo == null) {
            return 0;
        }
        return Math.max(0, vehiculo.getCapacidad() - cuposOcupados(ruta));
    }

    public double porcentajeOcupacion(RutaModel ruta) throws SQLException {
        VehiculoModel vehiculo = new VehiculoRepository().buscarPorId(ruta.getIdVehiculo());
        if (vehiculo == null || vehiculo.getCapacidad() == 0) {
            return 0;
        }
        return cuposOcupados(ruta) * 100.0 / vehiculo.getCapacidad();
    }

    public long duracionMinutos(RutaModel ruta) {
        return Duration.between(ruta.getHoraSalida(), ruta.getHoraFinal()).toMinutes();
    }
    
    
}