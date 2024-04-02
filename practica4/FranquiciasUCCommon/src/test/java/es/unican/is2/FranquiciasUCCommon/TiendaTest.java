package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TiendaTest {

	private Tienda tienda;
    private ArrayList<Empleado> empleados = new ArrayList<>();
    
    @Test
    public void constructor() {
    	tienda = new Tienda("UC", "Castros");
    	assertEquals("UC", tienda.getNombre());
    	assertEquals("Castros", tienda.getDireccion());
    }
    
    @Test
    public void testGastoMensualSueldos_EmpleadosVacios() {
        double resultado = tienda.gastoMensualSueldos();
        assertEquals(0.0, resultado);
    }

    @Test
    public void testGastoMensualSueldos() {
        Empleado empleado1 = new Empleado("","",Categoria.ENCARGADO, null);
        Empleado empleado2 = new Empleado("","",Categoria.AUXILIAR, null);
        empleados.add(empleado1);
        empleados.add(empleado2);

        tienda.añadeEmpleado(empleado1);
        tienda.añadeEmpleado(empleado2);

        double resultado = tienda.gastoMensualSueldos();
        assertEquals(3000.0, resultado);
    }

    @Test
    public void testGastoMensualSueldos_unitario() {
        Empleado empleado1 = new Empleado("","",Categoria.ENCARGADO, null);
        empleados.add(empleado1);
        tienda.añadeEmpleado(empleado1);
        double resultado = tienda.gastoMensualSueldos();
        assertEquals(2000.0, resultado);
    }
    
    @Test
    public void testAñadeEmpleado() {
        Empleado empleado = new Empleado();
        tienda.añadeEmpleado(empleado);
        assertTrue(tienda.getEmpleados().contains(empleado));
    }

    @Test
    public void testEliminaEmpleado_EmpleadoExistente() {
        Empleado empleado = new Empleado();
        tienda.añadeEmpleado(empleado);
        assertTrue(tienda.eliminaEmpleado(empleado));
        assertFalse(tienda.getEmpleados().contains(empleado));
    }

    @Test
    public void testEliminaEmpleado_EmpleadoNoExistente() {
        Empleado empleado = new Empleado();
        assertFalse(tienda.eliminaEmpleado(empleado));
    }

}
