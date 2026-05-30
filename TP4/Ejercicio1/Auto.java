package TP4.Ejercicio1;
public class Auto extends Motor {
    private String marca;
    private String modelo;

    public Auto(String marca, String modelo,
                int cilindrada, String combustible) {
        super(cilindrada, combustible);
        this.marca = marca;
        this.modelo = modelo;
    }

    public void mostrarDatos() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Cilindrada: " + cilindrada);
        System.out.println("Combustible: " + combustible);
    }
}