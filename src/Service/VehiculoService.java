package Service;
import Model.Vehiculo;
import Repository.VehiculoRepository;
import java.util.List;

public class VehiculoService {
    
    private VehiculoRepository repository;
    
    public VehiculoService()
    {
        this.repository = new VehiculoRepository();
    }
    
    public List<Vehiculo> listarVehiculos()
    {
        return repository.listar();
    }
    
    public boolean guardarVehiculo(Vehiculo vehiculo)
    {
        return repository.guardar(vehiculo);
    }
    
    public boolean actualizarVehiculo(Vehiculo vehiculo) 
    {
        return repository.actualizar(vehiculo);
    }
    public boolean eliminarVehiculo(int id) 
    {
        return repository.eliminar(id);
    }
}
