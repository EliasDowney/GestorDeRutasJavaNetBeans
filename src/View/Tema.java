package View;

import java.awt.Color;
import java.awt.Font;

public final class Tema {

    public static final Color NAVY = new Color(10, 45, 80);
    public static final Color GOLD = new Color(214, 170, 45);
    public static final Color LIGHT = new Color(240, 244, 248);
    public static final Color GRAY = new Color(120, 120, 120);
    public static final String FUENTE = "Segoe UI";

    private Tema() {
    }

    public static Font fuente(int estilo, int tamano) {
        return new Font(FUENTE, estilo, tamano);
    }

}
