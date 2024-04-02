package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TiendaTest {

	private Tienda tienda = new Tienda("Mi Tienda", "123 Calle Principal");
	private Empleado empleado1 = new Empleado("z", "z", Categoria.ENCARGADO, LocalDate.now());
    private Empleado empleado2 = new Empleado("z", "z", Categoria.AUXILIAR, LocalDate.now());    
    @Test
    public void setUp() {
        // Crear una nueva tienda antes de cada prueba
        assertEquals("Mi Tienda", tienda.getNombre());
        assertEquals("123 Calle Principal", tienda.getDireccion());
    }
    
    @AfterEach
    public void clean() {
    	tienda.getEmpleados().clear();
    }
    
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
        tienda.añadeEmpleado(empleado1);
        tienda.añadeEmpleado(empleado2);

        double resultado = tienda.gastoMensualSueldos();
        assertEquals(3000.0, resultado);
    }

    @Test
    public void testGastoMensualSueldos_unitario() {
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
    public void testGettersAndSetters() {
        tienda.setNombre("Hola");
        assertEquals("Hola", tienda.getNombre());

        tienda.setDireccion("Liencres");
        assertEquals("Liencres", tienda.getDireccion());
    }
    
    @Test
    public void testGastoMensual_vacio() {
        // Verificar el gasto mensual de sueldos
        assertEquals(0, tienda.gastoMensualSueldos()); // La suma de los sueldos de empleado1 y empleado2 es 2500
    }
    
    @Test
    public void testGastoMensual_unico() {
        // Añadir empleados a la tienda
        tienda.añadeEmpleado(empleado1);

        // Verificar el gasto mensual de sueldos
        assertEquals(2000, tienda.gastoMensualSueldos()); // La suma de los sueldos de empleado1 y empleado2 es 2500
    }

    @Test
    public void testEliminaEmpleadoExistente() {
        // Añadir empleado a la tienda
        tienda.añadeEmpleado(empleado1);

        // Verificar que el empleado se elimina correctamente de la lista
        assertTrue(tienda.eliminaEmpleado(empleado1));
        assertFalse(tienda.getEmpleados().contains(empleado1));
    }
    
    @Test
    public void testEliminaEmpleadoNoExistente() {
        // Verificar que el método devuelve false si se intenta eliminar un empleado que no está en la lista
        assertFalse(tienda.eliminaEmpleado(empleado1));
    }
}
