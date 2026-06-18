package Ejercicio4;

public class Cuadrado extends ElementoGrafico {
    private double lado;
    public Cuadrado(String colorHex, Punto posicionCentro, String nombreCapa, double lado) {
        super(colorHex, posicionCentro, nombreCapa);
        this.lado = lado;
    }

    //getters y setters
    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }
    


    //métodos específicos
    public double calcularArea() {
        return Math.pow(lado, 2);
    }

    public double calcularPerimetro() {
        return 4 * lado;
    }

    public void escalar(double factor) {
        this.lado *= Math.abs(factor);

        //si el factor es negativo, se invierte el cuadrado (aunque en un cuadrado no se nota la diferencia)
        if (factor < 0) {
            //no hay nada que hacer, el cuadrado sigue siendo un cuadrado
        }

        //si el factor es cero, el cuadrado se convierte en un "punto"
        if (factor == 0) {
            lado = 0;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Cuadrado{" +
                "lado=" + lado +
                '}';
    }
    
}
