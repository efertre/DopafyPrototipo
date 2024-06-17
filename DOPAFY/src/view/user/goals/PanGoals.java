package view.user.goals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view.user.DataUser;

public class PanGoals extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable goalsTable;
	private DefaultTableModel tableModel;
	private JTextField searchField;
	private CtrlPanGoals controller;
	private int userId;

	public PanGoals() {
		this.userId = DataUser.userId; // Asignar el userId proporcionado al miembro de la clase

		this.controller = new CtrlPanGoals();
		setBackground(new Color(152, 251, 152));
		setBounds(200, 50, 700, 450);
		setLayout(new BorderLayout());

		addComponents();
		loadGoals();

		// Revalidar y repintar el panel
		revalidate();
		repaint();

	}

	private void addComponents() {
		// Panel superior para título y búsqueda
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(700, 60)); // Reduce the height of the top panel
		topPanel.setBackground(new Color(152, 251, 152));

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(152, 251, 152));
		JLabel lblSearch = new JLabel("Buscar:");
		searchField = new JTextField(20);
		JButton btnSearch = new JButton("Buscar");
		searchPanel.add(lblSearch);
		searchPanel.add(searchField);
		searchPanel.add(btnSearch);
		topPanel.add(searchPanel, BorderLayout.CENTER); // Center the search panel in the top panel

		add(topPanel, BorderLayout.NORTH);

		// Panel central para la tabla de objetivos
		tableModel = new DefaultTableModel(
				new Object[] { "ID", "Descripción", "Fecha Inicio", "Progreso", "Puntos", "Dificultad" }, 0);
		goalsTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(goalsTable);
		add(scrollPane, BorderLayout.CENTER);

		// Panel inferior para botones de acciones
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(700, 50));
		bottomPanel.setBackground(new Color(152, 251, 152));

		JButton btnAdd = new JButton("Añadir");
		JButton btnEdit = new JButton("Editar");
		JButton btnDelete = new JButton("Eliminar");
		JButton btnRefresh = new JButton("Actualizar");

		bottomPanel.add(btnAdd);
		bottomPanel.add(btnEdit);
		bottomPanel.add(btnDelete);
		bottomPanel.add(btnRefresh);

		add(bottomPanel, BorderLayout.SOUTH);

		// Añadir acciones a los botones
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Dificultades y puntos asociados
				String[] dificultades = { "SENCILLO", "MODERADO", "DIFICIL", "RETADOR", "DESAFIANTE" };
				int[] puntos = { 1, 3, 5, 7, 10 };

				// Crear campos de entrada para el diálogo
				JTextField descripcionField = new JTextField();
				JTextField fechaField = new JTextField(); // El formato de fecha debe ser YYYY-MM-DD
				JTextField progresoField = new JTextField();
				JComboBox<String> dificultadBox = new JComboBox<>(dificultades);

				// Crear panel para el diálogo
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(0, 2));
				panel.add(new JLabel("Descripción:"));
				panel.add(descripcionField);
				panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
				panel.add(fechaField);
				panel.add(new JLabel("Progreso (%):"));
				panel.add(progresoField);
				panel.add(new JLabel("Dificultad:"));
				panel.add(dificultadBox);

				// Mostrar el diálogo
				int result = JOptionPane.showConfirmDialog(null, panel, "Añadir Objetivo", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					try {
						String descripcion = descripcionField.getText();
						Timestamp fechaInicio = Timestamp.valueOf(fechaField.getText() + " 00:00:00");
						int progreso = Integer.parseInt(progresoField.getText());
						if (progreso > 100 || progreso < 0) {
							throw new IllegalArgumentException();
						}
						String dificultad = (String) dificultadBox.getSelectedItem();
						int puntosAsignados = puntos[dificultadBox.getSelectedIndex()];

						if (controller.addGoal(userId, descripcion, fechaInicio, progreso, puntosAsignados,
								dificultad)) {
							loadGoals();
						}
					} catch (Exception e1) {

						JOptionPane.showMessageDialog(null,
								"Error al añadir el objetivo. Verifique los datos ingresados.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener la fila seleccionada en la tabla
				int selectedRow = goalsTable.getSelectedRow();
				if (selectedRow >= 0) { // Verificar si se ha seleccionado alguna fila
					// Obtener los valores actuales de la fila seleccionada
					int goalId = (int) tableModel.getValueAt(selectedRow, 0); // Obtener el ID del objetivo
					String descripcionActual = (String) tableModel.getValueAt(selectedRow, 1); // Obtener la descripción
																								// actual
					Timestamp fechaActual = Timestamp.valueOf(tableModel.getValueAt(selectedRow, 2).toString()); // Obtener
																													// la
																													// fecha
																													// actual
																													// y
																													// convertirla
																													// a
																													// Timestamp
					int progresoActual = (int) tableModel.getValueAt(selectedRow, 3); // Obtener el progreso actual
					String dificultadActual = tableModel.getValueAt(selectedRow, 4).toString(); // Obtener la dificultad
																								// actual

					// Crear un array con las posibles dificultades
					String[] dificultades = { "SENCILLO", "MODERADO", "DIFICIL", "RETADOR", "DESAFIANTE" };
					int[] puntos = { 1, 3, 5, 7, 10 };

					// Crear campos de entrada con los valores actuales
					JTextField descripcionField = new JTextField(descripcionActual);
					JTextField fechaField = new JTextField(fechaActual.toString().split(" ")[0]); // Solo la parte de la
																									// fecha YYYY-MM-DD
					JTextField progresoField = new JTextField(String.valueOf(progresoActual));
					JComboBox<String> dificultadBox = new JComboBox<>(dificultades);
					dificultadBox.setSelectedItem(dificultadActual); // Establecer la dificultad actual seleccionada

					// Crear un panel para el diálogo de edición
					JPanel panel = new JPanel();
					panel.setLayout(new GridLayout(0, 2)); // Usar un GridLayout con 2 columnas
					panel.add(new JLabel("Descripción:"));
					panel.add(descripcionField);
					panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
					panel.add(fechaField);
					panel.add(new JLabel("Progreso (%):"));
					panel.add(progresoField);
					panel.add(new JLabel("Dificultad:"));
					panel.add(dificultadBox);

					// Mostrar el diálogo de edición
					int result = JOptionPane.showConfirmDialog(null, panel, "Editar Objetivo",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) { // Si el usuario presiona "OK"
						try {
							// Obtener los valores editados por el usuario
							String descripcion = descripcionField.getText();
							Timestamp fechaInicio = Timestamp.valueOf(fechaField.getText() + " 00:00:00"); // Convertir
																											// la fecha
																											// a
																											// Timestamp

							int progreso = Integer.parseInt(progresoField.getText());
							if (progreso > 100 || progreso < 0) {
								throw new IllegalArgumentException();
							}
							String dificultad = (String) dificultadBox.getSelectedItem();
							int puntosAsignados = puntos[dificultadBox.getSelectedIndex()]; // Obtener los puntos según
																							// la dificultad
																							// seleccionada

							// Llamar al método del controlador para actualizar el objetivo
							if (controller.editGoal(goalId, descripcion, fechaInicio, progreso, puntosAsignados,
									dificultad)) {
								loadGoals(); // Recargar la tabla de objetivos si la actualización fue exitosa
							}
						} catch (Exception e1) {
							// Mostrar mensaje de error
							JOptionPane.showMessageDialog(PanGoals.this, "Datos editados con valores inválidos.",
									"Error de edición", JOptionPane.ERROR_MESSAGE); // Manejar cualquier excepción
																					// que ocurra durante la
																					// edición
						}
					}
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = goalsTable.getSelectedRow();
				if (selectedRow >= 0) {
					int goalId = (int) tableModel.getValueAt(selectedRow, 0);
					try {
						if (controller.deleteGoal(goalId)) {
							loadGoals();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion = searchField.getText();
				List<Object[]> goals = null;
				try {
					goals = controller.searchGoals(userId, descripcion);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateTableData(goals);
			}
		});

		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadGoals();
			}
		});
	}

	private void loadGoals() {
		List<Object[]> goals = null;
		try {
			goals = controller.getGoals(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateTableData(goals);
	}

	private void updateTableData(List<Object[]> data) {
		tableModel.setRowCount(0);
		for (Object[] row : data) {
			tableModel.addRow(row);
		}
	}

	public void updateUserId(int userId2) {
		this.userId = userId2;
		loadGoals();

	}

}
