package model;

import java.io.Serializable;

/**
 * Clase que representa un plan de membresía.
 */
public class PlanMembresia implements Serializable {

    private static final long serialVersionUID = 1L;

    // Identificador del plan de membresía
    private int subscriptionId;

    // Tipo de plan de membresía (GRATUITO, MEDIANO, COMPLETO)
    private TipoPlan tipo;

    // Precio del plan de membresía
    private float precio;

    /**
     * Constructor por defecto de PlanMembresia.
     * Inicializa subscriptionId y precio a 0, y tipo a null.
     */
    public PlanMembresia() {
    }

    /**
     * Constructor de PlanMembresia con todos los campos.
     *
     * @param subscriptionId Identificador del plan de membresía.
     * @param tipo Tipo de plan de membresía.
     * @param precio Precio del plan de membresía.
     */
    public PlanMembresia(int subscriptionId, TipoPlan tipo, float precio) {
        setSubscriptionId(subscriptionId);
        setTipo(tipo);
        setPrecio(precio);
    }

    /**
     * Obtiene el identificador del plan de membresía.
     *
     * @return Identificador del plan de membresía.
     */
    public int getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Establece el identificador del plan de membresía.
     *
     * @param subscriptionId Identificador del plan de membresía a establecer.
     */
    public void setSubscriptionId(int subscriptionId) {
        if (subscriptionId <= 0) {
            throw new IllegalArgumentException("El subscriptionId debe ser un número positivo.");
        }
        this.subscriptionId = subscriptionId;
    }

    /**
     * Obtiene el tipo de plan de membresía.
     *
     * @return Tipo de plan de membresía.
     */
    public TipoPlan getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de plan de membresía.
     *
     * @param tipo Tipo de plan de membresía a establecer.
     */
    public void setTipo(TipoPlan tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el precio del plan de membresía.
     *
     * @return Precio del plan de membresía.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del plan de membresía.
     *
     * @param precio Precio del plan de membresía a establecer.
     */
    public void setPrecio(float precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0.");
        }
        this.precio = precio;
    }

    /**
     * Método toString para imprimir el estado del objeto PlanMembresia.
     *
     * @return Representación en cadena del objeto PlanMembresia.
     */
    @Override
    public String toString() {
        return "PlanMembresia [subscriptionId=" + subscriptionId + ", tipo=" + tipo + ", precio=" + precio + "]";
    }

}
