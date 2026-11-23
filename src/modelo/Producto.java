package modelo;

public abstract class Producto {

    private final String codigo;
    private String nombre;
    private int cantidad;
    private double precioUnitario;
    private int stockMinimo;

    public Producto(String codigo, String nombre, int cantidad,
                    double precioUnitario, int stockMinimo) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.stockMinimo = stockMinimo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public boolean necesitaReposicion() {
        return cantidad <= stockMinimo;
    }

    public abstract double calcularValorInventario();

    public abstract String obtenerDetalleTipo();

    @Override
    public String toString() {
        return "[" + codigo + "] " +
                nombre +
                " | Cant: " + cantidad +
                " | Valor: $" +
                calcularValorInventario() +
                " | " +
                obtenerDetalleTipo();
    }
}