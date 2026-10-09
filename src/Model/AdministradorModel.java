package Model;

public class AdministradorModel {

    private int id;
    private String documento;
    private String clave;
    private String nombre;

    public AdministradorModel() {
    }

    public AdministradorModel(int id, String documento, String clave, String nombre) {
        this.id = id;
        this.documento = documento;
        this.clave = clave;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
