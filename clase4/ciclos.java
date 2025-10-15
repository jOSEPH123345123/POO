public class ciclos {
     public static void main(String[] args) {
          // Ciclo for
          for (int i = 1; i <= 5; i++) {
               System.out.println("For: Iteración " + i);
          }

          // Ciclo while
          int j = 1;
          while (j <= 5) {
               System.out.println("While: Iteración " + j);
               j++;
          }

          // Ciclo do-while
          int k = 1;
          do {
               System.out.println("Do-While: Iteración " + k);
               k++;
          } while (k <= 5);
     }
}