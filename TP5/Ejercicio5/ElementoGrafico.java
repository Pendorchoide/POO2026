package Ejercicio5;

public abstract class ElementoGrafico {
    private String colorHex;
    private Punto posicionCentro;
    private String nombreCapa;
    public ElementoGrafico(String colorHex, Punto posicionCentro, String nombreCapa) {
        this.colorHex = colorHex;
        this.posicionCentro = posicionCentro;
        this.nombreCapa = nombreCapa;
    }
    
    //getters
    public String getColorHex() {
        return colorHex;
    }

    public Punto getPosicionCentro() {
        return posicionCentro;
    }

    public String getNombreCapa() {
        return nombreCapa;
    }

    //setters
    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public void setPosicionCentro(Punto posicionCentro) {
        this.posicionCentro = posicionCentro;
    }

    public void setNombreCapa(String nombreCapa) {
        this.nombreCapa = nombreCapa;
    }

    //metodos especificos
    public void moverA(Punto nuevaPosicion) {
        this.posicionCentro = nuevaPosicion;
    }

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public abstract void escalar(double factor);


    @Override
    public String toString() {
        return "ElementoGrafico{" +
                "colorHex='" + colorHex + '\'' +
                ", posicionCentro=(" + posicionCentro.x + ", " + posicionCentro.y + ")" +
                ", nombreCapa='" + nombreCapa + '\'' +
                '}';
    }
}
