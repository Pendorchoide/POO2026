package pruebaGravity;

/**
 * Ejemplo de ENCAPSULAMIENTO (Ocultamiento de la información).
 * Los atributos son privados para que no puedan ser modificados directamente
 * desde fuera de la clase. Se accede a ellos mediante métodos públicos (getters
 * y setters).
 */
public class CuentaBancaria {
    // Ocultamiento de la información (atributos privados)
    private String titular;
    private double saldo;

    // Constructor: método especial para inicializar el objeto al crearlo
    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        // Validamos la información al inicializar para mantener un estado válido
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0;
            System.out.println("Error: El saldo inicial no puede ser negativo. Se ha establecido en 0.");
        }
    }

    // Método Getter: permite obtener (leer) el valor del atributo privado
    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    // Método para depositar dinero (expone una forma controlada de alterar el
    // estado)
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Depósito exitoso. Nuevo saldo de " + titular + ": $" + saldo);
        } else {
            System.out.println("La cantidad a depositar debe ser mayor a 0.");
        }
    }

    // Método para retirar dinero (protege las reglas de negocio, como no tener
    // saldo negativo)
    public void retirar(double cantidad) {
        if (cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retiro exitoso. Nuevo saldo de " + titular + ": $" + saldo);
        } else {
            System.out.println("Error: Fondos insuficientes o cantidad inválida para " + titular + ".");
        }
    }
}
