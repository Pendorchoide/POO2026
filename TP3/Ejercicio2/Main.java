package TP3.Ejercicio2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorEmpleado gestor = new GestorEmpleado();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Vistas.obtenerMenu());
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEmpleado(gestor, scanner);
                    break;
                case 2:
                    listarEmpleados(gestor);
                    break;
                case 3:
                    quitarEmpleado(gestor, scanner);
                    break;
                case 4:
                    mostrarPromedioSueldo(gestor);
                    break;
                case 5:
                    mostrarEmpleadoQueMasGana(gestor);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
            limpiarConsola();
        }
    }

    private static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void registrarEmpleado(GestorEmpleado gestor, Scanner scanner) {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el DNI del empleado: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el sueldo del empleado: ");
        float sueldo = scanner.nextFloat();
        scanner.nextLine();

        Empleado empleado = new Empleado(nombre, dni, sueldo);
        gestor.registrarEmpleado(empleado);

        System.out.println("Empleado registrado correctamente.");
    }

    private static void listarEmpleados(GestorEmpleado gestor) {
        System.out.println(Vistas.formatearListaEmpleados(gestor.getEmpleados()));
    }

    private static void quitarEmpleado(GestorEmpleado gestor, Scanner scanner) {
        System.out.print("Ingrese el DNI del empleado a quitar: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        boolean eliminado = gestor.quitarEmpleado(dni);
        if (eliminado) {
            System.out.println("Empleado quitado correctamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void mostrarPromedioSueldo(GestorEmpleado gestor) {
        float promedio = gestor.sueldoPromedio();
        if (gestor.getEmpleados().isEmpty()) {
            System.out.println("No hay empleados para calcular el promedio.");
        } else {
            System.out.println("El sueldo promedio es: " + promedio);
        }
    }

    private static void mostrarEmpleadoQueMasGana(GestorEmpleado gestor) {
        Empleado mejor = gestor.empleadoQueMasGana();
        if (mejor == null) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Empleado que más gana:");
            System.out.println(Vistas.formatearEmpleado(mejor));
        }
    }
}
