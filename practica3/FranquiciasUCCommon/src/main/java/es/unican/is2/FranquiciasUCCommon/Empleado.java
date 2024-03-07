package es.unican.is2.FranquiciasUCCommon;




import java.time.LocalDate;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {

	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;

	public Empleado() {	}

	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) {
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
	}

	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {
		double sueldoBase = obtenerSueldoBase();
		double complementoAntiguedad = obtenerComplementoAntiguedad();

		double sueldo = sueldoBase + complementoAntiguedad;

		// Reducción del 25% si el trabajador está de baja
		if (baja) {
			sueldo *= 0.75;
		}

		return sueldo;
	}

	private double obtenerSueldoBase() {
		switch (categoria) {
		case ENCARGADO:
			return 2000;
		case VENDEDOR:
			return 1500;
		case AUXILIAR:
			return 1000;
		default:
			throw new IllegalArgumentException("Categoría de empleado no válida");
		}
	}

	private double obtenerComplementoAntiguedad() {
		long antiguedad = LocalDate.now().getYear() - fechaContratacion.getYear();

		if (antiguedad > 20) {
			return 200;
		} else if (antiguedad > 10) {
			return 100;
		} else if (antiguedad > 5) {
			return 50;
		} else {
			return 0;
		}
	}

	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}

	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}


	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}

	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}

	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}

	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}


	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
