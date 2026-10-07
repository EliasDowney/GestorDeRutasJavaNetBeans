package Controller;

import Model.EstudianteModel;
import Model.NovedadEstudianteModel;
import Model.NovedadModel;
import Service.EstudianteService;
import Service.NovedadEstudianteService;

import java.util.List;

public class NovedadEstudianteController {

    private final NovedadEstudianteService service;

    public NovedadEstudianteController() {
        this(new NovedadEstudianteService());
    }

    public NovedadEstudianteController(NovedadEstudianteService service) {
        this.service = service;
    }

    public NovedadEstudianteController(EstudianteService estudianteService) {
        this(new NovedadEstudianteService(estudianteService));
    }

    public boolean registrar(NovedadEstudianteModel novedad) {
        return service.registrar(novedad);
    }

    public NovedadEstudianteModel buscarPorId(int id) {
        return service.buscarPorId(id);
    }

    public List<NovedadEstudianteModel> listar() {
        return service.listar();
    }

    public List<NovedadEstudianteModel> listarPorEstudiante(EstudianteModel estudiante) {
        return service.listarPorEstudiante(estudiante);
    }

    public List<NovedadModel> listarNovedadesDe(EstudianteModel estudiante) {
        return service.listarNovedadesDe(estudiante);
    }

    public boolean eliminar(int id) {
        return service.eliminar(id);
    }
}
