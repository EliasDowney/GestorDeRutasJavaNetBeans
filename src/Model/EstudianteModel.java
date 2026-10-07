package Model;

public class EstudianteModel {

    private String direccion;
    private String nombre;
    private String numero;

    public EstudianteModel() {
    }

    public EstudianteModel(String direccion, String nombre, String numero) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
