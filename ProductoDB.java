package basedatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ProductoDB {
	
	// Base de datos
	private String url = "jdbc:mariadb://localhost:3306/selvindb";
    private String user = "selvindb";
    private String password = "SHAN1985";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    
    // Insertar
    public void insertarProducto(Producto producto) {
        String sql = "INSERT INTO Productos (Nombre, Precio) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setBigDecimal(2, producto.getPrecio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // Actualizar
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE Productos SET Nombre = ?, Precio = ? WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setBigDecimal(2, producto.getPrecio());
            stmt.setInt(3, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // Eliminar
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM Productos WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // Lista de productos
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("ID"),
                    rs.getString("Nombre"),
                    rs.getBigDecimal("Precio")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}


