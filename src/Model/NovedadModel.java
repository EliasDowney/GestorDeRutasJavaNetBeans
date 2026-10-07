package Model;

public class NovedadModel {

    public static final String CATEGORIA_ALTA = "Alta";
    public static final String CATEGORIA_BAJA = "Baja";

    private int idNovedad;
    private String descripcion;
    private int fecha;
    private String estado;
    private String categoria;

    public NovedadModel() {
    }

    public NovedadModel(int idNovedad, String descripcion, int fecha, String estado, String categoria) {
        this.idNovedad = idNovedad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.categoria = categoria;
    }

    public NovedadModel(String descripcion, int fecha, String estado, String categoria) {
        this(0, descripcion, fecha, estado, categoria);
    }

    public int getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(int idNovedad) {
        this.idNovedad = idNovedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean esAlta() {
        return CATEGORIA_ALTA.equalsIgnoreCase(categoria);
    }
}
