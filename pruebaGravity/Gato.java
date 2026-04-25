package pruebaGravity;

/**
 * Otro ejemplo de HERENCIA y POLIMORFISMO.
 */
public class Gato extends Animal {

    public Gato(String nombre, int edad) {
        super(nombre, edad);
    }

    // POLIMORFISMO
    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " (el gato) maúlla: ¡Miau, miau!");
    }

    // Comportamiento propio que un 'Animal' genérico u otro subtipo no tiene
    // obligatoriamente
    public void ronronear() {
        System.out.println(getNombre() + " está muy relajado y ronronea: prrrrr...");
    }
}
