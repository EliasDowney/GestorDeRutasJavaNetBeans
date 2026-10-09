package Principal;

import View.PrincipalVista;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrincipalVista().setVisible(true));
    }

}
