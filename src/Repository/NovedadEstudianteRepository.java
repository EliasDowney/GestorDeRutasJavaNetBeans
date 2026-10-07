package Repository;

import Model.EstudianteModel;
import Model.NovedadEstudianteModel;

import java.util.ArrayList;
import java.util.List;

public class NovedadEstudianteRepository {

    private final List<NovedadEstudianteModel> novedades = new ArrayList<>();
    private int siguienteId = 1;

    public List<NovedadEstudianteModel> listar() {
        return new ArrayList<>(novedades);
    }

    public NovedadEstudianteModel buscarPorId(int id) {
        return novedades.stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<NovedadEstudianteModel> buscarPorEstudiante(EstudianteModel estudiante) {
        List<NovedadEstudianteModel> resultado = new ArrayList<>();
        if (estudiante == null) {
            return resultado;
        }
        for (NovedadEstudianteModel novedad : novedades) {
            if (novedad.getEstudiante() != null
                    && novedad.getEstudiante().getNumero() != null
                    && novedad.getEstudiante().getNumero().equals(estudiante.getNumero())) {
                resultado.add(novedad);
            }
        }
        return resultado;
    }

    public void guardar(NovedadEstudianteModel novedad) {
        if (novedad.getId() == 0) {
            int id = siguienteId++;
            novedad.setId(id);
            novedad.setIdNovedad(id);
        }
        NovedadEstudianteModel existente = buscarPorId(novedad.getId());
        if (existente != null) {
            novedades.set(novedades.indexOf(existente), novedad);
        } else {
            novedades.add(novedad);
        }
    }

    public boolean eliminar(int id) {
        return novedades.removeIf(n -> n.getId() == id);
    }
}
