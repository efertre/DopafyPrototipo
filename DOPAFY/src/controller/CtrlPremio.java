package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Premio;

public class CtrlPremio {
    private List<Premio> premios;
    private static final String FILE_PATH = "resources/data/premios.dat";

    public CtrlPremio() {
        this.premios = new ArrayList<>();
        loadFromFile();
    }

    // Obtener todos los premios
    public List<Premio> getAllPremios() {
        return new ArrayList<>(premios);
    } 

    // Agregar un nuevo premio
    public void addPremio(Premio premio) {
        premios.add(premio);
        saveToFile();
    }

    // Buscar un premio por su ID
    public Optional<Premio> getPremioById(int rewardId) {
        return premios.stream()
                      .filter(premio -> premio.getRewardId() == rewardId)
                      .findFirst();
    }

    // Actualizar un premio
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

    // Eliminar un premio por su ID
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

    // Guardar la lista de premios en un archivo
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(premios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar la lista de premios desde un archivo
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

