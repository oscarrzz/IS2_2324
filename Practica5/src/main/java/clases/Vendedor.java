package clases;
/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {
	
	private String id;
	private String nombre;
	private String dni;
	private double c;
	private double totalVentas;
	
	public Vendedor(String nombre, String id, String dni) {//wmc + 1
		this.nombre = nombre;
		this.id = id;
		this.dni = dni;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {//wmc + 1
		return nombre;//cCog + 1
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	public String getId() {//wmc + 1
		return id;//cCog + 1
	}
	
	/**
	 * Retorna la comision mensual acumulada
	 * @return Comision total acumulada
	 */
	public double getC() {//wmc + 1
		return c;//cCog + 1
	}
	
	public String getDni() {// wmc + 1
		return dni;//cCog + 1
	}
	
	/**
	 * Asigna valor a la comision mensual acumulada
	 * @param value comision a asignar
	 */
	public void setC(double value) {//wmc + 1
		this.c = value;
	}
	
	/**
	 * Retorna el importe total mensual de ventas
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas( ) {//wmc + 1
		return totalVentas;//cCog + 1
	}
	
	/**
	 * Asigna valor al total de ventas mensual
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) {//wmc + 1
		totalVentas = value;
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	public void anhade(double importe)  {//wmc + 1
		totalVentas += importe;
	}
}
