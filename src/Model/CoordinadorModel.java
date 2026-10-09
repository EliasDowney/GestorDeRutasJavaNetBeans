package Model;

public class CoordinadorModel extends UsuarioModel {

    private int idCoordinador;
    private String correo;

    public CoordinadorModel() {
    }

    public CoordinadorModel(int idCoordinador, String correo, String documento, String nombre, String apellido, String telefono, String estado) {
        super(documento, nombre, apellido, telefono, estado);
        this.idCoordinador = idCoordinador;
        this.correo = correo;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}