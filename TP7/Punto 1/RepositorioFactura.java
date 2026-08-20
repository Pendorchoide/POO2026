import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepositorioFactura {
 private static final String URL = "jdbc:mysql://localhost:3306/db";
 private static final String USUARIO = "root";
 private static final String CONTRASENA = "1234";

 public void guardar(Factura factura) throws SQLException {
  String query = "INSERT INTO facturas (cliente, total) VALUES (?, ?)";
  try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
       PreparedStatement sentencia = conexion.prepareStatement(query)) {
   sentencia.setString(1, factura.getNombreCliente());
   sentencia.setDouble(2, factura.getTotalFinal());
   sentencia.executeUpdate();
  }
 }
}