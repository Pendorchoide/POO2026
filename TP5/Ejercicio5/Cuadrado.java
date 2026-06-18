package Ejercicio5;

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
    @Override
    public double calcularArea() {
        return Math.pow(lado, 2);
    }
    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }

    @Override
    public void escalar(double factor) {
        this.lado *= factor;
    }

    @Override
    public String toString() {
        return super.toString() + " Cuadrado{" +
                "lado=" + lado +
                '}';
    }
    
}
