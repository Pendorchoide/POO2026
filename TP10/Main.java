package TP10;
import TP10.Exceptions.*;


public class Main{
    public static void main(String[] args) {

        EmpleadoService empleadoService = new EmpleadoService();

        try {
            Empleado empleado1 = new Empleado("Al", -2);
            empleadoService.agregar(empleado1);
            System.out.println("Empleado agregado con exito");


        } catch (StringException | EnteroPositivoException e) {
            System.out.println(e.getMessage());
        }

        try {
            Empleado empleado2 = new Empleado("Alejandro", -2);
            empleadoService.agregar(empleado2);
            System.out.println("Empleado agregado con exito");

        } catch (StringException | EnteroPositivoException e) {
            System.out.println(e.getMessage());
        }   

        try {
            Empleado empleado3 = new Empleado("Al", 2);
            empleadoService.agregar(empleado3);
            System.out.println("Empleado agregado con exito");

        } catch (StringException | EnteroPositivoException e) {
            System.out.println(e.getMessage());
        }   

        try {
            Empleado empleado4 = new Empleado("Alejandro", 200);
            empleadoService.agregar(empleado4);
            System.out.println("Empleado agregado con exito");

        } catch (StringException | EnteroPositivoException e) {
            System.out.println(e.getMessage());
        }     

        try {
            empleadoService.eliminar("Pedro");
            System.out.println("Empleado eliminado con exito");

        } catch (CollectionVaciaException | NoEncontradoException e) {
            System.out.println(e.getMessage());
        }  

        try {
            empleadoService.eliminar("Alejandro");
            System.out.println("Empleado eliminado con exito");

        } catch (CollectionVaciaException | NoEncontradoException e) {
            System.out.println(e.getMessage());

        }  

        try {
            empleadoService.eliminar("Juan");
            System.out.println("Empleado eliminado con exito");

        } catch (CollectionVaciaException | NoEncontradoException e) {
            System.out.println(e.getMessage());
        }  
        
        try {
            System.out.println(empleadoService.sueldoPromedio());

        } catch (CollectionVaciaException e) {
            System.out.println(e.getMessage());
        }  
    }
}