
package Model;

import java.time.LocalTime; //la utilizaremos para manejar datos de hora
import java.util.ArrayList; //para implementar las listas de estudiantes
import java.util.List; //para listar onjetos

public class RutaModel {
    private int idRuta; // se me paso preguntar asi "AWD123" o asi "12"
    private String nombre;
    private LocalTime horaSalida; 
    private LocalTime horaFinal;
    private String estado; // Estado de la ruta (ej: "En curso", "Finalizada")
    
//Relacion con demás clases
    private List<EstudianteModel> listaEstudiantes;//una ruta ; muchos estudiantes
    private ConductorModel conductor; // un conductor; una ruta
    private Vehiculo Vehiculo; // un vehiculoj ;una ruta
    private CordinadorModel Cordinador; //
    
//Listar estudiiantes... 
    public RutaModel() {
        this.listaEstudiantes = new ArrayList<>();
    }

    public RutaModel(int idRuta, String nombre, LocalTime horaSalida, LocalTime horaFinal, String estado, List listaEstudiantes, ConductorModel conductor, Vehiculo Vehiculo, CordinadorModel Cordinador) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.horaSalida = horaSalida; //lista para guardar estudiantes  
        this.horaFinal = horaFinal;
        this.estado = estado;
        this.listaEstudiantes = listaEstudiantes;
        this.conductor = conductor;
        this.Vehiculo = Vehiculo;
        this.Cordinador = Cordinador;
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
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<EstudianteModel> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public ConductorModel getConductor() {
        return conductor;
    }

    public void setConductor(ConductorModel conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return Vehiculo;
    }

    public void setVehiculo(Vehiculo Vehiculo) {
        this.Vehiculo = Vehiculo;
    }

    public CordinadorModel getCordinador() {
        return Cordinador;
    }

    public void setCordinador(CordinadorModel Cordinador) {
        this.Cordinador = Cordinador;
    }
    
    
}
