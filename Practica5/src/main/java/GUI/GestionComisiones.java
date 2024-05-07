package GUI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import clases.Tienda;
import clases.Vendedor;
import excepciones.DataAccessException;
import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

	/**
     * Programa principal basado en menú
     * @throws DataAccessException
     */
    public static void main(String[] args) throws DataAccessException { //WMC +1
        // Opciones del menú
        final int NUEVA_VENTA = 0, VENDEDOR_DEL_MES = 1, VENDEDORES = 2;

        // Variables auxiliares
        String dni;
        Lectura lect;

        List<Vendedor> vendedores;
        List<Vendedor> resultado;
        String msj;

        // Crea la tienda
        Tienda tienda = new Tienda("datosTienda.txt");

        // Crea la ventana de menú
        Menu menu = new Menu("Comisiones tienda");
        menu.insertaOpcion("Añadir venta", NUEVA_VENTA);
        menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
        menu.insertaOpcion("Vendedores por ventas", VENDEDORES);

        // Laço de espera de comandos del usuario
        while (true) { //WMC +1 CCog +1
            int opcion = menu.leeOpcion();

            // Realiza las acciones dependiendo de la opción elegida
            switch (opcion) { //CCog +1+1
                case NUEVA_VENTA:
                    lect = new Lectura("Datos Venta");
                    lect.creaEntrada("ID Vendedor", "");
                    lect.creaEntrada("Importe", "");
                    lect.esperaYCierra();
                    dni = lect.leeString("ID Vendedor");
                    double importe = lect.leeDouble("Importe");
                    try {
                        if (!tienda.anhadeVenta(dni, importe)) { //WMC +1+1 CCog +1+1+1
                            mensaje("ERROR", "El vendedor no existe");
                        }
                    } catch (DataAccessException e) { //WMC +1 CCog +1+1+1
                        mensaje("ERROR", "No se pudo guardar el cambio");
                    }
                    break;

                case VENDEDOR_DEL_MES: //CCog +1+1
                    try {
                        vendedores = tienda.vendedores();
                        resultado = new LinkedList<Vendedor>();
                        double maxVentas = 0.0;
                        for (Vendedor v : vendedores) { //WMC +1+1 CCog +1+1+1
                            if (v.getTotalVentas() > maxVentas) { //WMC +1+1 CCog +1+1+1+1
                                maxVentas = v.getTotalVentas();
                                resultado.clear();
                                resultado.add(v);
                            } else if (v.getTotalVentas() == maxVentas) { //WMC +1+1 CCog +1
                                resultado.add(v);
                            }
                        }

                        msj = "";
                        for (Vendedor vn : resultado) { //WMC +1 CCog +1+1+1
                            msj += vn.getNombre() + "\n";
                        }
                        mensaje("VENDEDORES DEL MES", msj);

                    } catch (DataAccessException e) { //WMC +1 CCog +1+1+1
                        mensaje("ERROR", "No se pudo acceder a los datos");
                    }
                    break;

                case VENDEDORES: //CCog +1+1
                    try {
                        vendedores = tienda.vendedores();
                        Collections.sort(vendedores, Comparator.comparingDouble(Vendedor::getTotalVentas).reversed());

                        msj = "";
                        for (Vendedor vn : vendedores) { //WMC +1 CCog +1+1+1
                            msj += vn.getNombre() + " (" + vn.getId() + ") " + vn.getTotalVentas() + "\n";
                        }
                        mensaje("VENDEDORES", msj);
                    } catch (DataAccessException e) { //WMC +1 CCog +1+1+1
                        mensaje("ERROR", "No se pudo acceder a los datos");
                    }
                    break;
            }
        }
    }

    /**
     * Método auxiliar que muestra una ventana de mensaje
     * @param titulo Título de la ventana
     * @param txt    Texto contenido en la ventana
     */
    private static void mensaje(String titulo, String txt) { //WMC +1
        Mensaje msj = new Mensaje(titulo);
        msj.escribe(txt);
    }

}