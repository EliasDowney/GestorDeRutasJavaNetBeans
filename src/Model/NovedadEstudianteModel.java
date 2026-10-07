package Model;

public class NovedadEstudianteModel extends NovedadModel {

    private int id;
    private EstudianteModel estudiante;

    public NovedadEstudianteModel() {
    }

    public NovedadEstudianteModel(String descripcion, int fecha, String estado, String categoria,
            EstudianteModel estudiante) {
        super(descripcion, fecha, estado, categoria);
        this.estudiante = estudiante;
    }

    public NovedadEstudianteModel(int id, int idNovedad, String descripcion, int fecha, String estado,
            String categoria, EstudianteModel estudiante) {
        super(idNovedad, descripcion, fecha, estado, categoria);
        this.id = id;
        this.estudiante = estudiante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EstudianteModel getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteModel estudiante) {
        this.estudiante = estudiante;
    }
}
