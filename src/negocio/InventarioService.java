package negocio;

import modelo.Producto;
import modelo.ProductoNoPerecedero;
import modelo.ProductoPerecedero;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventarioService {

    private List<Producto> productos;

    public InventarioService() {
        productos = new ArrayList<>();
    }

    public void registrarNoPerecedero(
            String codigo,
            String nombre,
            int cantidad,
            double precio,
            int stockMinimo,
            int garantia,
            String material)
            throws InventarioException {

        if (buscarPorCodigo(codigo) != null) {
            throw new InventarioException(
                    "Codigo duplicado."
            );
        }

        ProductoNoPerecedero producto =
                new ProductoNoPerecedero(
                        codigo,
                        nombre,
                        cantidad,
                        precio,
                        stockMinimo,
                        garantia,
                        material
                );

        productos.add(producto);
    }

    public void registrarPerecedero(
            String codigo,
            String nombre,
            int cantidad,
            double precio,
            int stockMinimo,
            LocalDate fecha,
            String lote)
            throws InventarioException {

        if (buscarPorCodigo(codigo) != null) {
            throw new InventarioException(
                    "Codigo duplicado."
            );
        }

        ProductoPerecedero producto =
                new ProductoPerecedero(
                        codigo,
                        nombre,
                        cantidad,
                        precio,
                        stockMinimo,
                        fecha,
                        lote
                );

        productos.add(producto);
    }

    public Producto buscarPorCodigo(String codigo) {

        for (Producto p : productos) {

            if (p.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return p;
            }
        }

        return null;
    }

    public void actualizarStock(
            String codigo,
            int movimiento)
            throws InventarioException {

        Producto p =
                buscarPorCodigo(codigo);

        if (p == null) {
            throw new InventarioException(
                    "Producto no encontrado."
            );
        }

        int nuevoStock =
                p.getCantidad() + movimiento;

        if (nuevoStock < 0) {
            throw new InventarioException(
                    "Stock insuficiente."
            );
        }

        p.setCantidad(nuevoStock);
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public double calcularValorTotal() {

        double total = 0;

        for (Producto p : productos) {
            total += p.calcularValorInventario();
        }

        return total;
    }

    public List<String> obtenerAlertas() {

        List<String> alertas =
                new ArrayList<>();

        for (Producto p : productos) {

            if (p.necesitaReposicion()) {

                alertas.add(
                        "STOCK BAJO -> "
                                + p.getNombre()
                );
            }
        }

        return alertas;
    }
}