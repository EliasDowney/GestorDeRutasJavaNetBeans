package Controller;

import Model.NovedadModel;
import Service.NovedadService;

import java.util.List;

public class NovedadController {

    private final NovedadService service;

    public NovedadController() {
        this(new NovedadService());
    }

    public NovedadController(NovedadService service) {
        this.service = service;
    }

    public boolean guardar(NovedadModel novedad) {
        return service.guardar(novedad);
    }

    public NovedadModel buscarPorId(int idNovedad) {
        return service.buscarPorId(idNovedad);
    }

    public List<NovedadModel> listar() {
        return service.listar();
    }

    public boolean eliminar(int idNovedad) {
        return service.eliminar(idNovedad);
    }
}
