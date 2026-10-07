package Repository;

import Model.EstudianteModel;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository {

    private final List<EstudianteModel> estudiantes = new ArrayList<>();

    public List<EstudianteModel> listar() {
        return new ArrayList<>(estudiantes);
    }

    public EstudianteModel buscarPorNumero(String numero) {
        if (numero == null) {
            return null;
        }
        return estudiantes.stream()
                .filter(e -> numero.equals(e.getNumero()))
                .findFirst()
                .orElse(null);
    }

    public void guardar(EstudianteModel estudiante) {
        EstudianteModel existente = buscarPorNumero(estudiante.getNumero());
        if (existente != null) {
            estudiantes.set(estudiantes.indexOf(existente), estudiante);
        } else {
            estudiantes.add(estudiante);
        }
    }

    public boolean eliminar(String numero) {
        return estudiantes.removeIf(e -> numero != null && numero.equals(e.getNumero()));
    }
}
