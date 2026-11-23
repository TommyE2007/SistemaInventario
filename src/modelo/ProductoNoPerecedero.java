package modelo;

public class ProductoNoPerecedero extends Producto {

    private int mesesGarantia;
    private String material;

    public ProductoNoPerecedero(String codigo,
                                String nombre,
                                int cantidad,
                                double precioUnitario,
                                int stockMinimo,
                                int mesesGarantia,
                                String material) {

        super(codigo, nombre, cantidad,
                precioUnitario, stockMinimo);

        this.mesesGarantia = mesesGarantia;
        this.material = material;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public double calcularValorInventario() {
        return getCantidad() * getPrecioUnitario();
    }

    @Override
    public String obtenerDetalleTipo() {
        return "No Perecedero | Material: " +
                material +
                " | Garantia: " +
                mesesGarantia +
                " meses";
    }
}