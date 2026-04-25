package pruebaGravity;

/**
 * Ejemplo de HERENCIA.
 * 'Perro' hereda características de la clase base 'Animal' usando la palabra
 * 'extends'.
 */
public class Perro extends Animal {

    // Atributo específico solo de los objetos tipo Perro
    private String raza;

    // Constructor
    public Perro(String nombre, int edad, String raza) {
        // 'super' llama al constructor de la clase padre (Animal)
        super(nombre, edad);
        this.raza = raza;
    }

    public String getRaza() {
        return raza;
    }

    /**
     * POLIMORFISMO: Sobreescritura (Override) de un método heredado.
     * El perro responde al mismo requerimiento "hacerSonido" pero a su manera
     * (ladrando).
     */
    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " (el perro) ladra: ¡Guau, guau!");
    }
}
