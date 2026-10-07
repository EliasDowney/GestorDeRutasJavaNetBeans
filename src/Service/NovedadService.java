package Service;

import Model.NovedadModel;
import Repository.NovedadRepository;

import java.util.List;

public class NovedadService {

    private final NovedadRepository repository;

    public NovedadService() {
        this.repository = new NovedadRepository();
    }

    public List<NovedadModel> listar() {
        return repository.listar();
    }

    public NovedadModel buscarPorId(int idNovedad) {
        return repository.buscarPorId(idNovedad);
    }

    public boolean guardar(NovedadModel novedad) {
        if (novedad == null) {
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
        repository.guardar(novedad);
        return true;
    }

    public boolean eliminar(int idNovedad) {
        if (repository.buscarPorId(idNovedad) == null) {
            return false;
        }
        return repository.eliminar(idNovedad);
    }
}
