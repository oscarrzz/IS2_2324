package es.unican.is2.FranquiciasUCBusiness;

import java.util.HashMap;
import java.util.Map;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.IGestionTiendas;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;

public class GestionTiendas implements IGestionTiendas {

    private Map<String, Tienda> tiendas; // Almacena las tiendas por nombre

    public GestionTiendas() {
        this.tiendas = new HashMap<>();
    }

    @Override
    public Tienda nuevaTienda(Tienda t) throws DataAccessException {
        if (!tiendas.containsKey(t.getNombre())) {
            tiendas.put(t.getNombre(), t);
            return t;
        } else {
            return null; // La tienda ya existe
        }
    }

    @Override
    public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
        Tienda tienda = tiendas.get(nombre);
        if (tienda != null) {
            if (tienda.getEmpleados().size() == 0) {
                tiendas.remove(nombre);
                return tienda;
            } else {
                throw new OperacionNoValidaException("No se puede eliminar la tienda porque tiene empleados.");
            }
        } else {
            return null; // La tienda no existe
        }
    }

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {
        return tiendas.get(nombre);
    }
}