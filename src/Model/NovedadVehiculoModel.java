package Model;

import java.time.LocalDate;

public class NovedadVehiculoModel extends NovedadModel {

    private int idVehiculo;
    private int idRuta;
    private int idConductor;

    public NovedadVehiculoModel() {
    }

    public NovedadVehiculoModel(int idNovedad, String descripcion, LocalDate fecha, String estado, String categoria,
            int idVehiculo, int idRuta, int idConductor) {
        super(idNovedad, descripcion, fecha, estado, categoria);
        this.idVehiculo = idVehiculo;
        this.idRuta = idRuta;
        this.idConductor = idConductor;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

}