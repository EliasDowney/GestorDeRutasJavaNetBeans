package Model;

public class CoordinadorModel {
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String estado;
    private int idCoordinador;

    public CoordinadorModel(String documento, String nombre, String apellido, String telefono, String correo, String estado, int idCoordinador) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        this.idCoordinador = idCoordinador;
    }

    // Getters y Setters
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getIdCoordinador() { return idCoordinador; }
    public void setIdCoordinador(int idCoordinador) { this.idCoordinador = idCoordinador; }
}