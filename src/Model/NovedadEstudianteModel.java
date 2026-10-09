package Model;

import java.time.LocalDate;

public class NovedadEstudianteModel extends NovedadModel {

    private int idEstudiante;
    private int idRuta;

    public NovedadEstudianteModel() {
    }

    public NovedadEstudianteModel(int idNovedad, String descripcion, LocalDate fecha, String estado, String categoria,
            int idEstudiante, int idRuta) {
        super(idNovedad, descripcion, fecha, estado, categoria);
        this.idEstudiante = idEstudiante;
        this.idRuta = idRuta;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

}