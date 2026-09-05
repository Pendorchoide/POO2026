package domain.descuento;

abstract class DescuentoXPorY implements DescuentoPorCantidad {
    private final int llevaX;
    private final int pagasY;

    DescuentoXPorY(int llevaX, int pagasY) {
        if (llevaX <= 0 || pagasY <= 0 || pagasY > llevaX) {
            throw new IllegalArgumentException("llevaX y pagasY deben ser positivos, y pagasY <= llevaX");
        }
        this.llevaX = llevaX;
        this.pagasY = pagasY;
    }

    @Override
    public double aplicar(int cantidadComprada, double precioUnitario) {
        int gruposPromocionales = cantidadComprada / llevaX;
        int unidadesSueltas = cantidadComprada % llevaX;
        int unidadesAPagar = (gruposPromocionales * pagasY) + unidadesSueltas;
        return (cantidadComprada - unidadesAPagar) * precioUnitario;
    }

    public int getLlevaX() { return llevaX; }
    public int getPagasY() { return pagasY; }
}