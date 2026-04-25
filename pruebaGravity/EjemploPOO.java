package pruebaGravity;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASE PRINCIPAL para probar los conceptos.
 * Aquí vemos en acción las Clases como "moldes" y los Objetos como entidades
 * reales.
 */
public class EjemploPOO {

    public static void main(String[] args) {

        System.out.println("=== 1. CLASES, OBJETOS Y OCULTAMIENTO (ENCAPSULAMIENTO) ===");
        // Una Clase es el plano (CuentaBancaria), y el Objeto es la "miCuenta" concreta
        // que estamos creando.
        CuentaBancaria miCuenta = new CuentaBancaria("Juan Pérez", 1000);

        // No podemos hacer: miCuenta.saldo = 50000; <- Error: el campo está oculto
        // (private).
        // Protegemos la integridad obligando a usar los métodos oficiales (retirar,
        // depositar):
        miCuenta.depositar(500);
        miCuenta.retirar(200);
        miCuenta.retirar(5000); // Intento bloquear un retiro sin fondos, las reglas protegen los datos.

        System.out.println("\n=== 2. HERENCIA ===");
        // Instanciamos objetos a partir de clases hijas.
        Perro miPerro = new Perro("Firulais", 3, "Labrador");
        Gato miGato = new Gato("Michi", 2);

        // Podemos usar el método 'dormir()' aunque no lo declaramos en 'Gato' o
        // 'Perro', lo HEREDAN de 'Animal'.
        miPerro.dormir();
        miGato.dormir();

        System.out.println("\n=== 3. ABSTRACCIÓN Y POLIMORFISMO ===");
        // Tratamos todos los perros y gatos como puros "Animales".
        // La lista es de Animales genéricos. No me importa qué subtipo sean.
        List<Animal> refugio = new ArrayList<>();
        refugio.add(miPerro);
        refugio.add(miGato);

        // Polimorfismo: Llamamos al mismo método "hacerSonido()", pero Java en tiempo
        // de
        // ejecución sabe qué versión usar basándose en el "Objeto real" (Perro o Gato).
        for (Animal animal : refugio) {
            animal.hacerSonido();
        }

        System.out.println("\n=== 4. MÉTODOS ESPECÍFICOS DE LAS CLASES ==");
        // Aunque hereden de Animal, retienen propiedades específicas.
        System.out.println("Raza de Firulais es: " + miPerro.getRaza());
        miGato.ronronear();
    }
}
