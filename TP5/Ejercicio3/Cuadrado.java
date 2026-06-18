package Ejercicio3;

public class Cuadrado extends Rectangulo {
    public Cuadrado(String colorHex, Punto posicionCentro, String nombreCapa, double lado) {
        super(colorHex, posicionCentro, nombreCapa, lado, lado);
    }

    @Override
    public void setLadoMenor(double lado) {
        super.setLadoMenor(lado);
        super.setLadoMayor(lado);
    }

    @Override
    public void setLadoMayor(double lado) {
        super.setLadoMenor(lado);
        super.setLadoMayor(lado);
    }

    @Override
    public String toString() {
        return super.toString() + " Cuadrado{" +
                "lado=" + getLadoMenor() +
                '}';
    }
    
}
