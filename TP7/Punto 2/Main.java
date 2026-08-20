/**
    El método obtener_costo() viola el Principio de Abierto/Cerrado (OCP) 
    porque utiliza una estructura condicional (if/elif/else) ligada a valores
    específicos para determinar el comportamiento según el tipo de correo.

    Razones de la violación:
     - Falta de cierre frente a modificación: 
        Cada vez que la empresa sume un nuevo proveedor (como "DHL") 
        o elimine uno existente, es obligatorio editar directamente el 
        código interno de la clase CalculadoraEnvios para agregar 
        una nueva rama elif.
     - Riesgo de regresión: Modificar una clase que ya funciona e 
        impacta la lógica existente incrementa el riesgo de introducir 
        errores inesperados (bugs) en proveedores que ya estaban 
        testeados y operativos.
     - Alto acoplamiento: La calculadora conoce 
        los detalles concretos y las fórmulas de 
        cálculo de cada servicio de mensajería, 
        centralizando responsabilidades que deberían 
        estar delegadas a cada proveedor.
 */

import java.util.List;
import java.util.ArrayList;

abstract class Envio {
    private double peso;

    public Envio(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return this.peso;
    }

    public abstract double obtenerCosto();
}

interface CalculadoraEnvios {
    double obtenerCosto();
}

class OCAEnvio extends Envio implements CalculadoraEnvios {
    public OCAEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return this.getPeso() * 15.0;
    }
}

class FedExEnvio extends Envio implements CalculadoraEnvios {
    public FedExEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return (this.getPeso() * 50.0) + 100.0;
    }
}

class AndreaniEnvio extends Envio implements CalculadoraEnvios {
    public AndreaniEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return this.getPeso() * 20.0;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Envio> envios = new ArrayList<>();
        envios.add(new OCAEnvio(20));
        envios.add(new FedExEnvio(15));
        envios.add(new AndreaniEnvio(10));

        envios.forEach(e -> {
            System.out.println(e.obtenerCosto());
        });
    }
}