package TP6;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear objetos de cada clase
        Edificio edificio1 = new Edificio("Av. Corrientes 1500, Buenos Aires", 50, 200, 15000);
        Edificio edificio2 = new Edificio("Calle Falsa 123, CABA", 30, 150, 8000);
        
        Bicicleta bicicleta1 = new Bicicleta("Trek", "Marlin 7", 30, "Montaña");
        Bicicleta bicicleta2 = new Bicicleta("Giant", "FastRoad SLR 1", 40, "Ruta");
        
        Auto auto1 = new Auto("Toyota", "Corolla", 180, 8.5, 15000);
        Auto auto2 = new Auto("Chevrolet", "Onix", 170, 7.5, 12000);

        ArrayList<ImpactoEcologico> objetosEcologicos = new ArrayList<>();
        

        objetosEcologicos.add(edificio1);
        objetosEcologicos.add(edificio2);
        objetosEcologicos.add(bicicleta1);
        objetosEcologicos.add(bicicleta2);
        objetosEcologicos.add(auto1);
        objetosEcologicos.add(auto2);

        System.out.println("========== IMPACTO ECOLÓGICO DE CARBONO (kg CO2/año) ==========");
        System.out.println();
        
        double impactoTotal = 0;
        for (ImpactoEcologico objeto : objetosEcologicos) {
            double impacto = objeto.getImpact();
            impactoTotal += impacto;
            
            // Aqui por ejemplo no se hace uso de polimorfismo, ya que se necesita acceder a métodos específicos de cada clase para mostrar información adicional
            // Se hace uso también de downcasting para poder usar los metodos específicos.
            if (objeto instanceof Edificio) {
                Edificio edificio = (Edificio) objeto;
                System.out.println(edificio);
                System.out.println("  └─ Consumo anual: " + edificio.getConsumoAnualGasNatural() + " m³ de gas natural");
            } else if (objeto instanceof Auto) {
                Auto auto = (Auto) objeto;
                System.out.println(auto);
                System.out.println("  └─ Km anuales: " + auto.getKmAnioPromedio() + " km");
            } else if (objeto instanceof Bicicleta) {
                Bicicleta bicicleta = (Bicicleta) objeto;
                System.out.println(bicicleta);
            }
            System.out.println("  Impacto Ecológico: " + String.format("%.2f", impacto) + " kg CO2/año");
            System.out.println();
        }
        
        System.out.println("==========================================================");
        System.out.println("IMPACTO ECOLÓGICO TOTAL: " + String.format("%.2f", impactoTotal) + " kg CO2/año");
    }
}
