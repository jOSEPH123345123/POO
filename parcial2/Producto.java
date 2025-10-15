public class Producto {
    // Atributos privados
    private String codigo;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    
    // Constructor por defecto
    public Producto() {
        this.codigo = "SIN_CODIGO";
        this.descripcion = "Sin descripción";
        this.cantidad = 1;
        this.precioUnitario = 0.0;
    }
    
    // Constructor parametrizado
    public Producto(String codigo, String descripcion, int cantidad, double precioUnitario) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código no puede estar vacío");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Descripción no puede estar vacía");
        }
        if (cantidad < 1) {
            throw new IllegalArgumentException("Cantidad debe ser al menos 1");
        }
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("Precio unitario no puede ser negativo");
        }
        
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código no puede estar vacío");
        }
        this.codigo = codigo;
    }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Descripción no puede estar vacía");
        }
        this.descripcion = descripcion;
    }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad < 1) {
            throw new IllegalArgumentException("Cantidad debe ser al menos 1");
        }
        this.cantidad = cantidad;
    }
    
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("Precio unitario no puede ser negativo");
        }
        this.precioUnitario = precioUnitario;
    }
    
    // Métodos de operaciones
    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }
    
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 50) {
            throw new IllegalArgumentException("El descuento debe estar entre 0% y 50%");
        }
        double descuento = precioUnitario * (porcentaje / 100);
        this.precioUnitario -= descuento;
    }
    
    public void incrementarCantidad(int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor a incrementar debe ser positivo");
        }
        this.cantidad += valor;
    }
    
    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + calcularSubtotal() +
                '}';
    }
}
