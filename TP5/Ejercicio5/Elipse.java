package Ejercicio5;

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
    @Override
    public double calcularArea() {
        return Math.PI * radioMenor * radioMayor;
    }
    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * Math.sqrt((Math.pow(radioMenor, 2) + Math.pow(radioMayor, 2)) / 2);
    }

    @Override
    public void escalar(double factor) {
        this.radioMenor *= Math.abs(factor);
        this.radioMayor *= Math.abs(factor);

        //si el factor es negativo, se invierte la elipse
        if (factor < 0) {
            double aux = radioMenor;
            radioMenor = radioMayor;
            radioMayor = aux;
        }

        //si el factor es cero, la elipse se convierte en un "punto"
        if (factor == 0) {
            radioMenor = 0;
            radioMayor = 0;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Elipse{" +
                "radioMenor=" + getRadioMenor() +
                ", radioMayor=" + getRadioMayor() +
                '}';
    }
}
