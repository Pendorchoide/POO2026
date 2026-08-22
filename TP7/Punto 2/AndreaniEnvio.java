

class AndreaniEnvio extends Envio {
    public AndreaniEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return this.getPeso() * 20.0;
    }
}