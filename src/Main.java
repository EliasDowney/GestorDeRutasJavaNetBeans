import Controller.EstudianteController;
import Controller.NovedadController;
import Controller.NovedadEstudianteController;
import Model.EstudianteModel;
import Model.NovedadEstudianteModel;
import Model.NovedadModel;
import Model.RutaModel;
import Service.EstudianteService;

import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EstudianteService estudianteService = new EstudianteService();
        EstudianteController estudianteController = new EstudianteController(estudianteService);
        NovedadController novedadController = new NovedadController();
        NovedadEstudianteController novedadEstudianteController =
                new NovedadEstudianteController(estudianteService);

        System.out.println("=== 1. ESTUDIANTES ===");
        EstudianteModel ana = new EstudianteModel("Calle 1 #2-3", "Ana Perez", "E-001");
        EstudianteModel luis = new EstudianteModel("Carrera 4 #5-6", "Luis Gomez", "E-002");
        System.out.println("Guardar Ana  : " + estudianteController.guardar(ana));
        System.out.println("Guardar Luis : " + estudianteController.guardar(luis));
        System.out.println("Guardar sin numero : "
                + estudianteController.guardar(new EstudianteModel("Dir X", "Sin Numero", "")));
        mostrarEstudiantes(estudianteController.listar());

        System.out.println();
        System.out.println("=== 2. RUTA 1 ---> * ESTUDIANTE ===");
        RutaModel ruta = new RutaModel(1, "Ruta Norte", LocalTime.of(6, 0), LocalTime.of(14, 0), "Activa");
        System.out.println("Agregar Ana  : " + ruta.agregarEstudiante(ana));
        System.out.println("Agregar Luis : " + ruta.agregarEstudiante(luis));
        System.out.println("Agregar Ana otra vez (duplicado): " + ruta.agregarEstudiante(ana));
        System.out.println("Ruta " + ruta.getNombre() + " [" + ruta.getHoraSalida() + " - "
                + ruta.getHoraFinal() + "] estudiantes=" + ruta.getListaEstudiantes().size());
        for (EstudianteModel e : ruta.getListaEstudiantes()) {
            System.out.println("   - " + e.getNombre() + " (" + e.getNumero() + ") " + e.getDireccion());
        }

        System.out.println();
        System.out.println("=== 3. NOVEDAD (entidad base) ===");
        NovedadModel novedad = new NovedadModel("Retraso en salida", 20261001, "Pendiente",
                NovedadModel.CATEGORIA_ALTA);
        System.out.println("Guardar novedad : " + novedadController.guardar(novedad)
                + " -> idNovedad=" + novedad.getIdNovedad());
        System.out.println("Guardar con categoria invalida : "
                + novedadController.guardar(new NovedadModel("Otra", 20261001, "Pendiente", "Media")));
        for (NovedadModel n : novedadController.listar()) {
            System.out.println("   #" + n.getIdNovedad() + " " + n.getDescripcion()
                    + " | fecha=" + n.getFecha() + " | " + n.getEstado() + " | " + n.getCategoria());
        }

        System.out.println();
        System.out.println("=== 4. NOVEDADES DE ESTUDIANTE (hereda de Novedad) ===");
        NovedadEstudianteModel ne1 = new NovedadEstudianteModel("Ausencia injustificada",
                20261002, "Abierta", NovedadModel.CATEGORIA_ALTA, ana);
        NovedadEstudianteModel ne2 = new NovedadEstudianteModel("Llegada tarde",
                20261003, "Cerrada", NovedadModel.CATEGORIA_BAJA, ana);
        NovedadEstudianteModel ne3 = new NovedadEstudianteModel("Observacion medica",
                20261004, "Abierta", NovedadModel.CATEGORIA_BAJA, luis);
        System.out.println("Registrar ne1 (Ana)   : " + novedadEstudianteController.registrar(ne1)
                + " -> id=" + ne1.getId() + ", idNovedad=" + ne1.getIdNovedad());
        System.out.println("Registrar ne2 (Ana)   : " + novedadEstudianteController.registrar(ne2)
                + " -> id=" + ne2.getId());
        System.out.println("Registrar ne3 (Luis)  : " + novedadEstudianteController.registrar(ne3)
                + " -> id=" + ne3.getId());

        EstudianteModel desconocido = new EstudianteModel("Dir ?", "Desconocido", "E-999");
        NovedadEstudianteModel ne4 = new NovedadEstudianteModel("Novedad sin estudiante registrado",
                20261005, "Abierta", NovedadModel.CATEGORIA_ALTA, desconocido);
        System.out.println("Registrar con estudiante NO registrado : "
                + novedadEstudianteController.registrar(ne4));
        System.out.println("Registrar sin estudiante : "
                + novedadEstudianteController.registrar(new NovedadEstudianteModel("X", 1, "Abierta",
                        NovedadModel.CATEGORIA_ALTA, null)));

        System.out.println();
        System.out.println("=== 5. CONSULTA * ---> 1 (novedades de Ana) ===");
        mostrarNovedadesDeEstudiante(novedadEstudianteController.listarNovedadesDe(ana));
        System.out.println("Detalle NovedadEstudiante de Ana:");
        for (NovedadEstudianteModel n : novedadEstudianteController.listarPorEstudiante(ana)) {
            System.out.println("   #" + n.getId() + " [" + n.getCategoria() + "] "
                    + n.getDescripcion() + " -> estudiante=" + n.getEstudiante().getNombre());
        }
        System.out.println("Detalle NovedadEstudiante de Luis:");
        for (NovedadEstudianteModel n : novedadEstudianteController.listarPorEstudiante(luis)) {
            System.out.println("   #" + n.getId() + " [" + n.getCategoria() + "] "
                    + n.getDescripcion() + " -> estudiante=" + n.getEstudiante().getNombre());
        }

        System.out.println();
        System.out.println("=== 6. TOTALES ===");
        System.out.println("estudiantes=" + estudianteController.listar().size()
                + ", novedades=" + novedadController.listar().size()
                + ", novedadesEstudiante=" + novedadEstudianteController.listar().size());
        System.out.println("Eliminar ne1 : " + novedadEstudianteController.eliminar(ne1.getId()));
        System.out.println("novedadesEstudiante ahora="
                + novedadEstudianteController.listar().size());
    }

    private static void mostrarEstudiantes(List<EstudianteModel> estudiantes) {
        for (EstudianteModel e : estudiantes) {
            System.out.println("   - " + e.getNombre() + " | " + e.getNumero() + " | " + e.getDireccion());
        }
    }

    private static void mostrarNovedadesDeEstudiante(List<NovedadModel> novedades) {
        System.out.println("Total=" + novedades.size());
        for (NovedadModel n : novedades) {
            String tipo = n instanceof NovedadEstudianteModel
                    ? "NovedadEstudiante"
                    : "Novedad";
            System.out.println("   - [" + tipo + "] idNovedad=" + n.getIdNovedad()
                    + " " + n.getDescripcion() + " | " + n.getCategoria()
                    + (n.esAlta() ? " (ALTA)" : " (BAJA)"));
        }
    }
}
