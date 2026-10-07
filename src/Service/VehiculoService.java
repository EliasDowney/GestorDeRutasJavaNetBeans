package Service;

import Model.VehiculoModel;
import Repository.VehiculoRepository;

import java.util.List;

public class VehiculoService {

    private final VehiculoRepository repository;

    public VehiculoService() {
        this.repository = new VehiculoRepository();
    }

    public List<VehiculoModel> listar() {
        return repository.listar();
    }

    public VehiculoModel buscarPorId(int idVehiculo) {
        return repository.buscarPorId(idVehiculo);
    }

    public boolean guardar(VehiculoModel vehiculo) {
        if (vehiculo == null) {
            return false;
        }
        if (vehiculo.getPlaca() == null || vehiculo.getPlaca().trim().isEmpty()) {
            return false;
        }
        if (vehiculo.getCapacidad() <= 0) {
            return false;
        }
        repository.guardar(vehiculo);
        return true;
    }

    public boolean eliminar(int idVehiculo) {
        return repository.eliminar(idVehiculo);
    }
}
