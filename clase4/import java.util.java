import java.util.Scanner;

public class Ejercicio52 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicita el nombre del mes y si el año es bisiesto
        System.out.print("Introduce el nombre del mes: ");
        String mes = sc.next().toLowerCase();
        System.out.print("¿El año es bisiesto? (true/false): ");
        boolean bisiesto = sc.nextBoolean();

        int dias;
        switch (mes) {
            case "enero": case "marz case "mayo": case "julio":
            case "agosto": case "octubre": case "diciembre":
                dias = 31;
                break;
            case "abril": case "junio": case "septiembre": case "noviembre":
                dias = 30;
                break;
            case "febrero":
                dias = bisiesto ? 29 : 28;
                break;
            default:
                dias = -1;
        }

        // Muestra el resultado
        if (dias != -1) {
            System.out.println("El mes de " + mes + " tiene " + dias + " días.");
        } else {
            System.out.println("Mes no válido.");
        }
        sc.close();
    }
}