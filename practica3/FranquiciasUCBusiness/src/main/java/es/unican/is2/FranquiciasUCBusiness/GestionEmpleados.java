package es.unican.is2.FranquiciasUCBusiness;

import java.util.HashMap;
import java.util.Map;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.Empleado;
import es.unican.is2.FranquiciasUCCommon.IGestionEmpleados;
import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;

public class GestionEmpleados implements IGestionEmpleados {

	private ITiendasDAO tiendaDAO;

	public GestionEmpleados() {
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda tienda = tiendaDAO.tiendaPorNombre(nombre);
		if (tienda == null) {
			return null;
		}
		if (tienda.buscaEmpleado(e.getDNI()) == null) {
			tienda.añadeEmpleado(e);;
			return e;
		} else {
			throw new OperacionNoValidaException("El empleado ya existe.");
		}
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		if (tiendas.containsKey(nombre)) {
			Tienda tienda = tiendas.get(nombre);
			Empleado e = tienda.buscaEmpleado(dni);
			boolean empleado = tienda.eliminaEmpleado(e);
			if (empleado) {
				return e;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		if (tiendas.containsKey(actual) && tiendas.containsKey(destino)) {
			Tienda tiendaActual = tiendas.get(actual);
			Tienda tiendaDestino = tiendas.get(destino);
			if (tiendaActual.buscaEmpleado(dni) != null) {
				Empleado e = tiendaActual.buscaEmpleado(dni);
				tiendaActual.eliminaEmpleado(e);
				tiendaDestino.añadeEmpleado(e);
				return true;
			} else {
				throw new OperacionNoValidaException("El empleado no pertenece a la tienda actual.");
			}
		} else {
			return false;
		}
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		for (Tienda tienda : tiendas.values()) {
			Empleado empleado = tienda.buscaEmpleado(dni);
			if (empleado != null) {
				return empleado;
			}
		}
		return null;
	}
}