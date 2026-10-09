package Model;

import java.time.LocalTime;

public class RutaModel {

    private int idRuta;
    private String nombre;
    private LocalTime horaSalida;
    private LocalTime horaFinal;
    private String estado;
    private int idConductor;
    private int idVehiculo;
    private int idCoordinador;

    public RutaModel() {
    }

    public RutaModel(int idRuta, String nombre, LocalTime horaSalida, LocalTime horaFinal, String estado,
            int idConductor, int idVehiculo, int idCoordinador) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.horaSalida = horaSalida;
        this.horaFinal = horaFinal;
        this.estado = estado;
        this.idConductor = idConductor;
        this.idVehiculo = idVehiculo;
        this.idCoordinador = idCoordinador;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    @Override
    public String toString() {
        return nombre + " (" + horaSalida + " - " + horaFinal + ")";
    }

}