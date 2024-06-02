package model;

public class Premio {

	// C\u00F3digo del premio
	private int rewardId;
	// Precio entero, porque no hay medios puntos
	private int precio;
	private short cantidad;
	private String tipo;

	// Constructor por defecto
	public Premio() {
		this.precio = 0;
		this.cantidad = 0;
	}

	// Constructor con todos los campos
	public Premio(int rewardId, int precio, short cantidad, String tipo) {
		setRewardId(rewardId);
		setPrecio(precio);
		setCantidad(cantidad);
		setTipo(tipo);
	}

	// Getters y Setters con validaciones
	public int getRewardId() {
		return rewardId;
	}

	public void setRewardId(int rewardId) {
		if (rewardId <= 0) {
			throw new IllegalArgumentException("El rewardId debe ser un número positivo.");
		}
		this.rewardId = rewardId;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		if (precio < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo.");
		}
		this.precio = precio;
	}

	public short getCantidad() {
		return cantidad;
	}

	public void setCantidad(short cantidad) {
		if (cantidad < 0) {
			throw new IllegalArgumentException("La cantidad no puede ser negativa.");
		}
		this.cantidad = cantidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo != null && tipo.length() > 100) {
			throw new IllegalArgumentException("El tipo no puede exceder los 100 caracteres.");
		}
		this.tipo = tipo;
	}

	// Método toString
	@Override
	public String toString() {
		return "Premio [rewardId=" + rewardId + ", precio=" + precio + ", cantidad=" + cantidad + ", tipo=" + tipo
				+ "]";
	}

}
