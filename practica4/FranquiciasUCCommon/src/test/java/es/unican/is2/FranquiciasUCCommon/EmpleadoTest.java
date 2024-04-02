package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EmpleadoTest {
	
	@Test
    public void testConstructor() {
        LocalDate fechaContratacion = LocalDate.of(2019, 1, 1);
        Empleado empleado = new Empleado("12345678A", "Juan", Categoria.ENCARGADO, fechaContratacion);
        assertEquals("12345678A", empleado.getDNI());
        assertEquals("Juan", empleado.getNombre());
        assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
        assertEquals(fechaContratacion, empleado.getFechaContratacion());
        assertEquals(false, empleado.getBaja());
    }
	
	@Test
    public void testGettersAndSetters() {
		Empleado empleado = new Empleado("12345678A", "Juan", Categoria.ENCARGADO, LocalDate.of(2010, 1, 1));
        empleado.setDNI("87654321B");
        assertEquals("87654321B", empleado.getDNI());

        empleado.setNombre("Pedro");
        assertEquals("Pedro", empleado.getNombre());

        empleado.setFechaContratacion(LocalDate.of(2020, 1, 1));
        assertEquals(LocalDate.of(2020, 1, 1), empleado.getFechaContratacion());

        empleado.setBaja(true);
        assertEquals(true, empleado.getBaja());

        empleado.setCategoria(Categoria.VENDEDOR);
        assertEquals(Categoria.VENDEDOR, empleado.getCategoria());
        
        empleado.setCategoria(Categoria.ENCARGADO);
        assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
        
        empleado.setCategoria(Categoria.AUXILIAR);
        assertEquals(Categoria.AUXILIAR, empleado.getCategoria());
    }

    @Test
    public void testSueldoBruto() {
        LocalDate fechaContratacion = LocalDate.of(2000, 3, 15);
        Empleado empleado = new Empleado("12345678A", "Juan", Categoria.ENCARGADO, fechaContratacion);
        double sueldoBruto = empleado.sueldoBruto();
        assertEquals(2200.0, sueldoBruto);
        
        fechaContratacion = LocalDate.of(2011, 3, 15);
        empleado.setFechaContratacion(fechaContratacion);
        sueldoBruto = empleado.sueldoBruto();
        assertEquals(2100.0, sueldoBruto);
        
        fechaContratacion = LocalDate.of(2017, 3, 15);
        empleado.setFechaContratacion(fechaContratacion);
        sueldoBruto = empleado.sueldoBruto();
        assertEquals(2050.0, sueldoBruto);
    }

    @Test
    public void testDarDeBaja() {
        Empleado empleado = new Empleado();
        assertFalse(empleado.getBaja());

        empleado.darDeBaja();

        assertTrue(empleado.getBaja());
    }

    @Test
    public void testDarDeAlta() {
        Empleado empleado = new Empleado();
        empleado.darDeBaja();
        assertTrue(empleado.getBaja());

        empleado.darDeAlta();

        assertFalse(empleado.getBaja());
    }
}
