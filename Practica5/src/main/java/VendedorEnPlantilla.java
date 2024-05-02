public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {// wmc + 1
		super(nombre, id, dni);
		this.tipo = tipo;
	}
	
	public TipoVendedor tipo() {// wmc + 1
		return tipo;
	}
	
	@Override
	public boolean equals(Object obj) {// wmc + 1
		if (!(obj instanceof VendedorEnPlantilla)) // wmc + 1
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
