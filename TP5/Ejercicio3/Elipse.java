package Ejercicio3;

public class Elipse extends ElementoGrafico {
    private double radioMenor;
    private double radioMayor;

    public Elipse(String colorHex, Punto posicionCentro, String nombreCapa, double radioMenor, double radioMayor) {
        super(colorHex, posicionCentro, nombreCapa);
        this.radioMenor = radioMenor;
        this.radioMayor = radioMayor;
    }

    //getters y setters
    public double getRadioMenor() {
        return radioMenor;
    }

    public double getRadioMayor() {
        return radioMayor;
    }

    public void setRadioMenor(double radioMenor) {
        this.radioMenor = radioMenor;
    }

    public void setRadioMayor(double radioMayor) {
        this.radioMayor = radioMayor;
    }

    //métodos específicos

    public double calcularArea() {
        return Math.PI * radioMenor * radioMayor;
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * Math.sqrt((Math.pow(radioMenor, 2) + Math.pow(radioMayor, 2)) / 2);
    }

    public void escalar(double factor) {
        this.radioMenor *= factor;
        this.radioMayor *= factor;
    }

    @Override
    public String toString() {
        return super.toString() + " Elipse{" +
                "radioMenor=" + radioMenor +
                ", radioMayor=" + radioMayor +
                '}';
    }
}
