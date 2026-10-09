package Model;

public class EstudianteModel extends UsuarioModel {

    private int idEstudiante;
    private String grado;
    private String direccion;
    private String nombreAcudiente;
    private String correoAcudiente;
    private Integer idRuta;

    public EstudianteModel() {
    }

    public EstudianteModel(int idEstudiante, String grado, String direccion,
            String nombreAcudiente, String correoAcudiente,
            String documento, String nombre, String apellido, String telefono, String estado) {
        super(documento, nombre, apellido, telefono, estado);
        this.idEstudiante = idEstudiante;
        this.grado = grado;
        this.direccion = direccion;
        this.nombreAcudiente = nombreAcudiente;
        this.correoAcudiente = correoAcudiente;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreAcudiente() {
        return nombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        this.nombreAcudiente = nombreAcudiente;
    }

    public String getCorreoAcudiente() {
        return correoAcudiente;
    }

    public void setCorreoAcudiente(String correoAcudiente) {
        this.correoAcudiente = correoAcudiente;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public String obtenerUbicacion() {
        return "Dirección: " + direccion;
    }

}