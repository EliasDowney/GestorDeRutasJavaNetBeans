package Repository;

import Model.NovedadModel;

import java.util.ArrayList;
import java.util.List;

public class NovedadRepository {

    private final List<NovedadModel> novedades = new ArrayList<>();
    private int siguienteId = 1;

    public List<NovedadModel> listar() {
        return new ArrayList<>(novedades);
    }

    public NovedadModel buscarPorId(int idNovedad) {
        return novedades.stream()
                .filter(n -> n.getIdNovedad() == idNovedad)
                .findFirst()
                .orElse(null);
    }

    public void guardar(NovedadModel novedad) {
        if (novedad.getIdNovedad() == 0) {
            novedad.setIdNovedad(siguienteId++);
        }
        NovedadModel existente = buscarPorId(novedad.getIdNovedad());
        if (existente != null) {
            novedades.set(novedades.indexOf(existente), novedad);
        } else {
            novedades.add(novedad);
        }
    }

    public boolean eliminar(int idNovedad) {
        return novedades.removeIf(n -> n.getIdNovedad() == idNovedad);
    }
}
