package domain.impuesto;

public final class Iva {
    public static final Impuesto GENERAL    = new IvaGeneral();
    public static final Impuesto REDUCIDA   = new IvaReducida();
    public static final Impuesto ADICIONAL  = new IvaAdicional();
    public static final Impuesto EXENTO     = new IvaExento();

    private Iva() {}
}