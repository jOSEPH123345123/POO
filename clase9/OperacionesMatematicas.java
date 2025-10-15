import java.util.Scanner;

public class OperacionesMatematicas {

    // Atributos de la clase
    private int a;
    private int b;

    // Constructor: permite inicializar la clase
    public OperacionesMatematicas(int a, int b) {
        this.a = a;
        this.b = b;
    }

    // Métodos
    public int sumar() {
        return a + b;
    }

    public int restar() {
        return a - b;
    }

    public int multiplicar() {
        return a * b;
    }

    public double dividir() {
        double resultado;
        if (b == 0) {
            resultado = 0.0;
        } else {
            // (double) significa hacer una conversión de entero a real (CAST)
            resultado = (double) a / (double) b;
        }
        return resultado;
    }

    // método para recibir numeros enteros por teclado
    public int leerEnteros() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite el número entero...");
        int n = sc.nextInt(); //capturar el dato que se digite por teclado
        /*
         * Para números enteros nextInt() -> ejemplo: sc.nextInt()
         * Para números reales nextDouble() -> ejemplo: sc.nextDouble()
         * Para booleanos nextBoolean() -> ejemplo: sc.nextBoolean()
         * Para cadenas (String) next() -> ejemplo: sc.next()
         */
        sc.close();
        return n;
    }

}
