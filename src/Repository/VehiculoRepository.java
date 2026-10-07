package Repository;

import Model.VehiculoModel;

import java.util.ArrayList;
import java.util.List;

public class VehiculoRepository {

    private final List<VehiculoModel> vehiculos = new ArrayList<>();

    public List<VehiculoModel> listar() {
        return new ArrayList<>(vehiculos);
    }

    public VehiculoModel buscarPorId(int idVehiculo) {
        return vehiculos.stream()
                .filter(v -> v.getIdVehiculo() == idVehiculo)
                .findFirst()
                .orElse(null);
    }

    public void guardar(VehiculoModel vehiculo) {
        VehiculoModel existente = buscarPorId(vehiculo.getIdVehiculo());
        if (existente != null) {
            vehiculos.set(vehiculos.indexOf(existente), vehiculo);
        } else {
            vehiculos.add(vehiculo);
        }
    }

    public boolean eliminar(int idVehiculo) {
        return vehiculos.removeIf(v -> v.getIdVehiculo() == idVehiculo);
    }
}

