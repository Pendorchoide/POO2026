package Ejercicio4;

public class Rectangulo extends ElementoGrafico {
    private double ladoMenor;
    private double ladoMayor;

    public Rectangulo(String colorHex, Punto posicionCentro, String nombreCapa, double ladoMenor, double ladoMayor) {
        super(colorHex, posicionCentro, nombreCapa);
        this.ladoMenor = ladoMenor;
        this.ladoMayor = ladoMayor;
    }

    //getters y setters
    public double getLadoMenor() {
        return ladoMenor;
    }
    
    public double getLadoMayor() {
        return ladoMayor;
    }

    public void setLadoMenor(double ladoMenor) {
        this.ladoMenor = ladoMenor;
    }

    public void setLadoMayor(double ladoMayor) {
        this.ladoMayor = ladoMayor;
    }

    //métodos específicos
    public double calcularArea() {
        return ladoMenor * ladoMayor;
    }

    public double calcularPerimetro() {
        return 2 * (ladoMenor + ladoMayor);
    }

    public void escalar(double factor) {
        this.ladoMenor *= Math.abs(factor);
        this.ladoMayor *= Math.abs(factor);

        //si el factor es negativo, se invierte el rectángulo
        if (factor < 0) {
            double aux = ladoMenor;
            ladoMenor = ladoMayor;
            ladoMayor = aux;
        }

        //si el factor es cero, el rectángulo se convierte en un "punto"
        if (factor == 0) {
            ladoMenor = 0;
            ladoMayor = 0;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Rectangulo{" +
                "ladoMenor=" + ladoMenor +
                ", ladoMayor=" + ladoMayor +
                '}';
    }
}
