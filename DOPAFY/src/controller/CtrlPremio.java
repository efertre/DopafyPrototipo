package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Premio;

/**
 * La clase CtrlPremio proporciona métodos para gestionar premios, incluyendo
 * agregar, actualizar, eliminar y recuperar premios desde y hacia un archivo.
 */
public class CtrlPremio {
    private List<Premio> premios;
    private static final String FILE_PATH = "resources/data/premios.dat";

    /**
     * Constructor que inicializa la lista de premios y carga los premios desde un archivo.
     */
    public CtrlPremio() {
        this.premios = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Obtiene todos los premios.
     *
     * @return Una lista con todos los premios.
     */
    public List<Premio> getAllPremios() {
        return new ArrayList<>(premios);
    }

    /**
     * Agrega un nuevo premio a la lista y lo guarda en el archivo.
     *
     * @param premio El premio a agregar.
     */
    public void addPremio(Premio premio) {
        premios.add(premio);
        saveToFile();
    }

    /**
     * Busca un premio por su ID.
     *
     * @param rewardId El ID del premio a buscar.
     * @return Un Optional que contiene el premio si se encuentra, de lo contrario está vacío.
     */
    public Optional<Premio> getPremioById(int rewardId) {
        return premios.stream()
                      .filter(premio -> premio.getRewardId() == rewardId)
                      .findFirst();
    }

    /**
     * Actualiza un premio existente.
     *
     * @param rewardId El ID del premio a actualizar.
     * @param precio   El nuevo precio del premio.
     * @param cantidad La nueva cantidad del premio.
     * @param tipo     El nuevo tipo del premio.
     * @return true si el premio se actualizó correctamente, false de lo contrario.
     */
    public boolean updatePremio(int rewardId, int precio, short cantidad, String tipo) {
        boolean updated = false;

        Optional<Premio> premioOpt = getPremioById(rewardId);
        if (premioOpt.isPresent()) {
            Premio premio = premioOpt.get();
            premio.setPrecio(precio);
            premio.setCantidad(cantidad);
            premio.setTipo(tipo);
            saveToFile();
            updated = true;
        }
        return updated;
    }

    /**
     * Elimina un premio por su ID.
     *
     * @param rewardId El ID del premio a eliminar.
     * @return true si el premio se elimin\u00F3 correctamente, false de lo contrario.
     */
    public boolean deletePremio(int rewardId) {
        boolean removed = false;

        Optional<Premio> premioOpt = getPremioById(rewardId);
        if (premioOpt.isPresent()) {
            premios.remove(premioOpt.get());
            saveToFile();
            removed = true;
        }
        return removed;
    }

    /**
     * Guarda la lista de premios en un archivo.
     */
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(premios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la lista de premios desde un archivo.
     */
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                premios = (List<Premio>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
