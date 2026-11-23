package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProductoPerecedero extends Producto {

    private LocalDate fechaVencimiento;
    private String lote;

    public ProductoPerecedero(String codigo,
                              String nombre,
                              int cantidad,
                              double precioUnitario,
                              int stockMinimo,
                              LocalDate fechaVencimiento,
                              String lote) {

        super(codigo, nombre, cantidad, precioUnitario, stockMinimo);

        this.fechaVencimiento = fechaVencimiento;
        this.lote = lote;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getLote() {
        return lote;
    }

    public long diasParaVencer() {
        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                fechaVencimiento
        );
    }

    public boolean estaProximoAVencer() {
        long dias = diasParaVencer();
        return dias >= 0 && dias <= 15;
    }

    @Override
    public double calcularValorInventario() {

        long dias = diasParaVencer();

        if (dias < 0) {
            return 0;
        }

        double subtotal =
                getCantidad() * getPrecioUnitario();

        if (dias <= 15) {
            return subtotal * 0.80;
        }

        return subtotal;
    }

    @Override
    public String obtenerDetalleTipo() {

        String estado;

        if (diasParaVencer() < 0) {
            estado = "CADUCADO";
        } else if (estaProximoAVencer()) {
            estado = "PROXIMO A VENCER (-20%)";
        } else {
            estado = "VIGENTE";
        }

        return "Perecedero | Lote: " +
                lote +
                " | Vence: " +
                fechaVencimiento +
                " (" + estado + ")";
    }
}