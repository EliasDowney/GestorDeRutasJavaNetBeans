package Service;

import Exepcion.Exepcionv;
import Model.VehiculoModel;
import Repository.VehiculoRepository;
import java.sql.SQLException;
import java.util.List;

public class VehiculoService {
    private void validarPlacaUnica(VehiculoModel vehiculo) throws SQLException, Exepcionv {
    for (VehiculoModel otro : repositorio.listar()) {
        boolean mismaPlaca = otro.getPlaca().equalsIgnoreCase(vehiculo.getPlaca().trim());
        boolean esOtroVehiculo = otro.getIdVehiculo() != vehiculo.getIdVehiculo();
        if (mismaPlaca && esOtroVehiculo) {
            throw new Exepcionv("Ya existe un vehiculo con la placa " + vehiculo.getPlaca() + ".");
        }
    }
  }


    private final VehiculoRepository repositorio = new VehiculoRepository();

    
    public int guardar(VehiculoModel vehiculo) throws SQLException , Exepcionv  {
        validarPlacaUnica(vehiculo);
        return repositorio.guardar(vehiculo);
    }

    public List<VehiculoModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public VehiculoModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(VehiculoModel vehiculo) throws SQLException, Exepcionv {
        validarPlacaUnica(vehiculo);
        return repositorio.actualizar(vehiculo);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }
   
}

