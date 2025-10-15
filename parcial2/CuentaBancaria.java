public class CuentaBancaria {
    // Atributos privados
    private String titular;
    private String numeroCuenta;
    private double saldo;
    
    // Constructor por defecto
    public CuentaBancaria() {
        this.titular = "Sin titular";
        this.numeroCuenta = "000000";
        this.saldo = 0.0;
    }
    
    // Constructor parametrizado
    public CuentaBancaria(String titular, String numeroCuenta, double saldo) {
        // Validaciones
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Titular no puede estar vacío");
        }
        if (numeroCuenta == null || numeroCuenta.length() < 6) {
            throw new IllegalArgumentException("Número de cuenta debe tener al menos 6 dígitos");
        }
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo no puede ser negativo");
        }
        
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }
    
    // Getters y Setters
    public String getTitular() {
        return titular;
    }
    
    public void setTitular(String titular) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Titular no puede estar vacío");
        }
        this.titular = titular;
    }
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.length() < 6) {
            throw new IllegalArgumentException("Número de cuenta debe tener al menos 6 dígitos");
        }
        this.numeroCuenta = numeroCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    // No hay setSaldo directo para proteger la integridad
    
    // Métodos de operaciones
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
        this.saldo += monto;
    }
    
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= monto;
    }
    
    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "titular='" + titular + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}