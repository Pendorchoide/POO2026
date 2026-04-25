package TP3.Ejercicio1;
import java.util.Scanner;

public class Main{

    public static void analizarNumero(int entero){
        
        Entero e = new Entero(entero);
        
        System.out.println("El cuadrado del numero " + e.getNumero() + " es: " + e.cuadrado());
        
        if (e.esPar()){
            System.out.println("El numero " + e.getNumero() + " es par");
        }
        
        if (e.esImpar()){
            System.out.println("El numero " + e.getNumero() + " es impar");
        }
        
        long factorial = e.factorial();
        
        if (factorial != -1){
            System.out.println("El factorial del numero " + e.getNumero() + " es: " + e.factorial());
        }
        else{
            System.err.println("No es posible calcular el factorial de " + e.getNumero() + " por no ser mayor a 0");
        }
        
        if (e.esPrimo()){
            System.out.println("El numero " + e.getNumero() + " es primo");
        }
        else{
            System.out.println("El numero " + e.getNumero() + " no es primo");
        }

        
        
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese un numero entero: ");
        analizarNumero(scanner.nextInt());
        
        System.out.println("Ingrese un numero entero: ");
        analizarNumero(scanner.nextInt());
        
        scanner.close();
    }
}

