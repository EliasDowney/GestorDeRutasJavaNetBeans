package Model;

public class VehiculoModel {
    private int idVehiculo;
    private String placa;
    private int capacidad;
    private String estado;

    public VehiculoModel() {
    }

    public VehiculoModel(int idVehiculo, String placa, int capacidad, String estado) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
