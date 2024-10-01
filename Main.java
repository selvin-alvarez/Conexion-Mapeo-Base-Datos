package basedatos;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ProductoDB productoDB = new ProductoDB();
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Configurar el scanner para usar el punto decimal
        int opcion;

        do {
        	
        	// Opciones del Menú
            mostrarMenu();
            opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    insertarProducto(scanner, productoDB);
                    break;
                case 2:
                    actualizarProducto(scanner, productoDB);
                    break;
                case 3:
                    eliminarProducto(scanner, productoDB);
                    break;
                case 4:
                    mostrarProductos(productoDB);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close(); // Cerrar scanner
    }
	
	// Menú
    private static void mostrarMenu() {
        System.out.print("Seleccione una opción:");
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("1. Insertar producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Mostrar productos");
        System.out.println("5. Salir");
    }

    private static int obtenerOpcion(Scanner scanner) {
        int opcion = -1;
        try {
            opcion = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            scanner.next(); 
        }
        scanner.nextLine(); 
        return opcion;
    }
    
    
    // Insertar
    private static void insertarProducto(Scanner scanner, ProductoDB productoDB) {
        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.nextLine();
        BigDecimal precio = obtenerPrecio(scanner);
        Producto nuevoProducto = new Producto(0, nombre, precio);
        productoDB.insertarProducto(nuevoProducto);
    }
    
    
    // Actualizar
    private static void actualizarProducto(Scanner scanner, ProductoDB productoDB) {
        System.out.println("Ingrese el ID del producto a actualizar:");
        int idActualizar = scanner.nextInt();
        scanner.nextLine();  
        System.out.println("Ingrese el nuevo nombre del producto:");
        String nuevoNombre = scanner.nextLine();
        BigDecimal nuevoPrecio = obtenerPrecio(scanner);
        Producto productoActualizado = new Producto(idActualizar, nuevoNombre, nuevoPrecio);
        productoDB.actualizarProducto(productoActualizado);
    }

    
    // Eliminar
    private static void eliminarProducto(Scanner scanner, ProductoDB productoDB) {
        System.out.println("Ingrese el ID del producto a eliminar:");
        int idEliminar = scanner.nextInt();
        productoDB.eliminarProducto(idEliminar);
    }

    
    // Mostrar
    private static void mostrarProductos(ProductoDB productoDB) {
        List<Producto> productos = productoDB.obtenerProductos();
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio()); 
        }
        System.out.println();
    }
    
    
    // Manejo de precio
    private static BigDecimal obtenerPrecio(Scanner scanner) {
        BigDecimal precio = null;
        while (precio == null) {
            try {
                System.out.println("Ingrese el precio del producto (use punto decimal):");
                precio = scanner.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Formato de precio incorrecto. Intente de nuevo.");
                scanner.next(); 
            }
        }
        return precio;
    }
}

