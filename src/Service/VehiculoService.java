package Service;

import Exepcion.Exepcionv;
import Model.VehiculoModel;
import Repository.VehiculoRepository;
import java.sql.SQLException;
import java.util.List;

public class VehiculoService {
    private void validarPlacaUnica(VehiculoModel v) throws SQLException, Exepcionv {
    for (VehiculoModel otro : repositorio.listar()) {
        boolean mismaPlaca = otro.getPlaca().equalsIgnoreCase(v.getPlaca().trim());
        boolean esOtroVehiculo = otro.getIdVehiculo() != v.getIdVehiculo();
        if (mismaPlaca && esOtroVehiculo) {
            throw new Exepcionv("Ya existe un vehiculo con la placa " + v.getPlaca() + ".");
        }
    }
  }


    private final VehiculoRepository repositorio = new VehiculoRepository();

    
    public int guardar(VehiculoModel v) throws SQLException , Exepcionv  {
        validarPlacaUnica(v);
        return repositorio.guardar(v);
    }

    public List<VehiculoModel> listar() throws SQLException {
        return repositorio.listar();
    }

    public VehiculoModel buscarPorId(int id) throws SQLException {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizar(VehiculoModel v) throws SQLException, Exepcionv {
        validarPlacaUnica(v);
        return repositorio.actualizar(v);
    }

    public boolean eliminar(int id) throws SQLException {
        return repositorio.eliminar(id);
    }
   
}

