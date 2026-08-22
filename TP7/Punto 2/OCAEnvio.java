class OCAEnvio extends Envio {
    public OCAEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return this.getPeso() * 15.0;
    }
}
