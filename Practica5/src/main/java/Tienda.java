import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;
	private String datos;
	
	private static final double CJUNIOR = 0.005;
	private static final double CSENIOR = 0.01;
	
	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) throws DataAccessException {// wmc + 1
		this.datos = datos;
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) {// wmc + 2 CCog + 1
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Senior);
				ven.setTotalVentas(totalVentas);
				ven.setC(totalComision);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Practicas")) {// wmc + 2 CCog + 1
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Junior);
				ven.setTotalVentas(totalVentas);
				ven.setC(totalComision);
				lista.add(ven);
			}
			while (in.hasNext()) {// wmc + 1 CCog + 1
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new vendedorEnPracticas(nombre, idIn, dni);
				ven.setTotalVentas(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {// wmc + 1 CCog + 1
			throw new DataAccessException();
		} finally {
			if (in != null) {// wmc + 1 CCog + 1
				in.close();
			}
		}
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String direccion() {// wmc + 1
		return direccion;//CCog + 1
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {// wmc + 1
		return nombre;//CCog + 1
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhade(Vendedor nuevo) throws DataAccessException { // wmc + 1
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) { // wmc + 1 CCog + 1
			return false;//CCog + 1
		}
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException { // wmc + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) {// wmc + 1 CCog + 1
			return false;//CCog + 1
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException {// wmc + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) {// wmc + 1 CCog + 1
			return false;//CCog + 1
		}
		double comision = 0;
		if (v instanceof VendedorEnPlantilla) {// wmc + 1 CCog + 1
			/*
			 * switch (((VendedorEnPlantilla) v).tipo()) {//CCog + 2 case Junior:// wmc + 1
			 * comision = importe * 0.005; break; case Senior:// wmc + 1 comision = importe
			 * * 0.01; break; }
			 */
			comision = asignaComision((VendedorEnPlantilla) v, importe);// CCog + 1
		}
		v.anhade(importe);
		v.setC(v.getC()+comision);
		vuelcaDatos();
		return true;
	}
	
	//Nuevo metodo creado
	public double asignaComision(VendedorEnPlantilla v, double importe) {// wmc +1
		double comision = 0;
		switch (v.tipo()) {//CCog + 1
			case Junior:// wmc + 1
				comision = importe * CJUNIOR; 
				break; 
			case Senior:// wmc + 1 
				comision = importe * CSENIOR; 
				break;
		}
		return comision;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {// wmc + 1

		for (Vendedor v : lista) {// wmc + 1 CCog + 1
			if (v.getId().equals(id)) {// wmc + 1 CCog + 2
				return v;//CCog + 1
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public 
	List<Vendedor> vendedores() throws DataAccessException {// wmc + 1
		return lista;
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {// wmc + 1
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) {// wmc + 1 CCog + 1
			if (v instanceof vendedorEnPracticas) {// wmc + 1 CCog + 2
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) {// wmc + 1 CCog + 1
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp.tipo().equals(TipoVendedor.Junior))// wmc + 1 CCog + 3
					junior.add(vp);
				else// wmc + 1 CCog + 1
					senior.add(vp);
			}
		}

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) {// wmc + 1 CCog + 1
				VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.dni()
						+ " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: "+ v1.getC());
			}
			out.println();
			out.println("Junior");
			for (Vendedor v : junior) {// wmc + 1 CCog + 1
				VendedorEnPlantilla v2 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v2.getNombre() + " Id: " + v2.getId() + " DNI: " + v2.dni()
						+ " TotalVentasMes: " + v2.getTotalVentas() + " TotalComision: "+ v2.getC());
			}
			out.println();
			out.println("Practicas");
			for (Vendedor v : practicas) {// wmc + 1 CCog + 1
				vendedorEnPracticas v3 = (vendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: " + v3.getDni()
						+ " TotalVentasMes: " + v3.getTotalVentas());
			}
		} catch (IOException e) {// wmc + 1 CCog + 1
			throw new DataAccessException();

		} finally {
			if (out != null)// wmc + 1 CCog + 1
				out.close();
		}
	}
}
