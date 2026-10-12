package Model;

import java.time.LocalDate;

public abstract class NovedadModel {

    private int idNovedad;
    private String descripcion;
    private LocalDate fecha;
    private String estado;
    private String categoria;

    public NovedadModel() {
    }

    public NovedadModel(int idNovedad, String descripcion, LocalDate fecha, String estado, String categoria) {
        this.idNovedad = idNovedad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.categoria = categoria;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public void cambiarEstado(String estado) {
        this.estado = estado;
    }

}