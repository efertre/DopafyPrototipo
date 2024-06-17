package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.PlanMembresia;
import model.TipoPlan;

/**
 * La clase CtrlPlanMembresia proporciona métodos para gestionar planes de
 * membres\u00EDa, incluyendo agregar, actualizar, eliminar y recuperar planes
 * desde y hacia un archivo.
 */
public class CtrlPlanMembresia {
    private List<PlanMembresia> planes;
    private static final String FILE_PATH = "resources/data/planes_membresia.dat";

    /**
     * Constructor que inicializa la lista de planes de membres\u00EDa y carga los
     * planes desde un archivo.
     */
    public CtrlPlanMembresia() {
        this.planes = new ArrayList<>();
        loadFromFile();
    }

    /**
     * M\u00E9todo para a\u00F1adir un nuevo plan de membres\u00EDa.
     *
     * @param subscriptionId El ID de la suscripci\u00F3n.
     * @param tipo           El tipo de plan.
     * @param precio         El precio del plan.
     */
    public void addPlan(int subscriptionId, TipoPlan tipo, float precio) {
        PlanMembresia nuevoPlan = new PlanMembresia(subscriptionId, tipo, precio);
        planes.add(nuevoPlan);
        saveToFile();
    }

    /**
     * M\u00E9todo para obtener todos los planes de membres\u00EDa.
     *
     * @return Una lista con todos los planes de membres\u00EDa.
     */
    public List<PlanMembresia> getAllPlanes() {
        return new ArrayList<>(planes);
    }

    /**
     * M\u00E9todo para obtener un plan de membres\u00EDa por ID.
     *
     * @param subscriptionId El ID de la suscripci\u00F3n.
     * @return Un Optional que contiene el plan si se encuentra, de lo contrario est\u00E1 vac\u00EDo.
     */
    public Optional<PlanMembresia> getPlanById(int subscriptionId) {
        return planes.stream()
                     .filter(plan -> plan.getSubscriptionId() == subscriptionId)
                     .findFirst();
    }

    /**
     * M\u00E9todo para actualizar un plan de membres\u00EDa.
     *
     * @param subscriptionId El ID de la suscripci\u00F3n.
     * @param tipo           El nuevo tipo de plan.
     * @return true si el plan se actualiz\u00F3 correctamente, false de lo contrario.
     */
    public boolean updatePlan(int subscriptionId, TipoPlan tipo) {
        boolean updated = false;

        Optional<PlanMembresia> planOpt = getPlanById(subscriptionId);
        if (planOpt.isPresent()) {
            PlanMembresia plan = planOpt.get();
            plan.setTipo(tipo);
            saveToFile();
            updated = true;
        }
        return updated;
    }

    /**
     * M\u00E9todo para eliminar un plan de membres\u00EDa.
     *
     * @param subscriptionId El ID de la suscripci\u00F3n.
     * @return true si el plan se elimin\u00F3 correctamente, false de lo contrario.
     */
    public boolean deletePlan(int subscriptionId) {
        boolean removed = planes.removeIf(plan -> plan.getSubscriptionId() == subscriptionId);
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    /**
     * M\u00E9todo para guardar los planes de membres\u00EDa en un fichero de objetos.
     */
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(planes);
        } catch (IOException e) {
           
        }
    }

    /**
     * M\u00E9todo para cargar los planes de membres\u00EDa desde un fichero de objetos.
     */
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                planes = (List<PlanMembresia>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                
            }
        }
    }
}
