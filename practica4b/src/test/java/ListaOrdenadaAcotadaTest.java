import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;

class ListaOrdenadaAcotadaTest {
	
	ListaOrdenadaAcotada<Integer> lista;
	
	@BeforeEach
	public void testCrearLista() {
		lista = new ListaOrdenadaAcotada<Integer>(5);
	}
	
	@Test
	public void testAdd() {
		lista.add(3);
		assertEquals(3, lista.get(0));
		assertThrows(NullPointerException.class, () -> lista.add(null));
		lista.add(3);
		lista.add(3);
		lista.add(3);
		lista.add(3);
		assertThrows(IllegalStateException.class, () -> lista.add(4));
	}
	@Test
	void testget() {
		lista.add(3);
		assertEquals(lista.get(0), 3);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-5));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(21));
		assertThrows(NullPointerException.class, () -> lista.get(1));
	}
	
	@Test
	void testRemove() {
		lista.add(5);
		assertEquals(lista.remove(0), 5);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(6));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
	}
	

}
