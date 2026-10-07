package Service;

import Model.EstudianteModel;
import Repository.EstudianteRepository;

import java.util.List;

public class EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteService() {
        this.repository = new EstudianteRepository();
    }

    public List<EstudianteModel> listar() {
        return repository.listar();
    }

    public EstudianteModel buscarPorNumero(String numero) {
        return repository.buscarPorNumero(numero);
    }

    public boolean guardar(EstudianteModel estudiante) {
        if (estudiante == null) {
            return false;
        }
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            return false;
        }
        if (estudiante.getNumero() == null || estudiante.getNumero().trim().isEmpty()) {
            return false;
        }
        if (estudiante.getDireccion() == null || estudiante.getDireccion().trim().isEmpty()) {
            return false;
        }
        repository.guardar(estudiante);
        return true;
    }

    public boolean eliminar(String numero) {
        if (numero == null || repository.buscarPorNumero(numero) == null) {
            return false;
        }
        return repository.eliminar(numero);
    }
}
