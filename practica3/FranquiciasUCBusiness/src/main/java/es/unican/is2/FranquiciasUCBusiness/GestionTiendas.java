package es.unican.is2.FranquiciasUCBusiness;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.IGestionTiendas;
import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;

public class GestionTiendas implements IGestionTiendas {

    private ITiendasDAO tiendas; // Almacena las tiendas por nombre

    public GestionTiendas(ITiendasDAO tiendasDAO) {
    	this.tiendas = tiendasDAO;
    }

    @Override
    public Tienda nuevaTienda(Tienda t) throws DataAccessException {
        if (tiendas.tiendaPorNombre(t.getNombre()) != null) {
            return null;
        } 
        tiendas.crearTienda(t);
        return t;
    }

    @Override
    public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
        Tienda tienda = tiendas.tiendaPorNombre(nombre);
        if (tienda == null) {
            return null;
        }
        if (tienda.getEmpleados().isEmpty()) {
        	throw new OperacionNoValidaException("La tienda tiene empleados");
        }
        tiendas.eliminarTienda(tienda.getId());
        return tienda;
        
    }

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {
        return tiendas.tiendaPorNombre(nombre);
    }
}