package Ejercicio3;

public class Circulo extends Elipse {

    public Circulo(String colorHex, Punto posicionCentro, String nombreCapa, double radio) {
        super(colorHex, posicionCentro, nombreCapa, radio, radio);
    }

    //getters y setters
    public double getRadio() {
        return getRadioMenor(); //o getRadioMayor(), ambos son iguales en un círculo
    }

    @Override
    public void setRadioMenor(double radio) {
        super.setRadioMenor(radio);
        super.setRadioMayor(radio);
    }

    @Override
    public void setRadioMayor(double radio) {
        super.setRadioMenor(radio);
        super.setRadioMayor(radio);
    }

    @Override
    public String toString() {
        return super.toString() + " Circulo{" +
                "radio=" + getRadio() +
                '}';
    }
    
}
