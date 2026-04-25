package TP3.Ejercicio2;

import java.util.Scanner;

public class Vistas {
    GestorEmpleado gestor;

    public Vistas(GestorEmpleado gestor){
        this.gestor = gestor;
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);
        int op;
        
        while (true) {
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Listar Empleados");
            System.out.println("3. Quitar Empleado");
            System.out.println("4. Promedio Sueldo");
            System.out.println("5. Empleado que mas gana");
            System.out.println("6. Salir");

            op = scanner.nextInt();
        
            switch (op) {
                case 1:
                        this.registrarEmpleado();
                    break;
                case 2:
                        System.out.println(this.gestor.getEmpleados().toString());
                case 3:
                        this.quitarEmpleado();
                    break;
                case 4:
                        this.promedioSueldo();
                    break;
                case 5:
                        this.empleadoQueMasGana();
                    break;
                case 6:
                        scanner.close();
                        return;
                default:
                     scanner.close();
                    break;
            }
        }
    }    


    public void registrarEmpleado(){
        Scanner scanner = new Scanner(System.in);
        String nombre;
        int dni;
        float sueldo;

        System.out.println("Ingrese el nombre del empleado");
        nombre = scanner.nextLine();

        System.out.println("Ingrese el DNI del empleado");
        dni = scanner.nextInt();

        System.out.println("Ingrese el sueldo del empleado");
        sueldo = scanner.nextFloat();

        Empleado empleado = new Empleado(nombre, dni, sueldo);

        this.gestor.regitrarEmpleado(empleado);
    }

    public void quitarEmpleado(){
        Scanner scanner = new Scanner(System.in);
        int dni;

        System.out.println("Ingrese el DNI del empleado a quitar");
        dni = scanner.nextInt();

        this.gestor.quitarEmpleado(dni);
    }

    public void promedioSueldo(){
        System.out.println("El sueldo promedio es: " + this.gestor.sueldoPromedio());
    }

    public void empleadoQueMasGana(){
        System.out.println("El empleado que mas gana es: " + this.gestor.empleadoQueMasGana().toString());
    }
}