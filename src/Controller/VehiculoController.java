package Controller;
import Model.Vehiculo;
import Service.VehiculoService;
import java.util.List;

public class VehiculoController {
    
    private VehiculoService service;
    
    public VehiculoController()
    {
        this.service = new VehiculoService();
    }
    public void listarVehiculos() 
    {
        List<Vehiculo> vehiculo = service.listarVehiculos();
        
        for(int i =0; i< vehiculo.size();i++)
        {
            System.out.println(vehiculo.get(i).getPlaca());
            System.out.println("  "+ vehiculo.get(i).getCapacidad());
            System.out.println("  "+ vehiculo.get(i).getEstado());
            System.out.println("----------");
        }   
    }
    public boolean guardarVehiculo(int id, String placa,int capacidad,String estado) 
    {
        Vehiculo vehiculo = new Vehiculo(id, placa, capacidad, estado);
        return service.guardarVehiculo(vehiculo);
    }
    
    public boolean actualizarVehiculo(int id, String placa,int capacidad,String estado) 
    {
        Vehiculo vehiculo = new Vehiculo(id, placa, capacidad, estado);
        return service.guardarVehiculo(vehiculo);
    }
    
    public boolean eliminarVehiculo(int id)
    {
        return service.eliminarVehiculo(id);
    }
}
