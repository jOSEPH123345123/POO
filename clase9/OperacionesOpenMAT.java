public class EjecutarOpeMat {
    public static void main(String[] args) {
        
        //Crear el objeto
        OperacionesMatematicas objOpe = new OperacionesMatematicas(5, 6);

        //Acceder al método sumar
        System.out.println("La suma es: " + objOpe.sumar());

        //Acceder al método restar
        System.out.println("La resta es: " + objOpe.restar());

        //Acceder al método multiplicar
        System.out.println("La multiplicación es: " + objOpe.multiplicar());        

        //Acceder al método dividir
        System.out.println("La división es: " + objOpe.dividir());        

    }    
}