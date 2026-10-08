package Controller;
import Model.VehiculoModel;
import Service.VehiculoService;

public class VehiculoController {
    
private VehiculoService service;
    
    public VehiculoController()
    {
        this.service = new VehiculoService();
    }

}
