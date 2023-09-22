package vuelos;
import vuelos.logic.Service;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        window = new JFrame();
        window.setContentPane(new JTabbedPane());
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });

        vuelos.presentation.vuelos.Model vuelosModel = new vuelos.presentation.vuelos.Model();
        vuelos.presentation.vuelos.View vuelosView = new vuelos.presentation.vuelos.View();

        vuelosController = new vuelos.presentation.vuelos.Controller(vuelosView, vuelosModel);

        window.getContentPane().add("Vuelos",vuelosView.getPanel());

        window.setSize(900,450);
        window.setResizable(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       // window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        window.setTitle("SIVU: Sistema de Vuelos");
        window.setVisible(true);
    }

        public static  vuelos.presentation.vuelos.Controller vuelosController;
        public static JFrame window;

        public static int MODE_CREATE =1;
        public static int MODE_EDIT = 2;
}
