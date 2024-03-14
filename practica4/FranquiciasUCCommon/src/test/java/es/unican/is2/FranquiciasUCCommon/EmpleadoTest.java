package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EmpleadoTest {

    @Test
    public void testSueldoBruto() {
        LocalDate fechaContratacion = LocalDate.of(2010, 1, 1);
        Empleado empleado = new Empleado("12345678A", "Juan", Categoria.ENCARGADO, fechaContratacion);

        double sueldoBruto = empleado.sueldoBruto();

        assertEquals(2200.0, sueldoBruto); // Sueldo base de ENCARGADO + complemento de antigüedad
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
