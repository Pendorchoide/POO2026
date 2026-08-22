
class FedExEnvio extends Envio{
    public FedExEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return (this.getPeso() * 50.0) + 100.0;
    }
}