package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;

<<<<<<< HEAD
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

=======
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TiendaTest {

	private Tienda tienda;
    private Empleado empleado1 = new Empleado("z", "z", Categoria.ENCARGADO, LocalDate.now());
    private Empleado empleado2 = new Empleado("z", "z", Categoria.AUXILIAR, LocalDate.now());

    @BeforeEach
    public void setUp() {
        // Crear una nueva tienda antes de cada prueba
        tienda = new Tienda("Mi Tienda", "123 Calle Principal");
        // Crear empleados de muestra
    }
    
    @AfterEach
    public void clean() {
    	tienda.getEmpleados().clear();
    }

    @Test
    public void testConstructor() {
        // Verificar si el constructor asigna correctamente los valores de nombre y dirección
        assertEquals("Mi Tienda", tienda.getNombre());
        assertEquals("123 Calle Principal", tienda.getDireccion());
    }

    @Test
    public void testGastoMensual_varios() {
        // Añadir empleados a la tienda
        tienda.añadeEmpleado(empleado1);
        tienda.añadeEmpleado(empleado2);

        // Verificar el gasto mensual de sueldos
        assertEquals(3000, tienda.gastoMensualSueldos(), 0.001); // La suma de los sueldos de empleado1 y empleado2 es 2500
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
    public void testAñadeEmpleado() {
        // Verificar que el empleado se añade correctamente a la lista
        tienda.añadeEmpleado(empleado1);
        assertTrue(tienda.getEmpleados().contains(empleado1));
    }
    
    public void testAñadeEmpleado_nulo() {
        // Verificar que el empleado se añade correctamente a la lista
        tienda.añadeEmpleado(null);
    	assertTrue(tienda.getEmpleados().isEmpty());
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
    
>>>>>>> 3291f597d780f8865c96a71218adc919248b93bd
}
