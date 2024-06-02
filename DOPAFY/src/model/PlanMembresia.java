package model;

public class PlanMembresia {

	private int subscriptionId;
	private TipoPlan tipo;
	private float precio;

	// Constructor por defecto
	public PlanMembresia() {
	}

	// Constructor con todos los campos
	public PlanMembresia(int subscriptionId, TipoPlan tipo, float precio) {
		setSubscriptionId(subscriptionId);
		setTipo(tipo);
		setPrecio(precio);
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	// Getters y Setters con validaciones
	public void setSubscriptionId(int subscriptionId) {
		if (subscriptionId <= 0) {
			throw new IllegalArgumentException("El subscriptionId debe ser un n\u00FAmero positivo.");
		}
		this.subscriptionId = subscriptionId;
	}

	public TipoPlan getTipo() {
		return tipo;
	}

	public void setTipo(TipoPlan tipo) {
		this.tipo = tipo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		if (precio <= 0) {
			throw new IllegalArgumentException("El precio debe ser mayor que 0.");
		}
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "PlanMembresia [subscriptionId=" + subscriptionId + ", tipo=" + tipo + ", precio=" + precio + "]";
	}

}
