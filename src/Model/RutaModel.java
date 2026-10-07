package Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RutaModel {

    private int idRuta;
    private String nombre;
    private LocalTime horaSalida;
    private LocalTime horaFinal;
    private String estado;
    private List<EstudianteModel> listaEstudiantes;
    private ConductorModel conductor;
    private VehiculoModel vehiculo;
    private CordinadorModel coordinador;

    public RutaModel() {
        this.listaEstudiantes = new ArrayList<>();
    }

    public RutaModel(int idRuta, String nombre, LocalTime horaSalida, LocalTime horaFinal, String estado) {
        this();
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.horaSalida = horaSalida;
        this.horaFinal = horaFinal;
        this.estado = estado;
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

    public List<EstudianteModel> getListaEstudiantes() {
        return Collections.unmodifiableList(listaEstudiantes);
    }

    public void setListaEstudiantes(List<EstudianteModel> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes == null ? new ArrayList<>() : new ArrayList<>(listaEstudiantes);
    }

    public boolean agregarEstudiante(EstudianteModel estudiante) {
        if (estudiante == null || listaEstudiantes.contains(estudiante)) {
            return false;
        }
        return listaEstudiantes.add(estudiante);
    }

    public boolean retirarEstudiante(EstudianteModel estudiante) {
        return listaEstudiantes.remove(estudiante);
    }

    public ConductorModel getConductor() {
        return conductor;
    }

    public void setConductor(ConductorModel conductor) {
        this.conductor = conductor;
    }

    public VehiculoModel getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoModel vehiculo) {
        this.vehiculo = vehiculo;
    }

    public CordinadorModel getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(CordinadorModel coordinador) {
        this.coordinador = coordinador;
    }
}
