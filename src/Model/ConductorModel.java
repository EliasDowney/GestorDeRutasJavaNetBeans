package Model;

public class ConductorModel extends UsuarioModel {

    private int idConductor;
    private String licencia;

    public ConductorModel() {
    }

    public ConductorModel(int idConductor, String licencia, String documento, String nombre, String apellido, String telefono, String estado) {
        super(documento, nombre, apellido, telefono, estado);
        this.idConductor = idConductor;
        this.licencia = licencia;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

}