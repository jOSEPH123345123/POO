public class Principal {
    public static void main(String[] args) {
        // Crear una cuenta válida
        CuentaBancaria cuenta = new CuentaBancaria("Juan Pérez", "123456", 1000.0);
        
        // Operaciones
        cuenta.depositar(500.0);
        cuenta.retirar(200.0);
        
        // Mostrar resultados
        System.out.println(cuenta.toString());
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}
