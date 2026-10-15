package Controller;

import Model.NovedadVehiculoModel;
import Service.NovedadVehiculoService;
import java.sql.SQLException;
import java.util.List;

public class NovedadVehiculoController extends NovedadController {

    private final NovedadVehiculoService servicio = new NovedadVehiculoService();

    public boolean guardarVehiculo(NovedadVehiculoModel n) throws SQLException {
        return servicio.guardar(n);
    }

    public List<NovedadVehiculoModel> listarNovedadesVehiculo() throws SQLException {
        return servicio.listar();
    }

    public boolean eliminarVehiculo(int idNovedad) throws SQLException {
        return servicio.eliminar(idNovedad);
    }

}
