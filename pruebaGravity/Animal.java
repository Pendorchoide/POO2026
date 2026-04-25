package pruebaGravity;

/**
 * Ejemplo de ABSTRACCIÓN y CLASE BASE.
 * Una clase abstracta es una plantilla general que no puede instanciarse por sí
 * misma
 * (no se puede usar 'new Animal()'). Sirve para que otras clases hereden de
 * ella.
 */
public abstract class Animal {
    // Encapsulamiento de los atributos comunes
    private String nombre;
    private int edad;

    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    /**
     * ABSTRACCIÓN pura: Método abstracto.
     * Obliga a que cualquier clase hija "concrete" cómo hace su sonido.
     * Nosotros sabemos que todo animal hace un sonido, pero cada uno lo hace
     * diferente.
     */
    public abstract void hacerSonido();

    // Método concreto (ya implementado). Todas las clases hijas heredarán este
    // comportamiento tal cual.
    public void dormir() {
        System.out.println(nombre + " está durmiendo. Zzz...");
    }
}
