package Service;
import Model.VehiculoModel;
import Repository.VehiculoRepository;

public class VehiculoService {
    
    private VehiculoRepository Repository;
    
    public VehiculoService()
    {
        this.Repository = new VehiculoRepository();
    }
    
}
