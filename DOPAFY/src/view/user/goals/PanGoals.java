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

/**
 * Panel para gestionar y visualizar los objetivos del usuario.
 */
public class PanGoals extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable goalsTable;
	private DefaultTableModel tableModel;
	private JTextField searchField;
	private CtrlPanGoals controller;
	private int userId;

	/**
	 * Constructor que inicializa el panel de objetivos del usuario.
	 */
	public PanGoals() {
		this.userId = DataUser.userId; // Asignar el userId proporcionado al miembro de la clase

		this.controller = new CtrlPanGoals();
		setBackground(new Color(152, 251, 152)); // Color de fondo verde claro
		setBounds(200, 50, 700, 450); // Establecer posici\u00F3n y tama\u00F1o del panel
		setLayout(new BorderLayout()); // Usar BorderLayout para organizar los componentes

		addComponents(); // Agregar todos los componentes visuales al panel
		loadGoals(); // Cargar los objetivos del usuario al iniciar el panel

		// Revalidar y repintar el panel para asegurar que los cambios sean visibles
		revalidate();
		repaint();
	}

	/**
	 * M\u00E9todo privado para agregar los componentes visuales al panel. Incluye
	 * paneles para b\u00FAsqueda, tabla de objetivos y botones de acci\u00F3n.
	 */
	private void addComponents() {
		// Panel superior para t\u00EDtulo y b\u00FAsqueda
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(700, 60)); // Reducir la altura del panel superior
		topPanel.setBackground(new Color(152, 251, 152)); // Establecer color de fondo verde claro

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(152, 251, 152)); // Color de fondo del panel de b\u00FAsqueda
		JLabel lblSearch = new JLabel("Buscar:"); // Etiqueta de b\u00FAsqueda
		searchField = new JTextField(20); // Campo de texto para b\u00FAsqueda
		JButton btnSearch = new JButton("Buscar"); // Bot\u00F3n de b\u00FAsqueda
		searchPanel.add(lblSearch); // Agregar etiqueta de b\u00FAsqueda al panel
		searchPanel.add(searchField); // Agregar campo de b\u00FAsqueda al panel
		searchPanel.add(btnSearch); // Agregar bot\u00F3n de b\u00FAsqueda al panel
		topPanel.add(searchPanel, BorderLayout.CENTER); // Centrar el panel de b\u00FAsqueda en el panel superior

		add(topPanel, BorderLayout.NORTH); // Agregar panel superior al norte del BorderLayout

		// Panel central para la tabla de objetivos
		tableModel = new DefaultTableModel(
				new Object[] { "ID", "Descripci\u00F3n", "Fecha Inicio", "Progreso", "Puntos", "Dificultad" }, 0);
		goalsTable = new JTable(tableModel); // Crear tabla de objetivos con el modelo de tabla predeterminado
		JScrollPane scrollPane = new JScrollPane(goalsTable); // Panel de desplazamiento para la tabla
		add(scrollPane, BorderLayout.CENTER); // Agregar tabla en el centro del BorderLayout

		// Panel inferior para botones de acciones
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(700, 50)); // Establecer tama\u00F1o preferido del panel inferior
		bottomPanel.setBackground(new Color(152, 251, 152)); // Color de fondo verde claro

		JButton btnAdd = new JButton("A\u00F1adir"); // Bot\u00F3n para a\u00F1adir objetivo
		JButton btnEdit = new JButton("Editar"); // Bot\u00F3n para editar objetivo
		JButton btnDelete = new JButton("Eliminar"); // Bot\u00F3n para eliminar objetivo
		JButton btnRefresh = new JButton("Actualizar"); // Bot\u00F3n para actualizar lista de objetivos

		bottomPanel.add(btnAdd); // Agregar bot\u00F3n de añadir al panel inferior
		bottomPanel.add(btnEdit); // Agregar bot\u00F3n de editar al panel inferior
		bottomPanel.add(btnDelete); // Agregar bot\u00F3n de eliminar al panel inferior
		bottomPanel.add(btnRefresh); // Agregar bot\u00EDn de actualizar al panel inferior

		add(bottomPanel, BorderLayout.SOUTH); // Agregar panel inferior al sur del BorderLayout

		// Asignar acciones a los botones
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Opciones de dificultad y puntos asociados
				String[] dificultades = { "SENCILLO", "MODERADO", "DIFICIL", "RETADOR", "DESAFIANTE" };
				int[] puntos = { 1, 3, 5, 7, 10 };

				// Crear campos de entrada para el di\u00E1logo
				JTextField descripcionField = new JTextField();
				JTextField fechaField = new JTextField(); // El formato de fecha debe ser YYYY-MM-DD
				JTextField progresoField = new JTextField();
				JComboBox<String> dificultadBox = new JComboBox<>(dificultades);

				// Crear panel para el di\u00E1logo
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(0, 2)); // Usar GridLayout con 2 columnas
				panel.add(new JLabel("Descripci\u00F3n:"));
				panel.add(descripcionField);
				panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
				panel.add(fechaField);
				panel.add(new JLabel("Progreso (%):"));
				panel.add(progresoField);
				panel.add(new JLabel("Dificultad:"));
				panel.add(dificultadBox);

				// Mostrar el di\u00E1logo para a\u00F1adir un objetivo
				int result = JOptionPane.showConfirmDialog(null, panel, "A\u00F1adir Objetivo", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) { // Si el usuario presiona "OK"
					try {
						// Obtener los valores ingresados por el usuario
						String descripcion = descripcionField.getText();
						Timestamp fechaInicio = Timestamp.valueOf(fechaField.getText() + " 00:00:00");
						int progreso = Integer.parseInt(progresoField.getText());
						if (progreso > 100 || progreso < 0) {
							throw new IllegalArgumentException();
						}
						String dificultad = (String) dificultadBox.getSelectedItem();
						int puntosAsignados = puntos[dificultadBox.getSelectedIndex()]; // Obtener puntos seg\u00FAn
																						// dificultad

						// Llamar al m\u00E9todo del controlador para a\u00F1adir el objetivo
						if (controller.addGoal(userId, descripcion, fechaInicio, progreso, puntosAsignados,
								dificultad)) {
							loadGoals(); // Recargar la lista de objetivos despu\u00E9s de a\u00F1adir
						}
					} catch (Exception e1) {
						// Mostrar mensaje de error si hay problemas con los datos ingresados
						JOptionPane.showMessageDialog(null,
								"Error al a\u00F1adir el objetivo. Verifique los datos ingresados.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// Acci\u00F3n para el bot\u00F3n de editar
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener la fila seleccionada en la tabla de objetivos
				int selectedRow = goalsTable.getSelectedRow();
				if (selectedRow >= 0) { // Verificar si se ha seleccionado alguna fila
					// Obtener los valores actuales de la fila seleccionada
					int goalId = (int) tableModel.getValueAt(selectedRow, 0); // ID del objetivo
					String descripcionActual = (String) tableModel.getValueAt(selectedRow, 1); // Descripci\u00F3n actual
					Timestamp fechaActual = Timestamp.valueOf(tableModel.getValueAt(selectedRow, 2).toString()); // Fecha
																													// actual
					int progresoActual = (int) tableModel.getValueAt(selectedRow, 3); // Progreso actual
					String dificultadActual = tableModel.getValueAt(selectedRow, 4).toString(); // Dificultad actual

					// Array de dificultades disponibles
					String[] dificultades = { "SENCILLO", "MODERADO", "DIFICIL", "RETADOR", "DESAFIANTE" };
					int[] puntos = { 1, 3, 5, 7, 10 };

					// Campos de entrada para el di\u00E1logo de edici\u00F3n con valores actuales
					JTextField descripcionField = new JTextField(descripcionActual);
					JTextField fechaField = new JTextField(fechaActual.toString().split(" ")[0]); // Solo fecha
																									// YYYY-MM-DD
					JTextField progresoField = new JTextField(String.valueOf(progresoActual));
					JComboBox<String> dificultadBox = new JComboBox<>(dificultades);
					dificultadBox.setSelectedItem(dificultadActual); // Establecer dificultad actual seleccionada

					// Panel para el di\u00E1logo de edici\u00F3n
					JPanel panel = new JPanel();
					panel.setLayout(new GridLayout(0, 2)); // Usar GridLayout con 2 columnas
					panel.add(new JLabel("Descripci\u00F3n:"));
					panel.add(descripcionField);
					panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
					panel.add(fechaField);
					panel.add(new JLabel("Progreso (%):"));
					panel.add(progresoField);
					panel.add(new JLabel("Dificultad:"));
					panel.add(dificultadBox);

					// Mostrar el di\u00E1logo de edici\u00F3n
					int result = JOptionPane.showConfirmDialog(null, panel, "Editar Objetivo",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) { // Si el usuario presiona "OK"
						try {
							// Obtener los valores editados por el usuario
							String descripcion = descripcionField.getText();
							Timestamp fechaInicio = Timestamp.valueOf(fechaField.getText() + " 00:00:00"); // Convertir
																											// fecha a
																											// Timestamp
							int progreso = Integer.parseInt(progresoField.getText());
							if (progreso > 100 || progreso < 0) {
								throw new IllegalArgumentException();
							}
							String dificultad = (String) dificultadBox.getSelectedItem();
							int puntosAsignados = puntos[dificultadBox.getSelectedIndex()]; // Obtener puntos seg\u00FAn
																							// dificultad seleccionada

							// Llamar al m\u00E9todo del controlador para editar el objetivo
							if (controller.editGoal(goalId, descripcion, fechaInicio, progreso, puntosAsignados,
									dificultad)) {
								loadGoals(); // Recargar la lista de objetivos despu\u00E9s de editar
							}
						} catch (Exception e1) {
							// Mostrar mensaje de error si hay problemas con los datos ingresados
							JOptionPane.showMessageDialog(PanGoals.this, "Datos editados con valores inv\u00E1lidos.",
									"Error de edici\u00F3n", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		// Acci\u00F3n para el bot\u00F3n de eliminar
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = goalsTable.getSelectedRow();
				if (selectedRow >= 0) { // Verificar si se ha seleccionado alguna fila
					int goalId = (int) tableModel.getValueAt(selectedRow, 0); // Obtener ID del objetivo seleccionado
					try {
						// Llamar al m\u00E9todo del controlador para eliminar el objetivo
						if (controller.deleteGoal(goalId)) {
							loadGoals(); // Recargar la lista de objetivos despu\u00E9s de eliminar
						}
					} catch (Exception e1) {
						e1.printStackTrace(); // Manejar cualquier excepci\u00F3n con un simple rastreo de pila
					}
				}
			}
		});

		// Acci\u00F3n para el bot\u00F3n de b\u00FAsqueda
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion = searchField.getText(); // Obtener texto de b\u00FAsqueda
				List<Object[]> goals = null;
				try {
					// Llamar al m\u00E9todo del controlador para buscar objetivos del usuario por
					// descripci\u00F3n
					goals = controller.searchGoals(userId, descripcion);
				} catch (Exception e1) {
					e1.printStackTrace(); // Manejar cualquier excepci\u00F3n con un simple rastreo de pila
				}
				updateTableData(goals); // Actualizar la tabla con los resultados de la b\u00FAsqueda
			}
		});

		// Acci\u00F3n para el bot\u00F3n de actualizar
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadGoals(); // Recargar la lista de objetivos al presionar el bot\u00F3n de actualizar
			}
		});
	}

	/**
	 * M\u00E9todo privado para cargar los objetivos del usuario en la tabla. Utiliza el
	 * controlador para obtener la lista de objetivos del usuario actual.
	 */
	private void loadGoals() {
		List<Object[]> goals = null;
		try {
			// Llamar al m\u00E9todo del controlador para obtener los objetivos del usuario
			goals = controller.getGoals(userId);
		} catch (Exception e) {
			e.printStackTrace(); // Manejar cualquier excepci\u00F3n con un simple rastreo de pila
		}
		updateTableData(goals); // Actualizar la tabla con los objetivos obtenidos
	}

	/**
	 * M\u00E9todo privado para actualizar los datos de la tabla con una lista de
	 * objetivos.
	 * 
	 * @param data Lista de objetos que contienen los datos de los objetivos a
	 *             mostrar en la tabla.
	 */
	private void updateTableData(List<Object[]> data) {
		tableModel.setRowCount(0); // Limpiar filas existentes en el modelo de tabla
		for (Object[] row : data) {
			tableModel.addRow(row); // Agregar cada fila de datos al modelo de tabla
		}
	}

	/**
	 * M\u00E9todo p\u00FAblico para actualizar el ID de usuario y recargar los objetivos
	 * asociados.
	 * 
	 * @param userId2 Nuevo ID de usuario a asignar.
	 */
	public void updateUserId(int userId2) {
		this.userId = userId2; // Asignar el nuevo ID de usuario
		loadGoals(); // Recargar los objetivos del usuario con el nuevo ID
	}
}
