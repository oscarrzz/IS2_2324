import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;

class ListaOrdenadaAcotadaTest {

	ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(5);
	
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
	

}
