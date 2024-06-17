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

public class DiaManual extends JDialog {

  private static final long serialVersionUID = 1L;
  private static boolean mostrada = false;

  final String path = "resources/config/manual.html";

  private JEditorPane txtTexto;

  public DiaManual(JPanel panel) {
    if (!mostrada) {
      try {
        String contenido = loadManual();
        initComponents();
        txtTexto.setText(contenido);
        mostrada = true;
      } catch (Exception fallo) {
        JOptionPane.showMessageDialog(panel, "No se puede abrir el manual.\n" + fallo.getMessage(), "Error.", JOptionPane.WARNING_MESSAGE);
      }
    }
  }

  private String loadManual() throws Exception {
    StringBuilder contenido = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = br.readLine()) != null) {
        contenido.append(line).append("\n");
      }
      if (contenido.length() > 0) { // Si hay contenido entonces eliminar el último salto de línea
        contenido.deleteCharAt(contenido.length() - 1);
      }
    } catch (IOException e) {
      throw new Exception(e);
    }
    return contenido.toString();
  }

  private void initComponents() {
    setTitle("Manual");
    setSize(600, 300);
    setResizable(true);
    setLocationRelativeTo(null);

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        mostrada = false;
        dispose();
      }
    });

    txtTexto = new JEditorPane();
    txtTexto.setContentType("text/html");
    txtTexto.setEditable(false);

    JScrollPane panScroll = new JScrollPane(txtTexto);
    getContentPane().add(panScroll, BorderLayout.CENTER);

    setVisible(true);
  }

}