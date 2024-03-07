package es.unican.is2.FranquiciasUCBusiness;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.Empleado;
import es.unican.is2.FranquiciasUCCommon.IEmpleadosDAO;
import es.unican.is2.FranquiciasUCCommon.IGestionEmpleados;
import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;

public class GestionEmpleados implements IGestionEmpleados {

	private ITiendasDAO tiendasDAO;
	private IEmpleadosDAO empleadosDAO;

	public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
		this.tiendasDAO = tiendasDAO;
		this.empleadosDAO = empleadosDAO; 
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);
		if (tienda == null) {
			return null;
		}
		if (tienda.buscaEmpleado(e.getDNI()) == null) {
			tienda.añadeEmpleado(e);
			e.darDeAlta();
			tiendasDAO.modificarTienda(tienda);
			empleadosDAO.modificarEmpleado(e);
			return e;
		} else {
			throw new OperacionNoValidaException("El empleado ya existe.");
		}
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);
		if (tienda == null) {
			return null;
		}
		if (tienda.buscaEmpleado(dni) != null) {
			Empleado e = tienda.buscaEmpleado(dni);
			tienda.eliminaEmpleado(e);
			e.darDeBaja();
			tiendasDAO.modificarTienda(tienda);
			empleadosDAO.eliminarEmpleado(dni);
			return e;
		} else {
			throw new OperacionNoValidaException("El empleado ya existe.");
		}
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		Tienda tiendaActual = tiendasDAO.tiendaPorNombre(actual);
		Tienda tiendaDestino = tiendasDAO.tiendaPorNombre(destino);
		if (tiendaActual == null || tiendaDestino == null) {
			return false;
		}
		Empleado e = tiendaActual.buscaEmpleado(dni);
		if (e != null) {
			tiendaActual.eliminaEmpleado(e);
			e.darDeBaja();
			tiendaDestino.añadeEmpleado(e);
			e.darDeAlta();
			empleadosDAO.modificarEmpleado(e);
			tiendasDAO.modificarTienda(tiendaActual);
			tiendasDAO.modificarTienda(tiendaDestino);
			return true;
		} else {
			throw new OperacionNoValidaException("El empleado no pertenece a la tienda");
		}
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		for (Tienda tienda : tiendasDAO.tiendas()) {
			Empleado empleado = tienda.buscaEmpleado(dni);
			if (empleado != null) {
				return empleado;
			}
		}
		return null;
	}
}