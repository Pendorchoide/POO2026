package Ejercicio4;

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

    //métodos específicos
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    public void escalar(double factor) {
        this.radio *= Math.abs(factor);

        //si el factor es negativo, se invierte el círculo (aunque en un círculo no se nota la inversión)
        if (factor < 0) {
            //no hay nada que invertir en un círculo, pero podríamos agregar algún comportamiento específico si lo deseamos
        }

        //si el factor es cero, el círculo se convierte en un "punto"
        if (factor == 0) {
            radio = 0;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Circulo{" +
                "radio=" + getRadio() +
                '}';
    }
    
}
