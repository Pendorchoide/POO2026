package domain.calculo;

public final class DetalleLinea {
    private final String codigo;
    private final int cantidad;
    private final double precioUnitario;
    private final double tasaIva;
    private final double descuento;
    private final double neto;
    private final double iva;

    public DetalleLinea(String codigo, int cantidad, double precioUnitario, double tasaIva,
                        double descuento, double neto, double iva) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.tasaIva = tasaIva;
        this.descuento = descuento;
        this.neto = neto;
        this.iva = iva;
    }

    public String getCodigo() { return codigo; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getTasaIva() { return tasaIva; }
    public double getDescuento() { return descuento; }
    public double getNeto() { return neto; }
    public double getIva() { return iva; }
}