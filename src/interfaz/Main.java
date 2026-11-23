package interfaz;

import modelo.Producto;
import negocio.InventarioException;
import negocio.InventarioService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner scanner =
            new Scanner(System.in);

    private static InventarioService servicio =
            new InventarioService();

    public static void main(String[] args) {

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n===== INVENTARIO =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Actualizar stock");
            System.out.println("3. Listar inventario");
            System.out.println("4. Ver alertas");
            System.out.println("0. Salir");

            opcion =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            switch (opcion) {

                case 1:
                    registrarProducto();
                    break;

                case 2:
                    actualizarStock();
                    break;

                case 3:
                    listarInventario();
                    break;

                case 4:
                    mostrarAlertas();
                    break;

                case 0:
                    System.out.println("Fin.");
                    break;
            }
        }
    }

    private static void registrarProducto() {

        try {

            System.out.println(
                    "1. Perecedero"
            );

            System.out.println(
                    "2. No Perecedero"
            );

            String tipo =
                    scanner.nextLine();

            System.out.print("Codigo: ");
            String codigo =
                    scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre =
                    scanner.nextLine();

            System.out.print("Cantidad: ");
            int cantidad =
                    Integer.parseInt(
                            scanner.nextLine());

            System.out.print("Precio: ");
            double precio =
                    Double.parseDouble(
                            scanner.nextLine());

            System.out.print("Stock minimo: ");
            int stockMin =
                    Integer.parseInt(
                            scanner.nextLine());

            if (tipo.equals("1")) {

                System.out.print(
                        "Fecha (AAAA-MM-DD): ");

                LocalDate fecha =
                        LocalDate.parse(
                                scanner.nextLine());

                System.out.print(
                        "Lote: ");

                String lote =
                        scanner.nextLine();

                servicio.registrarPerecedero(
                        codigo,
                        nombre,
                        cantidad,
                        precio,
                        stockMin,
                        fecha,
                        lote
                );

            } else {

                System.out.print(
                        "Garantia: ");

                int garantia =
                        Integer.parseInt(
                                scanner.nextLine());

                System.out.print(
                        "Material: ");

                String material =
                        scanner.nextLine();

                servicio.registrarNoPerecedero(
                        codigo,
                        nombre,
                        cantidad,
                        precio,
                        stockMin,
                        garantia,
                        material
                );
            }

            System.out.println(
                    "Producto registrado."
            );

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    private static void actualizarStock() {

        try {

            System.out.print("Codigo: ");
            String codigo =
                    scanner.nextLine();

            System.out.print("Movimiento: ");
            int mov =
                    Integer.parseInt(
                            scanner.nextLine());

            servicio.actualizarStock(
                    codigo,
                    mov
            );

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    private static void listarInventario() {

        for (Producto p :
                servicio.listarProductos()) {

            System.out.println(p);
        }

        System.out.println(
                "Valor total: $"
                        + servicio.calcularValorTotal()
        );
    }

    private static void mostrarAlertas() {

        for (String alerta :
                servicio.obtenerAlertas()) {

            System.out.println(alerta);
        }
    }
}