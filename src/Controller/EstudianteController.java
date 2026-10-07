package Controller;

import Model.EstudianteModel;
import Service.EstudianteService;

import java.util.List;

public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController() {
        this(new EstudianteService());
    }

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    public boolean guardar(EstudianteModel estudiante) {
        return service.guardar(estudiante);
    }

    public EstudianteModel buscarPorNumero(String numero) {
        return service.buscarPorNumero(numero);
    }

    public List<EstudianteModel> listar() {
        return service.listar();
    }

    public boolean eliminar(String numero) {
        return service.eliminar(numero);
    }
}
