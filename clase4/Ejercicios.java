public class Ejercicios {
    public static void main(String[] args) {
        // Ejemplo de condicionales: if, else if, else
        // Un condicional permite ejecutar diferentes bloques de código según una condición.
        int numero = 7;

        // Si el número es mayor que 0, se considera positivo.
        if (numero > 0) {
            System.out.println("El número es positivo");
        }
        // Si el número es menor que 0, se considera negativo.
        else if (numero < 0) {
            System.out.println("El número es negativo");
        }
        // Si no se cumple ninguna de las condiciones anteriores, el número es cero.
        else {
            System.out.println("El número es cero");
        }

        // Ejemplo de ciclo for
        // El ciclo for repite un bloque de código un número determinado de veces.
        System.out.println("Ciclo for del 1 al 5:");
        for (int i = 1; i <= 5; i++) {
            // En cada iteración, imprime el valor actual de i.
            System.out.println("Iteración: " + i);
        }

        // Ejemplo de ciclo while
        // El ciclo while repite el bloque de código mientras la condición sea verdadera.
        System.out.println("Ciclo while, cuenta regresiva desde 3:");
        int contador = 3;
        while (contador > 0) {
            // Imprime el valor actual de contador y luego lo decrementa.
            System.out.println("Contador: " + contador);
            contador--;
        }

        // Ejemplo de ciclo do-while
        // El ciclo do-while ejecuta el bloque de código al menos una vez y luego verifica la condición.
        System.out.println("Ciclo do-while, ejecuta al menos una vez:");
        int valor = 0;
        do {
            // Imprime el valor actual y lo incrementa.
            System.out.println("Valor actual: " + valor);
            valor++;
        } while (valor < 3);
    }
}

