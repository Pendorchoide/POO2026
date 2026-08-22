abstract class Envio {
    private double peso;

    public Envio(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return this.peso;
    }

    public abstract double obtenerCosto();
}