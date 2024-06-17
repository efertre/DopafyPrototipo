package view.config;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JEditorPane;

/**
 * La clase DiaManual representa un cuadro de di\u00E1logo modal que muestra un manual de usuario en formato HTML.
 * Este manual se carga desde un archivo local y se visualiza en un componente JEditorPane dentro del cuadro de di\u00E1logo.
 */
public class DiaManual extends JDialog {

    private static final long serialVersionUID = 1L;
    private static boolean mostrada = false;

    final String path = "resources/config/manual.html"; // Ruta del archivo del manual

    private JEditorPane txtTexto; // Componente para mostrar el contenido del manual en formato HTML

    /**
     * Constructor de la clase DiaManual.
     * Carga y muestra el manual si no ha sido mostrado previamente.
     *
     * @param panel Panel desde el cual se llama al cuadro de di\u00E1logo.
     */
    public DiaManual(JPanel panel) {
        if (!mostrada) {
            try {
                String contenido = loadManual(); // Cargar el contenido del manual desde el archivo
                initComponents(); // Inicializar los componentes del cuadro de di\u00E1logo
                txtTexto.setText(contenido); // Establecer el contenido en el JEditorPane
                mostrada = true; // Marcar como mostrada la ventana del manual
            } catch (Exception fallo) {
                // Mostrar un mensaje de error si no se puede abrir el manual
                JOptionPane.showMessageDialog(panel, "No se puede abrir el manual.\n" + fallo.getMessage(), "Error.", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Método privado para cargar el contenido del manual desde el archivo especificado.
     *
     * @return Contenido del manual en formato de cadena de texto.
     * @throws Exception Si ocurre un error al leer el archivo.
     */
    private String loadManual() throws Exception {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                contenido.append(line).append("\n"); // Leer cada l\u00EDnea del archivo y agregarla al contenido
            }
            if (contenido.length() > 0) {
                contenido.deleteCharAt(contenido.length() - 1); // Eliminar el \u00FAltimo salto de l\u00EDnea si existe
            }
        } catch (IOException e) {
            throw new Exception(e); // Lanzar una excepci\u00F3n si hay un error de lectura
        }
        return contenido.toString(); // Devolver el contenido del manual como una cadena de texto
    }

    /**
     * Inicializa los componentes del cuadro de diálogo.
     * Configura el título, tamaño, posición, cierre y el contenido del JEditorPane.
     */
    private void initComponents() {
        setTitle("Manual"); // Establecer el t\u00EDtulo del cuadro de di\u00E1logo
        setSize(600, 300); // Establecer el tamaño inicial del cuadro de di\u00E1logo
        setResizable(true); // Permitir redimensionar el cuadro de di\u00E1logo
        setLocationRelativeTo(null); // Centrar el cuadro de di\u00E1logo en la pantalla

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evitar que se cierre el cuadro de di\u00E1logo con la "X"
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mostrada = false; // Restablecer el estado de mostrada a falso al cerrar el cuadro de di\u00E1logo
                dispose(); // Liberar recursos y cerrar el cuadro de di\u00E1logo
            }
        });

        txtTexto = new JEditorPane(); // Crear el componente para mostrar el texto en formato HTML
        txtTexto.setContentType("text/html"); // Establecer el tipo de contenido como HTML
        txtTexto.setEditable(false); // Hacer que el texto no sea editable

        JScrollPane panScroll = new JScrollPane(txtTexto); // Crear un panel de desplazamiento para el texto
        getContentPane().add(panScroll, BorderLayout.CENTER); // Agregar el panel de desplazamiento al centro del cuadro de di\u00E1logo

        setVisible(true); // Hacer visible el cuadro de di\u00E1logo
    }

}
