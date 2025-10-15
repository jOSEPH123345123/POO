public class datosbasedelviajee {

    public static void main(String[] args) {
        // EJERCICIO 1: Datos base del viaje
        double d1 = 42.0, d2 = 58.5, d3 = 37.2;
        double t1 = 0.9, t2 = 1.4, t3 = 0.8;
        double l1 = 5.1, l2 = 6.9, l3 = 4.3;
        double preciolitro = 1.35;
        double masaCargaKg = 1200;
        double largo = 2.0, ancho = 1.2, alto = 1.1;
        double p1 = 2.5, p2 = 3.0, p3 = 2.0;
        double deprecPorKm = 0.08;
        double volCamionM3 = 10.0;
        double fco2 = 2.68;
        double galPorlitro = 0.264172;
        double vmin = 30.0, vmax = 90.0;
        double va = 40.0, vb = 80.0, ca = 0.05, cb = 0.09;
        double a = -0.0008, b = 0.08, c = 4.0;

        // Mostrar datos base
        System.out.println(" EJERCICIO 1: Datos base ");
        System.out.printf("d1=%.1f, d2=%.1f, d3=%.1f\n", d1, d2, d3);
        System.out.printf("t1=%.1f, t2=%.1f, t3=%.1f\n", t1, t2, t3);
        System.out.printf("l1=%.1f, l2=%.1f, l3=%.1f\n", l1, l2, l3);
        System.out.printf("preciolitro=%.2f, masaCargaKg=%.0f\n", preciolitro, masaCargaKg);
        System.out.printf("largo=%.1f, ancho=%.1f, alto=%.1f\n", largo, ancho, alto);
        System.out.printf("p1=%.1f, p2=%.1f, p3=%.1f\n", p1, p2, p3);
        System.out.printf("deprecPorKm=%.2f, volCamionM3=%.1f\n", deprecPorKm, volCamionM3);
        System.out.printf("fco2=%.2f, galPorlitro=%.6f\n", fco2, galPorlitro);

        // EJERCICIO 2: Velocidades y rendimiento 
        double v1 = d1 / t1;
        double v2 = d2 / t2;
        double v3 = d3 / t3;
        double kml1 = d1 / l1;
        double kml2 = d2 / l2;
        double kml3 = d3 / l3;
        double kmltotal = (d1 + d2 + d3) / (l1 + l2 + l3);

        System.out.println("\n EJERCICIO 2: Velocidades y rendimiento ");
        System.out.printf("v1=%.2f, v2=%.2f, v3=%.2f\n", v1, v2, v3);
        System.out.printf("kml1=%.2f, kml2=%.2f, kml3=%.2f\n", kml1, kml2, kml3);
        System.out.printf("kmltotal=%.3f\n", kmltotal);

        // EJERCICIO 3: Costos directos 
        double costoComb = (l1 + l2 + l3) * preciolitro;
        double deprec = (d1 + d2 + d3) * deprecPorKm;
        double peajes = p1 + p2 + p3;
        double costoDirecto = costoComb + deprec + peajes;
        double costoPorKm = costoDirecto / (d1 + d2 + d3);

        System.out.println("\n EJERCICIO 3: Costos directos ");
        System.out.printf("Comb=%.2f, Dep=%.2f, Peajes=%.2f, Directo=%.3f\n", costoComb, deprec, peajes, costoDirecto);
        System.out.printf("/km=%.3f\n", costoPorKm);

        // EJERCICIO 4: Volumen, densidad y ocupación 
        double volumenM3 = largo * ancho * alto;
        double densidad = masaCargaKg / volumenM3;
        double ocupacion = volumenM3 / volCamionM3;

        System.out.println("\n EJERCICIO 4: Volumen, densidad y ocupación ");
        System.out.printf("Volumen=%.3f m³\n", volumenM3);
        System.out.printf("Densidad=%.2f kg/m³\n", densidad);
        System.out.printf("Ocupacion=%.3f\n", ocupacion);

        // EJERCICIO 5: Emisiones de CO2 
        double co2Total = (l1 + l2 + l3) * fco2;
        double co2PorKm = co2Total / (d1 + d2 + d3);

        System.out.println("\n EJERCICIO 5: Emisiones de CO2 ");
        System.out.printf("CO2Total=%.3f kg\n", co2Total);
        System.out.printf("CO2porKm=%.3f kg/km\n", co2PorKm);

        // EJERCICIO 6: Conversiones y normalización
        double vprom = (d1 + d2 + d3) / (t1 + t2 + t3);
        double vpromMs = vprom * (1000.0 / 3600.0);
        double galTot = (l1 + l2 + l3) * galPorlitro;
        double vnorm = (vprom - vmin) / (vmax - vmin);

        System.out.println("\n EJERCICIO 6: Conversiones y normalización ");
        System.out.printf("vprom=%.2f km/h = %.3f m/s\n", vprom, vpromMs);
        System.out.printf("galTot=%.4f\n", galTot);
        System.out.printf("vnorm=%.3f\n", vnorm);

        // EJERCICIO 7: Promedios y dispersión 
        double vmedia = (v1 + v2 + v3) / 3.0;
        double varianza = ((v1 - vmedia) * (v1 - vmedia) + 
                          (v2 - vmedia) * (v2 - vmedia) + 
                          (v3 - vmedia) * (v3 - vmedia)) / 3.0;
        double sigma = Math.sqrt(varianza);
        double vpond_t = (t1 * v1 + t2 * v2 + t3 * v3) / (t1 + t2 + t3);

        System.out.println("\n EJERCICIO 7: Promedios y dispersión ");
        System.out.printf("vmedia=%.3f\n", vmedia);
        System.out.printf("vpond_t=%.3f\n", vpond_t);
        System.out.printf("σ=%.3f\n", sigma);

        // EJERCICIO 8: Interpolación lineal (mantenimiento) 
        double c_vprom = ca + (cb - ca) * ((vprom - va) / (vb - va));
        double costoMant = c_vprom * (d1 + d2 + d3);

        System.out.println("\n EJERCICIO 8: Interpolación lineal ");
        System.out.printf("c(vprom)=%.5f\n", c_vprom);
        System.out.printf("costoMant=%.3f\n", costoMant);

        // EJERCICIO 9: Modelo polinómico de rendimiento 
        double kmlModelo = a * vprom * vprom + b * vprom + c;
        double litrosModelo = (d1 + d2 + d3) / kmlModelo;

        System.out.println("\n EJERCICIO 9: Modelo polinómico ");
        System.out.printf("kmlModelo=%.3f\n", kmlModelo);
        System.out.printf("LitrosModelo=%.3f\n", litrosModelo);

        // EJERCICIO 10: Índice de eficiencia (score) 
        double w1 = 0.3, w2 = 0.3, w3 = 0.2, w4 = 0.2;
        double score = w1 * (1.0 / costoPorKm) + 
                     w2 * kmltotal + 
                     w3 * (1.0 / co2PorKm) + 
                     w4 * (1.0 / (1.0 + sigma));

        System.out.println("\n EJERCICIO 10: Índice de eficiencia ");
        System.out.printf("Score=%.4f\n", score);
    }
}