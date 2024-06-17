package model;

import java.io.Serializable;

/**
 * Clase que representa un premio.
 */
public class Premio implements Serializable {

    private static final long serialVersionUID = 1L;

    // Código del premio
    private int rewardId;

    // Precio entero, porque no hay medios puntos
    private int precio;

    // Cantidad del premio
    private short cantidad;

    // Tipo del premio
    private String tipo;

    /**
     * Constructor por defecto de Premio.
     * Inicializa precio y cantidad a 0.
     */
    public Premio() {
        this.precio = 0;
        this.cantidad = 0;
    }

    /**
     * Constructor de Premio con todos los campos.
     *
     * @param rewardId Código del premio.
     * @param precio Precio del premio.
     * @param cantidad Cantidad del premio.
     * @param tipo Tipo del premio.
     */
    public Premio(int rewardId, int precio, short cantidad, String tipo) {
        setRewardId(rewardId);
        setPrecio(precio);
        setCantidad(cantidad);
        setTipo(tipo);
    }

    /**
     * Obtiene el código del premio.
     *
     * @return Código del premio.
     */
    public int getRewardId() {
        return rewardId;
    }

    /**
     * Establece el código del premio.
     *
     * @param rewardId Código del premio a establecer.
     */
    public void setRewardId(int rewardId) {
        if (rewardId <= 0) {
            throw new IllegalArgumentException("El rewardId debe ser un número positivo.");
        }
        this.rewardId = rewardId;
    }

    /**
     * Obtiene el precio del premio.
     *
     * @return Precio del premio.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del premio.
     *
     * @param precio Precio del premio a establecer.
     */
    public void setPrecio(int precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad del premio.
     *
     * @return Cantidad del premio.
     */
    public short getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del premio.
     *
     * @param cantidad Cantidad del premio a establecer.
     */
    public void setCantidad(short cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el tipo del premio.
     *
     * @return Tipo del premio.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del premio.
     *
     * @param tipo Tipo del premio a establecer.
     */
    public void setTipo(String tipo) {
        if (tipo != null && tipo.length() > 100) {
            throw new IllegalArgumentException("El tipo no puede exceder los 100 caracteres.");
        }
        this.tipo = tipo;
    }

    /**
     * Método toString para imprimir el estado del objeto Premio.
     *
     * @return Representación en cadena del objeto Premio.
     */
    @Override
    public String toString() {
        return "Premio [rewardId=" + rewardId + ", precio=" + precio + ", cantidad=" + cantidad + ", tipo=" + tipo
                + "]";
    }

}
