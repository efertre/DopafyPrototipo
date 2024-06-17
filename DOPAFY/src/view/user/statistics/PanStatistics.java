package view.user.statistics;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Objetivo;

/**
 * Panel que muestra estad\u00EDsticas relacionadas con los objetivos y puntos de un usuario.
 */
public class PanStatistics extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel background;
    private CtrlStatistics ctrl; // Referencia al controlador de estad\u00EDsticas
    private int userId; // ID del usuario actual

    private List<Objetivo> completedGoals = null;
    private List<Objetivo> totalGoals = null;
    private Map<String, Integer> goalsByDifficulty = null;
    private int userPoints = 0;

    private JLabel totalGoalsLabel, completedGoalsLabel, difficultyLabel, userPointsLabel;

    /**
     * Constructor de la clase PanStatistics.
     * Inicializa el panel con un fondo de color verde claro y configura su tama\u00F1o y posici\u00F3n.
     * Tambi\u00E9n inicializa el controlador de estad\u00EDsticas y oculta el panel inicialmente.
     */
    public PanStatistics() {
        ctrl = new CtrlStatistics();

        setBackground(new Color(152, 251, 152)); // Color verde claro
        setBounds(200, 50, 700, 450);
        setLayout(null);
        setVisible(false);

        // Revalidar y repintar el panel
        revalidate();
        repaint();
    }

    /**
     * Actualiza el ID del usuario actual y muestra las estad\u00EDsticas correspondientes.
     * Limpia los componentes existentes antes de actualizarlos con las estad\u00EDsticas actuales del usuario.
     * 
     * @param userId2 ID del usuario actual
     */
    public void updateUserId(int userId2) {
        this.userId = userId2;

        // Limpiar los componentes existentes antes de actualizar
        removeAll();

        try {
            totalGoals = ctrl.getTotalGoals(userId);
            completedGoals = ctrl.getCompletedGoals(userId);
            goalsByDifficulty = ctrl.getGoalsByDifficulty(userId);
            userPoints = ctrl.getUserPoints(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mostrar los objetivos totales que lleva el usuario
        int numTotalGoals = (totalGoals == null) ? 0 : totalGoals.size();

        totalGoalsLabel = new JLabel("Objetivos Totales: " + numTotalGoals);
        totalGoalsLabel.setBounds(50, 50, 300, 30);
        totalGoalsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(totalGoalsLabel);

        // Mostrar los objetivos completados
        int numTotalGoalsCompleted = (completedGoals == null) ? 0 : completedGoals.size();

        completedGoalsLabel = new JLabel("Objetivos Completados: " + numTotalGoalsCompleted);
        completedGoalsLabel.setBounds(50, 100, 300, 30);
        completedGoalsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(completedGoalsLabel);

        // Mostrar los objetivos por dificultad
        int yPosition = 150;
        if (goalsByDifficulty != null) {
            for (String difficulty : goalsByDifficulty.keySet()) {
                difficultyLabel = new JLabel("Dificultad " + difficulty + ": " + goalsByDifficulty.get(difficulty));
                difficultyLabel.setBounds(50, yPosition, 300, 30);
                difficultyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                add(difficultyLabel);
                yPosition += 50;
            }
        }

        // Mostrar los puntos del usuario
        userPointsLabel = new JLabel("Puntos del Usuario: " + userPoints);
        userPointsLabel.setBounds(50, yPosition, 300, 30);
        userPointsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(userPointsLabel);

        // Cargar la imagen de fondo
        ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");

        // Crear el JLabel con la imagen de fondo
        background = new JLabel(iconBackground);
        background.setBounds(0, 0, 700, 450);
        add(background);

        // Volver a validar y repintar el panel después de actualizar los componentes
        revalidate();
        repaint();
    }

}
