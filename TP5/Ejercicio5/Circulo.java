package Ejercicio5;

public class Circulo extends ElementoGrafico {
    private double radio;

    public Circulo(String colorHex, Punto posicionCentro, String nombreCapa, double radio) {
        super(colorHex, posicionCentro, nombreCapa);
        this.radio = radio;
    }

    //getters y setters
    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
    @Override
    //métodos específicos
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }
    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public void escalar(double factor) {
        this.radio *= Math.abs(factor);
    }

    @Override
    public String toString() {
        return super.toString() + " Circulo{" +
                "radio=" + getRadio() +
                '}';
    }
    
}
