public class vendedorEnPracticas extends Vendedor {	
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param dni
	 */
	public vendedorEnPracticas(String nombre, String id, String dni) {// wmc + 1
		super(nombre, id, dni);
	}

	@Override
	public boolean equals(Object obj) {// wmc + 1
		if (!(obj instanceof vendedorEnPracticas)) // wmc + 1 ccog + 1
			return false;
		vendedorEnPracticas v = (vendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
