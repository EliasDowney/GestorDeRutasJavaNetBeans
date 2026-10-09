package Principal;

import Model.EstudianteModel;
import Service.RutaService;
import java.util.ArrayList;
import java.util.List;

public class DemoConsola {

    public static void main(String[] args) {
        RutaService service = new RutaService();

        List<EstudianteModel> pendientes = new ArrayList<>();
        pendientes.add(new EstudianteModel(1, "10", "CRA 100 # 77-83", "Ana", "ana@mail.com", "1001", "Ana", "Perez", "3001", "ACTIVO"));
        pendientes.add(new EstudianteModel(2, "9", "CLL 127 # 5-20", "Luis", "luis@mail.com", "1002", "Luis", "Gomez", "3002", "ACTIVO"));
        pendientes.add(new EstudianteModel(3, "11", "KR 30 # 10-25", "Sofia", "sofia@mail.com", "1003", "Sofia", "Diaz", "3003", "ACTIVO"));
        pendientes.add(new EstudianteModel(4, "8", "CLL 13 # 9-40", "Carlos", "carlos@mail.com", "1004", "Carlos", "Rojas", "3004", "ACTIVO"));

        System.out.println("=== ESTUDIANTES PENDIENTES ===");
        for (EstudianteModel e : pendientes) {
            System.out.println(e.getIdEstudiante() + ". " + e.getNombre() + " " + e.getApellido()
                    + " | " + e.getDireccion());
        }

        int cupo = 3;
        List<EstudianteModel> asignados = service.asignarEstudiantes(pendientes, cupo);

        System.out.println("\n=== ASIGNADOS (los mas lejanos primero, cupo: " + cupo + ") ===");
        for (EstudianteModel e : asignados) {
            System.out.println(e.getNombre() + " " + e.getApellido() + " | " + e.getDireccion());
        }

        System.out.println("\n=== QUEDARON SIN LUGAR ===");
        for (EstudianteModel e : pendientes) {
            if (!asignados.contains(e)) {
                System.out.println(e.getNombre() + " " + e.getApellido() + " | " + e.getDireccion());
            }
        }
    }

}
