public class PrincipalB {
    public static void main(String[] args) {
        // Crear un producto
        Producto producto = new Producto("PROD001", "Laptop Gamer", 2, 1500.0);
        
        // Aplicar pruebas
        System.out.println("Subtotal inicial: " + producto.calcularSubtotal());
        
        producto.aplicarDescuento(10.0); // 10% de descuento
        producto.incrementarCantidad(1);
        
        System.out.println(producto.toString());
        System.out.println("Subtotal final: " + producto.calcularSubtotal());
    }
}