package Service;

import Model.EstudianteModel;
import Model.RutaModel;
import Model.VehiculoModel;
import Repository.EstudianteRepository;
import Repository.RutaRepository;
import Repository.VehiculoRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RutaService {

    private final RutaRepository repositorio = new RutaRepository();
    private final EstudianteRepository estudianteRepositorio = new EstudianteRepository();

    public int guardar(RutaModel r) throws SQLException {
        return repositorio.guardar(r);
    }

    public List<RutaModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public RutaModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(RutaModel r) throws SQLException {
        return repositorio.actualizar(r);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }

    private static final Pattern PRIMER_NUMERO = Pattern.compile("\\d+");

    private static final Comparator<EstudianteModel> POR_LEJANIA =
            Comparator.comparingInt((EstudianteModel e) -> numeroDeDireccion(e.getDireccion())).reversed();

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
        return estudianteRepositorio.listarSinRuta();
    }

    public int asignarAutomatica(RutaModel ruta) throws SQLException {
        VehiculoModel vehiculo = new VehiculoRepository().buscarPorId(ruta.getIdVehiculo());
        if (vehiculo == null) {
            return 0;
        }
        int libres = vehiculo.getCapacidad() - estudianteRepositorio.listarPorRuta(ruta.getIdRuta()).size();
        if (libres <= 0) {
            return 0;
        }
        List<EstudianteModel> nuevos = asignarEstudiantes(estudianteRepositorio.listarSinRuta(), libres);
        for (EstudianteModel e : nuevos) {
            estudianteRepositorio.asignarRuta(e.getIdEstudiante(), ruta.getIdRuta());
        }
        return nuevos.size();
    }

    public boolean quitarEstudiante(int idEstudiante) throws SQLException {
        return estudianteRepositorio.quitarRuta(idEstudiante);
    }

}