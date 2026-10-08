package Repository;
import Model.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class VehiculoRepository {
    
    private List<Vehiculo> vehiculos;
    
    public VehiculoRepository() {
        
        vehiculos = new ArrayList<>();
        
        vehiculos.add(new Vehiculo(1,"ZOO123",16,"Activo"));
        vehiculos.add(new Vehiculo(2,"RAR753",24,"Activo"));
        vehiculos.add(new Vehiculo(3,"ONU098",20,"Activo"));
    }
    
    public List<Vehiculo> listar() {
        return vehiculos;
    }
    
    public boolean guardar(Vehiculo vehiculo)
    {
        for(int i=0;i < vehiculos.size();i++)
        {
            if(vehiculo.getIdVehiculo() != vehiculos.get(i).getIdVehiculo() || vehiculo.getPlaca() != vehiculos.get(i).getPlaca())
            {
               vehiculos.add(vehiculo); 
               return true;
            }
            
        }
        return false;
    }
    
    public boolean actualizar(Vehiculo vehiculoActualizado)
    {
        for (int i = 0; i < vehiculos.size(); i++) {

            if (vehiculos.get(i).getIdVehiculo() == vehiculoActualizado.getIdVehiculo()) {

                vehiculos.set(i, vehiculoActualizado);
             return true;
            }
        }
        return false;
    }
    
    public boolean eliminar(int id) 
    {
        for (Vehiculo vehiculo : vehiculos) 
        {
            if (vehiculo.getIdVehiculo() == id) 
            {
                vehiculo.setEstado("Inactivo");
               
                return true;
            }
        }
    return false;
    }
    
    
    
}
