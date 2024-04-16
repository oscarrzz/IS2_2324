import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Lista.ListaOrdenadaAcotada;


class ListaOrdenadaAcotadaTest {
	
	ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(5);;
	
	@BeforeEach
	public void testCrearLista() {
		lista.size();
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
	}
	
	@Test
	void testRemove() {
		lista.add(5);
		assertEquals(lista.remove(0), 5);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(6));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
	}
	
	@Test
	void testSize() {
		assertEquals(lista.size(), 0);
		lista.add(1);
		lista.add(1);
		assertEquals(lista.size(), 2);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		assertEquals(lista.size(), 5);
	}
	
	@Test
	void testClear() {
		lista.add(1);
		lista.add(1);
		lista.clear();
		assertEquals(lista.size(), 0);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.clear();
		assertEquals(lista.size(), 0);
	}

}
