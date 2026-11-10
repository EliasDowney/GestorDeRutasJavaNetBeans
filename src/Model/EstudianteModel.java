package Model;

public class EstudianteModel {
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String estado;
    private int idEstudiante;
    private String grado;
    private String direccion;
    private String nombreAcudiente;
    private String correoAcudiente;
    private Integer idRuta;

    public EstudianteModel(String documento, String nombre, String apellido, String telefono, String estado, int idEstudiante, String grado, String direccion, String nombreAcudiente, String correoAcudiente, Integer idRuta) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.estado = estado;
        this.idEstudiante = idEstudiante;
        this.grado = grado;
        this.direccion = direccion;
        this.nombreAcudiente = nombreAcudiente;
        this.correoAcudiente = correoAcudiente;
        this.idRuta = idRuta;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getUbicacion() {
        return "Dirección: " + direccion;
    }
}