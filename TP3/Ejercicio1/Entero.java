package TP3.Ejercicio1;

public class Entero { 
    private int numero; 
    
    public Entero(int numero) { 
        this.numero = numero; 
    } 
    
    public int getNumero() { return numero; } 
    
    public void setNumero(int numero) { this.numero = numero; } 
    
    // Calcula el cuadrado de n 
    public long cuadrado() { return (long) numero * numero; } 

    // Determina si n es par
    public boolean esPar() { return (numero % 2 == 0);}

    // Determina si n es par
    public boolean esImpar() { return (numero % 2 != 0);}

    //Calcular Factorial de n

    public long factorial() {
        if (numero < 0){
            return -1;  // Devuelve -1 para indicar que no se puede calcular el factorial de un número negativo
        }
        if (numero == 0 || numero == 1) {
            return 1; // El factorial de 0 y 1 es 1
        }
        long fac = numero;
        for (int i = numero; i > 1; i--){
            fac = fac * (i - 1);
        }
        return fac;
    }

    // Determinar si n es primo

    public boolean esPrimo(){
        if (numero < 2) return false;
        if (numero == 2) return true;
        if (numero % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(numero); i += 2){
            if ((numero % i ) == 0) {
                return false;
            }
        }
        return true;
    }

}
