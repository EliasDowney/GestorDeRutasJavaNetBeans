package Service;

import Model.EstudianteModel;
import Model.NovedadEstudianteModel;
import Model.NovedadModel;
import Repository.NovedadEstudianteRepository;

import java.util.ArrayList;
import java.util.List;

public class NovedadEstudianteService {

    private final NovedadEstudianteRepository repository;
    private final EstudianteService estudianteService;

    public NovedadEstudianteService() {
        this(new EstudianteService());
    }

    public NovedadEstudianteService(EstudianteService estudianteService) {
        this.repository = new NovedadEstudianteRepository();
        this.estudianteService = estudianteService;
    }

    public List<NovedadEstudianteModel> listar() {
        return repository.listar();
    }

    public NovedadEstudianteModel buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public boolean registrar(NovedadEstudianteModel novedad) {
        if (novedad == null) {
            return false;
        }
        if (novedad.getEstudiante() == null) {
            return false;
        }
        EstudianteModel registrado = estudianteService.buscarPorNumero(novedad.getEstudiante().getNumero());
        if (registrado == null) {
            return false;
        }
        if (novedad.getDescripcion() == null || novedad.getDescripcion().trim().isEmpty()) {
            return false;
        }
        if (novedad.getEstado() == null || novedad.getEstado().trim().isEmpty()) {
            return false;
        }
        String categoria = novedad.getCategoria();
        if (!NovedadModel.CATEGORIA_ALTA.equalsIgnoreCase(categoria)
                && !NovedadModel.CATEGORIA_BAJA.equalsIgnoreCase(categoria)) {
            return false;
        }
        novedad.setEstudiante(registrado);
        repository.guardar(novedad);
        return true;
    }

    public List<NovedadEstudianteModel> listarPorEstudiante(EstudianteModel estudiante) {
        return repository.buscarPorEstudiante(estudiante);
    }

    public List<NovedadModel> listarNovedadesDe(EstudianteModel estudiante) {
        List<NovedadModel> resultado = new ArrayList<>();
        for (NovedadEstudianteModel novedad : repository.buscarPorEstudiante(estudiante)) {
            resultado.add(novedad);
        }
        return resultado;
    }

    public boolean eliminar(int id) {
        if (repository.buscarPorId(id) == null) {
            return false;
        }
        return repository.eliminar(id);
    }
}
